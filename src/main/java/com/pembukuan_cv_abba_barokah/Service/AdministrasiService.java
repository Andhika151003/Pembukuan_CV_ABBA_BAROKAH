package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.AdministrasiDao;
import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import java.math.BigDecimal;
import java.util.List;


public class AdministrasiService {
    private final AdministrasiDao adminDao;
    
    public AdministrasiService() {
        this.adminDao = new AdministrasiDao();
    }

    public List<Administrasi> getAll() {
        return adminDao.getAll();
    }

    public Administrasi getById(int id) {
        return adminDao.getById(id);
    }

    public boolean simpanBaru(Administrasi admin) {
        if (admin.getJumlah() == null) {
            admin.setJumlah(BigDecimal.ZERO);
        }
        return adminDao.save(admin);
    }

    public boolean perbaruiData(Administrasi admin) {
        return adminDao.update(admin);
    }

    public boolean tambahSaldo(int id, BigDecimal nilaiTambah) {
        Administrasi admin = adminDao.getById(id);
        if (admin != null && nilaiTambah != null) {
            BigDecimal totalBaru = admin.getJumlah().add(nilaiTambah);
            admin.setJumlah(totalBaru);
            return adminDao.update(admin);
        }
        return false;
    }

    public boolean kurangSaldo(int id, BigDecimal nilaiKurang) {
        Administrasi admin = adminDao.getById(id);
        if (admin != null && nilaiKurang != null) {
            BigDecimal totalBaru = admin.getJumlah().subtract(nilaiKurang);
            admin.setJumlah(totalBaru);
            return adminDao.update(admin);
        }
        return false;
    }

    public boolean hapusAdministrasi(int id) {
        return adminDao.delete(id);
    }
}
