package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DataTransaksiController {

    // ===== INPUT FIELDS =====
    @FXML private TextField noFakturField;
    @FXML private DatePicker tanggalField;

    // ===== BUTTONS =====
    @FXML private Button btnSimpan;
    @FXML private Button btnReset;

    @FXML
    private void initialize() {
        // UX default
        noFakturField.setText("");
        tanggalField.setValue(null);
    }

    // ===== SIMPAN TRANSAKSI (MASTER DATA) =====
    @FXML
    private void handleSimpan() {
        boolean valid = true;

        // Reset error state
        noFakturField.getStyleClass().remove("error");
        tanggalField.getStyleClass().remove("error");

        String noFaktur = noFakturField.getText();
        LocalDate tanggal = tanggalField.getValue();

        if (noFaktur == null || noFaktur.trim().isEmpty()) {
            noFakturField.getStyleClass().add("error");
            valid = false;
        }

        if (tanggal == null) {
            tanggalField.getStyleClass().add("error");
            valid = false;
        }

        if (!valid) {
            return;
        }

        // =============================
        // TODO: Simpan ke tabel transaksi
        // transaksi(no_faktur, tanggal)
        // =============================
        System.out.println("Transaksi disimpan:");
        System.out.println("No Faktur : " + noFaktur);
        System.out.println("Tanggal   : " + tanggal);

        handleReset();
    }

    // ===== RESET FORM =====
    @FXML
    private void handleReset() {
        noFakturField.clear();
        tanggalField.setValue(null);
    }
}