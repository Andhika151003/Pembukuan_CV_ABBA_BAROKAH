package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
import com.pembukuan_cv_abba_barokah.Service.PembayaranService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PembayaranController {

    private final PembayaranService pembayaranService;

    public PembayaranController() {
        this.pembayaranService = new PembayaranService();
    }

    // ===================== READ =====================

    public List<Pembayaran> tampilkanSemuaPembayaran() {
        return pembayaranService.getAllPembayaran();
    }

    public Pembayaran tampilkanPembayaranById(int id) {
        return pembayaranService.getPembayaranById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahPembayaran(
            int idTransaksi,
            int idPenjualan,
            int idPembelian,
            int idUtang,
            LocalDate tanggalPembayaran,
            BigDecimal jumlahPembayaran,
            String metodePembayaran,
            String keterangan
    ) {
        Pembayaran pembayaran = new Pembayaran(
                idTransaksi,
                idPenjualan,
                idPembelian,
                idUtang,
                tanggalPembayaran,
                jumlahPembayaran,
                metodePembayaran,
                keterangan
        );

        return pembayaranService.simpanPembayaran(pembayaran);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiPembayaran(
            int id,
            int idTransaksi,
            int idPenjualan,
            int idPembelian,
            int idUtang,
            LocalDate tanggalPembayaran,
            BigDecimal jumlahPembayaran,
            String metodePembayaran,
            String keterangan
    ) {
        Pembayaran pembayaran = new Pembayaran(
                id,
                idTransaksi,
                idPenjualan,
                idPembelian,
                idUtang,
                tanggalPembayaran,
                jumlahPembayaran,
                metodePembayaran,
                keterangan
        );

        return pembayaranService.updatePembayaran(pembayaran);
    }

    // ===================== DELETE =====================

    public boolean hapusPembayaran(int id) {
        return pembayaranService.hapusPembayaran(id);
    }
}