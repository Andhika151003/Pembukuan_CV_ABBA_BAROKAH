package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Service.BiayaPemasaranService;

import java.time.LocalDate;
import java.util.List;

public class BiayaPemasaranController {

    private final BiayaPemasaranService biayaPemasaranService;

    public BiayaPemasaranController() {
        this.biayaPemasaranService = new BiayaPemasaranService();
    }

    // ===================== READ =====================

    public List<BiayaPemasaran> tampilkanSemua() {
        return biayaPemasaranService.getAll();
    }

    public BiayaPemasaran tampilkanById(int id) {
        return biayaPemasaranService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahBiaya(
            LocalDate tanggal,
            String deskripsi,
            int jumlah,
            BiayaPemasaran.ExpenseCategory category,
            BiayaPemasaran.MarketingExpenseType marketingType
    ) {
        BiayaPemasaran biaya = new BiayaPemasaran(
                tanggal,
                deskripsi,
                jumlah,
                category,
                marketingType
        );
        return biayaPemasaranService.tambahBiayaPemasaran(biaya);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiBiaya(
            int id,
            LocalDate tanggal,
            String deskripsi,
            int jumlah,
            BiayaPemasaran.ExpenseCategory category,
            BiayaPemasaran.MarketingExpenseType marketingType
    ) {
        BiayaPemasaran biaya = new BiayaPemasaran(
                id,
                tanggal,
                deskripsi,
                jumlah,
                category,
                marketingType
        );
        return biayaPemasaranService.perbaruiBiayaPemasaran(biaya);
    }

    // ===================== DELETE =====================

    public boolean hapusBiaya(int id) {
        return biayaPemasaranService.hapusBiayaPemasaran(id);
    }
}