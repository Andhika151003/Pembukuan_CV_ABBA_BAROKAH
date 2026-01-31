package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaksi {

    public enum Status {
        LUNAS("Lunas"),
        BELUM_LUNAS("Belum Lunas");

        private final String deskripsi;

        Status(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        @Override
        public String toString() {
            return deskripsi;
        }
    }

    private int id;
    private LocalDate tanggal_transaksi;
    private String nomor_Faktur;
    private String nama_Barang;
    private int kuantitas;
    private BigDecimal harga_Jual;
    private BigDecimal total_Penjualan;
    private Status status_Pembayaran;
    private LocalDate tanggal_Pembayaran;
    private String keterangan;
    private int id_Penjualan;

    // Constructor Lengkap
    public Transaksi(int id, LocalDate tanggal_transaksi, String nomor_Faktur, String nama_Barang, 
                     int kuantitas, BigDecimal harga_Jual, BigDecimal total_Penjualan, 
                     Status status_Pembayaran, LocalDate tanggal_Pembayaran, String keterangan, 
                     int id_Penjualan) {
        this.id = id;
        this.tanggal_transaksi = tanggal_transaksi;
        this.nomor_Faktur = nomor_Faktur;
        this.nama_Barang = nama_Barang;
        this.kuantitas = kuantitas;
        this.harga_Jual = harga_Jual;
        this.total_Penjualan = total_Penjualan;
        this.status_Pembayaran = status_Pembayaran;
        this.tanggal_Pembayaran = tanggal_Pembayaran;
        this.keterangan = keterangan;
        this.id_Penjualan = id_Penjualan;
    }

    // Constructor Tanpa ID
    public Transaksi(LocalDate tanggal_transaksi, String nomor_Faktur, String nama_Barang, 
                     int kuantitas, BigDecimal harga_Jual, BigDecimal total_Penjualan, 
                     Status status_Pembayaran, LocalDate tanggal_Pembayaran, String keterangan, 
                     int id_Penjualan) {
        this.tanggal_transaksi = tanggal_transaksi;
        this.nomor_Faktur = nomor_Faktur;
        this.nama_Barang = nama_Barang;
        this.kuantitas = kuantitas;
        this.harga_Jual = harga_Jual;
        this.total_Penjualan = total_Penjualan;
        this.status_Pembayaran = status_Pembayaran;
        this.tanggal_Pembayaran = tanggal_Pembayaran;
        this.keterangan = keterangan;
        this.id_Penjualan = id_Penjualan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getTanggal_transaksi() { return tanggal_transaksi; }
    public void setTanggal_transaksi(LocalDate tanggal_transaksi) { this.tanggal_transaksi = tanggal_transaksi; }
    public String getNomor_Faktur() { return nomor_Faktur; }
    public void setNomor_Faktur(String nomor_Faktur) { this.nomor_Faktur = nomor_Faktur; }
    public String getNama_Barang() { return nama_Barang; }
    public void setNama_Barang(String nama_Barang) { this.nama_Barang = nama_Barang; }
    public int getKuantitas() { return kuantitas; }
    public void setKuantitas(int kuantitas) { this.kuantitas = kuantitas; }
    public BigDecimal getHarga_Jual() { return harga_Jual; }
    public void setHarga_Jual(BigDecimal harga_Jual) { this.harga_Jual = harga_Jual; }
    public BigDecimal getTotal_Penjualan() { return total_Penjualan; }
    public void setTotal_Penjualan(BigDecimal total_Penjualan) { this.total_Penjualan = total_Penjualan; }
    public Status getStatus_Pembayaran() { return status_Pembayaran; }
    public void setStatus_Pembayaran(Status status_Pembayaran) { this.status_Pembayaran = status_Pembayaran; }
    public LocalDate getTanggal_Pembayaran() { return tanggal_Pembayaran; }
    public void setTanggal_Pembayaran(LocalDate tanggal_Pembayaran) { this.tanggal_Pembayaran = tanggal_Pembayaran; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public int getid_Penjualan() { return id_Penjualan; }
    public void setid_Penjualan(int id_Penjualan) { this.id_Penjualan = id_Penjualan; }

    @Override
    public String toString() {
        return "Transaksi{" +
                "id=" + id +
                ", tanggal_transaksi=" + tanggal_transaksi +
                ", nomor_Faktur='" + nomor_Faktur + '\'' +
                ", nama_Barang='" + nama_Barang + '\'' +
                ", kuantitas=" + kuantitas +
                ", harga_Jual=" + harga_Jual +
                ", total_Penjualan=" + total_Penjualan +
                ", status_Pembayaran=" + status_Pembayaran +
                ", tanggal_Pembayaran=" + tanggal_Pembayaran +
                ", keterangan='" + keterangan + '\'' +
                ", id_Penjualan=" + id_Penjualan +
                '}';
    }
}