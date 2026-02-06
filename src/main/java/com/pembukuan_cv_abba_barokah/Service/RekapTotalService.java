package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.Model.*;

import java.math.BigDecimal;

public class RekapTotalService {

    private final PenjualanService penjualanService = new PenjualanService();
    private final PembayaranService pembayaranService = new PembayaranService();
    private final SetorPajakService setorPajakService = new SetorPajakService();
    private final BiayaPemasaranService biayaPemasaranService = new BiayaPemasaranService();

    private final PembelianLangsungService pembelianLangsungService = new PembelianLangsungService();
    private final SwakelolaService swakelolaService = new SwakelolaService();

    /* =========================
       TOTAL DASAR
       ========================= */

    public BigDecimal totalPenjualan() {
        return penjualanService.getAll().stream()
                .map(Penjualan::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalPembayaranMasuk() {
        return pembayaranService.getAll().stream()
                .map(Pembayaran::getJumlahPembayaran)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalSetorPajak() {
        return setorPajakService.getAll().stream()
                .map(SetorPajak::getJumlahPajak)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalBiayaPemasaran() {
        return biayaPemasaranService.getAll().stream()
                .map(BiayaPemasaran::getJumlahPemasaran)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /* =========================
       HPP
       ========================= */

    public BigDecimal totalPembelianLangsung() {
        return pembelianLangsungService.getAll().stream()
                .map(p ->
                        p.getHargaPerolehanLangsung()
                                .add(p.getTransportasi())
                                .add(p.getUpah())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalSwakelola() {
        return swakelolaService.getAll().stream()
                .map(s ->
                        s.getBahan1()
                                .add(s.getBahan2())
                                .add(s.getBahan3())
                                .add(s.getOngkosTukangPotong())
                                .add(s.getOngkosTukangJahit())
                                .add(s.getLainLain())
                                .add(s.getTransportasi())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalHPP() {
        return totalPembelianLangsung().add(totalSwakelola());
    }
}