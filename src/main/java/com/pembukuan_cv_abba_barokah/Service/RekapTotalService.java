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

    /*
     * =========================
     * TOTAL DASAR
     * =========================
     */

    public BigDecimal totalPenjualan(int tahun) {
        return penjualanService.getAll().stream()
                .filter(p -> p.getTanggal().getYear() == tahun)
                .map(Penjualan::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalPembayaranMasuk(int tahun) {
        return pembayaranService.getAll().stream()
                .filter(p -> p.getTanggalPembayaran().getYear() == tahun)
                .map(Pembayaran::getJumlahPembayaran)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }    

    public BigDecimal totalSetorPajak(int tahun) {
        return setorPajakService.getAll().stream()
                .filter(p -> p.getTanggalSetor().getYear() == tahun)
                .map(SetorPajak::getJumlahPajak)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }    

    public BigDecimal totalBiayaPemasaran(int tahun) {
        return biayaPemasaranService.getAll().stream()
                .filter(p -> p.getTanggal().getYear() == tahun)
                .map(BiayaPemasaran::getJumlahPemasaran)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }    

    public BigDecimal totalPembelianLangsung(int tahun) {
        return pembelianLangsungService.getAll().stream()
                .filter(p -> p.getTanggal().getYear() == tahun)
                .map(p -> p.getHargaPerolehanLangsung()
                        .add(p.getTransportasi())
                        .add(p.getUpah()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }    

    public BigDecimal totalSwakelola(int tahun) {
        return swakelolaService.getAll().stream()
                .filter(s -> s.getTanggal().getYear() == tahun)
                .map(s -> s.getBahan1()
                        .add(s.getBahan2())
                        .add(s.getBahan3())
                        .add(s.getOngkosTukangPotong())
                        .add(s.getOngkosTukangJahit())
                        .add(s.getLainLain())
                        .add(s.getTransportasi()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }    

    public BigDecimal totalHPP(int tahun) {
        return totalPembelianLangsung(tahun)
                .add(totalSwakelola(tahun));
    }    
}