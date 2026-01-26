package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RekapTotalController {

    // ===== TOTAL FIELD (READ ONLY) =====
    @FXML private TextField totalHppField;
    @FXML private TextField totalPenjualanField;
    @FXML private TextField totalPembayaranField;
    @FXML private TextField totalSetorPajakField;
    @FXML private TextField totalBiayaPemasaranField;
    @FXML private TextField totalBiayaAdministrasiField;
    @FXML private TextField totalPembelianInventarisField;
    @FXML private TextField totalReturPenjualanField;
    @FXML private TextField totalReturPembelianField;
    @FXML private TextField totalPersediaanBarangField;
    @FXML private TextField totalUtangUsahaField;
    @FXML private TextField totalModalField;

    // ===== INIT =====
    @FXML
    private void initialize() {

        // ===== DATA DUMMY (NANTI DARI SERVICE / BACKEND) =====
        double totalHpp = 12_000_000;
        double totalPenjualan = 18_500_000;
        double totalPembayaran = 15_000_000;
        double totalSetorPajak = 1_200_000;
        double totalBiayaPemasaran = 750_000;
        double totalBiayaAdministrasi = 750_000;
        double totalPembelianInventaris = 750_000;
        double totalReturPenjualan = 750_000;
        double totalReturPembelian = 750_000;
        double totalPersediaanBarang = 750_000;
        double totalUtangUsaha = 750_000;
        double totalModal = 750_000;

        // ===== SET KE UI =====
        totalHppField.setText(formatRupiah(totalHpp));
        totalPenjualanField.setText(formatRupiah(totalPenjualan));
        totalPembayaranField.setText(formatRupiah(totalPembayaran));
        totalSetorPajakField.setText(formatRupiah(totalSetorPajak));
        totalBiayaPemasaranField.setText(formatRupiah(totalBiayaPemasaran));
        totalBiayaAdministrasiField.setText(formatRupiah(totalBiayaAdministrasi));
        totalPembelianInventarisField.setText(formatRupiah(totalPembelianInventaris));
        totalReturPenjualanField.setText(formatRupiah(totalReturPenjualan));
        totalReturPembelianField.setText(formatRupiah(totalReturPembelian));
        totalPersediaanBarangField.setText(formatRupiah(totalPersediaanBarang));
        totalUtangUsahaField.setText(formatRupiah(totalUtangUsaha));
        totalModalField.setText(formatRupiah(totalModal));
    }

    // ===== FORMAT RUPIAH =====
    private String formatRupiah(double value) {
        return String.format("Rp %,.0f", value).replace(",", ".");
    }
}