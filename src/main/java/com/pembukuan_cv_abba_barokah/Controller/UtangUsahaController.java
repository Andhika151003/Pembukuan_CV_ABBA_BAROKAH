package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class UtangUsahaController {

    @FXML private ComboBox<String> fakturBox;
    @FXML private TextField tanggalField;
    @FXML private TextField supplierField;
    @FXML private TextField totalField;
    @FXML private TextField sisaField;
    @FXML private TextField bayarField;
    @FXML private ComboBox<String> metodeBox;
    @FXML private Label statusLabel;
    @FXML private Button btnSimpan;

    @FXML
    private void initialize() {
        fakturBox.getItems().add("PB-001");
        metodeBox.getItems().addAll("Tunai", "Transfer");

        tanggalField.setText(LocalDate.now().toString());
        supplierField.setText("CV Sumber Makmur");
        totalField.setText("5000000");
        sisaField.setText("5000000");

        bayarField.textProperty().addListener((obs,o,n) -> validasi());
    }

    private void validasi() {
        try {
            double bayar = Double.parseDouble(bayarField.getText());
            double sisa = Double.parseDouble(sisaField.getText());

            if (bayar > 0 && bayar <= sisa) {
                statusLabel.setText(bayar == sisa ? "LUNAS" : "CICILAN");
                btnSimpan.setDisable(false);
            }
        } catch (Exception e) {
            btnSimpan.setDisable(true);
        }
    }

    @FXML
    private void handleSimpan() {
        System.out.println("Pembayaran utang disimpan");
        // Debit Utang Usaha
        // Kredit Kas
    }
}