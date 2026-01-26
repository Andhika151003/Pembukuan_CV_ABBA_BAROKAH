package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.NeracaLabaRugi;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.time.Year;
import java.util.Locale;

public class LaporanLabaController {

    @FXML private Label tahunLabel;

    @FXML private Label penjualanLabel;
    @FXML private Label returPenjualanLabel;
    @FXML private Label hppLabel;
    @FXML private Label labaKotorLabel;
    @FXML private Label biayaAdminLabel;
    @FXML private Label biayaOperasionalLabel;
    @FXML private Label biayaPemasaranLabel;
    @FXML private Label labaBersihLabel;

    @FXML private Button btnSimpan;

    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    private void initialize() {
        int tahun = Year.now().getValue();
        tahunLabel.setText("Tahun : " + tahun);

        // ===== SIMULASI DATA TAHUNAN (NANTI DARI BACKEND) =====
        LaporanLaba laporan = new LaporanLaba(
                tahun,
                18000000,
                750000,
                12000000,
                5250000,
                750000,
                750000,
                750000,
                3000000
        );

        tampilkanLaporan(laporan);
    }

    private void tampilkanLaporan(LaporanLaba l) {
        penjualanLabel.setText(rupiah.format(l.getPenjualan()));
        returPenjualanLabel.setText(rupiah.format(l.getReturPenjualan()));
        hppLabel.setText(rupiah.format(l.getHpp()));
        labaKotorLabel.setText(rupiah.format(l.getLabaKotor()));

        biayaAdminLabel.setText(rupiah.format(l.getBiayaAdministrasi()));
        biayaOperasionalLabel.setText(rupiah.format(l.getBiayaOperasional()));
        biayaPemasaranLabel.setText(rupiah.format(l.getBiayaPemasaran()));

        labaBersihLabel.setText(rupiah.format(l.getLabaBersih()));
    }

    @FXML
    private void handleSimpan() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Simpan Laporan Laba");
        alert.setContentText("Apakah laporan laba tahunan sudah sesuai?");

        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                // backend: export DOCX
                System.out.println("Laporan laba tahunan dikirim ke backend");
            }
        });
    }
}