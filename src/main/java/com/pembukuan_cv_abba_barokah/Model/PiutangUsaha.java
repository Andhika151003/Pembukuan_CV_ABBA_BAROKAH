package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PiutangUsaha {

    private int id;
    private LocalDate tanggal;
    private String nomorPiutang;
    private BigDecimal jumlahPiutang;
    private String namaPenerimaPiutang;
    private String keterangan;

    // SELECT
    public PiutangUsaha(int id,
            LocalDate tanggal,
            String nomorPiutang,
            BigDecimal jumlahPiutang,
            String namaPenerimaPiutang,
            String keterangan) {

        this.id = id;
        this.tanggal = tanggal;
        this.nomorPiutang = nomorPiutang;
        this.jumlahPiutang = jumlahPiutang;
        this.namaPenerimaPiutang = namaPenerimaPiutang;
        this.keterangan = keterangan;
    }

    // INSERT
    public PiutangUsaha(LocalDate tanggal,
            String nomorPiutang,
            BigDecimal jumlahPiutang,
            String namaPenerimaPiutang,
            String keterangan) {

        this(0, tanggal, nomorPiutang,
                jumlahPiutang, namaPenerimaPiutang, keterangan);
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getNomorPiutang() {
        return nomorPiutang;
    }

    public BigDecimal getJumlahPiutang() {
        return jumlahPiutang;
    }

    public String getNamaPenerimaPiutang() {
        return namaPenerimaPiutang;
    }

    public String getKeterangan() {
        return keterangan;
    }
}