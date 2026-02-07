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
    private byte[] buktiSetor; // karena BLOB
    private String keterangan;

    // SELECT
    public SetorPajak(int id,
            LocalDate tanggalSetor,
            JenisPajak jenisPajak,
            BigDecimal jumlahPajak,
            byte[] buktiSetor,
            String keterangan) {
        this.id = id;
        this.tanggalSetor = tanggalSetor;
        this.jenisPajak = jenisPajak;
        this.jumlahPajak = jumlahPajak;
        this.buktiSetor = buktiSetor;
        this.keterangan = keterangan;
    }

    // INSERT
    public SetorPajak(LocalDate tanggalSetor,
            JenisPajak jenisPajak,
            BigDecimal jumlahPajak,
            byte[] buktiSetor,
            String keterangan) {
        this(0, tanggalSetor, jenisPajak, jumlahPajak, buktiSetor, keterangan);
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggalSetor() {
        return tanggalSetor;
    }

    public JenisPajak getJenisPajak() {
        return jenisPajak;
    }

    public BigDecimal getJumlahPajak() {
        return jumlahPajak;
    }

    public byte[] getBuktiSetor() {
        return buktiSetor;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setTanggalSetor(LocalDate v) {
        this.tanggalSetor = v;
    }

    public void setJenisPajak(JenisPajak v) {
        this.jenisPajak = v;
    }

    public void setJumlahPajak(BigDecimal v) {
        this.jumlahPajak = v;
    }

    public void setBuktiSetor(byte[] v) {
        this.buktiSetor = v;
    }

    public void setKeterangan(String v) {
        this.keterangan = v;
    }
}