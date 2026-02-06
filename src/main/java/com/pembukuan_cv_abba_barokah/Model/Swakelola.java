package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Swakelola {

    private int id;
    private LocalDate tanggal;
    private BigDecimal bahan1;
    private BigDecimal bahan2;
    private BigDecimal bahan3;
    private BigDecimal ongkosTukangPotong;
    private BigDecimal ongkosTukangJahit;
    private BigDecimal lainLain;
    private BigDecimal transportasi;
    private String keterangan;
    private int idPenjualan;

    // SELECT
    public Swakelola(int id, LocalDate tanggal,
                     BigDecimal bahan1, BigDecimal bahan2, BigDecimal bahan3,
                     BigDecimal ongkosTukangPotong, BigDecimal ongkosTukangJahit,
                     BigDecimal lainLain, BigDecimal transportasi,
                     String keterangan, int idPenjualan) {

        this.id = id;
        this.tanggal = tanggal;
        this.bahan1 = bahan1;
        this.bahan2 = bahan2;
        this.bahan3 = bahan3;
        this.ongkosTukangPotong = ongkosTukangPotong;
        this.ongkosTukangJahit = ongkosTukangJahit;
        this.lainLain = lainLain;
        this.transportasi = transportasi;
        this.keterangan = keterangan;
        this.idPenjualan = idPenjualan;
    }

    // INSERT
    public Swakelola(LocalDate tanggal,
                     BigDecimal bahan1, BigDecimal bahan2, BigDecimal bahan3,
                     BigDecimal ongkosTukangPotong, BigDecimal ongkosTukangJahit,
                     BigDecimal lainLain, BigDecimal transportasi,
                     String keterangan, int idPenjualan) {

        this(0, tanggal, bahan1, bahan2, bahan3,
             ongkosTukangPotong, ongkosTukangJahit,
             lainLain, transportasi, keterangan, idPenjualan);
    }

    public int getId() { return id; }
    public LocalDate getTanggal() { return tanggal; }
    public BigDecimal getBahan1() { return bahan1; }
    public BigDecimal getBahan2() { return bahan2; }
    public BigDecimal getBahan3() { return bahan3; }
    public BigDecimal getOngkosTukangPotong() { return ongkosTukangPotong; }
    public BigDecimal getOngkosTukangJahit() { return ongkosTukangJahit; }
    public BigDecimal getLainLain() { return lainLain; }
    public BigDecimal getTransportasi() { return transportasi; }
    public String getKeterangan() { return keterangan; }
    public int getIdPenjualan() { return idPenjualan; }

    public void setTanggal(LocalDate v) { this.tanggal = v; }
    public void setBahan1(BigDecimal v) { this.bahan1 = v; }
    public void setBahan2(BigDecimal v) { this.bahan2 = v; }
    public void setBahan3(BigDecimal v) { this.bahan3 = v; }
    public void setOngkosTukangPotong(BigDecimal v) { this.ongkosTukangPotong = v; }
    public void setOngkosTukangJahit(BigDecimal v) { this.ongkosTukangJahit = v; }
    public void setLainLain(BigDecimal v) { this.lainLain = v; }
    public void setTransportasi(BigDecimal v) { this.transportasi = v; }
    public void setKeterangan(String v) { this.keterangan = v; }
}