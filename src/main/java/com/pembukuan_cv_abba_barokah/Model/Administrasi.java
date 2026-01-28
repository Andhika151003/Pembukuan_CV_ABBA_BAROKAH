package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Administrasi {

    // Enum untuk dropdown jenis administrasi
    public enum TipeAdministrasi {
        PENJUALAN("Penjualan"),
        HPP("HPP"),
        GAJI("Gaji Pegawai"),
        ADMINISTRASI("Biaya Administrasi"),
        PAJAK("Pajak"),
        PEMBELIAN("Pembelian Barang"),
        PEREDARAN_BRUTO("Peredaran Bruto"),
        LABA_RUGI("Laba Rugi"),
        PEMBAYARAN("Pembayaran"),
        TRANSAKSI("Transaksi");

        private final String deskripsi;
        TipeAdministrasi(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private LocalDate tanggal;
    private TipeAdministrasi jenisAdministrasi;
    private String deskripsi;
    private BigDecimal jumlah;
    private String keterangan;

    // Constructor Lengkap
    public Administrasi(int id, LocalDate tanggal, TipeAdministrasi jenisAdministrasi, String deskripsi, BigDecimal jumlah, String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.jenisAdministrasi = jenisAdministrasi;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    // Constructor Tanpa ID
    public Administrasi(LocalDate tanggal, TipeAdministrasi jenisAdministrasi, String deskripsi, BigDecimal jumlah, String keterangan) {
        this.tanggal = tanggal;
        this.jenisAdministrasi = jenisAdministrasi;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
    public TipeAdministrasi getJenisAdministrasi() { return jenisAdministrasi; }
    public void setJenisAdministrasi(TipeAdministrasi jenisAdministrasi) { this.jenisAdministrasi = jenisAdministrasi; }
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public BigDecimal getJumlah() { return jumlah; }
    public void setJumlah(BigDecimal jumlah) { this.jumlah = jumlah; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    @Override
    public String toString() {
        return "Administrasi{" +
                "id=" + id +
                ", tanggal=" + tanggal +
                ", jenisAdministrasi='" + jenisAdministrasi + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", jumlah=" + jumlah +
                ", keterangan='" + keterangan + '\'' +
                '}';
    }
}