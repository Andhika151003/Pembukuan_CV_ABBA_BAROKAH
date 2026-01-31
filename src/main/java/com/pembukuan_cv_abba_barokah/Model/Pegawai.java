package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pegawai {

    public enum StatusPegawai {
        TETAP("Tetap"),
        TIDAK_TETAP("Tidak Tetap");

        private final String deskripsi;
        StatusPegawai(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    public enum StatusAktif {
        AKTIF("Aktif"),
        NON_AKTIF("Non Aktif");

        private final String deskripsi;
        StatusAktif(String deskripsi) { this.deskripsi = deskripsi; }
        @Override public String toString() { return deskripsi; }
    }

    private int id;
    private String nama;
    private String jabatan;
    private StatusPegawai status_Pegawai;
    private BigDecimal gaji_Pokok;
    private LocalDate tanggal_Masuk;
    private StatusAktif status;

    // Constructor Lengkap
    public Pegawai(int id, String nama, String jabatan, StatusPegawai status_Pegawai, 
                   BigDecimal gaji_Pokok, LocalDate tanggal_Masuk, StatusAktif status) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
        this.status_Pegawai = status_Pegawai;
        this.gaji_Pokok = gaji_Pokok;
        this.tanggal_Masuk = tanggal_Masuk;
        this.status = status;
    }

    // Constructor Tanpa ID
    public Pegawai(String nama, String jabatan, StatusPegawai status_Pegawai, 
                   BigDecimal gaji_Pokok, LocalDate tanggal_Masuk, StatusAktif status) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.status_Pegawai = status_Pegawai;
        this.gaji_Pokok = gaji_Pokok;
        this.tanggal_Masuk = tanggal_Masuk;
        this.status = status;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }
    public StatusPegawai getStatus_Pegawai() { return status_Pegawai; }
    public void setStatus_Pegawai(StatusPegawai status_Pegawai) { this.status_Pegawai = status_Pegawai; }
    public BigDecimal getGaji_Pokok() { return gaji_Pokok; }
    public void setGaji_Pokok(BigDecimal gaji_Pokok) { this.gaji_Pokok = gaji_Pokok; }
    public LocalDate getTanggal_Masuk() { return tanggal_Masuk; }
    public void setTanggal_Masuk(LocalDate tanggal_Masuk) { this.tanggal_Masuk = tanggal_Masuk; }
    public StatusAktif getStatus() { return status; }
    public void setStatus(StatusAktif status) { this.status = status; }

    @Override
    public String toString() {
        return "Pegawai{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", status_Pegawai=" + status_Pegawai +
                ", status=" + status +
                '}';
    }
}