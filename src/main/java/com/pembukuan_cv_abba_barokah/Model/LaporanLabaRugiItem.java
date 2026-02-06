package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;

public class LaporanLabaRugiItem {

    private final String kode;
    private final String keterangan;
    private final BigDecimal jumlah;

    public LaporanLabaRugiItem(String kode, String keterangan, BigDecimal jumlah) {
        this.kode = kode;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
    }

    public String getKode() {
        return kode;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }
}