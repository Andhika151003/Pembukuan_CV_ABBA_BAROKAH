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
        PENJUALAN("Penjualan"),
        HPP("HPP"),
        GAJI("Gaji Pegawai"),
        ADMINISTRASI("Biaya Administrasi"),
        PAJAK("Pajak"),
        PEMBELIAN("Pembelian Barang"),
        PEREDARAN_BRUTO("Peredaran Bruto"),
        LABA_RUGI("Laba Rugi"),
        PEMBAYARAN("Pembayaran");

        private final String deskripsi;
        Kategori(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private LocalDate tanggal;
    private String nomorJurnal; 
    private JenisTransaksi jenisTransaksi; 
    private Kategori kategori;
    private String deskripsi; 
    private BigDecimal debit;
    private BigDecimal kredit; 
    private BigDecimal saldo;
    private int idAdministrasi;

    // Constructor Lengkap
    public JurnalPembukuan(int id, LocalDate tanggal, String nomorJurnal, 
                          JenisTransaksi jenisTransaksi, Kategori kategori, String deskripsi,
                          BigDecimal debit, BigDecimal kredit, BigDecimal saldo, int idAdministrasi) {
        this.id = id;
        this.tanggal = tanggal;
        this.nomorJurnal = nomorJurnal;
        this.jenisTransaksi = jenisTransaksi;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.debit = debit;
        this.kredit = kredit;
        this.saldo = saldo;
        this.idAdministrasi = idAdministrasi;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
    public String getNomorJurnal() { return nomorJurnal; }
    public void setNomorJurnal(String nomorJurnal) { this.nomorJurnal = nomorJurnal; }
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
    public int getIdAdministrasi() { return idAdministrasi; }
    public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

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
                ", idAdministrasi=" + idAdministrasi +
                '}';
    }
}