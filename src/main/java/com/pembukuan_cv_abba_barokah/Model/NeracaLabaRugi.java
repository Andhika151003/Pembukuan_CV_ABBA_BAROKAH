package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;

public class NeracaLabaRugi {
    private int id;
    private int tahun;
    private BigDecimal total_Pendapatan;
    private BigDecimal total_HPP;
    private BigDecimal laba_Kotor;
    private BigDecimal total_Biaya_Operasional;
    private BigDecimal laba_Bersih_Sebelum_Pajak;
    private BigDecimal pajak;
    private BigDecimal laba_Bersih;

    // Constructor Lengkap
    public NeracaLabaRugi(int id, int tahun, BigDecimal total_Pendapatan, BigDecimal total_HPP,
                          BigDecimal laba_Kotor, BigDecimal total_Biaya_Operasional,
                          BigDecimal laba_Bersih_Sebelum_Pajak, BigDecimal pajak, 
                          BigDecimal laba_Bersih) {
        this.id = id;
        this.tahun = tahun;
        this.total_Pendapatan = total_Pendapatan;
        this.total_HPP = total_HPP;
        this.laba_Kotor = laba_Kotor;
        this.total_Biaya_Operasional = total_Biaya_Operasional;
        this.laba_Bersih_Sebelum_Pajak = laba_Bersih_Sebelum_Pajak;
        this.pajak = pajak;
        this.laba_Bersih = laba_Bersih;
    }

    // Constructor Tanpa ID
    public NeracaLabaRugi(int tahun, BigDecimal total_Pendapatan, BigDecimal total_HPP,
                          BigDecimal laba_Kotor, BigDecimal total_Biaya_Operasional,
                          BigDecimal laba_Bersih_Sebelum_Pajak, BigDecimal pajak, 
                          BigDecimal laba_Bersih) {
        this.tahun = tahun;
        this.total_Pendapatan = total_Pendapatan;
        this.total_HPP = total_HPP;
        this.laba_Kotor = laba_Kotor;
        this.total_Biaya_Operasional = total_Biaya_Operasional;
        this.laba_Bersih_Sebelum_Pajak = laba_Bersih_Sebelum_Pajak;
        this.pajak = pajak;
        this.laba_Bersih = laba_Bersih;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }
    public BigDecimal getTotal_Pendapatan() { return total_Pendapatan; }
    public void setTotal_Pendapatan(BigDecimal total_Pendapatan) { this.total_Pendapatan = total_Pendapatan; }
    public BigDecimal getTotal_HPP() { return total_HPP; }
    public void setTotal_HPP(BigDecimal total_HPP) { this.total_HPP = total_HPP; }
    public BigDecimal getLaba_Kotor() { return laba_Kotor; }
    public void setLaba_Kotor(BigDecimal laba_Kotor) { this.laba_Kotor = laba_Kotor; }
    public BigDecimal getTotal_Biaya_Operasional() { return total_Biaya_Operasional; }
    public void setTotal_Biaya_Operasional(BigDecimal total_Biaya_Operasional) { this.total_Biaya_Operasional = total_Biaya_Operasional; }
    public BigDecimal getLaba_Bersih_Sebelum_Pajak() { return laba_Bersih_Sebelum_Pajak; }
    public void setLaba_Bersih_Sebelum_Pajak(BigDecimal laba_Bersih_Sebelum_Pajak) { this.laba_Bersih_Sebelum_Pajak = laba_Bersih_Sebelum_Pajak; }
    public BigDecimal getPajak() { return pajak; }
    public void setPajak(BigDecimal pajak) { this.pajak = pajak; }
    public BigDecimal getLaba_Bersih() { return laba_Bersih; }
    public void setLaba_Bersih(BigDecimal laba_Bersih) { this.laba_Bersih = laba_Bersih; }

    @Override
    public String toString() {
        return "NeracaLabaRugi{" +
                "id=" + id +
                ", tahun=" + tahun +
                ", total_Pendapatan=" + total_Pendapatan +
                ", total_HPP=" + total_HPP +
                ", laba_Kotor=" + laba_Kotor +
                ", total_Biaya_Operasional=" + total_Biaya_Operasional +
                ", laba_Bersih_Sebelum_Pajak=" + laba_Bersih_Sebelum_Pajak +
                ", pajak=" + pajak +
                ", laba_Bersih=" + laba_Bersih +
                '}';
    }
}