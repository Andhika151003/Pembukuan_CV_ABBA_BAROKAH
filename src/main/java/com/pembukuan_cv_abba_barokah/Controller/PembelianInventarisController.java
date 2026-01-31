package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PembelianInventarisController {

    private final PembelianInventarisService inventarisService;

    public PembelianInventarisController() {
        this.inventarisService = new PembelianInventarisService();
    }

    // ===================== READ =====================

    public List<PembelianInventaris> tampilkanSemuaPembelian() {
        return inventarisService.getAll();
    }

    public PembelianInventaris tampilkanPembelianById(int id) {
        return inventarisService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahPembelianInventaris(
            int noPembelian,
            LocalDate tanggalPembelian,
            PembelianInventaris.JenisInventaris jenisInventaris,
            String namaBarang,
            int jumlah,
            String satuan,
            BigDecimal hargaSatuan,
            BigDecimal ongkosKirim,
            PembelianInventaris.MetodePembayaran metodePembayaran,
            PembelianInventaris.StatusPembelian status,
            String keterangan
    ) {
        PembelianInventaris pembelian = new PembelianInventaris(
                noPembelian,
                tanggalPembelian,
                jenisInventaris,
                namaBarang,
                jumlah,
                satuan,
                hargaSatuan,
                ongkosKirim,
                BigDecimal.ZERO, // totalHarga dihitung di Service
                metodePembayaran,
                status,
                keterangan
        );

        return inventarisService.tambahPembelian(pembelian);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiPembelianInventaris(
            int id,
            int noPembelian,
            LocalDate tanggalPembelian,
            PembelianInventaris.JenisInventaris jenisInventaris,
            String namaBarang,
            int jumlah,
            String satuan,
            BigDecimal hargaSatuan,
            BigDecimal ongkosKirim,
            PembelianInventaris.MetodePembayaran metodePembayaran,
            PembelianInventaris.StatusPembelian status,
            String keterangan
    ) {
        PembelianInventaris pembelian = new PembelianInventaris(
                id,
                noPembelian,
                tanggalPembelian,
                jenisInventaris,
                namaBarang,
                jumlah,
                satuan,
                hargaSatuan,
                ongkosKirim,
                BigDecimal.ZERO, // dihitung ulang di Service
                metodePembayaran,
                status,
                keterangan
        );

        return inventarisService.perbaruiPembelian(pembelian);
    }

    // ===================== DELETE =====================

    public boolean hapusPembelianInventaris(int id) {
        return inventarisService.hapusPembelian(id);
    }
}