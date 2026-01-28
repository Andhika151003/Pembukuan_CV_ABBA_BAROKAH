package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.ReturPembelianDao;
import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
// import java.math.BigDecimal;
import java.util.List;

public class ReturPembelianService {
    private final ReturPembelianDao returDao;
    private final AdministrasiService administrasiService;

    public ReturPembelianService() {
        this.returDao = new ReturPembelianDao();
        this.administrasiService = new AdministrasiService();
    }

    public List<ReturPembelian> getAll() {
        return returDao.getAll();
    }

    public ReturPembelian getById(int id) {
        return returDao.getById(id);
    }

    /**
     * Menyimpan data retur pembelian baru.
     * Saldo hanya akan bertambah jika status langsung diatur ke SELESAI.
     */
    public boolean tambahRetur(ReturPembelian retur, int idAdministrasi) {
        boolean isSaved = returDao.save(retur);

        if (isSaved) {
            // Jika status retur SELESAI, maka dana dianggap sudah kembali ke kas/bank
            if (retur.getStatus_Retur() == ReturPembelian.StatusRetur.SELESAI) {
                return administrasiService.tambahSaldo(idAdministrasi, retur.getNilai_Retur());
            }
            return true;
        }
        return false;
    }

    /**
     * Memperbarui data retur dan menyesuaikan saldo jika status berubah menjadi SELESAI.
     */
    public boolean perbaruiRetur(ReturPembelian returBaru, int idAdministrasi) {
        ReturPembelian returLama = returDao.getById(returBaru.getId());
        if (returLama == null) return false;

        boolean isUpdated = returDao.update(returBaru);

        if (isUpdated) {
            // Logika: Jika sebelumnya PENDING/DISETUJUI lalu menjadi SELESAI, tambah saldo
            if (returLama.getStatus_Retur() != ReturPembelian.StatusRetur.SELESAI && 
                returBaru.getStatus_Retur() == ReturPembelian.StatusRetur.SELESAI) {
                return administrasiService.tambahSaldo(idAdministrasi, returBaru.getNilai_Retur());
            }
            // Jika ada revisi nominal pada retur yang sudah berstatus SELESAI
            else if (returLama.getStatus_Retur() == ReturPembelian.StatusRetur.SELESAI && 
                     returBaru.getStatus_Retur() == ReturPembelian.StatusRetur.SELESAI) {
                administrasiService.kurangSaldo(idAdministrasi, returLama.getNilai_Retur());
                administrasiService.tambahSaldo(idAdministrasi, returBaru.getNilai_Retur());
            }
            return true;
        }
        return false;
    }

    /**
     * Menghapus data retur. 
     * Jika retur yang dihapus sudah berstatus SELESAI, saldo akan dikurangi kembali.
     */
    public boolean hapusRetur(int id, int idAdministrasi) {
        ReturPembelian retur = returDao.getById(id);
        if (retur == null) return false;

        boolean isDeleted = returDao.delete(id);

        if (isDeleted) {
            if (retur.getStatus_Retur() == ReturPembelian.StatusRetur.SELESAI) {
                return administrasiService.kurangSaldo(idAdministrasi, retur.getNilai_Retur());
            }
            return true;
        }
        return false;
    }
}