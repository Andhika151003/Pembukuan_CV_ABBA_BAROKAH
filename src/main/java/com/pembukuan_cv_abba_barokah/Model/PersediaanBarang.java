package com.pembukuan_cv_abba_barokah.Model;

import java.time.LocalDate;
import java.math.BigDecimal;

public class PersediaanBarang {

    // Enum untuk Jenis Transaksi
    public enum JenisTransaksi {
        MASUK("Barang Masuk"),
        KELUAR("Barang Keluar"),
        RETURMASUK("Retur Masuk"),
        RETURKELUAR("Retur Keluar");

        private final String deskripsi;
        JenisTransaksi(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private LocalDate tanggal;
    private String nama_Barang;
    private String satuan;
    private JenisTransaksi jenis_Transaksi;
    private int jumlah_Masuk;
    private int jumlah_Keluar;
    private BigDecimal saldo_Akhir;
    private BigDecimal harga_Satuan;
    private String keterangan;

    // Constructor Lengkap (Dengan ID)
    public PersediaanBarang(int id, LocalDate tanggal, String nama_Barang, String satuan, 
                            JenisTransaksi jenis_Transaksi, int jumlah_Masuk, int jumlah_Keluar, 
                            BigDecimal saldo_Akhir, BigDecimal harga_Satuan, String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.nama_Barang = nama_Barang;
        this.satuan = satuan;
        this.jenis_Transaksi = jenis_Transaksi;
        this.jumlah_Masuk = jumlah_Masuk;
        this.jumlah_Keluar = jumlah_Keluar;
        this.saldo_Akhir = saldo_Akhir;
        this.harga_Satuan = harga_Satuan;
        this.keterangan = keterangan;
    }

    // Constructor Tanpa ID (Untuk Insert Data Baru)
    public PersediaanBarang(LocalDate tanggal, String nama_Barang, String satuan, 
                            JenisTransaksi jenis_Transaksi, int jumlah_Masuk, int jumlah_Keluar, 
                            BigDecimal saldo_Akhir, BigDecimal harga_Satuan, String keterangan) {
        this.tanggal = tanggal;
        this.nama_Barang = nama_Barang;
        this.satuan = satuan;
        this.jenis_Transaksi = jenis_Transaksi;
        this.jumlah_Masuk = jumlah_Masuk;
        this.jumlah_Keluar = jumlah_Keluar;
        this.saldo_Akhir = saldo_Akhir;
        this.harga_Satuan = harga_Satuan;
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public String getNama_Barang() { return nama_Barang; }
    public void setNama_Barang(String nama_Barang) { this.nama_Barang = nama_Barang; }

    public String getSatuan() { return satuan; }
    public void setSatuan(String satuan) { this.satuan = satuan; }

    public JenisTransaksi getJenis_Transaksi() { return jenis_Transaksi; }
    public void setJenis_Transaksi(JenisTransaksi jenis_Transaksi) { this.jenis_Transaksi = jenis_Transaksi; }

    public int getJumlah_Masuk() { return jumlah_Masuk; }
    public void setJumlah_Masuk(int jumlah_Masuk) { this.jumlah_Masuk = jumlah_Masuk; }

    public int getJumlah_Keluar() { return jumlah_Keluar; }
    public void setJumlah_Keluar(int jumlah_Keluar) { this.jumlah_Keluar = jumlah_Keluar; }

    public BigDecimal getSaldo_Akhir() { return saldo_Akhir; }
    public void setSaldo_Akhir(BigDecimal saldo_Akhir) { this.saldo_Akhir = saldo_Akhir; }

    public BigDecimal getHarga_Satuan() { return harga_Satuan; }
    public void setHarga_Satuan(BigDecimal harga_Satuan) { this.harga_Satuan = harga_Satuan; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    @Override
    public String toString() {
        return "PersediaanBarang{" +
                "tanggal=" + tanggal +
                ", nama_Barang='" + nama_Barang + '\'' +
                ", jenis_Transaksi=" + jenis_Transaksi +
                ", saldo_Akhir=" + saldo_Akhir +
                '}';
    }
}