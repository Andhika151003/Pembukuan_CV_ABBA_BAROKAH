package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.BiayaPemeliharaanDao;
import com.pembukuan_cv_abba_barokah.Model.BiayaPemeliharaan;

import java.util.List;

public class BiayaPemeliharaanService {

    private final BiayaPemeliharaanDao dao = new BiayaPemeliharaanDao();

    public boolean simpan(BiayaPemeliharaan b) {
        return dao.save(b);
    }

    public boolean update(BiayaPemeliharaan b) {
        return dao.update(b);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<BiayaPemeliharaan> getAll() {
        return dao.getAll();
    }
}