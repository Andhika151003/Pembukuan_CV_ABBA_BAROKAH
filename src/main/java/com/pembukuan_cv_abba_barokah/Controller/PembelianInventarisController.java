package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class PembelianInventarisController {

    @FXML private DatePicker tanggalPicker;
    @FXML private TextField namaInventarisField;
    @FXML private ComboBox<String> kategoriBox;
    @FXML private TextField jumlahField;
    @FXML private TextField hargaField;
    @FXML private TextField totalField;
    @FXML private ComboBox<String> metodeBox;
    @FXML private TextArea keteranganArea;
    @FXML private Button btnSimpan;

    @FXML
    private void initialize() {
        tanggalPicker.setValue(LocalDate.now());

        kategoriBox.getItems().addAll(
                "Peralatan",
                "Mesin",
                "Kendaraan",
                "Perlengkapan Kantor"
        );

        metodeBox.getItems().addAll(
                "Tunai",
                "Transfer",
                "Utang Usaha"
        );

        jumlahField.textProperty().addListener((obs,o,n) -> hitungTotal());
        hargaField.textProperty().addListener((obs,o,n) -> hitungTotal());
    }

    private void hitungTotal() {
        try {
            int jumlah = Integer.parseInt(jumlahField.getText());
            double harga = Double.parseDouble(hargaField.getText());

            if (jumlah > 0 && harga > 0) {
                totalField.setText(formatRupiah(jumlah * harga));
                btnSimpan.setDisable(false);
            }
        } catch (Exception e) {
            totalField.setText("");
            btnSimpan.setDisable(true);
        }
    }

    @FXML
    private void handleSimpan() {
        System.out.println("Inventaris disimpan");
        System.out.println("Tanggal : " + tanggalPicker.getValue());
        System.out.println("Nama    : " + namaInventarisField.getText());
        System.out.println("Total   : " + totalField.getText());

        // nanti:
        // Debit Inventaris
        // Kredit Kas / Utang Usaha
    }

    private String formatRupiah(double value) {
        return String.format("Rp %, .0f", value).replace(",", ".");
    }
}