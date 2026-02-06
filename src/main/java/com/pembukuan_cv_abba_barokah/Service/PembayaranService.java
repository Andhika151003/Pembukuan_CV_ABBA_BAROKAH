package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.PembayaranDao;
import com.pembukuan_cv_abba_barokah.Model.Pembayaran;

import java.util.List;

public class PembayaranService {

    private final PembayaranDao dao = new PembayaranDao();

    public boolean sudahAdaUntukPenjualan(int idPenjualan) {
        return dao.existsByIdPenjualan(idPenjualan);
    }

    public boolean simpan(Pembayaran pembayaran) {
        return dao.save(pembayaran);
    }

    public boolean update(Pembayaran pembayaran) {
        return dao.update(pembayaran);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<Pembayaran> getAll() {
        return dao.getAll();
    }
}