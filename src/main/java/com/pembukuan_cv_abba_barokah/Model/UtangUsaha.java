package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UtangUsaha {

    // Enum untuk Status Utang
    public enum StatusUtang {
        BELUM_LUNAS("Belum Lunas"),
        LUNAS("Lunas"),
        JATUH_TEMPO("Jatuh Tempo");

        private final String deskripsi;
        StatusUtang(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private String no_Utang; // Unique
    private LocalDate tanggal_Utang;
    private LocalDate tanggal_Jatuh_Tempo;
    // private int id_Supplier; // Foreign Key ke tabel supplier
    private BigDecimal jumlah_Utang;
    private BigDecimal jumlah_Dibayar;
    private BigDecimal sisa_Utang;
    private StatusUtang status_Utang;
    private String referensi_Pembelian; // No Faktur Pembelian terkait
    private String keterangan;

    // Constructor Lengkap (Dengan ID)
    public UtangUsaha(int id, String no_Utang, LocalDate tanggal_Utang, LocalDate tanggal_Jatuh_Tempo, 
                    //   int id_Supplier,
                       BigDecimal jumlah_Utang, BigDecimal jumlah_Dibayar, 
                      BigDecimal sisa_Utang, StatusUtang status_Utang, String referensi_Pembelian, 
                      String keterangan) {
        this.id = id;
        this.no_Utang = no_Utang;
        this.tanggal_Utang = tanggal_Utang;
        this.tanggal_Jatuh_Tempo = tanggal_Jatuh_Tempo;
        // this.id_Supplier = id_Supplier;
        this.jumlah_Utang = jumlah_Utang;
        this.jumlah_Dibayar = jumlah_Dibayar;
        this.sisa_Utang = sisa_Utang;
        this.status_Utang = status_Utang;
        this.referensi_Pembelian = referensi_Pembelian;
        this.keterangan = keterangan;
    }

    // Constructor Tanpa ID (Untuk Insert Data Baru)
    public UtangUsaha(String no_Utang, LocalDate tanggal_Utang, LocalDate tanggal_Jatuh_Tempo, 
                    //   int id_Supplier, 
                      BigDecimal jumlah_Utang, BigDecimal jumlah_Dibayar, 
                      BigDecimal sisa_Utang, StatusUtang status_Utang, String referensi_Pembelian, 
                      String keterangan) {
        this.no_Utang = no_Utang;
        this.tanggal_Utang = tanggal_Utang;
        this.tanggal_Jatuh_Tempo = tanggal_Jatuh_Tempo;
        // this.id_Supplier = id_Supplier;
        this.jumlah_Utang = jumlah_Utang;
        this.jumlah_Dibayar = jumlah_Dibayar;
        this.sisa_Utang = sisa_Utang;
        this.status_Utang = status_Utang;
        this.referensi_Pembelian = referensi_Pembelian;
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNo_Utang() { return no_Utang; }
    public void setNo_Utang(String no_Utang) { this.no_Utang = no_Utang; }

    public LocalDate getTanggal_Utang() { return tanggal_Utang; }
    public void setTanggal_Utang(LocalDate tanggal_Utang) { this.tanggal_Utang = tanggal_Utang; }

    public LocalDate getTanggal_Jatuh_Tempo() { return tanggal_Jatuh_Tempo; }
    public void setTanggal_Jatuh_Tempo(LocalDate tanggal_Jatuh_Tempo) { this.tanggal_Jatuh_Tempo = tanggal_Jatuh_Tempo; }

    // public int getId_Supplier() { return id_Supplier; }
    // public void setId_Supplier(int id_Supplier) { this.id_Supplier = id_Supplier; }

    public BigDecimal getJumlah_Utang() { return jumlah_Utang; }
    public void setJumlah_Utang(BigDecimal jumlah_Utang) { this.jumlah_Utang = jumlah_Utang; }

    public BigDecimal getJumlah_Dibayar() { return jumlah_Dibayar; }
    public void setJumlah_Dibayar(BigDecimal jumlah_Dibayar) { this.jumlah_Dibayar = jumlah_Dibayar; }

    public BigDecimal getSisa_Utang() { return sisa_Utang; }
    public void setSisa_Utang(BigDecimal sisa_Utang) { this.sisa_Utang = sisa_Utang; }

    public StatusUtang getStatus_Utang() { return status_Utang; }
    public void setStatus_Utang(StatusUtang status_Utang) { this.status_Utang = status_Utang; }

    public String getReferensi_Pembelian() { return referensi_Pembelian; }
    public void setReferensi_Pembelian(String referensi_Pembelian) { this.referensi_Pembelian = referensi_Pembelian; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    // Logic Method: Otomatis menghitung sisa utang
    public void hitungSisaUtang() {
        if (jumlah_Utang != null && jumlah_Dibayar != null) {
            this.sisa_Utang = jumlah_Utang.subtract(jumlah_Dibayar);
        }
    }

    @Override
    public String toString() {
        return "UtangUsaha{" +
                "no_Utang='" + no_Utang + '\'' +
                ", tanggal_Utang=" + tanggal_Utang +
                ", sisa_Utang=" + sisa_Utang +
                ", status_Utang=" + status_Utang +
                '}';
    }
}