package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String VALID_USERNAME = "bams";
    private static final String VALID_PASSWORD = "bams123";

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            showAlert(Alert.AlertType.INFORMATION,
                    "Login Success",
                    "Welcome to SiKeu ABBA!");
            loadDashboard();
        } else {
            showAlert(Alert.AlertType.ERROR,
                    "Login Failed",
                    "Username or password is incorrect!");
        }
    }

    private void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    LoginController.class.getResource("/com.pembukuan_cv_abba_barokah/View/SideBar.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("SiKeu ABBA");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
