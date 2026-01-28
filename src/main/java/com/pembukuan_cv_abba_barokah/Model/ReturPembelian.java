package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReturPembelian {

    // Enum untuk Alasan Retur Pembelian
    public enum AlasanRetur {
        RUSAK("Barang Rusak"),
        TIDAK_SESUAI("Tidak Sesuai"),
        KELEBIHAN_KIRIM("Kelebihan Kirim"),
        LAINNYA("Lainnya");

        private final String deskripsi;
        AlasanRetur(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    // Enum untuk Status Retur Pembelian
    public enum StatusRetur {
        PENDING("Pending"),
        DISETUJUI("Disetujui"),
        DITOLAK("Ditolak"),
        SELESAI("Selesai");

        private final String deskripsi;
        StatusRetur(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private String no_Retur_Pembelian; // Unique
    private LocalDate tanggal_Retur;
    // private int id_Pembelian; // Foreign Key ke pembelian_inventaris
    // private int id_Supplier;  // Foreign Key ke supplier
    // private int id_Barang;    // Foreign Key ke barang
    private int jumlah_Retur;
    private BigDecimal nilai_Retur;
    private AlasanRetur alasan_Retur;
    private String keterangan_Retur;
    private StatusRetur status_Retur;

    // Constructor Lengkap (Dengan ID)
    public ReturPembelian(int id, String no_Retur_Pembelian, LocalDate tanggal_Retur, //int id_Pembelian, 
                            // int id_Supplier, int id_Barang, 
                            int jumlah_Retur, BigDecimal nilai_Retur, 
                            AlasanRetur alasan_Retur, String keterangan_Retur, StatusRetur status_Retur) {
        this.id = id;
        this.no_Retur_Pembelian = no_Retur_Pembelian;
        this.tanggal_Retur = tanggal_Retur;
        // this.id_Pembelian = id_Pembelian;
        // this.id_Supplier = id_Supplier;
        // this.id_Barang = id_Barang;
        this.jumlah_Retur = jumlah_Retur;
        this.nilai_Retur = nilai_Retur;
        this.alasan_Retur = alasan_Retur;
        this.keterangan_Retur = keterangan_Retur;
        this.status_Retur = status_Retur;
    }

    // Constructor Tanpa ID (Untuk Insert Data Baru)
    public ReturPembelian(String no_Retur_Pembelian, LocalDate tanggal_Retur, //int id_Pembelian, 
                            // int id_Supplier, int id_Barang, 
                            int jumlah_Retur, BigDecimal nilai_Retur, 
                            AlasanRetur alasan_Retur, String keterangan_Retur, StatusRetur status_Retur) {
        this.no_Retur_Pembelian = no_Retur_Pembelian;
        this.tanggal_Retur = tanggal_Retur;
        // this.id_Pembelian = id_Pembelian;
        // this.id_Supplier = id_Supplier;
        // this.id_Barang = id_Barang;
        this.jumlah_Retur = jumlah_Retur;
        this.nilai_Retur = nilai_Retur;
        this.alasan_Retur = alasan_Retur;
        this.keterangan_Retur = keterangan_Retur;
        this.status_Retur = status_Retur;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNo_Retur_Pembelian() { return no_Retur_Pembelian; }
    public void setNo_Retur_Pembelian(String no_Retur_Pembelian) { this.no_Retur_Pembelian = no_Retur_Pembelian; }

    public LocalDate getTanggal_Retur() { return tanggal_Retur; }
    public void setTanggal_Retur(LocalDate tanggal_Retur) { this.tanggal_Retur = tanggal_Retur; }

    // public int getId_Pembelian() { return id_Pembelian; }
    // public void setId_Pembelian(int id_Pembelian) { this.id_Pembelian = id_Pembelian; }

    // public int getId_Supplier() { return id_Supplier; }
    // public void setId_Supplier(int id_Supplier) { this.id_Supplier = id_Supplier; }

    // public int getId_Barang() { return id_Barang; }
    // public void setId_Barang(int id_Barang) { this.id_Barang = id_Barang; }

    public int getJumlah_Retur() { return jumlah_Retur; }
    public void setJumlah_Retur(int jumlah_Retur) { this.jumlah_Retur = jumlah_Retur; }

    public BigDecimal getNilai_Retur() { return nilai_Retur; }
    public void setNilai_Retur(BigDecimal nilai_Retur) { this.nilai_Retur = nilai_Retur; }

    public AlasanRetur getAlasan_Retur() { return alasan_Retur; }
    public void setAlasan_Retur(AlasanRetur alasan_Retur) { this.alasan_Retur = alasan_Retur; }

    public String getKeterangan_Retur() { return keterangan_Retur; }
    public void setKeterangan_Retur(String keterangan_Retur) { this.keterangan_Retur = keterangan_Retur; }

    public StatusRetur getStatus_Retur() { return status_Retur; }
    public void setStatus_Retur(StatusRetur status_Retur) { this.status_Retur = status_Retur; }

    @Override
    public String toString() {
        return "ReturPersediaan{" +
                "id=" + id +
                ", no_Retur_Pembelian='" + no_Retur_Pembelian + '\'' +
                ", tanggal_Retur=" + tanggal_Retur +
                ", status_Retur=" + status_Retur +
                ", nilai_Retur=" + nilai_Retur +
                '}';
    }
}