package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HargaPokokPenjualan {
    private int id;
    private LocalDate tanggal;
    private String jenisProduk;
    private String kategori;
    private String namaItem;
    private int kuantitas;
    private BigDecimal hargaSatuan;
    private BigDecimal totalHarga;
    private String keterangan;
    private int idAdministrasi;

    // Constructor Lengkap
    public HargaPokokPenjualan(int id, LocalDate tanggal, String jenisProduk, String kategori, 
                               String namaItem, int kuantitas, BigDecimal hargaSatuan, 
                               BigDecimal totalHarga, String keterangan, int idAdministrasi) {
        this.id = id;
        this.tanggal = tanggal;
        this.jenisProduk = jenisProduk;
        this.kategori = kategori;
        this.namaItem = namaItem;
        this.kuantitas = kuantitas;
        this.hargaSatuan = hargaSatuan;
        this.totalHarga = totalHarga;
        this.keterangan = keterangan;
        this.idAdministrasi = idAdministrasi;
    }

    // Constructor Tanpa ID
    public HargaPokokPenjualan(LocalDate tanggal, String jenisProduk, String kategori, 
                               String namaItem, int kuantitas, BigDecimal hargaSatuan, 
                               BigDecimal totalHarga, String keterangan, int idAdministrasi) {
        this.tanggal = tanggal;
        this.jenisProduk = jenisProduk;
        this.kategori = kategori;
        this.namaItem = namaItem;
        this.kuantitas = kuantitas;
        this.hargaSatuan = hargaSatuan;
        this.totalHarga = totalHarga;
        this.keterangan = keterangan;
        this.idAdministrasi = idAdministrasi;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
    public String getJenisProduk() { return jenisProduk; }
    public void setJenisProduk(String jenisProduk) { this.jenisProduk = jenisProduk; }
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public String getNamaItem() { return namaItem; }
    public void setNamaItem(String namaItem) { this.namaItem = namaItem; }
    public int getKuantitas() { return kuantitas; }
    public void setKuantitas(int kuantitas) { this.kuantitas = kuantitas; }
    public BigDecimal getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(BigDecimal hargaSatuan) { this.hargaSatuan = hargaSatuan; }
    public BigDecimal getTotalHarga() { return totalHarga; }
    public void setTotalHarga(BigDecimal totalHarga) { this.totalHarga = totalHarga; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public int getIdAdministrasi() { return idAdministrasi; }
    public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

    @Override
    public String toString() {
        return "HargaPokokPenjualan{" +
                "id=" + id +
                ", tanggal=" + tanggal +
                ", jenisProduk='" + jenisProduk + '\'' +
                ", kategori='" + kategori + '\'' +
                ", namaItem='" + namaItem + '\'' +
                ", kuantitas=" + kuantitas +
                ", hargaSatuan=" + hargaSatuan +
                ", totalHarga=" + totalHarga +
                ", keterangan='" + keterangan + '\'' +
                ", idAdministrasi=" + idAdministrasi +
                '}';
    }
}