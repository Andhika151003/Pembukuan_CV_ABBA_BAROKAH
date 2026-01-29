package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Service.UtangUsahaService;

import java.util.List;

public class UtangUsahaController {

    private final UtangUsahaService utangService;

    public UtangUsahaController() {
        this.utangService = new UtangUsahaService();
    }

    /* ===================== READ ===================== */

    public List<UtangUsaha> getAllUtang() {
        return utangService.getAll();
    }

    public UtangUsaha getUtangById(int id) {
        if (id <= 0) return null;
        return utangService.getById(id);
    }

    /* ===================== CREATE ===================== */

    public boolean tambahUtang(UtangUsaha utang) {
        if (!isValidUtangBaru(utang)) return false;

        // pastikan sisa utang benar sebelum simpan
        utang.hitungSisaUtang();

        return utangService.tambahUtang(utang);
    }

    /* ===================== UPDATE / BAYAR ===================== */

    public boolean bayarUtang(UtangUsaha utangBaru, int idAdministrasi) {
        if (!isValidUtangUpdate(utangBaru)) return false;
        if (idAdministrasi <= 0) return false;

        // hitung ulang sisa utang sebelum update
        utangBaru.hitungSisaUtang();

        return utangService.bayarUtang(utangBaru, idAdministrasi);
    }

    /* ===================== DELETE ===================== */

    public boolean hapusUtang(int idUtang, int idAdministrasi) {
        if (idUtang <= 0) return false;
        if (idAdministrasi <= 0) return false;

        return utangService.hapusUtang(idUtang, idAdministrasi);
    }

    /* ===================== VALIDATION ===================== */

    private boolean isValidUtangBaru(UtangUsaha u) {
        if (u == null) return false;
        if (u.getNo_Utang() == null || u.getNo_Utang().isEmpty()) return false;
        if (u.getTanggal_Utang() == null) return false;
        if (u.getTanggal_Jatuh_Tempo() == null) return false;
        if (u.getJumlah_Utang() == null || u.getJumlah_Utang().signum() <= 0) return false;
        if (u.getJumlah_Dibayar() == null || u.getJumlah_Dibayar().signum() < 0) return false;
        if (u.getStatus_Utang() == null) return false;

        return true;
    }

    private boolean isValidUtangUpdate(UtangUsaha u) {
        if (!isValidUtangBaru(u)) return false;
        if (u.getId() <= 0) return false;
        return true;
    }
}