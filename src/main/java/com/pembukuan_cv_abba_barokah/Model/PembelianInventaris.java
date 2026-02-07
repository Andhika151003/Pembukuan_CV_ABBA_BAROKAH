package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PembelianInventaris {

    private int id;
    private String noPembelian;
    private String namaBarang;
    private int jumlah;
    private String satuan;
    private BigDecimal hargaSatuan;
    private BigDecimal ongkosKirim;
    private String keterangan;
    private LocalDate tanggalPembelian;

    // SELECT
    public PembelianInventaris(int id,
            String noPembelian,
            String namaBarang,
            int jumlah,
            String satuan,
            BigDecimal hargaSatuan,
            BigDecimal ongkosKirim,
            String keterangan,
            LocalDate tanggalPembelian) {

        this.id = id;
        this.noPembelian = noPembelian;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.hargaSatuan = hargaSatuan;
        this.ongkosKirim = ongkosKirim;
        this.keterangan = keterangan;
        this.tanggalPembelian = tanggalPembelian;
    }

    // INSERT
    public PembelianInventaris(String noPembelian,
            String namaBarang,
            int jumlah,
            String satuan,
            BigDecimal hargaSatuan,
            BigDecimal ongkosKirim,
            String keterangan,
            LocalDate tanggalPembelian) {

        this(0, noPembelian, namaBarang, jumlah,
                satuan, hargaSatuan, ongkosKirim,
                keterangan, tanggalPembelian);
    }

    public BigDecimal getTotal() {
        return hargaSatuan.multiply(BigDecimal.valueOf(jumlah))
                .add(ongkosKirim);
    }

    public int getId() {
        return id;
    }

    public String getNoPembelian() {
        return noPembelian;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public BigDecimal getHargaSatuan() {
        return hargaSatuan;
    }

    public BigDecimal getOngkosKirim() {
        return ongkosKirim;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public LocalDate getTanggalPembelian() {
        return tanggalPembelian;
    }
}