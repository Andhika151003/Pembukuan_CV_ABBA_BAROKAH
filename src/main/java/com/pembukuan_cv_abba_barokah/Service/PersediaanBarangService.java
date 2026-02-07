package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PersediaanBarangDao;
import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;

import java.util.List;

public class PersediaanBarangService {

    private final PersediaanBarangDao dao = new PersediaanBarangDao();

    public boolean simpan(PersediaanBarang p) {
        return dao.save(p);
    }

    public boolean update(PersediaanBarang p) {
        return dao.update(p);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<PersediaanBarang> getAll() {
        return dao.getAll();
    }
}