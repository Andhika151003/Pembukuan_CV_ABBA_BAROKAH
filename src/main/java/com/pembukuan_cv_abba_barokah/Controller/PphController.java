package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PphController {

    @FXML private Label labaKotorLabel;
    @FXML private Label pphLabel;

    // ===== DATA DUMMY (NANTI DARI PERHITUNGAN LABA) =====
    private double labaKotor = 10_000_000;

    @FXML
    private void initialize() {
        labaKotorLabel.setText(formatRupiah(labaKotor));

        double pph = labaKotor * 0.11;
        pphLabel.setText(formatRupiah(pph));
    }

    private String formatRupiah(double value) {
        return String.format("Rp %,.0f", value).replace(",", ".");
    }
}
