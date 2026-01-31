package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.HargaPokokPenjualanDao;
import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class HppService {
    private final HargaPokokPenjualanDao hppDao;
    private final JurnalPembukuanDao jurnalDao;

    public HppService() {
        this.hppDao = new HargaPokokPenjualanDao();
        this.jurnalDao = new JurnalPembukuanDao();
    }

    public List<HargaPokokPenjualan> getAllHpp() {
        return hppDao.getAll();
    }

    public boolean simpanHpp(HargaPokokPenjualan hpp) {
        BigDecimal total = hpp.getHargaSatuan().multiply(new BigDecimal(hpp.getKuantitas()));
        hpp.setTotalHarga(total);

        boolean isSaved = hppDao.save(hpp);

        if (isSaved) {
            int nomorJurnalInt = (int) (System.currentTimeMillis() % 1000000000); 

            JurnalPembukuan jurnal = new JurnalPembukuan(
                0,                                      // 1. id
                hpp.getTanggal(),                       // 2. tanggal
                nomorJurnalInt,                         // 3. nomorJurnal
                JurnalPembukuan.JenisTransaksi.PENGELUARAN, // 4. jenisTransaksi
                JurnalPembukuan.Kategori.TRANSAKSI,     // 5. kategori
                "HPP: " + hpp.getNamaItem() + " (" + hpp.getJenisProduk() + ")", // 6. deskripsi
                BigDecimal.ZERO,                        // 7. debit
                hpp.getTotalHarga(),                    // 8. kredit
                BigDecimal.ZERO,                        // 9. saldo
                hpp.getId_Transaksi(),                  // 10. id_Transaksi
                0,                                      // 11. id_Pembayaran
                0,                                      // 12. id_Pembelian
                0                                       // 13. id_Gaji
            );
            jurnalDao.save(jurnal);
        }

        return isSaved;
    }

    public boolean updateHpp(HargaPokokPenjualan hpp) {
        BigDecimal total = hpp.getHargaSatuan().multiply(new BigDecimal(hpp.getKuantitas()));
        hpp.setTotalHarga(total);
        
        return hppDao.update(hpp);
    }

    public boolean hapusHpp(int id) {
        return hppDao.delete(id);
    }

    public HargaPokokPenjualan getHppById(int id) {
        return hppDao.getById(id);
    }
}