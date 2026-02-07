package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PelunasanUtangDao;
import com.pembukuan_cv_abba_barokah.Model.PelunasanUtang;

import java.util.List;

public class PelunasanUtangService {

    private final PelunasanUtangDao dao = new PelunasanUtangDao();

    public boolean simpan(PelunasanUtang p) {
        return dao.save(p);
    }

    public boolean update(PelunasanUtang p) {
        return dao.update(p);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<PelunasanUtang> getAll() {
        return dao.getAll();
    }
}