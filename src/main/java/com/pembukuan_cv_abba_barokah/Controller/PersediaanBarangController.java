package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import com.pembukuan_cv_abba_barokah.Service.PersediaanBarangService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PersediaanBarangController {

    private final PersediaanBarangService persediaanBarangService;

    public PersediaanBarangController() {
        this.persediaanBarangService = new PersediaanBarangService();
    }

    /*
     * =========================
     * READ
     * =========================
     */

    public List<PersediaanBarang> getAllPersediaan() {
        return persediaanBarangService.getAll();
    }

    public PersediaanBarang getPersediaanById(int id) {
        return persediaanBarangService.getById(id);
    }

    /*
     * =========================
     * CREATE
     * =========================
     */

    public boolean tambahPersediaan(
            LocalDate tanggal,
            String namaBarang,
            String satuan,
            PersediaanBarang.JenisTransaksi jenisTransaksi,
            int jumlahMasuk,
            int jumlahKeluar,
            BigDecimal hargaSatuan,
            String keterangan) {

        // Validasi dasar
        if (tanggal == null || namaBarang == null || hargaSatuan == null) {
            return false;
        }

        PersediaanBarang persediaan = new PersediaanBarang(
                tanggal,
                namaBarang,
                satuan,
                jenisTransaksi,
                jumlahMasuk,
                jumlahKeluar,
                BigDecimal.ZERO, // saldo akhir dihitung di Service
                hargaSatuan,
                keterangan);

        return persediaanBarangService.tambahPersediaan(persediaan);
    }

    /*
     * =========================
     * UPDATE
     * =========================
     */

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
            String keterangan) {

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
                keterangan);

        return persediaanBarangService.perbaruiPersediaan(persediaan);
    }

    /*
     * =========================
     * DELETE
     * =========================
     */

    public boolean hapusPersediaan(int id) {
        return persediaanBarangService.hapusPersediaan(id);
    }
}