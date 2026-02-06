package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PphService {

    private static final BigDecimal TARIF_PPH = new BigDecimal("0.11");

    private final LabaRugiService labaRugiService = new LabaRugiService();

    /* ==========================
       HITUNG PPH 11%
       ========================== */
    public BigDecimal hitungPph(Penjualan penjualan) {

        BigDecimal laba = labaRugiService.labaRugi(penjualan);

        // jika rugi, PPh = 0
        if (laba.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        return laba.multiply(TARIF_PPH)
                   .setScale(0, RoundingMode.HALF_UP);
    }
}