package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.GajiPegawai;
import com.pembukuan_cv_abba_barokah.Service.GajiPegawaiService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GajiPegawaiController {

    private final GajiPegawaiService gajiPegawaiService;

    public GajiPegawaiController() {
        this.gajiPegawaiService = new GajiPegawaiService();
    }

    // ===================== READ =====================

    public List<GajiPegawai> tampilkanSemuaGaji() {
        return gajiPegawaiService.getAll();
    }

    // ===================== CREATE / PAY =====================

    public boolean bayarGajiPegawai(
            int idPegawai,
            String periode,
            BigDecimal gajiPokok,
            BigDecimal tunjangan,
            BigDecimal potongan,
            LocalDate tanggalPembayaran,
            GajiPegawai.Status statusPembayaran
    ) {
        GajiPegawai gaji = new GajiPegawai(
                idPegawai,
                periode,
                gajiPokok,
                tunjangan,
                potongan,
                BigDecimal.ZERO,      // total dihitung di Service
                tanggalPembayaran,
                statusPembayaran
        );

        return gajiPegawaiService.bayarGaji(gaji);
    }

    // ===================== UPDATE STATUS =====================

    public boolean updateStatusGaji(
            int id,
            int idPegawai,
            String periode,
            BigDecimal gajiPokok,
            BigDecimal tunjangan,
            BigDecimal potongan,
            BigDecimal totalGaji,
            LocalDate tanggalPembayaran,
            GajiPegawai.Status statusPembayaran
    ) {
        GajiPegawai gaji = new GajiPegawai(
                id,
                idPegawai,
                periode,
                gajiPokok,
                tunjangan,
                potongan,
                totalGaji,
                tanggalPembayaran,
                statusPembayaran
        );

        return gajiPegawaiService.updateStatusGaji(gaji);
    }

    // ===================== DELETE =====================

    public boolean hapusRiwayatGaji(int id) {
        return gajiPegawaiService.hapusRiwayatGaji(id);
    }
}