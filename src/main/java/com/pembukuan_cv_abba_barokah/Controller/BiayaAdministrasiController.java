package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Service.AdministrasiService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BiayaAdministrasiController {

    private final AdministrasiService administrasiService;

    public BiayaAdministrasiController() {
        this.administrasiService = new AdministrasiService();
    }

    // ===================== READ =====================

    public List<Administrasi> tampilkanSemuaData() {
        return administrasiService.getAll();
    }

    public Administrasi tampilkanById(int id) {
        return administrasiService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahAdministrasi(
            LocalDate tanggal,
            String jenisAdministrasi,
            String deskripsi,
            BigDecimal jumlah,
            String keterangan
    ) {
        Administrasi admin = new Administrasi(
                tanggal,
                jenisAdministrasi,
                deskripsi,
                jumlah,
                keterangan
        );
        return administrasiService.simpanBaru(admin);
    }

    // ===================== UPDATE =====================

    public boolean updateAdministrasi(
            int id,
            LocalDate tanggal,
            String jenisAdministrasi,
            String deskripsi,
            BigDecimal jumlah,
            String keterangan
    ) {
        Administrasi admin = new Administrasi(
                id,
                tanggal,
                jenisAdministrasi,
                deskripsi,
                jumlah,
                keterangan
        );
        return administrasiService.perbaruiData(admin);
    }

    // ===================== SALDO =====================

    public boolean tambahSaldo(int id, BigDecimal nilaiTambah) {
        return administrasiService.tambahSaldo(id, nilaiTambah);
    }

    public boolean kurangSaldo(int id, BigDecimal nilaiKurang) {
        return administrasiService.kurangSaldo(id, nilaiKurang);
    }

    // ===================== DELETE =====================

    public boolean hapusData(int id) {
        return administrasiService.hapusAdministrasi(id);
    }
}