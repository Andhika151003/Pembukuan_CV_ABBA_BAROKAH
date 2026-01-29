package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PersediaanBarangDao;
import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import java.math.BigDecimal;
import java.util.List;

public class PersediaanBarangService {
    private final PersediaanBarangDao persediaanBarangDao;

    public PersediaanBarangService() {
        this.persediaanBarangDao = new PersediaanBarangDao();
    }

    public List<PersediaanBarang> getAll() {
        return persediaanBarangDao.getAll();
    }

    public PersediaanBarang getById(int id) {
        return persediaanBarangDao.getById(id);
    }

    /**
     * Menyimpan transaksi persediaan baru dengan perhitungan saldo akhir otomatis.
     * Saldo akhir dihitung berdasarkan (Saldo Sebelumnya + Nilai Masuk - Nilai Keluar).
     */
    public boolean tambahPersediaan(PersediaanBarang p) {
        // 1. Ambil saldo terakhir dari database
        BigDecimal saldoTerakhir = getSaldoTerakhir();

        // 2. Hitung nilai perubahan (Masuk - Keluar) * Harga Satuan
        BigDecimal nilaiMasuk = BigDecimal.valueOf(p.getJumlah_Masuk()).multiply(p.getHarga_Satuan());
        BigDecimal nilaiKeluar = BigDecimal.valueOf(p.getJumlah_Keluar()).multiply(p.getHarga_Satuan());
        
        // 3. Hitung saldo baru
        BigDecimal saldoBaru = saldoTerakhir.add(nilaiMasuk).subtract(nilaiKeluar);

        // 4. Set saldo_akhir ke objek sebelum disimpan
        p.setSaldo_Akhir(saldoBaru);

        return persediaanBarangDao.save(p);
    }

    /**
     * Memperbarui data persediaan.
     * Catatan: Update data di tengah history dapat mempengaruhi saldo baris setelahnya.
     */
    public boolean perbaruiPersediaan(PersediaanBarang p) {
        return persediaanBarangDao.update(p);
    }

    /**
     * Menghapus data persediaan berdasarkan ID.
     */
    public boolean hapusPersediaan(int id) {
        return persediaanBarangDao.delete(id);
    }

    /**
     * Helper untuk mengambil saldo akhir dari transaksi paling terakhir.
     */
    private BigDecimal getSaldoTerakhir() {
        List<PersediaanBarang> list = persediaanBarangDao.getAll();
        if (list.isEmpty()) {
            return BigDecimal.ZERO;
        }
        // Mengambil saldo_akhir dari baris terakhir di tabel
        return list.get(list.size() - 1).getSaldo_Akhir();
    }
}