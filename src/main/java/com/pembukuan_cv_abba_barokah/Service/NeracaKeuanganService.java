package com.pembukuan_cv_abba_barokah.Service;

import java.math.BigDecimal;

public class NeracaKeuanganService {

    private final RekapTotalService rekap = new RekapTotalService();
    private final UtangUsahaService utangUsahaService = new UtangUsahaService();
    private final PembelianInventarisService inventarisService = new PembelianInventarisService();

    /* =======================
       ASET LANCAR
       ======================= */

    public BigDecimal bankSaldo() {
        // kas = pembayaran masuk - setor pajak
        return rekap.totalPembayaranMasuk()
                .subtract(rekap.totalSetorPajak());
    }

    public BigDecimal piutangUsaha() {
        return rekap.totalPenjualan()
                .subtract(rekap.totalPembayaranMasuk());
    }

    public BigDecimal persediaanBarang() {
        // persediaan = pembelian - HPP
        return rekap.totalHPP().compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : rekap.totalHPP();
    }

    public BigDecimal totalAsetLancar() {
        return bankSaldo()
                .add(piutangUsaha())
                .add(persediaanBarang());
    }

    /* =======================
       ASET TIDAK LANCAR
       ======================= */

       public BigDecimal totalInventaris() {
        return inventarisService.getAll().stream()
                .map(i ->
                        BigDecimal.valueOf(i.getJumlah())
                                .multiply(i.getHargaSatuan())
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }    

    public BigDecimal totalAsetTidakLancar() {
        return totalInventaris();
    }

    /* =======================
       TOTAL ASET
       ======================= */

    public BigDecimal totalAset() {
        return totalAsetLancar().add(totalAsetTidakLancar());
    }

    /* =======================
       KEWAJIBAN & EKUITAS
       ======================= */

    public BigDecimal totalUtangUsaha() {
        return utangUsahaService.getAll().stream()
                .map(u -> u.getJumlahUtang())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal ekuitas() {
        return totalAset().subtract(totalUtangUsaha());
    }

    public BigDecimal jumlahKewajibanDanEkuitas() {
        return totalUtangUsaha().add(ekuitas());
    }
}