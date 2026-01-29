package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Service.BiayaPemasaranService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BiayaPemasaranController {

    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<BiayaPemasaran.MarketingExpenseType> marketingTypeBox;
    @FXML private TextField jumlahField;
    @FXML private TextField idAdministrasiField;
    @FXML private TextArea deskripsiArea;
    @FXML private Button btnSimpan;

    private final BiayaPemasaranService biayaPemasaranService = new BiayaPemasaranService();

    @FXML
    private void initialize() {
        marketingTypeBox.getItems().addAll(
                BiayaPemasaran.MarketingExpenseType.values()
        );
    }

    @FXML
    private void handleSimpan() {

        try {
            BiayaPemasaran biaya = new BiayaPemasaran(
                    tanggalField.getValue(),
                    deskripsiArea.getText(),
                    Integer.parseInt(jumlahField.getText()),
                    BiayaPemasaran.ExpenseCategory.PEMASARAN,
                    marketingTypeBox.getValue(),
                    Integer.parseInt(idAdministrasiField.getText())
            );

            boolean sukses = biayaPemasaranService.tambahBiayaPemasaran(biaya);

            Alert alert = new Alert(
                    sukses ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR
            );
            alert.setHeaderText(null);
            alert.setContentText(
                    sukses ? "Biaya pemasaran berhasil disimpan."
                           : "Gagal menyimpan biaya pemasaran."
            );
            alert.showAndWait();

            if (sukses) clearForm();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,
                    "Input tidak valid. Periksa kembali data."
            ).showAndWait();
        }
    }

    private void clearForm() {
        tanggalField.setValue(null);
        marketingTypeBox.getSelectionModel().clearSelection();
        jumlahField.clear();
        idAdministrasiField.clear();
        deskripsiArea.clear();
    }
}