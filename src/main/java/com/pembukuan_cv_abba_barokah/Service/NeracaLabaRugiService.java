package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.NeracaLabaRugiDao;
import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.NeracaLabaRugi;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;

public class NeracaLabaRugiService {
    private final NeracaLabaRugiDao labaRugiDao;
    private final JurnalPembukuanDao jurnalDao;

    public NeracaLabaRugiService() {
        this.labaRugiDao = new NeracaLabaRugiDao();
        this.jurnalDao = new JurnalPembukuanDao();
    }

    public NeracaLabaRugi hitungLabaRugiTahunan(int tahun, int idAdmin) {
        List<JurnalPembukuan> jurnals = jurnalDao.getAll();
        
        BigDecimal pendapatan = BigDecimal.ZERO;
        BigDecimal hpp = BigDecimal.ZERO;
        BigDecimal biayaOperasional = BigDecimal.ZERO;
        BigDecimal pajakTotal = BigDecimal.ZERO;

        for (JurnalPembukuan j : jurnals) {
            // Filter hanya berdasarkan Tahun
            if (j.getTanggal().getYear() == tahun) {
                switch (j.getKategori()) {
                    case PENJUALAN:
                    case PEMBAYARAN:
                        pendapatan = pendapatan.add(j.getDebit());
                        break;
                    case HPP:
                        hpp = hpp.add(j.getKredit());
                        break;
                    case GAJI:
                    case ADMINISTRASI:
                    case PEMBELIAN:
                        biayaOperasional = biayaOperasional.add(j.getKredit());
                        break;
                    case PAJAK:
                        pajakTotal = pajakTotal.add(j.getKredit());
                        break;
                    default:
                        break;
                }
            }
        }

        BigDecimal labaKotor = pendapatan.subtract(hpp);
        BigDecimal labaSebelumPajak = labaKotor.subtract(biayaOperasional);
        BigDecimal labaBersih = labaSebelumPajak.subtract(pajakTotal);

        return new NeracaLabaRugi(
            tahun,
            pendapatan,
            hpp,
            labaKotor,
            biayaOperasional,
            labaSebelumPajak,
            pajakTotal,
            labaBersih,
            idAdmin
        );
    }

    public boolean simpanLaporan(NeracaLabaRugi laporan) {
        return labaRugiDao.save(laporan);
    }

    public List<NeracaLabaRugi> getSemuaRiwayat() {
        return labaRugiDao.getAll();
    }
}