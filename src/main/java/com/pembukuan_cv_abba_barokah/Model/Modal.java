package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Modal {
    public enum JenisModal {
        MODAL_AWAL("Modal Awal"),
        PENAMBAHAN("Penambahan Modal"),
        PRIVE("Pengambilan Prive"),
        LABA_DITAHAN("Laba Ditahan");

        private final String deskripsi;
        JenisModal(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private LocalDate tanggal;
    private JenisModal jenis_Modal;
    private BigDecimal jumlah;
    private String nama_Pemilik;
    private String keterangan;
    private BigDecimal saldo_Modal;

    // Constructor Lengkap (Dengan ID)
    public Modal(int id, LocalDate tanggal, JenisModal jenis_Modal, BigDecimal jumlah, 
                 String nama_Pemilik, String keterangan, BigDecimal saldo_Modal) {
        this.id = id;
        this.tanggal = tanggal;
        this.jenis_Modal = jenis_Modal;
        this.jumlah = jumlah;
        this.nama_Pemilik = nama_Pemilik;
        this.keterangan = keterangan;
        this.saldo_Modal = saldo_Modal;
    }

    // Constructor Tanpa ID (Untuk Input Baru)
    public Modal(LocalDate tanggal, JenisModal jenis_Modal, BigDecimal jumlah, 
                 String nama_Pemilik, String keterangan, BigDecimal saldo_Modal) {
        this.tanggal = tanggal;
        this.jenis_Modal = jenis_Modal;
        this.jumlah = jumlah;
        this.nama_Pemilik = nama_Pemilik;
        this.keterangan = keterangan;
        this.saldo_Modal = saldo_Modal;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public JenisModal getJenis_Modal() { return jenis_Modal; }
    public void setJenis_Modal(JenisModal jenis_Modal) { this.jenis_Modal = jenis_Modal; }

    public BigDecimal getJumlah() { return jumlah; }
    public void setJumlah(BigDecimal jumlah) { this.jumlah = jumlah; }

    public String getNama_Pemilik() { return nama_Pemilik; }
    public void setNama_Pemilik(String nama_Pemilik) { this.nama_Pemilik = nama_Pemilik; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    public BigDecimal getSaldo_Modal() { return saldo_Modal; }
    public void setSaldo_Modal(BigDecimal saldo_Modal) { this.saldo_Modal = saldo_Modal; }

    @Override
    public String toString() {
        return "Modal{" +
                "id=" + id +
                ", tanggal=" + tanggal +
                ", jenis_Modal=" + jenis_Modal +
                ", jumlah=" + jumlah +
                ", nama_Pemilik='" + nama_Pemilik + '\'' +
                ", saldo_Modal=" + saldo_Modal +
                '}';
    }
}