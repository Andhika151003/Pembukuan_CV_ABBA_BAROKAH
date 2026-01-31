package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.Model.*;
import java.math.BigDecimal;

public class RekapTotalService {

    private final HppService hppService;
    private final PenjualanService penjualanService;
    private final PembayaranService pembayaranService;
    private final SetorPajakService setorPajakService;
    private final BiayaPemasaranService biayaPemasaranService;

    public RekapTotalService() {
        this.hppService = new HppService();
        this.penjualanService = new PenjualanService();
        this.pembayaranService = new PembayaranService();
        this.setorPajakService = new SetorPajakService();
        this.biayaPemasaranService = new BiayaPemasaranService();
    }

    /* =========================
       TOTAL PER SUB MENU
       ========================= */

    public BigDecimal getTotalHpp() {
        return hppService.getAllHpp().stream()
                .map(HargaPokokPenjualan::getTotalHarga)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPenjualan() {
        return penjualanService.getAll().stream()
                .map(Penjualan::getTotal_Penjualan)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPembayaranMasuk() {
        return pembayaranService.getAllPembayaran().stream()
                .map(Pembayaran::getJumlah_Pembayaran)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalSetorPajak() {
        return setorPajakService.getAllPajak().stream()
                .map(SetorPajak::getJumlah_Pajak)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalBiayaPemasaran() {
        return biayaPemasaranService.getAll().stream()
                .map(b -> new BigDecimal(b.getJumlah()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /* =========================
       RINGKASAN KEUANGAN
       ========================= */

    public BigDecimal getTotalBiaya() {
        return getTotalHpp()
                .add(getTotalBiayaPemasaran())
                .add(getTotalSetorPajak());
    }

    public BigDecimal getLabaKotor() {
        return getTotalPenjualan().subtract(getTotalHpp());
    }

    /* =========================
       PAJAK
       ========================= */

    // PPH 11% dari Laba Kotor
    public BigDecimal getPph11Persen() {
        BigDecimal labaKotor = getLabaKotor();

        if (labaKotor.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        return labaKotor.multiply(new BigDecimal("0.11"));
    }

    // Pajak Belum Disetor = PPH 11% - Setor Pajak
    public BigDecimal getPajakBelumDisetor() {
        BigDecimal sisa = getPph11Persen().subtract(getTotalSetorPajak());

        return sisa.compareTo(BigDecimal.ZERO) < 0
                ? BigDecimal.ZERO
                : sisa;
    }

    public BigDecimal getLabaBersih() {
        return getTotalPenjualan().subtract(getTotalBiaya());
    }
}