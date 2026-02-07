package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Administrasi {

    public enum JenisAdministrasi {
        GAJI_PEGAWAI,
        LAIN_LAIN
    }

    private int id;
    private LocalDate tanggal;
    private JenisAdministrasi jenisAdministrasi;
    private BigDecimal jumlahAdministrasi;
    private String keterangan;

    // SELECT
    public Administrasi(int id,
            LocalDate tanggal,
            JenisAdministrasi jenisAdministrasi,
            BigDecimal jumlahAdministrasi,
            String keterangan) {

        this.id = id;
        this.tanggal = tanggal;
        this.jenisAdministrasi = jenisAdministrasi;
        this.jumlahAdministrasi = jumlahAdministrasi;
        this.keterangan = keterangan;
    }

    // INSERT
    public Administrasi(LocalDate tanggal,
            JenisAdministrasi jenisAdministrasi,
            BigDecimal jumlahAdministrasi,
            String keterangan) {

        this(0, tanggal, jenisAdministrasi, jumlahAdministrasi, keterangan);
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public JenisAdministrasi getJenisAdministrasi() {
        return jenisAdministrasi;
    }

    public BigDecimal getJumlahAdministrasi() {
        return jumlahAdministrasi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setTanggal(LocalDate v) {
        this.tanggal = v;
    }

    public void setJenisAdministrasi(JenisAdministrasi v) {
        this.jenisAdministrasi = v;
    }

    public void setJumlahAdministrasi(BigDecimal v) {
        this.jumlahAdministrasi = v;
    }

    public void setKeterangan(String v) {
        this.keterangan = v;
    }
}