package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PembelianInventaris {

    public enum MetodePembayaran {
        TUNAI, TRANSFER, KREDIT
    }

    public enum StatusPembelian {
        PROSES, SELESAI
    }

    private int id;
    private int noPembelian;
    private String jenisInventaris;
    private String namaBarang;
    private int jumlah;
    private int satuan;
    private BigDecimal hargaSatuan;
    private BigDecimal ongkosKirim;
    private MetodePembayaran metodePembayaran;
    private StatusPembelian statusPembelian;
    private String keterangan;
    private LocalDate tanggalPembelian;

    public PembelianInventaris(
            int id, int noPembelian, String jenisInventaris, String namaBarang,
            int jumlah, int satuan, BigDecimal hargaSatuan, BigDecimal ongkosKirim,
            MetodePembayaran metodePembayaran, StatusPembelian statusPembelian,
            String keterangan, LocalDate tanggalPembelian) {

        this.id = id;
        this.noPembelian = noPembelian;
        this.jenisInventaris = jenisInventaris;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.hargaSatuan = hargaSatuan;
        this.ongkosKirim = ongkosKirim;
        this.metodePembayaran = metodePembayaran;
        this.statusPembelian = statusPembelian;
        this.keterangan = keterangan;
        this.tanggalPembelian = tanggalPembelian;
    }

    public PembelianInventaris(
            int noPembelian, String jenisInventaris, String namaBarang,
            int jumlah, int satuan, BigDecimal hargaSatuan, BigDecimal ongkosKirim,
            MetodePembayaran metodePembayaran, StatusPembelian statusPembelian,
            String keterangan, LocalDate tanggalPembelian) {

        this(0, noPembelian, jenisInventaris, namaBarang,
             jumlah, satuan, hargaSatuan, ongkosKirim,
             metodePembayaran, statusPembelian, keterangan, tanggalPembelian);
    }

    public int getId() { return id; }
    public int getNoPembelian() { return noPembelian; }
    public String getJenisInventaris() { return jenisInventaris; }
    public String getNamaBarang() { return namaBarang; }
    public int getJumlah() { return jumlah; }
    public int getSatuan() { return satuan; }
    public BigDecimal getHargaSatuan() { return hargaSatuan; }
    public BigDecimal getOngkosKirim() { return ongkosKirim; }
    public MetodePembayaran getMetodePembayaran() { return metodePembayaran; }
    public StatusPembelian getStatusPembelian() { return statusPembelian; }
    public String getKeterangan() { return keterangan; }
    public LocalDate getTanggalPembelian() { return tanggalPembelian; }
}