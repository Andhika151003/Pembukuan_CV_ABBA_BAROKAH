package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Service.AdministrasiService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class BiayaAdministrasiController {

    // ================= FIELD (FXML) =================
    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<Administrasi.TipeAdministrasi> jenisBiayaBox;
    @FXML private TextField jumlahBiayaField;
    @FXML private TextField keteranganField;
    @FXML private Button btnSimpan;

    private AdministrasiService administrasiService;

    // ================= INIT =================
    @FXML
    private void initialize() {
        administrasiService = new AdministrasiService();

        // Isi ComboBox dari Enum Model
        jenisBiayaBox.getItems().setAll(Administrasi.TipeAdministrasi.values());

        // Validasi awal
        validateForm();

        // Listener validasi realtime
        tanggalField.valueProperty().addListener((obs, o, n) -> validateForm());
        jenisBiayaBox.valueProperty().addListener((obs, o, n) -> validateForm());
        jumlahBiayaField.textProperty().addListener((obs, o, n) -> validateForm());
    }

    // ================= VALIDASI =================
    private void validateForm() {
        boolean valid =
                tanggalField.getValue() != null &&
                jenisBiayaBox.getValue() != null &&
                !jumlahBiayaField.getText().isBlank();

        btnSimpan.setDisable(!valid);
    }

    // ================= SIMPAN =================
    @FXML
    private void handleSimpan() {
        try {
            BigDecimal jumlah = new BigDecimal(jumlahBiayaField.getText());

            Administrasi administrasi = new Administrasi(
                    tanggalField.getValue(),
                    jenisBiayaBox.getValue(),
                    jenisBiayaBox.getValue().toString(), // deskripsi
                    jumlah,
                    keteranganField.getText()
            );

            boolean sukses = administrasiService.simpanBaru(administrasi);

            if (sukses) {
                showAlert(Alert.AlertType.INFORMATION,
                        "Sukses",
                        "Data biaya administrasi berhasil disimpan.");
                resetForm();
            } else {
                showAlert(Alert.AlertType.ERROR,
                        "Gagal",
                        "Data biaya administrasi gagal disimpan.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING,
                    "Input Tidak Valid",
                    "Jumlah biaya harus berupa angka.");
        }
    }

    // ================= RESET =================
    private void resetForm() {
        tanggalField.setValue(null);
        jenisBiayaBox.setValue(null);
        jumlahBiayaField.clear();
        keteranganField.clear();
        btnSimpan.setDisable(true);
    }

    // ================= ALERT =================
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}