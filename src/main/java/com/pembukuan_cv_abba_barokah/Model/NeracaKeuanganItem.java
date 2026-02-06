package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;

public class NeracaKeuanganItem {

    private final String nama;
    private final BigDecimal nilai;

    public NeracaKeuanganItem(String nama, BigDecimal nilai) {
        this.nama = nama;
        this.nilai = nilai;
    }

    public String getNama() {
        return nama;
    }

    public BigDecimal getNilai() {
        return nilai;
    }
}