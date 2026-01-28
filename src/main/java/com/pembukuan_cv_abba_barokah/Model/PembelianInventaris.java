package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PembelianInventaris {

    public enum JenisInventaris {
        BAHAN("Bahan-bahan"),
        TUKANGPOTONG("Tukang Potong"),
        JAHIT("Jahit"),
        TRANSPORT("Transport"),
        DLL("dll");

        private final String deskripsi;
        JenisInventaris(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    public enum MetodePembayaran {
        TUNAI("Tunai"),
        KREDIT("Kredit"),
        TRANSFER("Transfer");

        private final String deskripsi;
        MetodePembayaran(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    public enum StatusPembelian {
        DRAFT("Draft"),
        DISETUJUI("Disetujui"),
        DITERIMA("Diterima"),
        DIBATALKAN("Dibatalkan");

        private final String deskripsi;
        StatusPembelian(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private int noPembelian; 
    private LocalDate tanggalPembelian;
    private JenisInventaris jenisInventaris;
    private String namaBarang;
    private int jumlah;
    private String satuan;
    private BigDecimal hargaSatuan;
    private BigDecimal ongkosKirim;
    private BigDecimal totalHarga;
    private MetodePembayaran metodePembayaran;
    private StatusPembelian status;
    private String keterangan;
    private int idAdministrasi; // Menghubungkan ke tabel Administrasi

    // Constructor Lengkap (Dengan ID)
    public PembelianInventaris(int id, int noPembelian, LocalDate tanggalPembelian, 
                               JenisInventaris jenisInventaris, String namaBarang, int jumlah, 
                               String satuan, BigDecimal hargaSatuan, BigDecimal ongkosKirim, 
                               BigDecimal totalHarga, MetodePembayaran metodePembayaran, 
                               StatusPembelian status, String keterangan, int idAdministrasi) {
        this.id = id;
        this.noPembelian = noPembelian;
        this.tanggalPembelian = tanggalPembelian;
        this.jenisInventaris = jenisInventaris;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.hargaSatuan = hargaSatuan;
        this.ongkosKirim = ongkosKirim;
        this.totalHarga = totalHarga;
        this.metodePembayaran = metodePembayaran;
        this.status = status;
        this.keterangan = keterangan;
        this.idAdministrasi = idAdministrasi;
    }

    // Constructor Tanpa ID (Untuk Input Baru)
    public PembelianInventaris(int noPembelian, LocalDate tanggalPembelian, 
                               JenisInventaris jenisInventaris, String namaBarang, int jumlah, 
                               String satuan, BigDecimal hargaSatuan, BigDecimal ongkosKirim, 
                               BigDecimal totalHarga, MetodePembayaran metodePembayaran, 
                               StatusPembelian status, String keterangan, int idAdministrasi) {
        this.noPembelian = noPembelian;
        this.tanggalPembelian = tanggalPembelian;
        this.jenisInventaris = jenisInventaris;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.hargaSatuan = hargaSatuan;
        this.ongkosKirim = ongkosKirim;
        this.totalHarga = totalHarga;
        this.metodePembayaran = metodePembayaran;
        this.status = status;
        this.keterangan = keterangan;
        this.idAdministrasi = idAdministrasi;
    }

    // Getters and Setters (Diperbaiki ke camelCase untuk menghindari error Service)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNoPembelian() { return noPembelian; }
    public void setNoPembelian(int noPembelian) { this.noPembelian = noPembelian; }

    public LocalDate getTanggalPembelian() { return tanggalPembelian; }
    public void setTanggalPembelian(LocalDate tanggalPembelian) { this.tanggalPembelian = tanggalPembelian; }

    public JenisInventaris getJenisInventaris() { return jenisInventaris; }
    public void setJenisInventaris(JenisInventaris jenisInventaris) { this.jenisInventaris = jenisInventaris; }

    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public String getSatuan() { return satuan; }
    public void setSatuan(String satuan) { this.satuan = satuan; }

    public BigDecimal getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(BigDecimal hargaSatuan) { this.hargaSatuan = hargaSatuan; }

    public BigDecimal getOngkosKirim() { return ongkosKirim; }
    public void setOngkosKirim(BigDecimal ongkosKirim) { this.ongkosKirim = ongkosKirim; }

    public BigDecimal getTotalHarga() { return totalHarga; }
    public void setTotalHarga(BigDecimal totalHarga) { this.totalHarga = totalHarga; }

    public MetodePembayaran getMetodePembayaran() { return metodePembayaran; }
    public void setMetodePembayaran(MetodePembayaran metodePembayaran) { this.metodePembayaran = metodePembayaran; }

    public StatusPembelian getStatus() { return status; }
    public void setStatus(StatusPembelian status) { this.status = status; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    public int getIdAdministrasi() { return idAdministrasi; }
    public void setIdAdministrasi(int idAdministrasi) { this.idAdministrasi = idAdministrasi; }

    @Override
    public String toString() {
        return "PembelianInventaris{" +
                "id=" + id +
                ", noPembelian=" + noPembelian +
                ", totalHarga=" + totalHarga +
                ", metodePembayaran=" + metodePembayaran +
                ", idAdministrasi=" + idAdministrasi +
                '}';
    }
}