package com.pembukuan_cv_abba_barokah.Service;

import java.math.BigDecimal;

public class PajakBelumDisetorService {

   private final LabaRugiService labaRugiService = new LabaRugiService();
   private final PenjualanService penjualanService = new PenjualanService();
   private final SetorPajakService setorPajakService = new SetorPajakService();

   /*
    * ===============================
    * TOTAL PPH 11%
    * ===============================
    */
   public BigDecimal totalPph11() {
      return penjualanService.getAll().stream()
            .map(labaRugiService::hitungPph)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   /*
    * ===============================
    * TOTAL SETOR PAJAK
    * ===============================
    */
   public BigDecimal totalSetorPajak() {
      return setorPajakService.getAll().stream()
            .map(sp -> sp.getJumlahPajak())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   /*
    * ===============================
    * PAJAK BELUM DISETOR
    * ===============================
    */
   public BigDecimal pajakBelumDisetor() {

      BigDecimal hasil = totalPph11().subtract(totalSetorPajak());

      return hasil.compareTo(BigDecimal.ZERO) < 0
            ? BigDecimal.ZERO
            : hasil;
   }
}