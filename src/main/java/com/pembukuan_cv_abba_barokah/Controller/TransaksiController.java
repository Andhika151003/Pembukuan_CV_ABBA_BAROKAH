package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Transaksi;
import com.pembukuan_cv_abba_barokah.Service.TransaksiService;

import java.util.List;

public class TransaksiController {

    private final TransaksiService transaksiService;

    public TransaksiController() {
        this.transaksiService = new TransaksiService();
    }

    /* ===================== READ ===================== */

    public List<Transaksi> getAllTransaksi() {
        return transaksiService.getAllTransaksi();
    }

    public Transaksi getTransaksiById(int id) {
        if (id <= 0) return null;
        return transaksiService.getTransaksiById(id);
    }

    /* ===================== CREATE ===================== */

    public boolean simpanTransaksi(Transaksi transaksi) {
        if (!isValidTransaksi(transaksi)) return false;
        return transaksiService.simpanTransaksi(transaksi);
    }

    /* ===================== UPDATE ===================== */

    public boolean updateTransaksi(Transaksi transaksi) {
        if (transaksi == null || transaksi.getId() <= 0) return false;
        if (!isValidTransaksi(transaksi)) return false;

        return transaksiService.updateTransaksi(transaksi);
    }

    /* ===================== DELETE ===================== */

    public boolean hapusTransaksi(int id) {
        if (id <= 0) return false;
        return transaksiService.hapusTransaksi(id);
    }

    /* ===================== VALIDATION ===================== */

    private boolean isValidTransaksi(Transaksi t) {
        if (t == null) return false;
        if (t.getTanggal_transaksi() == null) return false;
        if (t.getNomor_Faktur() == null || t.getNomor_Faktur().isEmpty()) return false;
        if (t.getNama_Barang() == null || t.getNama_Barang().isEmpty()) return false;
        if (t.getKuantitas() <= 0) return false;
        if (t.getHarga_Jual() == null || t.getHarga_Jual().signum() <= 0) return false;
        if (t.getStatus_Pembayaran() == null) return false;
        if (t.getIdAdministrasi() <= 0) return false;

        // Jika status LUNAS, tanggal pembayaran wajib ada
        if (t.getStatus_Pembayaran() == Transaksi.Status.LUNAS &&
            t.getTanggal_Pembayaran() == null) {
            return false;
        }

        return true;
    }
}