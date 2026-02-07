package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BiayaPemeliharaan {

    private int id;
    private LocalDate tanggal;
    private BigDecimal jumlahBiayaPemeliharaan;
    private String keterangan;

    // SELECT
    public BiayaPemeliharaan(int id,
            LocalDate tanggal,
            BigDecimal jumlahBiayaPemeliharaan,
            String keterangan) {

        this.id = id;
        this.tanggal = tanggal;
        this.jumlahBiayaPemeliharaan = jumlahBiayaPemeliharaan;
        this.keterangan = keterangan;
    }

    // INSERT
    public BiayaPemeliharaan(LocalDate tanggal,
            BigDecimal jumlahBiayaPemeliharaan,
            String keterangan) {

        this(0, tanggal, jumlahBiayaPemeliharaan, keterangan);
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public BigDecimal getJumlahBiayaPemeliharaan() {
        return jumlahBiayaPemeliharaan;
    }

    public String getKeterangan() {
        return keterangan;
    }
}