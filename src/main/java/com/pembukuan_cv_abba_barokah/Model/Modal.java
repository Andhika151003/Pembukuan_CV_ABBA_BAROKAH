package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Modal {

    public enum JenisModal {
        MODAL_AWAL,
        TAMBAHAN_MODAL,
        PRIVE
    }

    private int id;
    private LocalDate tanggal;
    private JenisModal jenisModal;
    private BigDecimal jumlah;
    private String namaPemilik;
    private String keterangan;

    // Constructor dengan ID
    public Modal(int id, LocalDate tanggal, JenisModal jenisModal,
                 BigDecimal jumlah, String namaPemilik, String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.jenisModal = jenisModal;
        this.jumlah = jumlah;
        this.namaPemilik = namaPemilik;
        this.keterangan = keterangan;
    }

    // Constructor tanpa ID (insert)
    public Modal(LocalDate tanggal, JenisModal jenisModal,
                 BigDecimal jumlah, String namaPemilik, String keterangan) {
        this(0, tanggal, jenisModal, jumlah, namaPemilik, keterangan);
    }

    // GETTER
    public int getId() { return id; }
    public LocalDate getTanggal() { return tanggal; }
    public JenisModal getJenisModal() { return jenisModal; }
    public BigDecimal getJumlah() { return jumlah; }
    public String getNamaPemilik() { return namaPemilik; }
    public String getKeterangan() { return keterangan; }

    // SETTER (untuk update)
    public void setId(int id) { this.id = id; }
}