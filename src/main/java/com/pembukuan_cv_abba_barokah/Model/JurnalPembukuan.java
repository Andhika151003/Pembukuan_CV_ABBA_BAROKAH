package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JurnalPembukuan {

    public enum JenisTransaksi {
        PEMASUKAN("Pemasukan"),
        PENGELUARAN("Pengeluaran");

        private final String deskripsi;
        JenisTransaksi(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    public enum Kategori {
        GAJI("Gaji Pegawai"),
        PEMBELIAN("Pembelian Barang"),
        TRANSAKSI("Transaksi"),
        PEMBAYARAN("Pembayaran");

        private final String deskripsi;
        Kategori(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private LocalDate tanggal;
    private int nomorJurnal; 
    private JenisTransaksi jenisTransaksi; 
    private Kategori kategori;
    private String deskripsi; 
    private BigDecimal debit;
    private BigDecimal kredit; 
    private BigDecimal saldo;
    private int id_Transaksi;
    private int id_Pembayaran;
    private int id_Pembelian;
    private int id_Gaji;


    // Constructor Lengkap
    public JurnalPembukuan(int id, LocalDate tanggal, int nomorJurnal, 
                          JenisTransaksi jenisTransaksi, Kategori kategori, String deskripsi,
                          BigDecimal debit, BigDecimal kredit, BigDecimal saldo, int id_Transaksi, int id_Pembayaran, int id_Pembelian,
                          int id_Gaji) {
        this.id = id;
        this.tanggal = tanggal;
        this.nomorJurnal = nomorJurnal;
        this.jenisTransaksi = jenisTransaksi;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.debit = debit;
        this.kredit = kredit;
        this.saldo = saldo;
        this.id_Transaksi = id_Transaksi;
        this.id_Pembayaran = id_Pembayaran;
        this.id_Pembelian = id_Pembelian;
        this.id_Gaji = id_Gaji;
        
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
    public int getNomorJurnal() { return nomorJurnal; }
    public void setNomorJurnal(int nomorJurnal) { this.nomorJurnal = nomorJurnal; }
    public JenisTransaksi getJenisTransaksi() { return jenisTransaksi; }
    public void setJenisTransaksi(JenisTransaksi jenisTransaksi) { this.jenisTransaksi = jenisTransaksi; }
    public Kategori getKategori() { return kategori; }
    public void setKategori(Kategori kategori) { this.kategori = kategori; }
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public BigDecimal getDebit() { return debit; }
    public void setDebit(BigDecimal debit) { this.debit = debit; }
    public BigDecimal getKredit() { return kredit; }
    public void setKredit(BigDecimal kredit) { this.kredit = kredit; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public int getid_Transaksi() { return id_Transaksi; }
    public void setid_Transaksi(int id_Transaksi) { this.id_Transaksi = id_Transaksi; }
    public int getid_Pembayaran() { return id_Pembayaran; }
    public void setid_Pembayaran(int id_Pembayaran) { this.id_Pembayaran = id_Pembayaran; }
    public int getid_Pembelian() { return id_Pembelian; }
    public void setid_Pembelian(int id_Pembelian) { this.id_Pembelian= id_Pembelian; }
    public int getid_Gaji() { return id_Gaji; }
    public void setid_Gaji(int id_Gaji) { this.id_Gaji = id_Gaji; }

    @Override
    public String toString() {
        return "JurnalPembukuan{" +
                "id=" + id +
                ", tanggal=" + tanggal +
                ", nomorJurnal='" + nomorJurnal + '\'' +
                ", jenisTransaksi=" + jenisTransaksi +
                ", kategori='" + kategori + '\'' +
                ", debit=" + debit +
                ", kredit=" + kredit +
                ", id_Transaksi=" + id_Transaksi+
                ", id_Pembayaran=" + id_Pembayaran+
                ", id_Pembelian=" + id_Pembelian+
                ", id_Gaji=" + id_Gaji+
                '}';
    }
}