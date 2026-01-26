package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pembayaran {
    private int id;
    private int id_Transaksi;
    private LocalDate tanggal_Pembayaran;
    private BigDecimal jumlah_Pembayaran;
    private String metode_Pembayaran;
    private String keterangan;
    private int idAdministrasi;

    // Constructor Lengkap
    public Pembayaran(int id, int id_Transaksi, LocalDate tanggal_Pembayaran, 
                      BigDecimal jumlah_Pembayaran, String metode_Pembayaran, 
                      String keterangan, int idAdministrasi) {
        this.id = id;
        this.id_Transaksi = id_Transaksi;
        this.tanggal_Pembayaran = tanggal_Pembayaran;
        this.jumlah_Pembayaran = jumlah_Pembayaran;
        this.metode_Pembayaran = metode_Pembayaran;
        this.keterangan = keterangan;
        this.idAdministrasi = idAdministrasi;
    }

    // Constructor Tanpa ID
    public Pembayaran(int id_Transaksi, LocalDate tanggal_Pembayaran, 
                      BigDecimal jumlah_Pembayaran, String metode_Pembayaran, 
                      String keterangan, int idAdministrasi) {
        this.id_Transaksi = id_Transaksi;
        this.tanggal_Pembayaran = tanggal_Pembayaran;
        this.jumlah_Pembayaran = jumlah_Pembayaran;
        this.metode_Pembayaran = metode_Pembayaran;
        this.keterangan = keterangan;
        this.idAdministrasi = idAdministrasi;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getId_Transaksi() { return id_Transaksi; }
    public void setId_Transaksi(int id_Transaksi) { this.id_Transaksi = id_Transaksi; }
    public LocalDate getTanggal_Pembayaran() { return tanggal_Pembayaran; }
    public void setTanggal_Pembayaran(LocalDate tanggal_Pembayaran) { this.tanggal_Pembayaran = tanggal_Pembayaran; }
    public BigDecimal getJumlah_Pembayaran() { return jumlah_Pembayaran; }
    public void setJumlah_Pembayaran(BigDecimal jumlah_Pembayaran) { this.jumlah_Pembayaran = jumlah_Pembayaran; }
    public String getMetode_Pembayaran() { return metode_Pembayaran; }
    public void setMetode_Pembayaran(String metode_Pembayaran) { this.metode_Pembayaran = metode_Pembayaran; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public int getIdAdministrasi() { return idAdministrasi; }
    public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

    @Override
    public String toString() {
        return "Pembayaran{" +
                "id=" + id +
                ", id_Transaksi=" + id_Transaksi +
                ", tanggal_Pembayaran=" + tanggal_Pembayaran +
                ", jumlah_Pembayaran=" + jumlah_Pembayaran +
                ", metode_Pembayaran='" + metode_Pembayaran + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", idAdministrasi=" + idAdministrasi +
                '}';
    }
}