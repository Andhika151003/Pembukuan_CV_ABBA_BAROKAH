package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.UtangUsahaDao;
import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;

import java.util.List;

public class UtangUsahaService {

    private final UtangUsahaDao dao = new UtangUsahaDao();

    public boolean simpan(UtangUsaha u) {
        return dao.save(u);
    }

    public boolean update(UtangUsaha u) {
        return dao.update(u);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public boolean sudahAdaUntukPembelian(int idPembelian) {
        return dao.existsByIdPembelian(idPembelian);
    }

    public List<UtangUsaha> getAll() {
        return dao.getAll();
    }
}