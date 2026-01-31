package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
import com.pembukuan_cv_abba_barokah.Service.ReturPembelianService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReturPembelianController {

    private final ReturPembelianService returService;

    public ReturPembelianController() {
        this.returService = new ReturPembelianService();
    }

    // ===================== READ =====================

    public List<ReturPembelian> tampilkanSemuaRetur() {
        return returService.getAll();
    }

    public ReturPembelian tampilkanReturById(int id) {
        return returService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahReturPembelian(
            int noRetur,
            LocalDate tanggalRetur,
            int idPembelian,
            int jumlahRetur,
            BigDecimal nilaiRetur,
            ReturPembelian.AlasanRetur alasanRetur,
            String keterangan,
            ReturPembelian.StatusRetur statusRetur,
            int idAdministrasi
    ) {
        ReturPembelian retur = new ReturPembelian(
                noRetur,
                tanggalRetur,
                idPembelian,
                jumlahRetur,
                nilaiRetur,
                alasanRetur,
                keterangan,
                statusRetur
        );

        return returService.tambahRetur(retur, idAdministrasi);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiReturPembelian(
            int id,
            int noRetur,
            LocalDate tanggalRetur,
            int idPembelian,
            int jumlahRetur,
            BigDecimal nilaiRetur,
            ReturPembelian.AlasanRetur alasanRetur,
            String keterangan,
            ReturPembelian.StatusRetur statusRetur,
            int idAdministrasi
    ) {
        ReturPembelian retur = new ReturPembelian(
                id,
                noRetur,
                tanggalRetur,
                idPembelian,
                jumlahRetur,
                nilaiRetur,
                alasanRetur,
                keterangan,
                statusRetur
        );

        return returService.perbaruiRetur(retur, idAdministrasi);
    }

    // ===================== DELETE =====================

    public boolean hapusReturPembelian(int id, int idAdministrasi) {
        return returService.hapusRetur(id, idAdministrasi);
    }
}