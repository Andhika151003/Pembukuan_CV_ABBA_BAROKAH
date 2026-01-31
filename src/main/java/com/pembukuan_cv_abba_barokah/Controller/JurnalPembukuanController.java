package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import com.pembukuan_cv_abba_barokah.Service.JurnalPembukuanService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JurnalPembukuanController {

    private final JurnalPembukuanService jurnalService;

    public JurnalPembukuanController() {
        this.jurnalService = new JurnalPembukuanService();
    }

    // ===================== READ =====================

    public List<JurnalPembukuan> tampilkanSemuaJurnal() {
        return jurnalService.getAllJurnal();
    }

    public List<JurnalPembukuan> tampilkanJurnalByKategori(
            JurnalPembukuan.Kategori kategori
    ) {
        return jurnalService.getJurnalByKategori(kategori);
    }

    public List<JurnalPembukuan> tampilkanJurnalByPeriode(
            int bulan,
            int tahun
    ) {
        return jurnalService.getJurnalByPeriode(bulan, tahun);
    }

    // ===================== CREATE =====================

    public boolean tambahJurnal(
            LocalDate tanggal,
            int nomorJurnal,
            JurnalPembukuan.JenisTransaksi jenisTransaksi,
            JurnalPembukuan.Kategori kategori,
            String deskripsi,
            BigDecimal debit,
            BigDecimal kredit,
            BigDecimal saldo,
            int idTransaksi,
            int idPembayaran,
            int idPembelian,
            int idGaji
    ) {
        JurnalPembukuan jurnal = new JurnalPembukuan(
                0,              // id (auto)
                tanggal,
                nomorJurnal,
                jenisTransaksi,
                kategori,
                deskripsi,
                debit,
                kredit,
                saldo,
                idTransaksi,
                idPembayaran,
                idPembelian,
                idGaji
        );

        return jurnalService.tambahJurnal(jurnal);
    }

    // ===================== UPDATE =====================

    public boolean updateJurnal(
            int id,
            LocalDate tanggal,
            int nomorJurnal,
            JurnalPembukuan.JenisTransaksi jenisTransaksi,
            JurnalPembukuan.Kategori kategori,
            String deskripsi,
            BigDecimal debit,
            BigDecimal kredit,
            BigDecimal saldo,
            int idTransaksi,
            int idPembayaran,
            int idPembelian,
            int idGaji
    ) {
        JurnalPembukuan jurnal = new JurnalPembukuan(
                id,
                tanggal,
                nomorJurnal,
                jenisTransaksi,
                kategori,
                deskripsi,
                debit,
                kredit,
                saldo,
                idTransaksi,
                idPembayaran,
                idPembelian,
                idGaji
        );

        return jurnalService.updateJurnal(jurnal);
    }

    // ===================== DELETE =====================

    public boolean hapusJurnal(int id) {
        return jurnalService.hapusJurnal(id);
    }

    // ===================== REKAP =====================

    public BigDecimal hitungTotalDebit(List<JurnalPembukuan> list) {
        return jurnalService.hitungTotalDebit(list);
    }

    public BigDecimal hitungTotalKredit(List<JurnalPembukuan> list) {
        return jurnalService.hitungTotalKredit(list);
    }
}