package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JurnalPembukuan {

    private final LocalDate tanggal;
    private final String keterangan;
    private final BigDecimal debit;
    private final BigDecimal kredit;

    public JurnalPembukuan(LocalDate tanggal, String keterangan,
                           BigDecimal debit, BigDecimal kredit) {
        this.tanggal = tanggal;
        this.keterangan = keterangan;
        this.debit = debit;
        this.kredit = kredit;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public BigDecimal getKredit() {
        return kredit;
    }
}