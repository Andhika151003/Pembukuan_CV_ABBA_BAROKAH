package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.Model.*;

import java.math.BigDecimal;

public class RekapTotalService {

    private final HppService hppService = new HppService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final PembayaranService pembayaranService = new PembayaranService();
    private final SetorPajakService setorPajakService = new SetorPajakService();
    private final BiayaPemasaranService biayaPemasaranService = new BiayaPemasaranService();

    /* =========================
       TOTAL DASAR
       ========================= */

    public BigDecimal totalHpp() {
        return hppService.getAll().stream()
                .map(HargaPokokPenjualan::getTotalHarga)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

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
       PERHITUNGAN LABA
       ========================= */

    public BigDecimal labaKotor() {
        return totalPenjualan().subtract(totalHpp());
    }

    // PPh Final 11% dari laba kotor
    public BigDecimal pph11Persen() {
        BigDecimal laba = labaKotor();
        if (laba.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return laba.multiply(new BigDecimal("0.11"));
    }

    public BigDecimal pajakBelumDisetor() {
        BigDecimal sisa = pph11Persen().subtract(totalSetorPajak());
        return sisa.compareTo(BigDecimal.ZERO) < 0
                ? BigDecimal.ZERO
                : sisa;
    }

    /* =========================
       BIAYA & LABA BERSIH
       ========================= */

    public BigDecimal totalBiayaOperasional() {
        return totalBiayaPemasaran(); // siap ditambah biaya lain nanti
    }

    public BigDecimal labaBersih() {
        return labaKotor()
                .subtract(totalBiayaOperasional())
                .subtract(pph11Persen());
    }
}