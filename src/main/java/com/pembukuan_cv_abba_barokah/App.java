package com.pembukuan_cv_abba_barokah;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/pembukuan_cv_abba_barokah/View/LoginView.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            stage.setTitle("Sistem Pembukuan CV ABBA BAROKAH");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Gagal memuat file FXML. Pastikan path benar.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}