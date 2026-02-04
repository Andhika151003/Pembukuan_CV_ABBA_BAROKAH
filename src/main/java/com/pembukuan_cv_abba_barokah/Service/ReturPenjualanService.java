package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.ReturPenjualanDao;
import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;

import java.util.List;

public class ReturPenjualanService {

    private final ReturPenjualanDao dao = new ReturPenjualanDao();

    public boolean simpan(ReturPenjualan r) {
        return dao.save(r);
    }

    public boolean update(ReturPenjualan r) {
        return dao.update(r);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<ReturPenjualan> getAll() {
        return dao.getAll();
    }
}