package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CashflowPenjualan {

    private LocalDate tanggal;
    private String noFaktur;
    private BigDecimal nominalPenjualan;
    private BigDecimal nominalPembayaran;
    private BigDecimal pph11;

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public BigDecimal getNominalPenjualan() {
        return nominalPenjualan;
    }

    public void setNominalPenjualan(BigDecimal nominalPenjualan) {
        this.nominalPenjualan = nominalPenjualan;
    }

    public BigDecimal getNominalPembayaran() {
        return nominalPembayaran;
    }

    public void setNominalPembayaran(BigDecimal nominalPembayaran) {
        this.nominalPembayaran = nominalPembayaran;
    }

    public BigDecimal getPph11() {
        return pph11;
    }

    public void setPph11(BigDecimal pph11) {
        this.pph11 = pph11;
    }
}