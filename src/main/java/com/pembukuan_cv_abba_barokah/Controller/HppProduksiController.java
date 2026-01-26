package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class HppProduksiController {

    // ===== INPUT TANGGAL PRODUKSI =====
    @FXML
    private DatePicker tanggalProduksiPicker;

    // ===== INPUT FIELDS =====
    @FXML private TextField namaProdukField;
    @FXML private TextField jumlahProduksiField;
    @FXML private TextField bahanMentahField;
    @FXML private TextField tenagaKerjaField;
    @FXML private TextField overheadField;
    @FXML private TextField biayaProsesField;

    // ===== OUTPUT FIELD =====
    @FXML
    private TextField hppUnitField;

    // ===== BUTTONS =====
    @FXML private Button btnHitung;
    @FXML private Button btnSimpan;

    @FXML
    private void initialize() {
        btnSimpan.setDisable(true);
        hppUnitField.setText("");

        // default UX: isi hari ini (boleh diubah user)
        tanggalProduksiPicker.setValue(LocalDate.now());
    }

    // ===== HITUNG HPP =====
    @FXML
    private void handleHitung() {
        boolean valid = true;

        TextField[] fields = {
                namaProdukField,
                jumlahProduksiField,
                bahanMentahField,
                tenagaKerjaField,
                overheadField,
                biayaProsesField
        };

        for (TextField field : fields) {
            field.getStyleClass().remove("error");
            if (field.getText().trim().isEmpty()) {
                field.getStyleClass().add("error");
                valid = false;
            }
        }

        if (!valid || tanggalProduksiPicker.getValue() == null) {
            btnSimpan.setDisable(true);
            hppUnitField.setText("");
            return;
        }

        try {
            int jumlahProduksi = Integer.parseInt(jumlahProduksiField.getText());
            double bahanMentah = Double.parseDouble(bahanMentahField.getText());
            double tenagaKerja = Double.parseDouble(tenagaKerjaField.getText());
            double overhead = Double.parseDouble(overheadField.getText());
            double biayaProses = Double.parseDouble(biayaProsesField.getText());

            if (jumlahProduksi <= 0) {
                jumlahProduksiField.getStyleClass().add("error");
                btnSimpan.setDisable(true);
                return;
            }

            double totalHpp =
                    bahanMentah + tenagaKerja + overhead + biayaProses;

            double hppPerUnit = totalHpp / jumlahProduksi;

            hppUnitField.setText(formatRupiah(hppPerUnit));
            btnSimpan.setDisable(false);

        } catch (NumberFormatException e) {
            btnSimpan.setDisable(true);
            hppUnitField.setText("");
        }
    }

    // ===== SIMPAN =====
    @FXML
    private void handleSimpan() {
        System.out.println("HPP Produksi disimpan");
        System.out.println("Tanggal Produksi : " + tanggalProduksiPicker.getValue());
    }

    private String formatRupiah(double value) {
        return String.format("Rp %, .0f", value).replace(",", ".");
    }
}