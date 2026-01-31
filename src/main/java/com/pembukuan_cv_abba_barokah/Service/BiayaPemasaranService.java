package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.BiayaPemasaranDao;
import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import java.util.List;

public class BiayaPemasaranService {
    private final BiayaPemasaranDao biayaPemasaranDao;

    public BiayaPemasaranService() {
        this.biayaPemasaranDao = new BiayaPemasaranDao();
    }

    public List<BiayaPemasaran> getAll() {
        return biayaPemasaranDao.getAll();
    }

    public BiayaPemasaran getById(int id) {
        return biayaPemasaranDao.getById(id);
    }

    public boolean tambahBiayaPemasaran(BiayaPemasaran biaya) {
        return biayaPemasaranDao.save(biaya);
    }

    public boolean perbaruiBiayaPemasaran(BiayaPemasaran biaya) {
        return biayaPemasaranDao.update(biaya);
    }

    public boolean hapusBiayaPemasaran(int id) {
        return biayaPemasaranDao.delete(id);
    }
}