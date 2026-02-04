package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.LaporanLabaRugiDao;
import com.pembukuan_cv_abba_barokah.Model.LaporanLabaRugi;

public class LaporanLabaRugiService {

    private final LaporanLabaRugiDao dao = new LaporanLabaRugiDao();

    public LaporanLabaRugi getByTahun(int tahun) {
        return dao.getByTahun(tahun);
    }
}