package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.SetorPajakDao;
import com.pembukuan_cv_abba_barokah.Model.SetorPajak;

import java.util.List;

public class SetorPajakService {

    private final SetorPajakDao dao = new SetorPajakDao();

    public boolean simpan(SetorPajak sp) {
        return dao.save(sp);
    }

    public boolean update(SetorPajak sp) {
        return dao.update(sp);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<SetorPajak> getAll() {
        return dao.getAll();
    }
}