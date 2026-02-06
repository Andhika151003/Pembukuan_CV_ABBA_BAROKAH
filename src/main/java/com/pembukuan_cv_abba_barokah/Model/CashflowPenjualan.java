package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CashflowPenjualan {

    private final LocalDate tanggal;
    private final String noFaktur;
    private final BigDecimal totalPenjualan;
    private final BigDecimal pembayaran;
    private final BigDecimal laba;
    private final BigDecimal pph11;
    private final int idPenjualan;

    public CashflowPenjualan(
            LocalDate tanggal,
            String noFaktur,
            BigDecimal totalPenjualan,
            BigDecimal pembayaran,
            BigDecimal laba,
            BigDecimal pph11,
            int idPenjualan) {
        this.tanggal = tanggal;
        this.noFaktur = noFaktur;
        this.totalPenjualan = totalPenjualan;
        this.pembayaran = pembayaran;
        this.laba = laba;
        this.pph11 = pph11;
        this.idPenjualan = idPenjualan;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public BigDecimal getTotalPenjualan() {
        return totalPenjualan;
    }

    public BigDecimal getPembayaran() {
        return pembayaran;
    }

    public BigDecimal getLaba() {
        return laba;
    }

    public BigDecimal getPph11() {
        return pph11;
    }

    public int getIdPenjualan() {
        return idPenjualan;
    }
}