package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.CashflowPenjualanDao;
import com.pembukuan_cv_abba_barokah.Model.CashflowPenjualan;

import java.util.List;

public class CashflowPenjualanService {

    private final CashflowPenjualanDao dao = new CashflowPenjualanDao();

    public List<CashflowPenjualan> getByPeriode(String bulan, String tahun) {
        return dao.getByPeriode(bulan, tahun);
    }
}