package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.AdministrasiDao;
import com.pembukuan_cv_abba_barokah.Model.Administrasi;


import java.util.List;

public class AdministrasiService{

    private final AdministrasiDao dao = new AdministrasiDao();

    public boolean simpan(Administrasi ads) {
        return dao.save(ads);
    }

    public List<Administrasi> getAll() {
        return dao.getAll();
    }

    public boolean update(Administrasi ads) {
        return dao.update(ads);
    }
    
    public boolean delete(int id) {
        return dao.delete(id);
    }    
}