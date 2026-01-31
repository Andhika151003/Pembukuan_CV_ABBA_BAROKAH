package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SetorPajak {
    private int id;
    private LocalDate tanggal_Setor;
    private String jenis_Pajak;
    private BigDecimal jumlah_Pajak;
    private String periode;
    private String bukti_Setor;

    // Constructor Lengkap
    public SetorPajak(int id, LocalDate tanggal_Setor, String jenis_Pajak, BigDecimal jumlah_Pajak, String periode, String bukti_Setor) {
        this.id = id;
        this.tanggal_Setor = tanggal_Setor;
        this.jenis_Pajak = jenis_Pajak;
        this.jumlah_Pajak = jumlah_Pajak;
        this.periode = periode;
        this.bukti_Setor = bukti_Setor;
    }

    // Constructor Tanpa ID
    public SetorPajak(LocalDate tanggal_Setor, String jenis_Pajak, BigDecimal jumlah_Pajak, String periode, String bukti_Setor) {
        this.tanggal_Setor = tanggal_Setor;
        this.jenis_Pajak = jenis_Pajak;
        this.jumlah_Pajak = jumlah_Pajak;
        this.periode = periode;
        this.bukti_Setor = bukti_Setor;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getTanggal_Setor() { return tanggal_Setor; }
    public void setTanggal_Setor(LocalDate tanggal_Setor) { this.tanggal_Setor = tanggal_Setor; }
    public String getJenis_Pajak() { return jenis_Pajak; }
    public void setJenis_Pajak(String jenis_Pajak) { this.jenis_Pajak = jenis_Pajak; }
    public BigDecimal getJumlah_Pajak() { return jumlah_Pajak; }
    public void setJumlah_Pajak(BigDecimal jumlah_Pajak) { this.jumlah_Pajak = jumlah_Pajak; }
    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }
    public String getBukti_Setor() { return bukti_Setor; }
    public void setBukti_Setor(String bukti_Setor) { this.bukti_Setor = bukti_Setor; }

    @Override
    public String toString() {
        return "SetorPajak{" +
                "id=" + id +
                ", tanggal_Setor=" + tanggal_Setor +
                ", jenis_Pajak='" + jenis_Pajak + '\'' +
                ", jumlah_Pajak=" + jumlah_Pajak +
                ", periode='" + periode + '\'' +
                ", bukti_Setor='" + bukti_Setor + '\'' +
                '}';
    }
}