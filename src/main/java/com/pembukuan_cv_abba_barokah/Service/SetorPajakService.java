package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.SetorPajakDao;
// import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
// import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
// import java.math.BigDecimal;
import java.util.List;

public class SetorPajakService {
    private final SetorPajakDao pajakDao;
    // private final JurnalPembukuanDao jurnalDao;

    public SetorPajakService() {
        this.pajakDao = new SetorPajakDao();
        // this.jurnalDao = new JurnalPembukuanDao();
    }

    public List<SetorPajak> getAllPajak() {
        return pajakDao.getAll();
    }

    public boolean simpanSetorPajak(SetorPajak pajak) {
        boolean isSaved = pajakDao.save(pajak);

        // if (isSaved) {
        //     int nomorJurnalInt = (int) (System.currentTimeMillis() % 1000000000); 

        //     JurnalPembukuan jurnal = new JurnalPembukuan(
        //         0,                                      // 1. id
        //         pajak.getTanggal_Setor(),               // 2. tanggal
        //         nomorJurnalInt,                         // 3. nomorJurnal
        //         JurnalPembukuan.JenisTransaksi.PENGELUARAN, // 4. jenisTransaksi
        //         JurnalPembukuan.Kategori.TRANSAKSI,     // 5. kategori
        //         "Setor Pajak: " + pajak.getJenis_Pajak() + " Periode " + pajak.getPeriode(), // 6. deskripsi
        //         BigDecimal.ZERO,                        // 7. debit
        //         pajak.getJumlah_Pajak(),                // 8. kredit (Uang Keluar)
        //         BigDecimal.ZERO,                        // 9. saldo
        //         0,                                      // 10. id_Transaksi
        //         0,                                      // 11. id_Pembayaran
        //         0,                                      // 12. id_Pembelian
        //         0                                       // 13. id_Gaji
        //     );
            
        //     jurnalDao.save(jurnal);
        // }

        return isSaved;
    }

    public boolean updatePajak(SetorPajak pajak) {
        return pajakDao.update(pajak);
    }

    public boolean hapusPajak(int id) {
        return pajakDao.delete(id);
    }
    
    public SetorPajak getPajakById(int id) {
        return pajakDao.getById(id);
    }
}