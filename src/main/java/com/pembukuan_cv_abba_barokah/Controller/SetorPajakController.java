package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Service.SetorPajakService;

import java.util.List;

public class SetorPajakController {

    private final SetorPajakService pajakService;

    public SetorPajakController() {
        this.pajakService = new SetorPajakService();
    }

    /* ===================== READ ===================== */

    public List<SetorPajak> getAllSetorPajak() {
        return pajakService.getAllPajak();
    }

    public SetorPajak getSetorPajakById(int id) {
        if (id <= 0) return null;
        return pajakService.getPajakById(id);
    }

    /* ===================== CREATE ===================== */

    public boolean simpanSetorPajak(SetorPajak pajak) {
        if (!isValidSetorPajak(pajak)) return false;
        return pajakService.simpanSetorPajak(pajak);
    }

    /* ===================== UPDATE ===================== */

    public boolean updateSetorPajak(SetorPajak pajak) {
        if (pajak == null || pajak.getId() <= 0) return false;
        if (!isValidSetorPajak(pajak)) return false;

        return pajakService.updatePajak(pajak);
    }

    /* ===================== DELETE ===================== */

    public boolean hapusSetorPajak(int id) {
        if (id <= 0) return false;
        return pajakService.hapusPajak(id);
    }

    /* ===================== VALIDATION ===================== */

    private boolean isValidSetorPajak(SetorPajak pajak) {
        if (pajak == null) return false;
        if (pajak.getTanggal_Setor() == null) return false;
        if (pajak.getJenis_Pajak() == null || pajak.getJenis_Pajak().isEmpty()) return false;
        if (pajak.getJumlah_Pajak() == null || pajak.getJumlah_Pajak().signum() <= 0) return false;
        if (pajak.getPeriode() == null || pajak.getPeriode().isEmpty()) return false;
        if (pajak.getIdAdministrasi() <= 0) return false;

        // bukti setor boleh null (opsional), tapi kalau ada tidak boleh kosong
        if (pajak.getBukti_Setor() != null && pajak.getBukti_Setor().isEmpty()) {
            return false;
        }

        return true;
    }
}