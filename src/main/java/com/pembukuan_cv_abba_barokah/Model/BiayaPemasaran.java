package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BiayaPemasaran {

    public enum MarketingType {
        IKLAN, PROMOSI, EVENT, LAINNYA
    }

    private int id;
    private LocalDate tanggal;
    private String deskripsi;
    private BigDecimal jumlahPemasaran;
    private String kategori;
    private MarketingType marketingType;

    // Constructor full (SELECT)
    public BiayaPemasaran(
            int id,
            LocalDate tanggal,
            String deskripsi,
            BigDecimal jumlahPemasaran,
            String kategori,
            MarketingType marketingType) {

        this.id = id;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.jumlahPemasaran = jumlahPemasaran;
        this.kategori = kategori;
        this.marketingType = marketingType;
    }

    // Constructor insert
    public BiayaPemasaran(
            LocalDate tanggal,
            String deskripsi,
            BigDecimal jumlahPemasaran,
            String kategori,
            MarketingType marketingType) {

        this(0, tanggal, deskripsi,
                jumlahPemasaran, kategori, marketingType);
    }

    // GETTER
    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public BigDecimal getJumlahPemasaran() {
        return jumlahPemasaran;
    }

    public String getKategori() {
        return kategori;
    }

    public MarketingType getMarketingType() {
        return marketingType;
    }
}