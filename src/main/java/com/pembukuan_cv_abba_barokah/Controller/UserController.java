package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.DAO.UserDao;
import com.pembukuan_cv_abba_barokah.Model.User;
import com.pembukuan_cv_abba_barokah.Service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserController {

    private final UserService userService = new UserService();
    private final UserDao userDao = new UserDao();

    /* ================= LOGIN ================= */
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            messageLabel.setText("Username dan password wajib diisi!");
            messageLabel.getStyleClass().setAll("error");
            return;
        }

        User user = userService.login(username, password);

        if (user != null) {
            try {
                // LOAD SIDEBAR
                Parent root = FXMLLoader.load(
                        getClass().getResource(
                                "/com/pembukuan_cv_abba_barokah/View/SideBar.fxml"));

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();

            } catch (Exception e) {
                e.printStackTrace();
                messageLabel.setText("Gagal membuka dashboard!");
                messageLabel.getStyleClass().setAll("error");
            }
        } else {
            messageLabel.setText("Username atau password salah!");
            messageLabel.getStyleClass().setAll("error");
        }
    }

    /* ================= REGISTER ================= */
    @FXML
    private TextField regUsernameField;
    @FXML
    private PasswordField regPasswordField;
    @FXML
    private Label regMessageLabel;

    @FXML
    private void handleRegister() {
        String username = regUsernameField.getText();
        String password = regPasswordField.getText();

        if (username.isBlank() || password.isBlank()) {
            regMessageLabel.setText("Semua field wajib diisi!");
            regMessageLabel.getStyleClass().setAll("error");
            return;
        }

        if (userDao.getByUsername(username) != null) {
            regMessageLabel.setText("Username sudah digunakan!");
            regMessageLabel.getStyleClass().setAll("error");
            return;
        }

        User user = new User(
                0,
                username,
                User.hashPassword(password),
                "user 1");

        if (userDao.save(user)) {
            regMessageLabel.setText("Registrasi berhasil! Silakan login.");
            regMessageLabel.getStyleClass().setAll("success");
        } else {
            regMessageLabel.setText("Registrasi gagal!");
            regMessageLabel.getStyleClass().setAll("error");
        }
    }

    /* ================= NAVIGATION ================= */
    @FXML
    private void goToLogin(ActionEvent event) {
        switchScene(event, "/com/pembukuan_cv_abba_barokah/View/LoginView.fxml");
    }

    @FXML
    private void goToRegister(ActionEvent event) {
        switchScene(event, "/com/pembukuan_cv_abba_barokah/View/RegisterView.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}