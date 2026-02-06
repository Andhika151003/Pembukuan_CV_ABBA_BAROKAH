package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PembelianLangsungDao;
import com.pembukuan_cv_abba_barokah.Model.PembelianLangsung;

import java.util.List;

public class PembelianLangsungService {

    private final PembelianLangsungDao dao = new PembelianLangsungDao();

    public boolean sudahAdaUntukPenjualan(int idPenjualan) {
        return dao.existsByIdPenjualan(idPenjualan);
    }

    public boolean simpan(PembelianLangsung p) {
        return dao.save(p);
    }

    public boolean update(PembelianLangsung p) {
        return dao.update(p);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<PembelianLangsung> getAll() {
        return dao.getAll();
    }
}