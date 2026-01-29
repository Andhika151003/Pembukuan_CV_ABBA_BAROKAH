package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class BiayaAdministrasiMainController {

    @FXML
    private StackPane contentPane;

    @FXML
    public void initialize() {
        // Default saat pertama dibuka
        openBiayaAdministrasi();
    }

    @FXML
    private void openBiayaAdministrasi() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/BiayaAdministrasi.fxml");
    }

    @FXML
    private void openPegawai() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Pegawai.fxml");
    }

    @FXML
    private void openGajiPegawai() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/GajiPegawai.fxml");
    }

    private void loadContent(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent view = loader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}