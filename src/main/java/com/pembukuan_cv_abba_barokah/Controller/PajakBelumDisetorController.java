package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PajakBelumDisetorController {

    @FXML private Label totalPphLabel;
    @FXML private Label totalSetorLabel;
    @FXML private Label sisaPajakLabel;

    // ===== DATA DUMMY (NANTI DARI AGREGASI) =====
    private double totalPph = 1_100_000;
    private double totalSetor = 600_000;

    @FXML
    private void initialize() {

        totalPphLabel.setText(formatRupiah(totalPph));
        totalSetorLabel.setText(formatRupiah(totalSetor));

        double sisa = totalPph - totalSetor;
        sisaPajakLabel.setText(formatRupiah(sisa));
    }

    private String formatRupiah(double value) {
        return String.format("Rp %,.0f", value).replace(",", ".");
    }
}