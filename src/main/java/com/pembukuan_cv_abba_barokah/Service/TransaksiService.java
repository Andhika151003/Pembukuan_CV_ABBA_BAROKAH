package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.TransaksiDao;
// import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.Transaksi;
// import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class TransaksiService {
    private final TransaksiDao transaksiDao;
    // private final JurnalPembukuanDao jurnalDao;

    public TransaksiService() {
        this.transaksiDao = new TransaksiDao();
        // this.jurnalDao = new JurnalPembukuanDao();
    }

    public List<Transaksi> getAllTransaksi() {
        return transaksiDao.getAll();
    }

    public boolean simpanTransaksi(Transaksi t) {
        BigDecimal total = t.getHarga_Jual().multiply(new BigDecimal(t.getKuantitas()));
        t.setTotal_Penjualan(total);

        boolean isSaved = transaksiDao.save(t);

        // if (isSaved && t.getStatus_Pembayaran() == Transaksi.Status.LUNAS) {
            
        //     int nomorJurnalInt = (int) (System.currentTimeMillis() % 1000000000); 
        //     JurnalPembukuan jurnal = new JurnalPembukuan(
        //         0,                                      // 1. id
        //         t.getTanggal_transaksi(),               // 2. tanggal
        //         nomorJurnalInt,                         // 3. nomorJurnal
        //         JurnalPembukuan.JenisTransaksi.PEMASUKAN, // 4. jenisTransaksi
        //         JurnalPembukuan.Kategori.TRANSAKSI,     // 5. kategori
        //         "Penjualan: " + t.getNama_Barang() + " (Qty: " + t.getKuantitas() + ")", // 6. deskripsi
        //         t.getTotal_Penjualan(),                 // 7. debit (Uang Masuk)
        //         BigDecimal.ZERO,                        // 8. kredit
        //         BigDecimal.ZERO,                        // 9. saldo
        //         t.getId(),                              // 10. id_Transaksi
        //         0,                                      // 11. id_Pembayaran
        //         0,                                      // 12. id_Pembelian
        //         0                                       // 13. id_Gaji
        //     );
        //     jurnalDao.save(jurnal);
        // }

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