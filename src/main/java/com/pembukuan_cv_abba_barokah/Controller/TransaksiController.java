package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Transaksi;
import com.pembukuan_cv_abba_barokah.Service.TransaksiService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TransaksiController {

    private final TransaksiService transaksiService;

    public TransaksiController() {
        this.transaksiService = new TransaksiService();
    }

    // ===================== READ =====================

    public List<Transaksi> tampilkanSemuaTransaksi() {
        return transaksiService.getAllTransaksi();
    }

    public Transaksi tampilkanTransaksiById(int id) {
        return transaksiService.getTransaksiById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahTransaksi(
            LocalDate tanggalTransaksi,
            String nomorFaktur,
            String namaBarang,
            int kuantitas,
            BigDecimal hargaJual,
            Transaksi.Status statusPembayaran,
            LocalDate tanggalPembayaran,
            String keterangan,
            int idPenjualan
    ) {
        Transaksi transaksi = new Transaksi(
                tanggalTransaksi,
                nomorFaktur,
                namaBarang,
                kuantitas,
                hargaJual,
                BigDecimal.ZERO,        // total dihitung di Service
                statusPembayaran,
                tanggalPembayaran,
                keterangan,
                idPenjualan
        );

        return transaksiService.simpanTransaksi(transaksi);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiTransaksi(
            int id,
            LocalDate tanggalTransaksi,
            String nomorFaktur,
            String namaBarang,
            int kuantitas,
            BigDecimal hargaJual,
            Transaksi.Status statusPembayaran,
            LocalDate tanggalPembayaran,
            String keterangan,
            int idPenjualan
    ) {
        Transaksi transaksi = new Transaksi(
                id,
                tanggalTransaksi,
                nomorFaktur,
                namaBarang,
                kuantitas,
                hargaJual,
                BigDecimal.ZERO,        // dihitung ulang di Service
                statusPembayaran,
                tanggalPembayaran,
                keterangan,
                idPenjualan
        );

        return transaksiService.updateTransaksi(transaksi);
    }

    // ===================== DELETE =====================

    public boolean hapusTransaksi(int id) {
        return transaksiService.hapusTransaksi(id);
    }
}