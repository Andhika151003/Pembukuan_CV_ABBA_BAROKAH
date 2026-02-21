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
   public BigDecimal totalPph11(int tahun) {
      return penjualanService.getAll().stream()
            .filter(p -> p.getTanggal().getYear() == tahun)
            .map(labaRugiService::hitungPph)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   /*
    * ===============================
    * TOTAL SETOR PAJAK
    * ===============================
    */
   public BigDecimal totalSetorPajak(int tahun) {
      return setorPajakService.getAll().stream()
              .filter(sp -> sp.getTanggalSetor().getYear() == tahun)
              .map(sp -> sp.getJumlahPajak())
              .reduce(BigDecimal.ZERO, BigDecimal::add);
  }  

   /*
    * ===============================
    * PAJAK BELUM DISETOR
    * ===============================
    */
   public BigDecimal pajakBelumDisetor(int tahun) {

      BigDecimal hasil = totalPph11(tahun)
              .subtract(totalSetorPajak(tahun));
  
      return hasil.compareTo(BigDecimal.ZERO) < 0
              ? BigDecimal.ZERO
              : hasil;
  }  
}