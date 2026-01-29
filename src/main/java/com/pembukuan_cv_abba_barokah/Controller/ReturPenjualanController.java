package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import com.pembukuan_cv_abba_barokah.Service.ReturPenjualanService;

import java.util.List;

public class ReturPenjualanController {

    private final ReturPenjualanService returService;

    public ReturPenjualanController() {
        this.returService = new ReturPenjualanService();
    }

    /* ===================== READ ===================== */

    public List<ReturPenjualan> getAllReturPenjualan() {
        return returService.getAll();
    }

    public ReturPenjualan getReturPenjualanById(int id) {
        if (id <= 0) return null;
        return returService.getById(id);
    }

    /* ===================== CREATE ===================== */

    public boolean tambahReturPenjualan(ReturPenjualan retur, int idAdministrasi) {
        if (!isValidRetur(retur)) return false;
        return returService.tambahRetur(retur, idAdministrasi);
    }

    /* ===================== UPDATE ===================== */

    public boolean perbaruiReturPenjualan(ReturPenjualan retur, int idAdministrasi) {
        if (retur == null || retur.getId() <= 0) return false;
        if (!isValidRetur(retur)) return false;

        return returService.perbaruiRetur(retur, idAdministrasi);
    }

    /* ===================== DELETE ===================== */

    public boolean hapusReturPenjualan(int idRetur, int idAdministrasi) {
        if (idRetur <= 0) return false;
        return returService.hapusRetur(idRetur, idAdministrasi);
    }

    /* ===================== VALIDATION ===================== */

    private boolean isValidRetur(ReturPenjualan retur) {
        if (retur == null) return false;
        if (retur.getNo_Retur() == null || retur.getNo_Retur().isEmpty()) return false;
        if (retur.getTanggal_Retur() == null) return false;
        if (retur.getJumlah_Retur() <= 0) return false;
        if (retur.getNilai_Retur() == null || retur.getNilai_Retur().signum() <= 0) return false;
        if (retur.getAlasan_Retur() == null) return false;
        if (retur.getStatus_Retur() == null) return false;
        if (retur.getJenis_Pengembalian() == null) return false;

        // Jika pengembalian bukan ganti barang, tanggal pengembalian wajib ada
        if (retur.getJenis_Pengembalian() != ReturPenjualan.JenisPengembalian.GANTI_BARANG &&
            retur.getTanggal_Pengembalian() == null) {
            return false;
        }

        return true;
    }
}