package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.ReturPenjualanDao;
import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import java.util.List;

public class ReturPenjualanService {
    private final ReturPenjualanDao returDao;
    private final AdministrasiService administrasiService;

    public ReturPenjualanService() {
        this.returDao = new ReturPenjualanDao();
        this.administrasiService = new AdministrasiService();
    }

    public List<ReturPenjualan> getAll() {
        return returDao.getAll();
    }

    public ReturPenjualan getById(int id) {
        return returDao.getById(id);
    }

    /**
     * Menyimpan data retur penjualan baru.
     * @param idAdministrasi ID akun kas/bank yang digunakan jika ada refund tunai.
     */
    public boolean tambahRetur(ReturPenjualan retur, int idAdministrasi) {
        boolean isSaved = returDao.save(retur);

        if (isSaved) {
            // Logika: Jika status SELESAI dan pengembalian berupa TUNAI, potong saldo
            if (retur.getStatus_Retur() == ReturPenjualan.StatusRetur.SELESAI && 
                retur.getJenis_Pengembalian() == ReturPenjualan.JenisPengembalian.TUNAI) {
                return administrasiService.kurangSaldo(idAdministrasi, retur.getNilai_Retur());
            }
            return true;
        }
        return false;
    }

    /**
     * Memperbarui data retur dan menyesuaikan saldo kas/bank.
     */
    public boolean perbaruiRetur(ReturPenjualan returBaru, int idAdministrasi) {
        ReturPenjualan returLama = returDao.getById(returBaru.getId());
        if (returLama == null) return false;

        boolean isUpdated = returDao.update(returBaru);

        if (isUpdated) {
            // Jika sebelumnya belum selesai, lalu sekarang jadi SELESAI + TUNAI
            if (returLama.getStatus_Retur() != ReturPenjualan.StatusRetur.SELESAI && 
                returBaru.getStatus_Retur() == ReturPenjualan.StatusRetur.SELESAI &&
                returBaru.getJenis_Pengembalian() == ReturPenjualan.JenisPengembalian.TUNAI) {
                administrasiService.kurangSaldo(idAdministrasi, returBaru.getNilai_Retur());
            }
            // Jika ada perubahan nominal pada retur tunai yang sudah SELESAI
            else if (returLama.getStatus_Retur() == ReturPenjualan.StatusRetur.SELESAI && 
                     returBaru.getStatus_Retur() == ReturPenjualan.StatusRetur.SELESAI &&
                     returBaru.getJenis_Pengembalian() == ReturPenjualan.JenisPengembalian.TUNAI) {
                administrasiService.tambahSaldo(idAdministrasi, returLama.getNilai_Retur());
                administrasiService.kurangSaldo(idAdministrasi, returBaru.getNilai_Retur());
            }
            return true;
        }
        return false;
    }

    /**
     * Menghapus data retur. 
     * Jika retur tunai yang sudah selesai dihapus, maka uang dianggap kembali ke kas.
     */
    public boolean hapusRetur(int id, int idAdministrasi) {
        ReturPenjualan retur = returDao.getById(id);
        if (retur == null) return false;

        boolean isDeleted = returDao.delete(id);

        if (isDeleted) {
            if (retur.getStatus_Retur() == ReturPenjualan.StatusRetur.SELESAI && 
                retur.getJenis_Pengembalian() == ReturPenjualan.JenisPengembalian.TUNAI) {
                return administrasiService.tambahSaldo(idAdministrasi, retur.getNilai_Retur());
            }
            return true;
        }
        return false;
    }
}