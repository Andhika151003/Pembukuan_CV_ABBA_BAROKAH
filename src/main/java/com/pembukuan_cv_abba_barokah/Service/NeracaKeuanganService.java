package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.NeracaKeuanganDao;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;

import java.math.BigDecimal;
import java.util.List;

public class NeracaKeuanganService {

    private final NeracaKeuanganDao dao = new NeracaKeuanganDao();
    private final JurnalPembukuanService jurnalService = new JurnalPembukuanService();

    /* ================= BANK ================= */

    public BigDecimal bank(String tahun) {

        BigDecimal totalDebit = BigDecimal.ZERO;
        BigDecimal totalKredit = BigDecimal.ZERO;

        for (int bulan = 1; bulan <= 12; bulan++) {

            String bln = String.format("%02d", bulan);

            List<JurnalPembukuan> list = jurnalService.getByPeriode(bln, tahun);

            for (JurnalPembukuan j : list) {

                if (j.getDebit() != null)
                    totalDebit = totalDebit.add(j.getDebit());

                if (j.getKredit() != null)
                    totalKredit = totalKredit.add(j.getKredit());
            }
        }

        return totalDebit.subtract(totalKredit);
    }

    /* ================= ASET LANCAR ================= */

    public BigDecimal piutang(String tahun) {
        return dao.totalPiutang(tahun);
    }

    public BigDecimal persediaan(String tahun) {
        return dao.totalPersediaan(tahun);
    }

    public BigDecimal asetLancar(String tahun) {
        return bank(tahun)
                .add(piutang(tahun))
                .add(persediaan(tahun));
    }

    /* ================= ASET TIDAK LANCAR ================= */

    public BigDecimal inventaris(String tahun) {
        return dao.totalInventaris(tahun);
    }

    public BigDecimal asetTidakLancar(String tahun) {
        return inventaris(tahun);
    }

    public BigDecimal totalAset(String tahun) {
        return asetLancar(tahun)
                .add(asetTidakLancar(tahun));
    }

    /* ================= KEWAJIBAN ================= */

    public BigDecimal utang(String tahun) {
        return dao.totalUtang(tahun);
    }

    public BigDecimal totalKewajiban(String tahun) {
        return utang(tahun);
    }

    /* ================= EKUITAS ================= */

    public BigDecimal ekuitas(String tahun) {
        return totalAset(tahun)
                .subtract(totalKewajiban(tahun));
    }

    public BigDecimal modal(String tahun) {
        return dao.totalModal(tahun);
    }

    public BigDecimal totalEkuitas(String tahun) {
        return ekuitas(tahun)
                .add(modal(tahun));
    }

    public BigDecimal jumlahKewajibanDanEkuitas(String tahun) {
        return totalKewajiban(tahun)
                .add(totalEkuitas(tahun));
    }    
}