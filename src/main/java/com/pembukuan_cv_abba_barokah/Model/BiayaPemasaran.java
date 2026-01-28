package com.pembukuan_cv_abba_barokah.Model;

import java.time.LocalDate;

public class BiayaPemasaran {

    public enum ExpenseCategory {
        PEMASARAN,
        OPERASIONAL,
        ADMINISTRASI,
        GAJI,
        UTILITAS
    }

    public enum MarketingExpenseType {
        IKLAN,
        PROMOSI,
        KOMISI_SALES,
        EVENT,
        MATERI_PROMOSI,
        TRANSPORT_MARKETING,
        TOOLS_DIGITAL
    }

    private int id;
    private LocalDate tanggal;
    private String deskripsi;
    private int jumlah;
    private ExpenseCategory category;
    private MarketingExpenseType marketingType;
    private int idAdministrasi;

    // Constructor Lengkap
    public BiayaPemasaran(int id, LocalDate tanggal, String deskripsi, int jumlah, 
                          ExpenseCategory category, MarketingExpenseType marketingType, 
                          int idAdministrasi) {
        this.id = id;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.category = category;
        this.marketingType = marketingType;
        this.idAdministrasi = idAdministrasi;
    }

    // Constructor Tanpa ID (untuk penambahan data baru)
    public BiayaPemasaran(LocalDate tanggal, String deskripsi, int jumlah, 
                          ExpenseCategory category, MarketingExpenseType marketingType, 
                          int idAdministrasi) {
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.category = category;
        this.marketingType = marketingType;
        this.idAdministrasi = idAdministrasi;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public ExpenseCategory getCategory() { return category; }
    public void setCategory(ExpenseCategory category) { this.category = category; }

    public MarketingExpenseType getMarketingType() { return marketingType; }
    public void setMarketingType(MarketingExpenseType marketingType) { this.marketingType = marketingType; }

    public int getIdAdministrasi() { return idAdministrasi; }
    public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

    @Override
    public String toString() {
        return "BiayaPemasaran{" +
                "id=" + id +
                ", tanggal=" + tanggal +
                ", deskripsi='" + deskripsi + '\'' +
                ", jumlah=" + jumlah +
                ", category=" + category +
                ", marketingType=" + marketingType +
                ", idAdministrasi=" + idAdministrasi +
                '}';
    }
}