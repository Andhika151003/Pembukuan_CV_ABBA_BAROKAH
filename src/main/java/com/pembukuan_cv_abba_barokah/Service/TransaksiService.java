package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.TransaksiDao;
import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.Transaksi;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class TransaksiService {
    private final TransaksiDao transaksiDao;
    private final JurnalPembukuanDao jurnalDao;
    private final AdministrasiService adminService;

    public TransaksiService() {
        this.transaksiDao = new TransaksiDao();
        this.jurnalDao = new JurnalPembukuanDao();
        this.adminService = new AdministrasiService();
    }

    public List<Transaksi> getAllTransaksi() {
        return transaksiDao.getAll();
    }

    public boolean simpanTransaksi(Transaksi t) {
        // 1. Hitung total penjualan secara otomatis (Harga x Kuantitas)
        BigDecimal total = t.getHarga_Jual().multiply(new BigDecimal(t.getKuantitas()));
        t.setTotal_Penjualan(total);

        // 2. Simpan detail transaksi ke database
        boolean isSaved = transaksiDao.save(t);

        // 3. Jika status LUNAS, lakukan otomatisasi ke tabel lain
        if (isSaved && t.getStatus_Pembayaran() == Transaksi.Status.LUNAS) {
            
            // Update Saldo di tabel Administrasi pusat (Uang Masuk)
            adminService.tambahSaldo(t.getIdAdministrasi(), t.getTotal_Penjualan());

            // Catat otomatis ke Jurnal Pembukuan sebagai Pemasukan
            JurnalPembukuan jurnal = new JurnalPembukuan(
                0,
                t.getTanggal_transaksi(),
                t.getNomor_Faktur(),
                JurnalPembukuan.JenisTransaksi.PEMASUKAN,
                JurnalPembukuan.Kategori.PENJUALAN,
                "Penjualan: " + t.getNama_Barang() + " (Qty: " + t.getKuantitas() + ")",
                t.getTotal_Penjualan(), // Debit (Uang Masuk)
                BigDecimal.ZERO,        // Kredit
                BigDecimal.ZERO,        // Saldo
                t.getIdAdministrasi()
            );
            jurnalDao.save(jurnal);
        }

        return isSaved;
    }

    public boolean updateTransaksi(Transaksi t) {
        // Hitung ulang total sebelum update
        BigDecimal total = t.getHarga_Jual().multiply(new BigDecimal(t.getKuantitas()));
        t.setTotal_Penjualan(total);
        
        return transaksiDao.update(t);
    }

    public boolean hapusTransaksi(int id) {
        return transaksiDao.delete(id);
    }
    
    public Transaksi getTransaksiById(int id) {
        return transaksiDao.getById(id);
    }
}