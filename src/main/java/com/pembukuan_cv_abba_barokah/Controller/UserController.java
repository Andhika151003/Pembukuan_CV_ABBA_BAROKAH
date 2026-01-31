package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.User;
import com.pembukuan_cv_abba_barokah.Service.UserService;
import com.pembukuan_cv_abba_barokah.DAO.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserController {

    private final UserService userService;
    private final UserDao userDao;

    public UserController() {
        this.userService = new UserService();
        this.userDao = new UserDao();
    }

    // ===================== LOGIN =====================
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

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
            messageLabel.setText("Login berhasil sebagai: " + user.getRole());
            messageLabel.getStyleClass().setAll("success");

            // TODO: redirect dashboard
        } else {
            messageLabel.setText("Username atau password salah!");
            messageLabel.getStyleClass().setAll("error");
        }
    }

    // ===================== REGISTER =====================
    @FXML private TextField regUsernameField;
    @FXML private PasswordField regPasswordField;
    @FXML private Label regMessageLabel;

    @FXML
    private void handleRegister() {
        String username = regUsernameField.getText();
        String password = regPasswordField.getText();

        if (username.isBlank() || password.isBlank()) {
            regMessageLabel.setText("Semua field wajib diisi!");
            regMessageLabel.getStyleClass().setAll("error");
            return;
        }

        // cek username sudah ada
        if (userDao.getByUsername(username) != null) {
            regMessageLabel.setText("Username sudah digunakan!");
            regMessageLabel.getStyleClass().setAll("error");
            return;
        }

        String hashedPassword = User.hashPassword(password);

        User user = new User(
            0,
            username,
            hashedPassword,
            "user 1"
        );

        boolean success = userDao.save(user);

        if (success) {
            regMessageLabel.setText("Registrasi berhasil! Silakan login.");
            regMessageLabel.getStyleClass().setAll("success");
        } else {
            regMessageLabel.setText("Registrasi gagal!");
            regMessageLabel.getStyleClass().setAll("error");
        }
    }
}