package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PersediaanBarang {

    public enum JenisTransaksi {
        MASUK, KELUAR, ADJUSTMENT
    }

    private int id;
    private LocalDate tanggal;
    private String namaBarang;
    private String satuan;
    private JenisTransaksi jenisTransaksi;
    private int jumlah;
    private BigDecimal hargaSatuan;
    private String keterangan;

    // Constructor lengkap (SELECT)
    public PersediaanBarang(int id, LocalDate tanggal, String namaBarang, String satuan,
                            JenisTransaksi jenisTransaksi, int jumlah,
                            BigDecimal hargaSatuan, String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.namaBarang = namaBarang;
        this.satuan = satuan;
        this.jenisTransaksi = jenisTransaksi;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.keterangan = keterangan;
    }

    // Constructor insert
    public PersediaanBarang(LocalDate tanggal, String namaBarang, String satuan,
                            JenisTransaksi jenisTransaksi, int jumlah,
                            BigDecimal hargaSatuan, String keterangan) {
        this(0, tanggal, namaBarang, satuan, jenisTransaksi, jumlah, hargaSatuan, keterangan);
    }

    // Getter
    public int getId() { return id; }
    public LocalDate getTanggal() { return tanggal; }
    public String getNamaBarang() { return namaBarang; }
    public String getSatuan() { return satuan; }
    public JenisTransaksi getJenisTransaksi() { return jenisTransaksi; }
    public int getJumlah() { return jumlah; }
    public BigDecimal getHargaSatuan() { return hargaSatuan; }
    public String getKeterangan() { return keterangan; }
}