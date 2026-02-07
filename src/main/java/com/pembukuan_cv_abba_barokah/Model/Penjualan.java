package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Penjualan {

    private int id;
    private String noFaktur;
    private LocalDate tanggal;
    private String namaCustomer;
    private String alamatCustomer;
    private BigDecimal total;
    private String keterangan;

    // INSERT
    public Penjualan(String noFaktur,
            LocalDate tanggal,
            String namaCustomer,
            String alamatCustomer,
            BigDecimal total,
            String keterangan) {

        this.noFaktur = noFaktur;
        this.tanggal = tanggal;
        this.namaCustomer = namaCustomer;
        this.alamatCustomer = alamatCustomer;
        this.total = total;
        this.keterangan = keterangan;
    }

    // SELECT
    public Penjualan(int id,
            String noFaktur,
            LocalDate tanggal,
            String namaCustomer,
            String alamatCustomer,
            BigDecimal total,
            String keterangan) {

        this(noFaktur, tanggal, namaCustomer, alamatCustomer, total, keterangan);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public String getAlamatCustomer() {
        return alamatCustomer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setNoFaktur(String v) {
        this.noFaktur = v;
    }

    public void setTanggal(LocalDate v) {
        this.tanggal = v;
    }

    public void setNamaCustomer(String v) {
        this.namaCustomer = v;
    }

    public void setAlamatCustomer(String v) {
        this.alamatCustomer = v;
    }

    public void setTotal(BigDecimal v) {
        this.total = v;
    }

    public void setKeterangan(String v) {
        this.keterangan = v;
    }
}