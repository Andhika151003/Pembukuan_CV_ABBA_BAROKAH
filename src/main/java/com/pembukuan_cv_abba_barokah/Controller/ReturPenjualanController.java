package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import com.pembukuan_cv_abba_barokah.Service.ReturPenjualanService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReturPenjualanController {

    private final ReturPenjualanService returService;

    public ReturPenjualanController() {
        this.returService = new ReturPenjualanService();
    }

    // ===================== READ =====================

    public List<ReturPenjualan> tampilkanSemuaRetur() {
        return returService.getAll();
    }

    public ReturPenjualan tampilkanReturById(int id) {
        return returService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahReturPenjualan(
            int noRetur,
            LocalDate tanggalRetur,
            int idPenjualan,
            int idTransaksi,
            int jumlahRetur,
            BigDecimal nilaiRetur,
            ReturPenjualan.AlasanRetur alasan,
            String keterangan,
            ReturPenjualan.StatusRetur status,
            ReturPenjualan.JenisPengembalian jenisPengembalian,
            LocalDate tanggalPengembalian,
            int idAdministrasi
    ) {
        ReturPenjualan retur = new ReturPenjualan(
                noRetur,
                tanggalRetur,
                idPenjualan,
                idTransaksi,
                jumlahRetur,
                nilaiRetur,
                alasan,
                keterangan,
                status,
                jenisPengembalian,
                tanggalPengembalian
        );

        return returService.tambahRetur(retur, idAdministrasi);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiReturPenjualan(
            int id,
            int noRetur,
            LocalDate tanggalRetur,
            int idPenjualan,
            int idTransaksi,
            int jumlahRetur,
            BigDecimal nilaiRetur,
            ReturPenjualan.AlasanRetur alasan,
            String keterangan,
            ReturPenjualan.StatusRetur status,
            ReturPenjualan.JenisPengembalian jenisPengembalian,
            LocalDate tanggalPengembalian,
            int idAdministrasi
    ) {
        ReturPenjualan retur = new ReturPenjualan(
                id,
                noRetur,
                tanggalRetur,
                idPenjualan,
                idTransaksi,
                jumlahRetur,
                nilaiRetur,
                alasan,
                keterangan,
                status,
                jenisPengembalian,
                tanggalPengembalian
        );

        return returService.perbaruiRetur(retur, idAdministrasi);
    }

    // ===================== DELETE =====================

    public boolean hapusReturPenjualan(int id, int idAdministrasi) {
        return returService.hapusRetur(id, idAdministrasi);
    }
}