package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReturPenjualan {

    // Enum untuk Alasan Retur
    public enum AlasanRetur {
        RUSAK("Barang Rusak"),
        TIDAK_SESUAI("Tidak Sesuai"),
        KELEBIHAN_KIRIM("Kelebihan Kirim"),
        LAINNYA("Lainnya");

        private final String deskripsi;
        AlasanRetur(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    // Enum untuk Status Retur
    public enum StatusRetur {
        PENDING("Pending"),
        DISETUJUI("Disetujui"),
        DITOLAK("Ditolak"),
        SELESAI("Selesai");

        private final String deskripsi;
        StatusRetur(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    // Enum untuk Jenis Pengembalian
    public enum JenisPengembalian {
        TUNAI("Tunai"),
        GANTI_BARANG("Ganti Barang"),
        POTONG_PIUTANG("Potong Piutang");

        private final String deskripsi;
        JenisPengembalian(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private int no_Retur;
    private LocalDate tanggal_Retur;
    private int id_Penjualan;
    private int id_Transaksi;
    private int jumlah_Retur;
    private BigDecimal nilai_Retur;
    private AlasanRetur alasan_Retur;
    private String keterangan_Retur;
    private StatusRetur status_Retur;
    private JenisPengembalian jenis_Pengembalian;
    private LocalDate tanggal_Pengembalian;

    // Constructor Lengkap (Dengan ID)
    public ReturPenjualan(int id, int no_Retur, LocalDate tanggal_Retur, int id_Penjualan, int id_Transaksi, 
                          int jumlah_Retur, BigDecimal nilai_Retur, 
                          AlasanRetur alasan_Retur, String keterangan_Retur, 
                          StatusRetur status_Retur, JenisPengembalian jenis_Pengembalian, 
                          LocalDate tanggal_Pengembalian) {
        this.id = id;
        this.no_Retur = no_Retur;
        this.tanggal_Retur = tanggal_Retur;
        this.id_Penjualan = id_Penjualan;
        this.id_Transaksi = id_Transaksi;
        this.jumlah_Retur = jumlah_Retur;
        this.nilai_Retur = nilai_Retur;
        this.alasan_Retur = alasan_Retur;
        this.keterangan_Retur = keterangan_Retur;
        this.status_Retur = status_Retur;
        this.jenis_Pengembalian = jenis_Pengembalian;
        this.tanggal_Pengembalian = tanggal_Pengembalian;
    }

    // Constructor Tanpa ID (Untuk Insert Data Baru)
    public ReturPenjualan(int no_Retur, LocalDate tanggal_Retur, int id_Penjualan,int id_Transaksi,  
                          int jumlah_Retur, BigDecimal nilai_Retur, 
                          AlasanRetur alasan_Retur, String keterangan_Retur, 
                          StatusRetur status_Retur, JenisPengembalian jenis_Pengembalian, 
                          LocalDate tanggal_Pengembalian) {
        this.no_Retur = no_Retur;
        this.tanggal_Retur = tanggal_Retur;
        this.id_Penjualan = id_Penjualan;
        this.id_Transaksi = id_Transaksi;
        this.jumlah_Retur = jumlah_Retur;
        this.nilai_Retur = nilai_Retur;
        this.alasan_Retur = alasan_Retur;
        this.keterangan_Retur = keterangan_Retur;
        this.status_Retur = status_Retur;
        this.jenis_Pengembalian = jenis_Pengembalian;
        this.tanggal_Pengembalian = tanggal_Pengembalian;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNo_Retur() { return no_Retur; }
    public void setNo_Retur(int no_Retur) { this.no_Retur = no_Retur; }

    public LocalDate getTanggal_Retur() { return tanggal_Retur; }
    public void setTanggal_Retur(LocalDate tanggal_Retur) { this.tanggal_Retur = tanggal_Retur; }

    public int getId_Penjualan() { return id_Penjualan; }
    public void setId_Penjualan(int id_Penjualan) { this.id_Penjualan = id_Penjualan; }

    public int getid_Transaksi() { return id_Transaksi; }
    public void setid_Transaksi(int id_Transaksi) { this.id_Transaksi = id_Transaksi; }

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

    public JenisPengembalian getJenis_Pengembalian() { return jenis_Pengembalian; }
    public void setJenis_Pengembalian(JenisPengembalian jenis_Pengembalian) { this.jenis_Pengembalian = jenis_Pengembalian; }

    public LocalDate getTanggal_Pengembalian() { return tanggal_Pengembalian; }
    public void setTanggal_Pengembalian(LocalDate tanggal_Pengembalian) { this.tanggal_Pengembalian = tanggal_Pengembalian; }

    @Override
    public String toString() {
        return "ReturPenjualan{" +
                "id=" + id +
                ", no_Retur='" + no_Retur + '\'' +
                ", tanggal_Retur=" + tanggal_Retur +
                ", id_Penjualan=" + id_Penjualan +
                ", id_Transaksi=" + id_Transaksi +
                ", jumlah_Retur=" + jumlah_Retur +
                ", nilai_Retur=" + nilai_Retur +
                ", status_Retur=" + status_Retur +
                '}';
    }
}