package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
import com.pembukuan_cv_abba_barokah.Service.ReturPembelianService;

import java.util.List;

public class ReturPembelianController {

    private final ReturPembelianService returService;

    public ReturPembelianController() {
        this.returService = new ReturPembelianService();
    }

    /* ===================== READ ===================== */

    public List<ReturPembelian> getAllReturPembelian() {
        return returService.getAll();
    }

    public ReturPembelian getReturPembelianById(int id) {
        if (id <= 0) return null;
        return returService.getById(id);
    }

    /* ===================== CREATE ===================== */

    public boolean tambahReturPembelian(ReturPembelian retur, int idAdministrasi) {
        if (!isValidRetur(retur)) return false;
        return returService.tambahRetur(retur, idAdministrasi);
    }

    /* ===================== UPDATE ===================== */

    public boolean perbaruiReturPembelian(ReturPembelian retur, int idAdministrasi) {
        if (retur == null || retur.getId() <= 0) return false;
        if (!isValidRetur(retur)) return false;

        return returService.perbaruiRetur(retur, idAdministrasi);
    }

    /* ===================== DELETE ===================== */

    public boolean hapusReturPembelian(int idRetur, int idAdministrasi) {
        if (idRetur <= 0) return false;
        return returService.hapusRetur(idRetur, idAdministrasi);
    }

    /* ===================== VALIDATION ===================== */

    private boolean isValidRetur(ReturPembelian retur) {
        if (retur == null) return false;
        if (retur.getNo_Retur_Pembelian() == null || retur.getNo_Retur_Pembelian().isEmpty()) return false;
        if (retur.getTanggal_Retur() == null) return false;
        if (retur.getJumlah_Retur() <= 0) return false;
        if (retur.getNilai_Retur() == null || retur.getNilai_Retur().signum() <= 0) return false;
        if (retur.getAlasan_Retur() == null) return false;
        if (retur.getStatus_Retur() == null) return false;

        return true;
    }
}