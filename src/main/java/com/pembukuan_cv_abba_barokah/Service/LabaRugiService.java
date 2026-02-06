package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.Model.*;

import java.math.BigDecimal;
import java.util.Optional;

public class LabaRugiService {

    private final PenjualanService penjualanService = new PenjualanService();
    private final PembelianLangsungService pembelianLangsungService = new PembelianLangsungService();
    private final SwakelolaService swakelolaService = new SwakelolaService();

    /* ===============================
       TOTAL HPP PER PENJUALAN
       =============================== */
    public BigDecimal totalHppByPenjualan(int idPenjualan) {

        BigDecimal total = BigDecimal.ZERO;

        // Pembelian Langsung (0 atau 1)
        Optional<PembelianLangsung> pl = pembelianLangsungService.getAll().stream()
                .filter(p -> p.getIdPenjualan() == idPenjualan)
                .findFirst();

        if (pl.isPresent()) {
            PembelianLangsung p = pl.get();
            total = total
                    .add(p.getHargaPerolehanLangsung())
                    .add(p.getTransportasi())
                    .add(p.getUpah());
        }

        // Swakelola (0 atau 1)
        Optional<Swakelola> sw = swakelolaService.getAll().stream()
                .filter(s -> s.getIdPenjualan() == idPenjualan)
                .findFirst();

        if (sw.isPresent()) {
            Swakelola s = sw.get();
            total = total
                    .add(s.getBahan1())
                    .add(s.getBahan2())
                    .add(s.getBahan3())
                    .add(s.getOngkosTukangPotong())
                    .add(s.getOngkosTukangJahit())
                    .add(s.getLainLain())
                    .add(s.getTransportasi());
        }

        return total;
    }

    /* ===============================
       LABA / RUGI
       =============================== */
    public BigDecimal labaRugi(Penjualan penjualan) {
        return penjualan.getTotal()
                .subtract(totalHppByPenjualan(penjualan.getId()));
    }
}