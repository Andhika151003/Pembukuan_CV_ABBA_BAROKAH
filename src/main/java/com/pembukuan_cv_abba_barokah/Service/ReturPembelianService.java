package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.ReturPembelianDao;
import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;

import java.util.List;

public class ReturPembelianService {

    private final ReturPembelianDao dao = new ReturPembelianDao();

    public boolean simpan(ReturPembelian r) {
        return dao.save(r);
    }

    public boolean update(ReturPembelian r) {
        return dao.update(r);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public boolean sudahAdaUntukPembelian(int idPembelian) {
        return dao.existsByIdPembelian(idPembelian);
    }

    public List<ReturPembelian> getAll() {
        return dao.getAll();
    }
}