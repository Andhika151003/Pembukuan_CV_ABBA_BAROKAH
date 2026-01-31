package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pembayaran {
    private int id;
    private int id_Transaksi;
    private int id_Penjualan;
    private int id_Pembelian;
    private int id_Utang;
    private LocalDate tanggal_Pembayaran;
    private BigDecimal jumlah_Pembayaran;
    private String metode_Pembayaran;
    private String keterangan;

    // Constructor Lengkap
    public Pembayaran(int id, int id_Transaksi, int id_Penjualan, int id_Pembelian, int id_Utang, 
                      LocalDate tanggal_Pembayaran, BigDecimal jumlah_Pembayaran, 
                      String metode_Pembayaran, String keterangan) {
        this.id = id;
        this.id_Transaksi = id_Transaksi;
        this.id_Penjualan = id_Penjualan;
        this.id_Pembelian = id_Pembelian;
        this.id_Utang = id_Utang;
        this.tanggal_Pembayaran = tanggal_Pembayaran;
        this.jumlah_Pembayaran = jumlah_Pembayaran;
        this.metode_Pembayaran = metode_Pembayaran;
        this.keterangan = keterangan;
    }

    // Constructor Tanpa ID (Untuk Simpan Data Baru)
    public Pembayaran(int id_Transaksi, int id_Penjualan, int id_Pembelian, int id_Utang, 
                      LocalDate tanggal_Pembayaran, BigDecimal jumlah_Pembayaran, 
                      String metode_Pembayaran, String keterangan) {
        this.id_Transaksi = id_Transaksi;
        this.id_Penjualan = id_Penjualan;
        this.id_Pembelian = id_Pembelian;
        this.id_Utang = id_Utang;
        this.tanggal_Pembayaran = tanggal_Pembayaran;
        this.jumlah_Pembayaran = jumlah_Pembayaran;
        this.metode_Pembayaran = metode_Pembayaran;
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getId_Transaksi() { return id_Transaksi; }
    public void setId_Transaksi(int id_Transaksi) { this.id_Transaksi = id_Transaksi; }

    public int getId_Penjualan() { return id_Penjualan; }
    public void setId_Penjualan(int id_Penjualan) { this.id_Penjualan = id_Penjualan; }

    public int getId_Pembelian() { return id_Pembelian; }
    public void setId_Pembelian(int id_Pembelian) { this.id_Pembelian = id_Pembelian; }

    public int getId_Utang() { return id_Utang; }
    public void setId_Utang(int id_Utang) { this.id_Utang = id_Utang; }

    public LocalDate getTanggal_Pembayaran() { return tanggal_Pembayaran; }
    public void setTanggal_Pembayaran(LocalDate tanggal_Pembayaran) { this.tanggal_Pembayaran = tanggal_Pembayaran; }

    public BigDecimal getJumlah_Pembayaran() { return jumlah_Pembayaran; }
    public void setJumlah_Pembayaran(BigDecimal jumlah_Pembayaran) { this.jumlah_Pembayaran = jumlah_Pembayaran; }

    public String getMetode_Pembayaran() { return metode_Pembayaran; }
    public void setMetode_Pembayaran(String metode_Pembayaran) { this.metode_Pembayaran = metode_Pembayaran; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    @Override
    public String toString() {
        return "Pembayaran{" +
                "id=" + id +
                ", id_Transaksi=" + id_Transaksi +
                ", id_Penjualan=" + id_Penjualan +
                ", id_Pembelian=" + id_Pembelian +
                ", id_Utang=" + id_Utang +
                ", tanggal_Pembayaran=" + tanggal_Pembayaran +
                ", jumlah_Pembayaran=" + jumlah_Pembayaran +
                ", metode_Pembayaran='" + metode_Pembayaran + '\'' +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}