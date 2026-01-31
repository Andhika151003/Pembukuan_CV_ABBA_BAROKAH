package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.NeracaKeuangan;
import com.pembukuan_cv_abba_barokah.Service.NeracaKeuanganService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class NeracaKeuanganController {

    private final NeracaKeuanganService neracaService;

    public NeracaKeuanganController() {
        this.neracaService = new NeracaKeuanganService();
    }

    // ===================== READ =====================

    public List<NeracaKeuangan> tampilkanSemuaNeraca() {
        return neracaService.getAll();
    }

    public NeracaKeuangan tampilkanNeracaById(int id) {
        return neracaService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahNeraca(
            LocalDate tanggal,
            int tahun,
            BigDecimal kas,
            BigDecimal piutangUsaha,
            BigDecimal persediaanBarang,
            BigDecimal peralatan,
            BigDecimal transportasi,
            BigDecimal akumulasiPenyusutan,
            BigDecimal utangUsaha,
            BigDecimal utangJangkaPanjang,
            BigDecimal modalPemilik,
            BigDecimal labaRugi,
            String keterangan
    ) {
        NeracaKeuangan neraca = new NeracaKeuangan(
                tanggal,
                tahun,
                kas,
                piutangUsaha,
                persediaanBarang,
                peralatan,
                transportasi,
                akumulasiPenyusutan,
                utangUsaha,
                utangJangkaPanjang,
                modalPemilik,
                labaRugi,
                keterangan
        );

        // Validasi keseimbangan dilakukan di Service
        return neracaService.simpanNeraca(neraca);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiNeraca(
            int id,
            LocalDate tanggal,
            int tahun,
            BigDecimal kas,
            BigDecimal piutangUsaha,
            BigDecimal persediaanBarang,
            BigDecimal peralatan,
            BigDecimal transportasi,
            BigDecimal akumulasiPenyusutan,
            BigDecimal utangUsaha,
            BigDecimal utangJangkaPanjang,
            BigDecimal modalPemilik,
            BigDecimal labaRugi,
            String keterangan
    ) {
        NeracaKeuangan neraca = new NeracaKeuangan(
                id,
                tanggal,
                tahun,
                kas,
                piutangUsaha,
                persediaanBarang,
                peralatan,
                transportasi,
                akumulasiPenyusutan,
                utangUsaha,
                utangJangkaPanjang,
                modalPemilik,
                labaRugi,
                keterangan
        );

        return neracaService.perbaruiNeraca(neraca);
    }

    // ===================== DELETE =====================

    public boolean hapusNeraca(int id) {
        return neracaService.hapusNeraca(id);
    }
}