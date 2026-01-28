package com.pembukuan_cv_abba_barokah.Service;

import com.pembukuan_cv_abba_barokah.DAO.NeracaKeuanganDao;
import com.pembukuan_cv_abba_barokah.Model.NeracaKeuangan;
import java.util.List;

public class NeracaKeuanganService {
    private final NeracaKeuanganDao neracaDao;

    public NeracaKeuanganService() {
        this.neracaDao = new NeracaKeuanganDao();
    }

    public List<NeracaKeuangan> getAll() {
        return neracaDao.getAll();
    }

    public NeracaKeuangan getById(int id) {
        return neracaDao.getById(id);
    }

    /**
     * Menyimpan data neraca keuangan baru.
     * Melakukan pengecekan apakah posisi keuangan sudah seimbang.
     */
    public boolean simpanNeraca(NeracaKeuangan neraca) {
        if (!isBalanced(neraca)) {
            // Logika bisnis: Laporan neraca tidak boleh disimpan jika tidak seimbang
            return false;
        }
        return neracaDao.save(neraca);
    }

    /**
     * Memperbarui data neraca keuangan yang sudah ada.
     */
    public boolean perbaruiNeraca(NeracaKeuangan neraca) {
        if (!isBalanced(neraca)) {
            return false;
        }
        return neracaDao.update(neraca);
    }

    public boolean hapusNeraca(int id) {
        return neracaDao.delete(id);
    }

    /**
     * Memvalidasi keseimbangan akuntansi: Total Aset = Total Kewajiban + Total Ekuitas.
     * Memanfaatkan metode perhitungan yang sudah ada di model NeracaKeuangan.
     */
    public boolean isBalanced(NeracaKeuangan neraca) {
        // Bandingkan Total Aset dengan (Total Kewajiban + Total Ekuitas)
        return neraca.getTotalAset().compareTo(
            neraca.getTotalKewajiban().add(neraca.getTotalEkuitas())
        ) == 0;
    }
}