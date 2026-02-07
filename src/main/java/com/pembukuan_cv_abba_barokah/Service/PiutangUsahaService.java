package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PiutangUsahaDao;
import com.pembukuan_cv_abba_barokah.Model.PiutangUsaha;

import java.util.List;

public class PiutangUsahaService {

    private final PiutangUsahaDao dao = new PiutangUsahaDao();

    public boolean simpan(PiutangUsaha p) {
        return dao.save(p);
    }

    public boolean update(PiutangUsaha p) {
        return dao.update(p);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<PiutangUsaha> getAll() {
        return dao.getAll();
    }
}