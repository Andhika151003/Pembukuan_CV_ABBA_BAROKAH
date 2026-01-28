package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.BiayaPemasaranDao;
import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import java.math.BigDecimal;
import java.util.List;

public class BiayaPemasaranService {
    private final BiayaPemasaranDao biayaPemasaranDao;
    private final AdministrasiService administrasiService;

    public BiayaPemasaranService() {
        this.biayaPemasaranDao = new BiayaPemasaranDao();
        this.administrasiService = new AdministrasiService();
    }

    public List<BiayaPemasaran> getAll() {
        return biayaPemasaranDao.getAll();
    }

    public BiayaPemasaran getById(int id) {
        return biayaPemasaranDao.getById(id);
    }

    /**
     * Menyimpan biaya pemasaran baru dan otomatis memotong saldo administrasi.
     */
    public boolean tambahBiayaPemasaran(BiayaPemasaran biaya) {
        // 1. Simpan data ke tabel BiayaPemasaran
        boolean isSaved = biayaPemasaranDao.save(biaya);

        if (isSaved) {
            // 2. Jika berhasil, potong saldo di tabel Administrasi berdasarkan idAdministrasi
            // Mengonversi int jumlah ke BigDecimal karena AdministrasiService menggunakan BigDecimal
            BigDecimal nominal = new BigDecimal(biaya.getJumlah());
            return administrasiService.kurangSaldo(biaya.getIdAdministrasi(), nominal);
        }
        return false;
    }


    public boolean perbaruiBiayaPemasaran(BiayaPemasaran biayaBaru) {
        // Ambil data lama untuk menghitung selisih saldo
        BiayaPemasaran biayaLama = biayaPemasaranDao.getById(biayaBaru.getId());
        if (biayaLama == null) return false;

        boolean isUpdated = biayaPemasaranDao.update(biayaBaru);

        if (isUpdated) {
            // Logika penyesuaian saldo:
            // Kembalikan saldo lama, lalu kurangi dengan saldo baru
            BigDecimal nominalLama = new BigDecimal(biayaLama.getJumlah());
            BigDecimal nominalBaru = new BigDecimal(biayaBaru.getJumlah());

            administrasiService.tambahSaldo(biayaLama.getIdAdministrasi(), nominalLama);
            return administrasiService.kurangSaldo(biayaBaru.getIdAdministrasi(), nominalBaru);
        }
        return false;
    }


    public boolean hapusBiayaPemasaran(int id) {
        BiayaPemasaran biaya = biayaPemasaranDao.getById(id);
        if (biaya == null) return false;

        boolean isDeleted = biayaPemasaranDao.delete(id);

        if (isDeleted) {
            // Jika biaya dihapus, uang dikembalikan ke saldo administrasi
            BigDecimal nominal = new BigDecimal(biaya.getJumlah());
            return administrasiService.tambahSaldo(biaya.getIdAdministrasi(), nominal);
        }
        return false;
    }
}