package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Service.UtangUsahaService;

import java.util.List;

public class UtangUsahaController {

    private final UtangUsahaService utangService;

    public UtangUsahaController() {
        this.utangService = new UtangUsahaService();
    }

    // ================== READ ==================

    public List<UtangUsaha> getAllUtang() {
        return utangService.getAll();
    }

    public UtangUsaha getUtangById(int id) {
        return utangService.getById(id);
    }

    // ================== CREATE ==================

    /**
     * Mencatat utang baru
     */
    public boolean tambahUtang(UtangUsaha utang) {
        // hitung sisa utang awal
        utang.hitungSisaUtang();
        return utangService.tambahUtang(utang);
    }

    // ================== UPDATE / BAYAR ==================

    /**
     * Membayar atau memperbarui utang
     * @param utang Data utang terbaru
     * @param idAdministrasi akun kas/bank
     */
    public boolean bayarUtang(UtangUsaha utang, int idAdministrasi) {
        utang.hitungSisaUtang();
        return utangService.bayarUtang(utang, idAdministrasi);
    }

    // ================== DELETE ==================

    /**
     * Menghapus catatan utang
     */
    public boolean hapusUtang(int idUtang, int idAdministrasi) {
        return utangService.hapusUtang(idUtang, idAdministrasi);
    }
}