package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PenjualanDao;
import com.pembukuan_cv_abba_barokah.Model.Penjualan;
// import java.math.BigDecimal;
import java.util.List;

public class PenjualanService {
    private final PenjualanDao penjualanDao;
    private final AdministrasiService administrasiService;

    public PenjualanService() {
        this.penjualanDao = new PenjualanDao();
        this.administrasiService = new AdministrasiService();
    }

    public List<Penjualan> getAll() {
        return penjualanDao.getAll();
    }

    public Penjualan getById(int id) {
        return penjualanDao.getById(id);
    }

    /**
     * Menyimpan data penjualan baru.
     * Jika status LUNAS, maka saldo pada idAdministrasi akan bertambah.
     * @param jual Objek Penjualan
     * @param idAdministrasi ID akun kas/bank tujuan uang masuk
     */
    public boolean tambahPenjualan(Penjualan jual, int idAdministrasi) {
        boolean isSaved = penjualanDao.save(jual);

        if (isSaved) {
            // Logika: Jika pembayaran LUNAS, maka tambah saldo Administrasi
            if (jual.getStatus_Pembayaran() == Penjualan.StatusPembayaran.LUNAS) {
                return administrasiService.tambahSaldo(idAdministrasi, jual.getTotal_Penjualan());
            }
            return true;
        }
        return false;
    }

    /**
     * Memperbarui data penjualan.
     * Menangani perubahan status dari BELUM_LUNAS menjadi LUNAS.
     */
    public boolean perbaruiPenjualan(Penjualan jualBaru, int idAdministrasi) {
        Penjualan jualLama = penjualanDao.getById(jualBaru.getId());
        if (jualLama == null) return false;

        boolean isUpdated = penjualanDao.update(jualBaru);

        if (isUpdated) {
            // Jika sebelumnya BELUM_LUNAS sekarang jadi LUNAS, tambah saldo
            if (jualLama.getStatus_Pembayaran() != Penjualan.StatusPembayaran.LUNAS && 
                jualBaru.getStatus_Pembayaran() == Penjualan.StatusPembayaran.LUNAS) {
                administrasiService.tambahSaldo(idAdministrasi, jualBaru.getTotal_Penjualan());
            } 
            // Jika ada perubahan nominal pada data yang sudah LUNAS, sesuaikan selisihnya
            else if (jualLama.getStatus_Pembayaran() == Penjualan.StatusPembayaran.LUNAS && 
                     jualBaru.getStatus_Pembayaran() == Penjualan.StatusPembayaran.LUNAS) {
                administrasiService.kurangSaldo(idAdministrasi, jualLama.getTotal_Penjualan());
                administrasiService.tambahSaldo(idAdministrasi, jualBaru.getTotal_Penjualan());
            }
            return true;
        }
        return false;
    }

    /**
     * Menghapus data penjualan. 
     * Jika data yang dihapus berstatus LUNAS, saldo akan dikurangi (dibatalkan).
     */
    public boolean hapusPenjualan(int id, int idAdministrasi) {
        Penjualan jual = penjualanDao.getById(id);
        if (jual == null) return false;

        boolean isDeleted = penjualanDao.delete(id);

        if (isDeleted) {
            if (jual.getStatus_Pembayaran() == Penjualan.StatusPembayaran.LUNAS) {
                return administrasiService.kurangSaldo(idAdministrasi, jual.getTotal_Penjualan());
            }
            return true;
        }
        return false;
    }
}