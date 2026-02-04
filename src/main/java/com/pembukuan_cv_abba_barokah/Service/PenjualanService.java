package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PenjualanDao;
import com.pembukuan_cv_abba_barokah.Model.Penjualan;

import java.util.List;

public class PenjualanService {

    private final PenjualanDao dao = new PenjualanDao();

    public List<Penjualan> getAll() {
        return dao.getAll();
    }

    public boolean simpan(Penjualan p) {
        return dao.save(p);
    }

    public boolean update(Penjualan p) {
        return dao.update(p);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }
}