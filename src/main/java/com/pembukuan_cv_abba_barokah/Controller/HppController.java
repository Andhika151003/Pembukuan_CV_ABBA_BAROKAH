package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HppController {

    @FXML
    private StackPane contentPane;

    // ===== UTIL =====
    private void showContent(String text) {
        contentPane.getChildren().setAll(new Label(text));
    }

    // ===== NAVIGATION =====

    @FXML
    private void openPembelianLangsung() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PembelianLangsung.fxml");
    }

    @FXML
    private void openSwakelola() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Swakelola.fxml");
    }

    private void loadContent(String fxml) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxml));
            contentPane.getChildren().setAll(view);
        } catch (Exception e) {
            showContent("Gagal memuat halaman");
            e.printStackTrace();
        }
    }
}