package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import com.pembukuan_cv_abba_barokah.Service.PersediaanBarangService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PersediaanBarangController {

    private final PersediaanBarangService persediaanService;

    public PersediaanBarangController() {
        this.persediaanService = new PersediaanBarangService();
    }

    // ===================== READ =====================

    public List<PersediaanBarang> tampilkanSemuaPersediaan() {
        return persediaanService.getAll();
    }

    public PersediaanBarang tampilkanPersediaanById(int id) {
        return persediaanService.getById(id);
    }

    // ===================== CREATE =====================

    public boolean tambahPersediaan(
            LocalDate tanggal,
            String namaBarang,
            String satuan,
            PersediaanBarang.JenisTransaksi jenisTransaksi,
            int jumlahMasuk,
            int jumlahKeluar,
            BigDecimal hargaSatuan,
            String keterangan
    ) {
        // saldo_Akhir diisi dummy → akan dihitung ulang oleh Service
        PersediaanBarang persediaan = new PersediaanBarang(
                tanggal,
                namaBarang,
                satuan,
                jenisTransaksi,
                jumlahMasuk,
                jumlahKeluar,
                BigDecimal.ZERO,   // saldo akhir dihitung service
                hargaSatuan,
                keterangan
        );

        return persediaanService.tambahPersediaan(persediaan);
    }

    // ===================== UPDATE =====================

    public boolean perbaruiPersediaan(
            int id,
            LocalDate tanggal,
            String namaBarang,
            String satuan,
            PersediaanBarang.JenisTransaksi jenisTransaksi,
            int jumlahMasuk,
            int jumlahKeluar,
            BigDecimal saldoAkhir,
            BigDecimal hargaSatuan,
            String keterangan
    ) {
        PersediaanBarang persediaan = new PersediaanBarang(
                id,
                tanggal,
                namaBarang,
                satuan,
                jenisTransaksi,
                jumlahMasuk,
                jumlahKeluar,
                saldoAkhir,
                hargaSatuan,
                keterangan
        );

        return persediaanService.perbaruiPersediaan(persediaan);
    }

    // ===================== DELETE =====================

    public boolean hapusPersediaan(int id) {
        return persediaanService.hapusPersediaan(id);
    }
}
