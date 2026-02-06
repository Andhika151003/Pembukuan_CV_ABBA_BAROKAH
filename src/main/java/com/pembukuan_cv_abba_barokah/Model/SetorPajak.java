package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SetorPajak {

    public enum JenisPajak {
        PPN, PPH21, PPH_BADAN
    }

    private int id;
    private LocalDate tanggalSetor;
    private JenisPajak jenisPajak;
    private BigDecimal jumlahPajak;
    private String periode;
    private String buktiSetor;
    private int idPenjualan;

    // SELECT
    public SetorPajak(int id, LocalDate tanggalSetor, JenisPajak jenisPajak,
                      BigDecimal jumlahPajak, String periode,
                      String buktiSetor, int idPenjualan) {
        this.id = id;
        this.tanggalSetor = tanggalSetor;
        this.jenisPajak = jenisPajak;
        this.jumlahPajak = jumlahPajak;
        this.periode = periode;
        this.buktiSetor = buktiSetor;
        this.idPenjualan = idPenjualan;
    }

    // INSERT
    public SetorPajak(LocalDate tanggalSetor, JenisPajak jenisPajak,
                      BigDecimal jumlahPajak, String periode,
                      String buktiSetor, int idPenjualan) {
        this(0, tanggalSetor, jenisPajak, jumlahPajak, periode, buktiSetor, idPenjualan);
    }

    public int getId() { return id; }
    public LocalDate getTanggalSetor() { return tanggalSetor; }
    public JenisPajak getJenisPajak() { return jenisPajak; }
    public BigDecimal getJumlahPajak() { return jumlahPajak; }
    public String getPeriode() { return periode; }
    public String getBuktiSetor() { return buktiSetor; }
    public int getIdPenjualan() { return idPenjualan; }

    public void setTanggalSetor(LocalDate v) { this.tanggalSetor = v; }
    public void setJenisPajak(JenisPajak v) { this.jenisPajak = v; }
    public void setJumlahPajak(BigDecimal v) { this.jumlahPajak = v; }
    public void setPeriode(String v) { this.periode = v; }
    public void setBuktiSetor(String v) { this.buktiSetor = v; }
}