package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BiayaAdministrasiController {

    // ===== FIELD =====
    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<String> jenisBiayaBox;
    @FXML private TextField jumlahBiayaField;
    @FXML private TextField keteranganField;

    @FXML private Button btnSimpan;

    // ===== INIT =====
    @FXML
    private void initialize() {

        // Isi sesuai draft
        jenisBiayaBox.getItems().addAll(
                "Gaji Pegawai",
                "Biaya Administrasi Lainnya"
        );

        validateForm();

        // Listener validasi
        tanggalField.valueProperty().addListener((a, b, c) -> validateForm());
        jenisBiayaBox.valueProperty().addListener((a, b, c) -> validateForm());
        jumlahBiayaField.textProperty().addListener((a, b, c) -> validateForm());
    }

    // ===== VALIDASI =====
    private void validateForm() {

        boolean valid =
                tanggalField.getValue() != null &&
                jenisBiayaBox.getValue() != null &&
                !jumlahBiayaField.getText().isBlank();

        btnSimpan.setDisable(!valid);
    }

    // ===== SIMPAN (FRONTEND ONLY) =====
    @FXML
    private void handleSimpan() {

        System.out.println("=== BIAYA ADMINISTRASI ===");
        System.out.println("Tanggal      : " + tanggalField.getValue());
        System.out.println("Jenis Biaya  : " + jenisBiayaBox.getValue());
        System.out.println("Jumlah       : " + jumlahBiayaField.getText());
        System.out.println("Keterangan   : " + keteranganField.getText());

        // reset optional (boleh, tidak melanggar draft)
        tanggalField.setValue(null);
        jenisBiayaBox.setValue(null);
        jumlahBiayaField.clear();
        keteranganField.clear();

        btnSimpan.setDisable(true);
    }
}