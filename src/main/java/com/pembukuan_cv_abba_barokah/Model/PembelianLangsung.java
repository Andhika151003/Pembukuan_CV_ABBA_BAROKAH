package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PembelianLangsung {

    private int id;
    private LocalDate tanggal;
    private BigDecimal hargaPerolehanLangsung;
    private BigDecimal transportasi;
    private BigDecimal upah;
    private String keterangan;
    private int idPenjualan;

    // SELECT
    public PembelianLangsung(int id, LocalDate tanggal,
                             BigDecimal hargaPerolehanLangsung,
                             BigDecimal transportasi,
                             BigDecimal upah,
                             String keterangan,
                             int idPenjualan) {
        this.id = id;
        this.tanggal = tanggal;
        this.hargaPerolehanLangsung = hargaPerolehanLangsung;
        this.transportasi = transportasi;
        this.upah = upah;
        this.keterangan = keterangan;
        this.idPenjualan = idPenjualan;
    }

    // INSERT
    public PembelianLangsung(LocalDate tanggal,
                             BigDecimal hargaPerolehanLangsung,
                             BigDecimal transportasi,
                             BigDecimal upah,
                             String keterangan,
                             int idPenjualan) {
        this(0, tanggal, hargaPerolehanLangsung, transportasi, upah, keterangan, idPenjualan);
    }

    public int getId() { return id; }
    public LocalDate getTanggal() { return tanggal; }
    public BigDecimal getHargaPerolehanLangsung() { return hargaPerolehanLangsung; }
    public BigDecimal getTransportasi() { return transportasi; }
    public BigDecimal getUpah() { return upah; }
    public String getKeterangan() { return keterangan; }
    public int getIdPenjualan() { return idPenjualan; }

    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
    public void setHargaPerolehanLangsung(BigDecimal v) { this.hargaPerolehanLangsung = v; }
    public void setTransportasi(BigDecimal v) { this.transportasi = v; }
    public void setUpah(BigDecimal v) { this.upah = v; }
    public void setKeterangan(String v) { this.keterangan = v; }
    public void setIdPenjualan(int v) { this.idPenjualan = v; }
}