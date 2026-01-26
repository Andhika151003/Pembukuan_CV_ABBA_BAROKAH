package com.pembukuan_cv_abba_barokah.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

public class HppBarangJadiController {

    // ===== AUTO TRANSAKSI =====
    @FXML private ComboBox<String> noFakturCombo;
    @FXML private TextField tanggalField;

    // ===== INPUT FIELDS =====
    @FXML private TextField namaBarangField;
    @FXML private TextField jumlahProduksiField;
    @FXML private TextField bahanBakuField;
    @FXML private TextField tenagaKerjaField;
    @FXML private TextField overheadField;

    // ===== OUTPUT FIELD =====
    @FXML private TextField hppUnitField;

    // ===== BUTTONS =====
    @FXML private Button btnHitung;
    @FXML private Button btnSimpan;

    // ===== SIMULASI DATA TRANSAKSI (NANTI GANTI DB) =====
    private final Map<String, String> transaksiMap = new HashMap<>();

    @FXML
    private void initialize() {
        btnSimpan.setDisable(true);
        hppUnitField.setText("");
        tanggalField.setText("");

        // ===== DATA DUMMY TRANSAKSI =====
        transaksiMap.put("INV-001", "2026-01-10");
        transaksiMap.put("INV-002", "2026-01-12");

        noFakturCombo.setItems(
                FXCollections.observableArrayList(transaksiMap.keySet())
        );

        // AUTO ISI TANGGAL SAAT FAKTUR DIPILIH
        noFakturCombo.setOnAction(e -> {
            String faktur = noFakturCombo.getValue();
            tanggalField.setText(transaksiMap.getOrDefault(faktur, ""));
        });
    }

    // ===== HITUNG HPP =====
    @FXML
    private void handleHitung() {
        boolean valid = true;

        TextField[] fields = {
                namaBarangField,
                jumlahProduksiField,
                bahanBakuField,
                tenagaKerjaField,
                overheadField
        };

        for (TextField field : fields) {
            field.getStyleClass().remove("error");
            if (field.getText().trim().isEmpty()) {
                field.getStyleClass().add("error");
                valid = false;
            }
        }

        if (!valid) {
            btnSimpan.setDisable(true);
            hppUnitField.setText("");
            return;
        }

        try {
            int jumlahProduksi = Integer.parseInt(jumlahProduksiField.getText());
            double bahanBaku = Double.parseDouble(bahanBakuField.getText());
            double tenagaKerja = Double.parseDouble(tenagaKerjaField.getText());
            double overhead = Double.parseDouble(overheadField.getText());

            if (jumlahProduksi <= 0) {
                jumlahProduksiField.getStyleClass().add("error");
                btnSimpan.setDisable(true);
                return;
            }

            double totalHpp = bahanBaku + tenagaKerja + overhead;
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
        System.out.println("HPP Barang Jadi disimpan");
        System.out.println("Faktur  : " + noFakturCombo.getValue());
        System.out.println("Tanggal : " + tanggalField.getText());
    }

    // ===== FORMAT RUPIAH =====
    private String formatRupiah(double value) {
        return String.format("Rp %, .0f", value).replace(",", ".");
    }
}