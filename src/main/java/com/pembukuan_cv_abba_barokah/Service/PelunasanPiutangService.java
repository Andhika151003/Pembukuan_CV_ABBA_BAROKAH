package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PelunasanPiutangDao;
import com.pembukuan_cv_abba_barokah.Model.PelunasanPiutang;

import java.util.List;

public class PelunasanPiutangService {

    private final PelunasanPiutangDao dao = new PelunasanPiutangDao();

    public boolean simpan(PelunasanPiutang p) {
        return dao.save(p);
    }

    public boolean update(PelunasanPiutang p) {
        return dao.update(p);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<PelunasanPiutang> getAll() {
        return dao.getAll();
    }
}
