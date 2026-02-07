package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PelunasanPiutang {

    private int id;
    private LocalDate tanggal;
    private BigDecimal jumlahPelunasanPiutang;
    private String namaPenerimaPiutang;
    private String keterangan;

    // SELECT
    public PelunasanPiutang(int id,
            LocalDate tanggal,
            BigDecimal jumlahPelunasanPiutang,
            String namaPenerimaPiutang,
            String keterangan) {

        this.id = id;
        this.tanggal = tanggal;
        this.jumlahPelunasanPiutang = jumlahPelunasanPiutang;
        this.namaPenerimaPiutang = namaPenerimaPiutang;
        this.keterangan = keterangan;
    }

    // INSERT
    public PelunasanPiutang(LocalDate tanggal,
            BigDecimal jumlahPelunasanPiutang,
            String namaPenerimaPiutang,
            String keterangan) {

        this(0, tanggal, jumlahPelunasanPiutang,
                namaPenerimaPiutang, keterangan);
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public BigDecimal getJumlahPelunasanPiutang() {
        return jumlahPelunasanPiutang;
    }

    public String getNamaPenerimaPiutang() {
        return namaPenerimaPiutang;
    }

    public String getKeterangan() {
        return keterangan;
    }
}