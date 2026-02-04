package com.pembukuan_cv_abba_barokah.Model;

import java.math.BigDecimal;

public class NeracaKeuangan {

    private BigDecimal kasBank;
    private BigDecimal piutangUsaha;
    private BigDecimal persediaanBarang;
    private BigDecimal asetTidakLancar;
    private BigDecimal totalUtang;
    private BigDecimal modalDisetor;

    // ===== getter & setter =====
    public BigDecimal getKasBank() { return kasBank; }
    public void setKasBank(BigDecimal kasBank) { this.kasBank = kasBank; }

    public BigDecimal getPiutangUsaha() { return piutangUsaha; }
    public void setPiutangUsaha(BigDecimal piutangUsaha) { this.piutangUsaha = piutangUsaha; }

    public BigDecimal getPersediaanBarang() { return persediaanBarang; }
    public void setPersediaanBarang(BigDecimal persediaanBarang) { this.persediaanBarang = persediaanBarang; }

    public BigDecimal getAsetTidakLancar() { return asetTidakLancar; }
    public void setAsetTidakLancar(BigDecimal asetTidakLancar) { this.asetTidakLancar = asetTidakLancar; }

    public BigDecimal getTotalUtang() { return totalUtang; }
    public void setTotalUtang(BigDecimal totalUtang) { this.totalUtang = totalUtang; }

    public BigDecimal getModalDisetor() { return modalDisetor; }
    public void setModalDisetor(BigDecimal modalDisetor) { this.modalDisetor = modalDisetor; }
}