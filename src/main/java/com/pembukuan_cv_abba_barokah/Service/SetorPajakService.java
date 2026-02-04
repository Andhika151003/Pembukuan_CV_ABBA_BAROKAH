package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.SetorPajakDao;
import com.pembukuan_cv_abba_barokah.Model.SetorPajak;

import java.util.List;

public class SetorPajakService {

    private final SetorPajakDao dao = new SetorPajakDao();

    public boolean simpan(SetorPajak sp) {
        return dao.save(sp);
    }

    public List<SetorPajak> getAll() {
        return dao.getAll();
    }
}