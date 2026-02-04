package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.HargaPokokPenjualanDao;
import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;

import java.util.List;

public class HppService {

    private final HargaPokokPenjualanDao dao = new HargaPokokPenjualanDao();

    public boolean simpan(HargaPokokPenjualan hpp) {
        return dao.save(hpp);
    }

    public boolean update(HargaPokokPenjualan hpp) {
        return dao.update(hpp);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

    public List<HargaPokokPenjualan> getAll() {
        return dao.getAll();
    }
}