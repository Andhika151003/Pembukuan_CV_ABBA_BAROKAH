package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.NeracaKeuanganDao;
import com.pembukuan_cv_abba_barokah.Model.NeracaKeuangan;

import java.math.BigDecimal;

public class NeracaKeuanganService {

    private final NeracaKeuanganDao dao = new NeracaKeuanganDao();

    public NeracaKeuangan get() {
        return dao.getData();
    }

    public BigDecimal totalAsetLancar(NeracaKeuangan n) {
        return n.getKasBank()
                .add(n.getPiutangUsaha())
                .add(n.getPersediaanBarang());
    }

    public BigDecimal jumlahAset(NeracaKeuangan n) {
        return totalAsetLancar(n)
                .add(n.getAsetTidakLancar());
    }

    public BigDecimal labaDitahan(NeracaKeuangan n) {
        return jumlahAset(n)
                .subtract(n.getTotalUtang())
                .subtract(n.getModalDisetor());
    }

    public BigDecimal jumlahEkuitas(NeracaKeuangan n) {
        return n.getModalDisetor()
                .add(labaDitahan(n));
    }

    public BigDecimal totalKewajibanDanEkuitas(NeracaKeuangan n) {
        return n.getTotalUtang()
                .add(jumlahEkuitas(n));
    }
}