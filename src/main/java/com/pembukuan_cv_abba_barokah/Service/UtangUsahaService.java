package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.UtangUsahaDao;
import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;

import java.math.BigDecimal;
import java.util.List;

public class UtangUsahaService {

    private final UtangUsahaDao dao = new UtangUsahaDao();

    public boolean simpan(UtangUsaha u) {
        return dao.save(u);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<UtangUsaha> getAll() {
        return dao.getAll();
    }

    public BigDecimal hitungSisaUtang(UtangUsaha u) {
        return u.getJumlahUtang().subtract(u.getJumlahDibayar());
    }
}