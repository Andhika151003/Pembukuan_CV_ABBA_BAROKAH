package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
// import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class JurnalPembukuanService {
    private final JurnalPembukuanDao jurnalDao;

    public JurnalPembukuanService() {
        this.jurnalDao = new JurnalPembukuanDao();
    }

    public List<JurnalPembukuan> getAllJurnal() {
        return jurnalDao.getAll();
    }

    public List<JurnalPembukuan> getJurnalByKategori(JurnalPembukuan.Kategori kategori) {
        return jurnalDao.getAll().stream()
                .filter(j -> j.getKategori() == kategori)
                .collect(Collectors.toList());
    }

    public List<JurnalPembukuan> getJurnalByPeriode(int bulan, int tahun) {
        return jurnalDao.getAll().stream()
                .filter(j -> j.getTanggal().getMonthValue() == bulan && 
                             j.getTanggal().getYear() == tahun)
                .collect(Collectors.toList());
    }

    public BigDecimal hitungTotalDebit(List<JurnalPembukuan> list) {
        return list.stream()
                .map(JurnalPembukuan::getDebit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal hitungTotalKredit(List<JurnalPembukuan> list) {
        return list.stream()
                .map(JurnalPembukuan::getKredit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean hapusJurnal(int id) {
        return jurnalDao.delete(id);
    }
}