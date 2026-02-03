package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.GajiPegawaiDao;
// import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.GajiPegawai;
// import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class GajiPegawaiService {
    private final GajiPegawaiDao gajiDao;
    // private final JurnalPembukuanDao jurnalDao;

    public GajiPegawaiService() {
        this.gajiDao = new GajiPegawaiDao();
        // this.jurnalDao = new JurnalPembukuanDao();
    }

    public List<GajiPegawai> getAll() {
        return gajiDao.getAll();
    }

    public boolean bayarGaji(GajiPegawai gaji) {
        BigDecimal pokok = (gaji.getGaji_pokok() != null) ? gaji.getGaji_pokok() : BigDecimal.ZERO;
        BigDecimal tunjangan = (gaji.getTunjangan() != null) ? gaji.getTunjangan() : BigDecimal.ZERO;
        BigDecimal potongan = (gaji.getPotongan() != null) ? gaji.getPotongan() : BigDecimal.ZERO;
        
        BigDecimal total = pokok.add(tunjangan).subtract(potongan);
        gaji.setTotal_gaji(total);

        boolean isGajiSaved = gajiDao.save(gaji);

        // if (isGajiSaved && gaji.getStatus_pembayaran() == GajiPegawai.Status.LUNAS) {
        //     int nomorJurnalInt = (int) (System.currentTimeMillis() % 1000000000); 

        //     JurnalPembukuan jurnal = new JurnalPembukuan(
        //         0,                                      // 1. id
        //         gaji.getTanggal_pembayaran(),           // 2. tanggal
        //         nomorJurnalInt,                         // 3. nomorJurnal
        //         JurnalPembukuan.JenisTransaksi.PENGELUARAN, // 4. jenisTransaksi
        //         JurnalPembukuan.Kategori.GAJI,          // 5. kategori
        //         "Bayar Gaji: Periode " + gaji.getPeriode(), // 6. deskripsi
        //         BigDecimal.ZERO,                        // 7. debit
        //         gaji.getTotal_gaji(),                   // 8. kredit
        //         BigDecimal.ZERO,                        // 9. saldo
        //         0,                                      // 10. id_Transaksi
        //         0,                                      // 11. id_Pembayaran
        //         0,                                      // 12. id_Pembelian
        //         gaji.getId()                            // 13. id_Gaji
        //     );
            
        //     jurnalDao.save(jurnal);
        // }

        return isGajiSaved;
    }

    public boolean updateStatusGaji(GajiPegawai gaji) {
        return gajiDao.update(gaji);
    }

    public boolean hapusRiwayatGaji(int id) {
        return gajiDao.delete(id);
    }
}