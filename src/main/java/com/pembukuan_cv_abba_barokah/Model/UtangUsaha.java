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
    private int no_Utang;
    private LocalDate tanggal_Utang;
    private LocalDate tanggal_Jatuh_Tempo;
    private int id_Pembelian; 
    private BigDecimal jumlah_Utang;
    private BigDecimal jumlah_Dibayar;
    private BigDecimal sisa_Utang;
    private StatusUtang status_Utang;
    private String keterangan;

    // Constructor Lengkap (Dengan ID)
    public UtangUsaha(int id, int no_Utang, LocalDate tanggal_Utang, LocalDate tanggal_Jatuh_Tempo, 
                       int id_Pembelian,
                       BigDecimal jumlah_Utang, BigDecimal jumlah_Dibayar, 
                      BigDecimal sisa_Utang, StatusUtang status_Utang,
                      String keterangan) {
        this.id = id;
        this.no_Utang = no_Utang;
        this.tanggal_Utang = tanggal_Utang;
        this.tanggal_Jatuh_Tempo = tanggal_Jatuh_Tempo;
        this.id_Pembelian = id_Pembelian;
        this.jumlah_Utang = jumlah_Utang;
        this.jumlah_Dibayar = jumlah_Dibayar;
        this.sisa_Utang = sisa_Utang;
        this.status_Utang = status_Utang;
        this.keterangan = keterangan;
    }

    // Constructor Tanpa ID (Untuk Insert Data Baru)
    public UtangUsaha(int no_Utang, LocalDate tanggal_Utang, LocalDate tanggal_Jatuh_Tempo, 
                      int id_Pembelian, 
                      BigDecimal jumlah_Utang, BigDecimal jumlah_Dibayar, 
                      BigDecimal sisa_Utang, StatusUtang status_Utang, 
                      String keterangan) {
        this.no_Utang = no_Utang;
        this.tanggal_Utang = tanggal_Utang;
        this.tanggal_Jatuh_Tempo = tanggal_Jatuh_Tempo;
        this.id_Pembelian = id_Pembelian;
        this.jumlah_Utang = jumlah_Utang;
        this.jumlah_Dibayar = jumlah_Dibayar;
        this.sisa_Utang = sisa_Utang;
        this.status_Utang = status_Utang;
        this.keterangan = keterangan;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNo_Utang() { return no_Utang; }
    public void setNo_Utang(int no_Utang) { this.no_Utang = no_Utang; }

    public LocalDate getTanggal_Utang() { return tanggal_Utang; }
    public void setTanggal_Utang(LocalDate tanggal_Utang) { this.tanggal_Utang = tanggal_Utang; }

    public LocalDate getTanggal_Jatuh_Tempo() { return tanggal_Jatuh_Tempo; }
    public void setTanggal_Jatuh_Tempo(LocalDate tanggal_Jatuh_Tempo) { this.tanggal_Jatuh_Tempo = tanggal_Jatuh_Tempo; }

    public int getid_Pembelian() { return id_Pembelian; }
    public void setid_Pembelian(int id_Pembelian) { this.id_Pembelian = id_Pembelian; }

    public BigDecimal getJumlah_Utang() { return jumlah_Utang; }
    public void setJumlah_Utang(BigDecimal jumlah_Utang) { this.jumlah_Utang = jumlah_Utang; }

    public BigDecimal getJumlah_Dibayar() { return jumlah_Dibayar; }
    public void setJumlah_Dibayar(BigDecimal jumlah_Dibayar) { this.jumlah_Dibayar = jumlah_Dibayar; }

    public BigDecimal getSisa_Utang() { return sisa_Utang; }
    public void setSisa_Utang(BigDecimal sisa_Utang) { this.sisa_Utang = sisa_Utang; }

    public StatusUtang getStatus_Utang() { return status_Utang; }
    public void setStatus_Utang(StatusUtang status_Utang) { this.status_Utang = status_Utang; }

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
                ", id_Pembelian=" + id_Pembelian +
                '}';
    }
}