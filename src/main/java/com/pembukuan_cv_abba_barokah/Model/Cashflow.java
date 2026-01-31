package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;

public class Cashflow {
    private int id;
    private String bulan;
    private int tahun;
    private BigDecimal totalPemasukan;      
    private BigDecimal totalPengeluaran;   
    private BigDecimal saldoAwal;           
    private BigDecimal saldoAkhir;

    // Constructor
    public Cashflow(int id, String bulan, int tahun, BigDecimal totalPemasukan,
                    BigDecimal totalPengeluaran, BigDecimal saldoAwal, 
                    BigDecimal saldoAkhir) {
        this.id = id;
        this.bulan = bulan;
        this.tahun = tahun;
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.saldoAwal = saldoAwal;
        this.saldoAkhir = saldoAkhir;
    }

    // Constructor (tanpa id)
    public Cashflow(String bulan, int tahun, BigDecimal totalPemasukan,
                    BigDecimal totalPengeluaran, BigDecimal saldoAwal, 
                    BigDecimal saldoAkhir) {
        this.bulan = bulan;
        this.tahun = tahun;
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.saldoAwal = saldoAwal;
        this.saldoAkhir = saldoAkhir;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public BigDecimal getTotalPemasukan() {
        return totalPemasukan;
    }

    public void setTotalPemasukan(BigDecimal totalPemasukan) {
        this.totalPemasukan = totalPemasukan;
    }

    public BigDecimal getTotalPengeluaran() {
        return totalPengeluaran;
    }

    public void setTotalPengeluaran(BigDecimal totalPengeluaran) {
        this.totalPengeluaran = totalPengeluaran;
    }

    public BigDecimal getSaldoAwal() {
        return saldoAwal;
    }

    public void setSaldoAwal(BigDecimal saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    public BigDecimal getSaldoAkhir() {
        return saldoAkhir;
    }

    public void setSaldoAkhir(BigDecimal saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }

    //Method kalkulasi
    public void hitungSaldoAkhir() {
        this.saldoAkhir = this.saldoAwal
            .add(this.totalPemasukan)
            .subtract(this.totalPengeluaran);
    }

    @Override
    public String toString() {
        return "Cashflow{" +
                "id=" + id +
                ", bulan='" + bulan + '\'' +
                ", tahun=" + tahun +
                ", totalPemasukan=" + totalPemasukan +
                ", totalPengeluaran=" + totalPengeluaran +
                ", saldoAwal=" + saldoAwal +
                ", saldoAkhir=" + saldoAkhir +
                '}';
    }
}