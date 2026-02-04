package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Administrasi {

    public enum JenisAdministrasi {
        GAJI_PEGAWAI, LAIN_LAIN
    }

    private int id;
    private LocalDate tanggal;
    private JenisAdministrasi jenisAdministrasi;
    private String deskripsi;
    private BigDecimal jumlahAdministrasi;
    private String keterangan;

    public Administrasi(
            int id,
            LocalDate tanggal,
            JenisAdministrasi jenisAdministrasi,
            String deskripsi,
            BigDecimal jumlahAdministrasi,
            String keterangan) {

        this.id = id;
        this.tanggal = tanggal;
        this.jenisAdministrasi = jenisAdministrasi;
        this.deskripsi = deskripsi;
        this.jumlahAdministrasi = jumlahAdministrasi;
        this.keterangan = keterangan;
    }

    public Administrasi(
            LocalDate tanggal,
            JenisAdministrasi jenisAdministrasi,
            String deskripsi,
            BigDecimal jumlahAdministrasi,
            String keterangan) {

        this(0, tanggal, jenisAdministrasi, deskripsi, jumlahAdministrasi, keterangan);
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public BigDecimal getJumlahAdministrasi() {
        return jumlahAdministrasi;
    }

    public String getKeterangan() {
        return keterangan;
    }
}