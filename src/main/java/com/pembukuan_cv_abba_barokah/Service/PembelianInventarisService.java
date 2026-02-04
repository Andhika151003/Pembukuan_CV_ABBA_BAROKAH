package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PembelianInventarisDao;
import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;

import java.util.List;

public class PembelianInventarisService {

    private final PembelianInventarisDao dao = new PembelianInventarisDao();

    public boolean simpan(PembelianInventaris pi) {
        return dao.save(pi);
    }

    public boolean update(PembelianInventaris pi) {
        return dao.update(pi);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<PembelianInventaris> getAll() {
        return dao.getAll();
    }
}