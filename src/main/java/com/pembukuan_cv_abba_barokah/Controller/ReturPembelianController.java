package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ReturPembelianController {

    @FXML private DatePicker tanggalPicker;
    @FXML private ComboBox<String> fakturBox;
    @FXML private ComboBox<String> barangBox;
    @FXML private TextField jumlahField;
    @FXML private TextField hargaField;
    @FXML private TextField totalField;
    @FXML private TextArea alasanArea;
    @FXML private Button btnSimpan;

    @FXML
    private void initialize() {
        tanggalPicker.setValue(LocalDate.now());

        fakturBox.getItems().addAll("PB-001", "PB-002");
        barangBox.getItems().addAll("Bahan A", "Bahan B");

        hargaField.setText("120000");

        jumlahField.textProperty().addListener((obs,o,n) -> hitung());
    }

    private void hitung() {
        try {
            int qty = Integer.parseInt(jumlahField.getText());
            double harga = Double.parseDouble(hargaField.getText());

            if (qty > 0) {
                totalField.setText(formatRupiah(qty * harga));
                btnSimpan.setDisable(false);
            }
        } catch (Exception e) {
            totalField.setText("");
            btnSimpan.setDisable(true);
        }
    }

    @FXML
    private void handleSimpan() {
        System.out.println("Retur pembelian disimpan");
        // Debit Utang Usaha / Kas
        // Kredit Retur Pembelian
    }

    private String formatRupiah(double value) {
        return String.format("Rp %, .0f", value).replace(",", ".");
    }
}