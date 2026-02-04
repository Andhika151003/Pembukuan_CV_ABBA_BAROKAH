package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.BiayaPemasaranDao;
import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;

import java.util.List;

public class BiayaPemasaranService {

    private final BiayaPemasaranDao dao = new BiayaPemasaranDao();

    public boolean simpan(BiayaPemasaran bp) {
        return dao.save(bp);
    }

    public List<BiayaPemasaran> getAll() {
        return dao.getAll();
    }
}