package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;

import java.util.List;

public class JurnalPembukuanService {

    private final JurnalPembukuanDao dao = new JurnalPembukuanDao();

    public List<JurnalPembukuan> getByPeriode(String bulan, String tahun) {
        return dao.getByPeriode(bulan, tahun);
    }
}