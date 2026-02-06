package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.SwakelolaDao;
import com.pembukuan_cv_abba_barokah.Model.Swakelola;

import java.util.List;

public class SwakelolaService {

    private final SwakelolaDao dao = new SwakelolaDao();

    public boolean sudahAdaUntukPenjualan(int idPenjualan) {
        return dao.existsByIdPenjualan(idPenjualan);
    }

    public boolean simpan(Swakelola s) {
        return dao.save(s);
    }

    public boolean update(Swakelola s) {
        return dao.update(s);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<Swakelola> getAll() {
        return dao.getAll();
    }
}