package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ModalController {

    @FXML private DatePicker tanggalPicker;
    @FXML private ComboBox<String> jenisBox;
    @FXML private TextField jumlahField;
    @FXML private TextField saldoField;
    @FXML private TextArea keteranganArea;
    @FXML private Button btnSimpan;

    // simulasi saldo modal
    private double saldoModal = 20_000_000;

    @FXML
    private void initialize() {
        tanggalPicker.setValue(LocalDate.now());
        jenisBox.getItems().addAll("Setoran Modal", "Prive");

        saldoField.setText(formatRupiah(saldoModal));

        jumlahField.textProperty().addListener((obs,o,n) -> validasi());
        jenisBox.valueProperty().addListener((obs,o,n) -> validasi());
    }

    private void validasi() {
        try {
            double jumlah = Double.parseDouble(jumlahField.getText());
            if (jumlah > 0 && jenisBox.getValue() != null) {
                btnSimpan.setDisable(false);
            } else {
                btnSimpan.setDisable(true);
            }
        } catch (Exception e) {
            btnSimpan.setDisable(true);
        }
    }

    @FXML
    private void handleSimpan() {
        double jumlah = Double.parseDouble(jumlahField.getText());

        if ("Setoran Modal".equals(jenisBox.getValue())) {
            saldoModal += jumlah;
        } else {
            saldoModal -= jumlah;
        }

        saldoField.setText(formatRupiah(saldoModal));
        System.out.println("Transaksi modal disimpan");
    }

    private String formatRupiah(double value) {
        return String.format("Rp %, .0f", value).replace(",", ".");
    }
}