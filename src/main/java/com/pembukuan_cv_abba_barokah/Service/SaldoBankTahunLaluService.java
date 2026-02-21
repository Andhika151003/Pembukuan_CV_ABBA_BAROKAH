package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.SaldoBankTahunLaluDao;
import com.pembukuan_cv_abba_barokah.Model.SaldoBankTahunLalu;

import java.util.List;

public class SaldoBankTahunLaluService {

    private final SaldoBankTahunLaluDao dao = new SaldoBankTahunLaluDao();

    public void save(SaldoBankTahunLalu data) {
        dao.insert(data);
    }

    public void update(SaldoBankTahunLalu data) {
        dao.update(data);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public List<SaldoBankTahunLalu> getAll() {
        return dao.findAll();
    }
}