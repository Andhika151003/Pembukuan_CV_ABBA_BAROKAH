package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cashflow {
    private int id;
    private LocalDate tanggal;
    private BigDecimal totalPemasukan;      
    private BigDecimal totalPengeluaran;   
    private BigDecimal saldoAwal;           
    private BigDecimal saldoAkhir;
    private BigDecimal pph;

    //Konstanta tarif pph
    private static final BigDecimal TARIF_PPH = new BigDecimal("0.11");

    // Constructor
    public Cashflow(int id, LocalDate tanggal, BigDecimal totalPemasukan,
                    BigDecimal totalPengeluaran, BigDecimal saldoAwal, 
                    BigDecimal saldoAkhir, BigDecimal pph) {
        this.id = id;
        this.tanggal = tanggal;
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.saldoAwal = saldoAwal;
        this.saldoAkhir = saldoAkhir;
        this.pph = pph;
    }

    // Constructor (tanpa id)
    public Cashflow(LocalDate tanggal , BigDecimal totalPemasukan,
                    BigDecimal totalPengeluaran, BigDecimal saldoAwal, 
                    BigDecimal saldoAkhir, BigDecimal pph) {
        this.tanggal = tanggal;
        this.totalPemasukan = totalPemasukan;
        this.totalPengeluaran = totalPengeluaran;
        this.saldoAwal = saldoAwal;
        this.saldoAkhir = saldoAkhir;
        this.pph = pph;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
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

    public BigDecimal getPph() {
        return pph;
    }

    public void setPph(BigDecimal pph) {
        this.pph = pph;
    }

    //Method kalkulasi
    public void hitungSaldoAkhir() {
        if (this.pph == null) kalkulasiPph();
        this.saldoAkhir = this.saldoAwal
            .add(this.totalPemasukan)
            .subtract(this.totalPengeluaran)
            .subtract(this.pph);
    }

    //Method kalkulasi menghitung pph otomatis
    public void kalkulasiPph() {
        if (this.totalPemasukan != null) {
            this.pph = this.totalPemasukan.multiply(TARIF_PPH);
        } else {
            this.pph = BigDecimal.ZERO;
        }
    }

    @Override
    public String toString() {
        return "Cashflow{" +
                "id=" + id +
                ", tanggal='" + tanggal +
                ", totalPemasukan=" + totalPemasukan +
                ", totalPengeluaran=" + totalPengeluaran +
                ", saldoAwal=" + saldoAwal +
                ", saldoAkhir=" + saldoAkhir +
                ", pph=" + pph +
                '}';
    }
}