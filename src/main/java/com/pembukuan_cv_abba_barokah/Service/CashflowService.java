package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.CashflowDao;
import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.Cashflow;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class CashflowService {
    private final CashflowDao cashflowDao;
    private final JurnalPembukuanDao jurnalDao;

    public CashflowService() {
        this.cashflowDao = new CashflowDao();
        this.jurnalDao = new JurnalPembukuanDao();
    }

    public List<Cashflow> getAllCashflow() {
        return cashflowDao.getAll();
    }

    public boolean simpanCashflow(Cashflow cf) {
        cf.hitungSaldoAkhir();
        return cashflowDao.save(cf);
    }

    public void hitungOtomatisBulanIni(Cashflow cf, int bulan, int tahun) {
        List<JurnalPembukuan> listJurnal = jurnalDao.getAll();
        BigDecimal totalMasuk = BigDecimal.ZERO;
        BigDecimal totalKeluar = BigDecimal.ZERO;

        for (JurnalPembukuan j : listJurnal) {
            if (j.getTanggal().getMonthValue() == bulan && j.getTanggal().getYear() == tahun) {
                totalMasuk = totalMasuk.add(j.getDebit());
                totalKeluar = totalKeluar.add(j.getKredit());
            }
        }

        cf.setTotalPemasukan(totalMasuk);
        cf.setTotalPengeluaran(totalKeluar);
        cf.hitungSaldoAkhir();
    }

    public boolean hapusCashflow(int id) {
        return cashflowDao.delete(id);
    }

    public Cashflow getCashflowById(int id) {
        return cashflowDao.getById(id);
    }
}