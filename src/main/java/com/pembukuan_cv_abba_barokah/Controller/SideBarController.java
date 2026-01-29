package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SideBarController {

    @FXML
    private StackPane contentPane;

    @FXML
    private StackPane logoutOverlay;

    // ===== UTIL =====
    private void showContent(String text) {
        contentPane.getChildren().clear();

        Label label = new Label(text);
        label.getStyleClass().add("content-placeholder");

        contentPane.getChildren().add(label);
    }

    // ===== INPUT DATA =====

    @FXML
    private void openAdministrasi() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/BiayaAdministrasiMain.fxml");
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

    // ===== LOGOUT =====
    @FXML
    private void handleLogout() {
        logoutOverlay.setVisible(true);
        logoutOverlay.setManaged(true);
    }

    @FXML
    private void cancelLogout() {
        logoutOverlay.setVisible(false);
        logoutOverlay.setManaged(false);
    }

    @FXML
    private void confirmLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/pembukuan_cv_abba_barokah/View/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutOverlay.getScene().getWindow();
            stage.setScene(new Scene(root, 900, 600));
            stage.setTitle("SiKeu ABBA - Login");
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
