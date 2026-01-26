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
    private void openDataTransaksi() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/DataTransaksi.fxml");
    }

    @FXML
    private void openHppBarang() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/HppBarangJadi.fxml");
    }

    @FXML
    private void openHppProduksi() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/HppProduksi.fxml");
    }

    @FXML
    private void openPenjualan() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Penjualan.fxml");
    }

    @FXML
    private void openPembayaran() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PembayaranPembeli.fxml");
    }

    @FXML
    private void openSetorPajak() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/SetorPajak.fxml");
    }

    @FXML
    private void openBiayaPemasaran() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/BiayaPemasaran.fxml");
    }

    @FXML
    private void openAdministrasi() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/BiayaAdministrasi.fxml");
    }

    @FXML
    private void openPembelianInventaris() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PembelianInventaris.fxml");
    }

    @FXML
    private void openReturPenjualan() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/ReturPenjualan.fxml");
    }

    @FXML
    private void openReturPembelian() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/ReturPembelian.fxml");
    }

    @FXML
    private void openPersediaanBarang() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PersediaanBarang.fxml");
    }

    @FXML
    private void openUtangUsaha() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/UtangUsaha.fxml");
    }

    @FXML
    private void openModal() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Modal.fxml");
    }

    // ===== PERHITUNGAN =====
    @FXML
    private void openRekap() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/RekapTotal.fxml");
    }

    @FXML
    private void openLaba() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/LabaKotor.fxml");
    }

    @FXML
    private void openPph() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Pph.fxml");
    }

    @FXML
    private void openBelumSetorPajak() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PajakBelumDisetor.fxml");
    }

    // ===== LAPORAN =====
    @FXML
    private void openJurnal() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/JurnalPembukuan.fxml");
    }

    @FXML
    private void openCashflow() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/CashflowPenjualan.fxml");
    }

    @FXML
    private void openLaporanLaba() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/LaporanLaba.fxml");
    }

    @FXML
    private void openLaporanNeracaKeuangan() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/LaporanNeraca.fxml");
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
