package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Service.SetorPajakService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SetorPajakController {

    private final SetorPajakService pajakService;

    public SetorPajakController() {
        this.pajakService = new SetorPajakService();
    }

    // ===================== READ =====================

    public List<SetorPajak> tampilkanSemuaSetorPajak() {
        return pajakService.getAllPajak();
    }

    public SetorPajak tampilkanSetorPajakById(int id) {
        return pajakService.getPajakById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahSetorPajak(
            LocalDate tanggalSetor,
            String jenisPajak,
            BigDecimal jumlahPajak,
            String periode,
            String buktiSetor
    ) {
        SetorPajak pajak = new SetorPajak(
                tanggalSetor,
                jenisPajak,
                jumlahPajak,
                periode,
                buktiSetor
        );

        return pajakService.simpanSetorPajak(pajak);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiSetorPajak(
            int id,
            LocalDate tanggalSetor,
            String jenisPajak,
            BigDecimal jumlahPajak,
            String periode,
            String buktiSetor
    ) {
        SetorPajak pajak = new SetorPajak(
                id,
                tanggalSetor,
                jenisPajak,
                jumlahPajak,
                periode,
                buktiSetor
        );

        return pajakService.updatePajak(pajak);
    }

    // ===================== DELETE =====================

    public boolean hapusSetorPajak(int id) {
        return pajakService.hapusPajak(id);
    }
}