package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;

public class LaporanLabaRugi {

    private int tahun;

    private BigDecimal totalPenjualan;
    private BigDecimal totalReturPenjualan;
    private BigDecimal totalPendapatan;

    private BigDecimal totalHpp;
    private BigDecimal labaKotor;

    private BigDecimal biayaAdministrasi;
    private BigDecimal biayaPemasaran;
    private BigDecimal totalPajak;

    private BigDecimal totalBiayaOperasional;
    private BigDecimal labaBersih;

    /* GETTER & SETTER */

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public BigDecimal getTotalPenjualan() { return totalPenjualan; }
    public void setTotalPenjualan(BigDecimal totalPenjualan) { this.totalPenjualan = totalPenjualan; }

    public BigDecimal getTotalReturPenjualan() { return totalReturPenjualan; }
    public void setTotalReturPenjualan(BigDecimal totalReturPenjualan) { this.totalReturPenjualan = totalReturPenjualan; }

    public BigDecimal getTotalPendapatan() { return totalPendapatan; }
    public void setTotalPendapatan(BigDecimal totalPendapatan) { this.totalPendapatan = totalPendapatan; }

    public BigDecimal getTotalHpp() { return totalHpp; }
    public void setTotalHpp(BigDecimal totalHpp) { this.totalHpp = totalHpp; }

    public BigDecimal getLabaKotor() { return labaKotor; }
    public void setLabaKotor(BigDecimal labaKotor) { this.labaKotor = labaKotor; }

    public BigDecimal getBiayaAdministrasi() { return biayaAdministrasi; }
    public void setBiayaAdministrasi(BigDecimal biayaAdministrasi) { this.biayaAdministrasi = biayaAdministrasi; }

    public BigDecimal getBiayaPemasaran() { return biayaPemasaran; }
    public void setBiayaPemasaran(BigDecimal biayaPemasaran) { this.biayaPemasaran = biayaPemasaran; }

    public BigDecimal getTotalPajak() { return totalPajak; }
    public void setTotalPajak(BigDecimal totalPajak) { this.totalPajak = totalPajak; }

    public BigDecimal getTotalBiayaOperasional() { return totalBiayaOperasional; }
    public void setTotalBiayaOperasional(BigDecimal totalBiayaOperasional) {
        this.totalBiayaOperasional = totalBiayaOperasional;
    }

    public BigDecimal getLabaBersih() { return labaBersih; }
    public void setLabaBersih(BigDecimal labaBersih) { this.labaBersih = labaBersih; }
}