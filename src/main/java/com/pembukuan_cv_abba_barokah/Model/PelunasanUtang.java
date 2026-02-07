package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PelunasanUtang {

    private int id;
    private LocalDate tanggal;
    private BigDecimal jumlahPelunasanUtang;
    private String namaPemberiUtang;
    private String keterangan;

    // SELECT
    public PelunasanUtang(int id,
            LocalDate tanggal,
            BigDecimal jumlahPelunasanUtang,
            String namaPemberiUtang,
            String keterangan) {

        this.id = id;
        this.tanggal = tanggal;
        this.jumlahPelunasanUtang = jumlahPelunasanUtang;
        this.namaPemberiUtang = namaPemberiUtang;
        this.keterangan = keterangan;
    }

    // INSERT
    public PelunasanUtang(LocalDate tanggal,
            BigDecimal jumlahPelunasanUtang,
            String namaPemberiUtang,
            String keterangan) {

        this(0, tanggal, jumlahPelunasanUtang,
                namaPemberiUtang, keterangan);
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public BigDecimal getJumlahPelunasanUtang() {
        return jumlahPelunasanUtang;
    }

    public String getNamaPemberiUtang() {
        return namaPemberiUtang;
    }

    public String getKeterangan() {
        return keterangan;
    }
}