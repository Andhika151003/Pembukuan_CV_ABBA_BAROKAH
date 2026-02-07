package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PersediaanBarang {

    private int id;
    private LocalDate tanggal;
    private String namaBarang;
    private int jumlah;
    private BigDecimal hargaSatuan;
    private String keterangan;

    // SELECT
    public PersediaanBarang(int id,
            LocalDate tanggal,
            String namaBarang,
            int jumlah,
            BigDecimal hargaSatuan,
            String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.keterangan = keterangan;
    }

    // INSERT
    public PersediaanBarang(LocalDate tanggal,
            String namaBarang,
            int jumlah,
            BigDecimal hargaSatuan,
            String keterangan) {
        this(0, tanggal, namaBarang, jumlah, hargaSatuan, keterangan);
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public BigDecimal getHargaSatuan() {
        return hargaSatuan;
    }

    public String getKeterangan() {
        return keterangan;
    }
}