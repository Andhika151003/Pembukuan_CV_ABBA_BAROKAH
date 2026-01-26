package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class PembayaranPembeliController {

    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<String> fakturBox;
    @FXML private TextField namaPembeliField;
    @FXML private ComboBox<String> metodeBox;

    @FXML private Label totalFakturLabel;
    @FXML private Label sudahBayarLabel;
    @FXML private Label sisaLabel;
    @FXML private Label statusLabel;

    @FXML private TextField jumlahBayarField;
    @FXML private TextArea keteranganArea;
    @FXML private Label errorLabel;
    @FXML private Button btnSimpan;

    private double totalFaktur;
    private double sudahDibayar;

    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    public void initialize() {

        // AUTO TANGGAL
        tanggalField.setValue(LocalDate.now());

        // DUMMY FAKTUR (nanti dari DB / transaksi penjualan)
        fakturBox.getItems().addAll("INV-001", "INV-002", "INV-003");

        metodeBox.getItems().addAll("Tunai", "Transfer", "QRIS");

        btnSimpan.setDisable(true);

        // AUTO LOAD DATA SAAT FAKTUR DIPILIH
        fakturBox.valueProperty().addListener((obs, oldVal, faktur) -> {
            if (faktur != null) {
                loadDataByFaktur(faktur);
            }
        });

        jumlahBayarField.textProperty().addListener((obs, o, n) -> {
            validateInput();
            updateStatus();
        });
    }

    private void loadDataByFaktur(String faktur) {
        // ===== DUMMY DATA (SIMULASI DARI PENJUALAN) =====
        namaPembeliField.setText("Customer A");

        totalFaktur = 2_500_000;
        sudahDibayar = 1_000_000;

        refreshDetail();
    }

    private void refreshDetail() {
        totalFakturLabel.setText(rupiah.format(totalFaktur));
        sudahBayarLabel.setText(rupiah.format(sudahDibayar));
        sisaLabel.setText(rupiah.format(totalFaktur - sudahDibayar));
        statusLabel.setText("BELUM LUNAS");
        statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
    }

    private void updateStatus() {
        double bayar = parseNominal(jumlahBayarField.getText());
        double sisa = totalFaktur - sudahDibayar;

        if (bayar >= sisa && sisa > 0) {
            statusLabel.setText("LUNAS");
            statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        } else if (bayar > 0) {
            statusLabel.setText("CICILAN");
            statusLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
        }
    }

    private void validateInput() {
        errorLabel.setText("");

        if (fakturBox.getValue() == null ||
            metodeBox.getValue() == null ||
            jumlahBayarField.getText().isEmpty()) {
            btnSimpan.setDisable(true);
            return;
        }

        double bayar = parseNominal(jumlahBayarField.getText());
        double sisa = totalFaktur - sudahDibayar;

        if (bayar <= 0 || bayar > sisa) {
            errorLabel.setText("Jumlah bayar tidak valid");
            btnSimpan.setDisable(true);
            return;
        }

        btnSimpan.setDisable(false);
    }

    private double parseNominal(String text) {
        try {
            return Double.parseDouble(text.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    @FXML
    private void handleSimpan() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Berhasil");
        alert.setContentText("Pembayaran berhasil disimpan\nStatus: " + statusLabel.getText());
        alert.showAndWait();
    }
}