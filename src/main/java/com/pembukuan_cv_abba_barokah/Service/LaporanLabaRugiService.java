package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.LaporanLabaRugiDao;
import java.math.BigDecimal;

public class LaporanLabaRugiService {

    private final LaporanLabaRugiDao dao = new LaporanLabaRugiDao();

    public BigDecimal totalPenjualan(String tahun) {
        return dao.totalPenjualan(tahun);
    }

    public BigDecimal totalReturPenjualan(String tahun) {
        return dao.totalReturPenjualan(tahun);
    }

    public BigDecimal totalPendapatan(String tahun) {
        return totalPenjualan(tahun)
                .subtract(totalReturPenjualan(tahun));
    }

    public BigDecimal totalHpp(String tahun) {
        return dao.totalHpp(tahun);
    }

    public BigDecimal totalReturPembelian(String tahun) {
        return dao.totalReturPembelian(tahun);
    }

    public BigDecimal totalPembelian(String tahun) {
        return totalHpp(tahun)
                .subtract(totalReturPembelian(tahun));
    }

    public BigDecimal labaKotor(String tahun) {
        return totalPendapatan(tahun)
                .subtract(totalPembelian(tahun));
    }

    public BigDecimal totalBiayaAdministrasi(String tahun) {
        return dao.totalBiayaAdministrasi(tahun);
    }

    // ⬅ otomatis pakai logic DAO terbaru
    public BigDecimal totalBiayaOperasional(String tahun) {
        return dao.totalBiayaOperasional(tahun);
    }

    public BigDecimal totalBiayaPemasaran(String tahun) {
        return dao.totalBiayaPemasaran(tahun);
    }

    public BigDecimal totalBiayaPemeliharaan(String tahun) {
        return dao.totalBiayaPemeliharaan(tahun);
    }

    public BigDecimal totalBiaya(String tahun) {
        return totalBiayaAdministrasi(tahun)
                .add(totalBiayaOperasional(tahun))
                .add(totalBiayaPemasaran(tahun))
                .add(totalBiayaPemeliharaan(tahun));
    }

    public BigDecimal labaBersih(String tahun) {
        return labaKotor(tahun)
                .subtract(totalBiaya(tahun));
    }
}