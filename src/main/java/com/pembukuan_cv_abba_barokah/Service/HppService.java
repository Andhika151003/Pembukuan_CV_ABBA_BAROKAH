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
    private final AdministrasiService adminService;

    public HppService() {
        this.hppDao = new HargaPokokPenjualanDao();
        this.jurnalDao = new JurnalPembukuanDao();
        this.adminService = new AdministrasiService();
    }

    public List<HargaPokokPenjualan> getAllHpp() {
        return hppDao.getAll();
    }

    public boolean simpanHpp(HargaPokokPenjualan hpp) {
        BigDecimal total = hpp.getHargaSatuan().multiply(new BigDecimal(hpp.getKuantitas()));
        hpp.setTotalHarga(total);

        boolean isSaved = hppDao.save(hpp);

        if (isSaved) {
            adminService.kurangSaldo(hpp.getIdAdministrasi(), hpp.getTotalHarga());
            JurnalPembukuan jurnal = new JurnalPembukuan(
                0,
                hpp.getTanggal(),
                "HPP-" + System.currentTimeMillis(), // Nomor Jurnal unik
                JurnalPembukuan.JenisTransaksi.PENGELUARAN,
                JurnalPembukuan.Kategori.HPP,
                "HPP: " + hpp.getNamaItem() + " (" + hpp.getJenisProduk() + ")",
                BigDecimal.ZERO,      // Debit
                hpp.getTotalHarga(),  // Kredit (Biaya Keluar)
                BigDecimal.ZERO,      // Saldo
                hpp.getIdAdministrasi()
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