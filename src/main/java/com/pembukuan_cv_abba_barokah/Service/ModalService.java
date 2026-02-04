package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.ModalDao;
import com.pembukuan_cv_abba_barokah.Model.Modal;

import java.util.List;

public class ModalService {

    private final ModalDao dao = new ModalDao();

    public boolean simpan(Modal modal) {
        return dao.save(modal);
    }

    public boolean update(Modal modal) {
        return dao.update(modal);
    }

    public boolean hapus(int id) {
        return dao.delete(id);
    }

    public List<Modal> getAll() {
        return dao.getAll();
    }
}