package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.JurnalPembukuanDao;
import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class JurnalPembukuanService {
    private final JurnalPembukuanDao jurnalDao;

    public JurnalPembukuanService() {
        this.jurnalDao = new JurnalPembukuanDao();
    }

    /**
     * Mengambil semua data rekap dan menghitung saldo secara dinamis.
     */
    public List<JurnalPembukuan> getAllJurnalWithSaldo() {
        List<JurnalPembukuan> list = jurnalDao.getAll();
        BigDecimal saldoAkumulasi = BigDecimal.ZERO;

        for (JurnalPembukuan jurnal : list) {
            // Rumus: Saldo Baru = Saldo Sebelumnya + Debit - Kredit
            saldoAkumulasi = saldoAkumulasi.add(jurnal.getDebit()).subtract(jurnal.getKredit());
            jurnal.setSaldo(saldoAkumulasi);
        }

        return list;
    }

    /**
     * Menghitung total seluruh debit dari list yang ditampilkan.
     */
    public BigDecimal hitungTotalDebit(List<JurnalPembukuan> list) {
        return list.stream()
                .map(JurnalPembukuan::getDebit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Menghitung total seluruh kredit dari list yang ditampilkan.
     */
    public BigDecimal hitungTotalKredit(List<JurnalPembukuan> list) {
        return list.stream()
                .map(JurnalPembukuan::getKredit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Filter data berdasarkan periode bulan dan tahun.
     */
    public List<JurnalPembukuan> getJurnalByPeriode(int bulan, int tahun) {
        // Ambil semua data yang sudah ber-saldo, lalu filter
        return getAllJurnalWithSaldo().stream()
                .filter(j -> j.getTanggal().getMonthValue() == bulan && 
                             j.getTanggal().getYear() == tahun)
                .collect(Collectors.toList());
    }
}