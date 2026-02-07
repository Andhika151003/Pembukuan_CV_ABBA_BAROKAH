package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;

public class NeracaKeuangan {

    private final String keterangan;
    private final BigDecimal jumlah;

    public NeracaKeuangan(String keterangan, BigDecimal jumlah) {
        this.keterangan = keterangan;
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }
}