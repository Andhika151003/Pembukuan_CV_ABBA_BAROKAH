package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UtangUsaha {

    private int id;
    private String noUtang;
    private LocalDate tanggalUtang;
    private BigDecimal jumlahUtang;
    private String pemberiUtang;
    private String keterangan;

    // SELECT
    public UtangUsaha(int id,
            String noUtang,
            LocalDate tanggalUtang,
            BigDecimal jumlahUtang,
            String pemberiUtang,
            String keterangan) {

        this.id = id;
        this.noUtang = noUtang;
        this.tanggalUtang = tanggalUtang;
        this.jumlahUtang = jumlahUtang;
        this.pemberiUtang = pemberiUtang;
        this.keterangan = keterangan;
    }

    // INSERT
    public UtangUsaha(String noUtang,
            LocalDate tanggalUtang,
            BigDecimal jumlahUtang,
            String pemberiUtang,
            String keterangan) {

        this(0, noUtang, tanggalUtang, jumlahUtang, pemberiUtang, keterangan);
    }

    public int getId() {
        return id;
    }

    public String getNoUtang() {
        return noUtang;
    }

    public LocalDate getTanggalUtang() {
        return tanggalUtang;
    }

    public BigDecimal getJumlahUtang() {
        return jumlahUtang;
    }

    public String getPemberiUtang() {
        return pemberiUtang;
    }

    public String getKeterangan() {
        return keterangan;
    }
}