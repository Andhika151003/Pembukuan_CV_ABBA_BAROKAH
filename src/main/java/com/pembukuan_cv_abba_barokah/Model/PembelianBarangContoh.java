package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PembelianBarangContoh {
    private int id;
    private LocalDate tanggal_Pembelian;
    private String nama_Barang;
    private String supplier;
    private int kuantitas;
    private BigDecimal harga_Satuan; 
    private BigDecimal total_Harga;  
    private int idAdministrasi;      

    // Constructor Lengkap
    public PembelianBarangContoh(int id, LocalDate tanggal_Pembelian, String nama_Barang, 
                                String supplier, int kuantitas, BigDecimal harga_Satuan, 
                                BigDecimal total_Harga, int idAdministrasi) {
        this.id = id;
        this.tanggal_Pembelian = tanggal_Pembelian;
        this.nama_Barang = nama_Barang;
        this.supplier = supplier;
        this.kuantitas = kuantitas;
        this.harga_Satuan = harga_Satuan;
        this.total_Harga = total_Harga;
        this.idAdministrasi = idAdministrasi;
    }

    // Constructor Tanpa ID
    public PembelianBarangContoh(LocalDate tanggal_Pembelian, String nama_Barang, 
                                String supplier, int kuantitas, BigDecimal harga_Satuan, 
                                BigDecimal total_Harga, int idAdministrasi) {
        this.tanggal_Pembelian = tanggal_Pembelian;
        this.nama_Barang = nama_Barang;
        this.supplier = supplier;
        this.kuantitas = kuantitas;
        this.harga_Satuan = harga_Satuan;
        this.total_Harga = total_Harga;
        this.idAdministrasi = idAdministrasi;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getTanggal_Pembelian() { return tanggal_Pembelian; }
    public void setTanggal_Pembelian(LocalDate tanggal_Pembelian) { this.tanggal_Pembelian = tanggal_Pembelian; }
    public String getNama_Barang() { return nama_Barang; }
    public void setNama_Barang(String nama_Barang) { this.nama_Barang = nama_Barang; }
    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
    public int getKuantitas() { return kuantitas; }
    public void setKuantitas(int kuantitas) { this.kuantitas = kuantitas; }
    public BigDecimal getHarga_Satuan() { return harga_Satuan; }
    public void setHarga_Satuan(BigDecimal harga_Satuan) { this.harga_Satuan = harga_Satuan; }
    public BigDecimal getTotal_Harga() { return total_Harga; }
    public void setTotal_Harga(BigDecimal total_Harga) { this.total_Harga = total_Harga; }
    public int getIdAdministrasi() { return idAdministrasi; }
    public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

    @Override
    public String toString() {
        return "PembelianBarangContoh{" +
                "id=" + id +
                ", tanggal_Pembelian=" + tanggal_Pembelian +
                ", nama_Barang='" + nama_Barang + '\'' +
                ", supplier='" + supplier + '\'' +
                ", kuantitas=" + kuantitas +
                ", harga_Satuan=" + harga_Satuan +
                ", total_Harga=" + total_Harga +
                ", idAdministrasi=" + idAdministrasi +
                '}';
    }
}