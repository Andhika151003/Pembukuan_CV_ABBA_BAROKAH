package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PenjualanController {

    private final PenjualanService penjualanService;

    public PenjualanController() {
        this.penjualanService = new PenjualanService();
    }

    // ===================== READ =====================

    public List<Penjualan> tampilkanSemuaPenjualan() {
        return penjualanService.getAll();
    }

    public Penjualan tampilkanPenjualanById(int id) {
        return penjualanService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahPenjualan(
            int noPenjualan,
            LocalDate tanggalPenjualan,
            String namaCustomer,
            String alamatCustomer,
            BigDecimal totalPenjualan,
            Penjualan.MetodePembayaran metodePembayaran,
            Penjualan.StatusPembayaran statusPembayaran,
            String keterangan,
            int idAdministrasi
    ) {
        Penjualan penjualan = new Penjualan(
                noPenjualan,
                tanggalPenjualan,
                namaCustomer,
                alamatCustomer,
                totalPenjualan,
                metodePembayaran,
                statusPembayaran,
                keterangan
        );

        return penjualanService.tambahPenjualan(penjualan, idAdministrasi);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiPenjualan(
            int id,
            int noPenjualan,
            LocalDate tanggalPenjualan,
            String namaCustomer,
            String alamatCustomer,
            BigDecimal totalPenjualan,
            Penjualan.MetodePembayaran metodePembayaran,
            Penjualan.StatusPembayaran statusPembayaran,
            String keterangan,
            int idAdministrasi
    ) {
        Penjualan penjualan = new Penjualan(
                id,
                noPenjualan,
                tanggalPenjualan,
                namaCustomer,
                alamatCustomer,
                totalPenjualan,
                metodePembayaran,
                statusPembayaran,
                keterangan
        );

        return penjualanService.perbaruiPenjualan(penjualan, idAdministrasi);
    }

    // ===================== DELETE =====================

    public boolean hapusPenjualan(int id, int idAdministrasi) {
        return penjualanService.hapusPenjualan(id, idAdministrasi);
    }
}