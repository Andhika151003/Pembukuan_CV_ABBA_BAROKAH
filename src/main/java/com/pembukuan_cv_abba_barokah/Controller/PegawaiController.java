package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Pegawai;
import com.pembukuan_cv_abba_barokah.Service.PegawaiService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PegawaiController {

    private final PegawaiService pegawaiService;

    public PegawaiController() {
        this.pegawaiService = new PegawaiService();
    }

    // ===================== READ =====================

    public List<Pegawai> tampilkanSemuaPegawai() {
        return pegawaiService.getAllPegawai();
    }

    public Pegawai tampilkanPegawaiById(int id) {
        return pegawaiService.getPegawaiById(id);
    }

    public List<Pegawai> tampilkanPegawaiAktif() {
        return pegawaiService.getPegawaiAktif();
    }

    // ===================== CREATE =====================

    public boolean tambahPegawai(
            String nama,
            String jabatan,
            Pegawai.StatusPegawai statusPegawai,
            BigDecimal gajiPokok,
            LocalDate tanggalMasuk,
            Pegawai.StatusAktif statusAktif
    ) {
        Pegawai pegawai = new Pegawai(
                nama,
                jabatan,
                statusPegawai,
                gajiPokok,
                tanggalMasuk,
                statusAktif
        );

        return pegawaiService.simpanPegawai(pegawai);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiPegawai(
            int id,
            String nama,
            String jabatan,
            Pegawai.StatusPegawai statusPegawai,
            BigDecimal gajiPokok,
            LocalDate tanggalMasuk,
            Pegawai.StatusAktif statusAktif
    ) {
        Pegawai pegawai = new Pegawai(
                id,
                nama,
                jabatan,
                statusPegawai,
                gajiPokok,
                tanggalMasuk,
                statusAktif
        );

        return pegawaiService.perbaruiPegawai(pegawai);
    }

    // ===================== DELETE =====================

    public boolean hapusPegawai(int id) {
        return pegawaiService.hapusPegawai(id);
    }
}