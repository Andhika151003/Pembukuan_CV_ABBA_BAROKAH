package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.NeracaLabaRugi;
import com.pembukuan_cv_abba_barokah.Service.NeracaLabaRugiService;

import java.util.List;

public class NeracaLabaRugiController {

    private final NeracaLabaRugiService labaRugiService;

    public NeracaLabaRugiController() {
        this.labaRugiService = new NeracaLabaRugiService();
    }

    // ===================== HITUNG & VIEW =====================

    /**
     * Menghitung laporan laba rugi berdasarkan tahun tertentu.
     * Seluruh perhitungan dilakukan di Service.
     */
    public NeracaLabaRugi hitungLabaRugiTahunan(int tahun) {
        return labaRugiService.hitungLabaRugiTahunan(tahun);
    }

    // ===================== SAVE =====================

    /**
     * Menyimpan laporan laba rugi yang sudah dihitung.
     */
    public boolean simpanLaporan(NeracaLabaRugi laporan) {
        return labaRugiService.simpanLaporan(laporan);
    }

    // ===================== READ =====================

    /**
     * Menampilkan seluruh riwayat laporan laba rugi.
     */
    public List<NeracaLabaRugi> tampilkanSemuaRiwayat() {
        return labaRugiService.getSemuaRiwayat();
    }
}