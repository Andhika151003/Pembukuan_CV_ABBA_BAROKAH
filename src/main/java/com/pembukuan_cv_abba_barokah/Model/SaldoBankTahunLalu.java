package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaldoBankTahunLalu {

    private Integer id;
    private LocalDate tanggal;
    private BigDecimal totalSaldo;
    private String keterangan;

    public SaldoBankTahunLalu(Integer id,
            LocalDate tanggal,
            BigDecimal totalSaldo,
            String keterangan) {
        this.id = id;
        this.tanggal = tanggal;
        this.totalSaldo = totalSaldo;
        this.keterangan = keterangan;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public BigDecimal getTotalSaldo() {
        return totalSaldo;
    }

    public String getKeterangan() {
        return keterangan;
    }
}