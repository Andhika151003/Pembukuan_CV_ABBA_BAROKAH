package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SetorPajakController {

    // ===== HEADER =====
    @FXML
    private ComboBox<String> masaPajakBox;
    @FXML
    private ComboBox<String> jenisPajakBox;
    @FXML
    private DatePicker tanggalSetorField;
    @FXML
    private ComboBox<String> metodeBayarBox;
    @FXML
    private TextField buktiSetorField;
    @FXML
    private Label statusLabel;
    @FXML
    private ComboBox<String> periodePelaporanBox;
    @FXML
    private Label sumberPajakLabel;

    // ===== AUTO HITUNG =====
    @FXML
    private Label totalPenjualanLabel;
    @FXML
    private Label dppLabel;
    @FXML
    private Label ppnLabel;
    @FXML
    private Label totalPajakLabel;

    // ===== KETERANGAN =====
    @FXML
    private TextArea keteranganArea;

    @FXML
    private Button btnSimpan;

    // ===== DATA DUMMY (NANTI DARI BACKEND) =====
    private double totalPenjualan = 15_000_000;

    private StatusSetor status = StatusSetor.BELUM_SETOR;

    // ===== INIT =====
    @FXML
    private void initialize() {

        masaPajakBox.getItems().addAll("Januari 2026", "Februari 2026", "Maret 2026");
        jenisPajakBox.getItems().addAll("PPN", "PPh");
        periodePelaporanBox.getItems().addAll(
                "Bulanan",
                "Tahunan");
        sumberPajakLabel.setText("Penjualan");

        metodeBayarBox.getItems().addAll(
                "Transfer Bank", "E-Billing", "Virtual Account");

        // AUTO ISI TOTAL PENJUALAN
        totalPenjualanLabel.setText(formatRupiah(totalPenjualan));

        hitungPajak();
        validateForm();

        // ===== LISTENER =====
        masaPajakBox.valueProperty().addListener((a, b, c) -> validateForm());
        jenisPajakBox.valueProperty().addListener((a, b, c) -> {
            hitungPajak();
            validateForm();
        });

        tanggalSetorField.valueProperty().addListener((a, b, c) -> validateForm());
        metodeBayarBox.valueProperty().addListener((a, b, c) -> validateForm());
        buktiSetorField.textProperty().addListener((a, b, c) -> validateForm());

        updateStatus();
    }

    private enum StatusSetor {
        BELUM_SETOR,
        SIAP_DISETOR,
        SUDAH_SETOR
    }

    // ===== HITUNG PAJAK =====
    private void hitungPajak() {

        if (!"PPN".equals(jenisPajakBox.getValue())) {
            dppLabel.setText("Rp 0");
            ppnLabel.setText("Rp 0");
            totalPajakLabel.setText("Rp 0");
            return;
        }

        double dpp = totalPenjualan;
        double ppn = dpp * 0.11;

        dppLabel.setText(formatRupiah(dpp));
        ppnLabel.setText(formatRupiah(ppn));
        totalPajakLabel.setText(formatRupiah(ppn));
    }

    // ===== VALIDASI =====
    private void validateForm() {

        boolean valid = masaPajakBox.getValue() != null &&
                jenisPajakBox.getValue() != null &&
                tanggalSetorField.getValue() != null &&
                metodeBayarBox.getValue() != null &&
                !buktiSetorField.getText().isBlank();

        btnSimpan.setDisable(!valid);

        if (status != StatusSetor.SUDAH_SETOR) {
            status = valid ? StatusSetor.SIAP_DISETOR : StatusSetor.BELUM_SETOR;
            updateStatus();
        }
    }

    private void updateStatus() {

        switch (status) {

            case BELUM_SETOR -> {
                statusLabel.setText("BELUM SETOR");
                statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                lockForm(false);
            }

            case SIAP_DISETOR -> {
                statusLabel.setText("SIAP DISETOR");
                statusLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
                lockForm(false);
            }

            case SUDAH_SETOR -> {
                statusLabel.setText("SUDAH SETOR");
                statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                lockForm(false);
            }
        }
    }

    private void lockForm(boolean lock) {

        masaPajakBox.setDisable(lock);
        jenisPajakBox.setDisable(lock);
        tanggalSetorField.setDisable(lock);
        metodeBayarBox.setDisable(lock);
        buktiSetorField.setDisable(lock);
        keteranganArea.setDisable(lock);
    }

    // ===== SIMPAN =====
    @FXML
    private void handleSimpan() {

        status = StatusSetor.SUDAH_SETOR;
        updateStatus();

        btnSimpan.setDisable(true);

        System.out.println("Data setor pajak siap dikirim ke backend");
    }

    // ===== FORMAT =====
    private String formatRupiah(double value) {
        return String.format("Rp %,.0f", value).replace(",", ".");
    }
}