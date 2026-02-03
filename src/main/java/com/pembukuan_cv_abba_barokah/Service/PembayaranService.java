package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PembayaranDao;
// import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
// import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
// import java.math.BigDecimal;
import java.util.List;

public class PembayaranService {
    private final PembayaranDao pembayaranDao;
    // private final JurnalPembukuanDao jurnalDao;

    public PembayaranService() {
        this.pembayaranDao = new PembayaranDao();
        // this.jurnalDao = new JurnalPembukuanDao();
    }

    public List<Pembayaran> getAllPembayaran() {
        return pembayaranDao.getAll();
    }

    public boolean simpanPembayaran(Pembayaran pembayaran) {
        boolean isSaved = pembayaranDao.save(pembayaran);

        // if (isSaved) {
        //     int nomorJurnalInt = (int) (System.currentTimeMillis() % 1000000000); 

        //     JurnalPembukuan jurnal = new JurnalPembukuan(
        //         0,                                      // 1. id
        //         pembayaran.getTanggal_Pembayaran(),      // 2. tanggal
        //         nomorJurnalInt,                         // 3. nomorJurnal
        //         JurnalPembukuan.JenisTransaksi.PEMASUKAN, // 4. jenisTransaksi
        //         JurnalPembukuan.Kategori.PEMBAYARAN,    // 5. kategori
        //         "Pembayaran Masuk: " + pembayaran.getMetode_Pembayaran() + " - " + pembayaran.getKeterangan(), // 6. deskripsi
        //         pembayaran.getJumlah_Pembayaran(),      // 7. debit (Uang Masuk)
        //         BigDecimal.ZERO,                        // 8. kredit
        //         BigDecimal.ZERO,                        // 9. saldo
        //         pembayaran.getId_Transaksi(),           // 10. id_Transaksi
        //         pembayaran.getId(),                     // 11. id_Pembayaran
        //         0,                                      // 12. id_Pembelian
        //         0                                       // 13. id_Gaji
        //     );
            
        //     jurnalDao.save(jurnal);
        // }

        return isSaved;
    }

    public boolean updatePembayaran(Pembayaran pembayaran) {
        return pembayaranDao.update(pembayaran);
    }

    public boolean hapusPembayaran(int id) {
        return pembayaranDao.delete(id);
    }

    public Pembayaran getPembayaranById(int id) {
        return pembayaranDao.getById(id);
    }
}