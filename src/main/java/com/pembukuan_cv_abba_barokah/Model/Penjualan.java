package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Penjualan {

    // Enum untuk Metode Pembayaran
    public enum MetodePembayaran {
        TUNAI("Tunai"),
        KREDIT("Kredit"),
        TRANSFER("Transfer");

        private final String deskripsi;
        MetodePembayaran(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    // Enum untuk Status Pembayaran
    public enum StatusPembayaran {
        LUNAS("Lunas"),
        BELUM_LUNAS("Belum Lunas"),
        DIBATALKAN("Dibatalkan");

        private final String deskripsi;
        StatusPembayaran(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private String no_Penjualan; // Unique
    private LocalDate tanggal_Penjualan;
    private String nama_Customer;
    private String alamat_Customer;
    private BigDecimal total_Penjualan;
    private MetodePembayaran metode_Pembayaran;
    private StatusPembayaran status_Pembayaran;
    private String keterangan;

    // Constructor Lengkap (Dengan ID)
    public Penjualan(int id, String no_Penjualan, LocalDate tanggal_Penjualan, String nama_Customer, 
                     String alamat_Customer, BigDecimal total_Penjualan, 
                     MetodePembayaran metode_Pembayaran, StatusPembayaran status_Pembayaran, 
                     String keterangan) {
        this.id = id;
        this.no_Penjualan = no_Penjualan;
        this.tanggal_Penjualan = tanggal_Penjualan;
        this.nama_Customer = nama_Customer;
        this.alamat_Customer = alamat_Customer;
        this.total_Penjualan = total_Penjualan;
        this.metode_Pembayaran = metode_Pembayaran;
        this.status_Pembayaran = status_Pembayaran;
        this.keterangan = keterangan;
    }

    // Constructor Tanpa ID (Untuk Insert Data Baru)
    public Penjualan(String no_Penjualan, LocalDate tanggal_Penjualan, String nama_Customer, 
                     String alamat_Customer, BigDecimal total_Penjualan, 
                     MetodePembayaran metode_Pembayaran, StatusPembayaran status_Pembayaran, 
                     String keterangan) {
        this.no_Penjualan = no_Penjualan;
        this.tanggal_Penjualan = tanggal_Penjualan;
        this.nama_Customer = nama_Customer;
        this.alamat_Customer = alamat_Customer;
        this.total_Penjualan = total_Penjualan;
        this.metode_Pembayaran = metode_Pembayaran;
        this.status_Pembayaran = status_Pembayaran;
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNo_Penjualan() { return no_Penjualan; }
    public void setNo_Penjualan(String no_Penjualan) { this.no_Penjualan = no_Penjualan; }

    public LocalDate getTanggal_Penjualan() { return tanggal_Penjualan; }
    public void setTanggal_Penjualan(LocalDate tanggal_Penjualan) { this.tanggal_Penjualan = tanggal_Penjualan; }

    public String getNama_Customer() { return nama_Customer; }
    public void setNama_Customer(String nama_Customer) { this.nama_Customer = nama_Customer; }

    public String getAlamat_Customer() { return alamat_Customer; }
    public void setAlamat_Customer(String alamat_Customer) { this.alamat_Customer = alamat_Customer; }

    public BigDecimal getTotal_Penjualan() { return total_Penjualan; }
    public void setTotal_Penjualan(BigDecimal total_Penjualan) { this.total_Penjualan = total_Penjualan; }

    public MetodePembayaran getMetode_Pembayaran() { return metode_Pembayaran; }
    public void setMetode_Pembayaran(MetodePembayaran metode_Pembayaran) { this.metode_Pembayaran = metode_Pembayaran; }

    public StatusPembayaran getStatus_Pembayaran() { return status_Pembayaran; }
    public void setStatus_Pembayaran(StatusPembayaran status_Pembayaran) { this.status_Pembayaran = status_Pembayaran; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    @Override
    public String toString() {
        return "Penjualan{" +
                "no_Penjualan='" + no_Penjualan + '\'' +
                ", nama_Customer='" + nama_Customer + '\'' +
                ", total_Penjualan=" + total_Penjualan +
                ", status_Pembayaran=" + status_Pembayaran +
                '}';
    }
}