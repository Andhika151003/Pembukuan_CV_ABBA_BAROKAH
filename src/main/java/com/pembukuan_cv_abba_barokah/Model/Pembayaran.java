package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pembayaran {

    /* ================= ENUM ================= */

    public enum MetodePembayaran {
        TUNAI, TRANSFER, KREDIT
    }

    /* ================= FIELD ================= */

    private int id;
    private LocalDate tanggalPembayaran;
    private BigDecimal jumlahPembayaran;
    private MetodePembayaran metodePembayaran;
    private String keterangan;

    // FK
    private int idPenjualan;

    /* ================= CONSTRUCTOR ================= */

    // Constructor lengkap (SELECT)
    public Pembayaran(
            int id,
            LocalDate tanggalPembayaran,
            BigDecimal jumlahPembayaran,
            MetodePembayaran metodePembayaran,
            String keterangan,
            int idPenjualan) {

        this.id = id;
        this.tanggalPembayaran = tanggalPembayaran;
        this.jumlahPembayaran = jumlahPembayaran;
        this.metodePembayaran = metodePembayaran;
        this.keterangan = keterangan;
        this.idPenjualan = idPenjualan;
    }

    // Constructor insert (tanpa ID)
    public Pembayaran(
            LocalDate tanggalPembayaran,
            BigDecimal jumlahPembayaran,
            MetodePembayaran metodePembayaran,
            String keterangan,
            int idPenjualan) {

        this(0,
             tanggalPembayaran,
             jumlahPembayaran,
             metodePembayaran,
             keterangan,
             idPenjualan);
    }

    /* ================= GETTER ================= */

    public int getId() { return id; }
    public LocalDate getTanggalPembayaran() { return tanggalPembayaran; }
    public BigDecimal getJumlahPembayaran() { return jumlahPembayaran; }
    public MetodePembayaran getMetodePembayaran() { return metodePembayaran; }
    public String getKeterangan() { return keterangan; }
    public int getIdPenjualan() { return idPenjualan; }

    public void setId(int id) { this.id = id; }
    public void setTanggalPembayaran(LocalDate tanggalPembayaran) { this.tanggalPembayaran = tanggalPembayaran; }
    public void setJumlahPembayaran(BigDecimal jumlahPembayaran) { this.jumlahPembayaran = jumlahPembayaran; }
    public void setMetodePembayaran(MetodePembayaran metodePembayaran) { this.metodePembayaran = metodePembayaran; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public void setIdPenjualan(int idPenjualan) { this.idPenjualan = idPenjualan; }
}