package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SetorPajak {

    public enum JenisPajak {
        PPN, PPH21, PPH23, PPH_FINAL
    }

    private int id;
    private LocalDate tanggalSetor;
    private JenisPajak jenisPajak;
    private BigDecimal jumlahPajak;
    private String periode;
    private String buktiSetor;

    // FK (opsional)
    private int idPenjualan;

    // Constructor full (SELECT)
    public SetorPajak(
            int id,
            LocalDate tanggalSetor,
            JenisPajak jenisPajak,
            BigDecimal jumlahPajak,
            String periode,
            String buktiSetor,
            int idPenjualan) {

        this.id = id;
        this.tanggalSetor = tanggalSetor;
        this.jenisPajak = jenisPajak;
        this.jumlahPajak = jumlahPajak;
        this.periode = periode;
        this.buktiSetor = buktiSetor;
        this.idPenjualan = idPenjualan;
    }

    // Constructor insert
    public SetorPajak(
            LocalDate tanggalSetor,
            JenisPajak jenisPajak,
            BigDecimal jumlahPajak,
            String periode,
            String buktiSetor,
            int idPenjualan) {

        this(0, tanggalSetor, jenisPajak,
             jumlahPajak, periode, buktiSetor, idPenjualan);
    }

    // GETTER
    public int getId() { return id; }
    public LocalDate getTanggalSetor() { return tanggalSetor; }
    public JenisPajak getJenisPajak() { return jenisPajak; }
    public BigDecimal getJumlahPajak() { return jumlahPajak; }
    public String getPeriode() { return periode; }
    public String getBuktiSetor() { return buktiSetor; }
    public int getIdPenjualan() { return idPenjualan; }
}