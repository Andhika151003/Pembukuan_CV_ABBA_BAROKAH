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
    private void openHpp() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Hpp.fxml");
    }

    @FXML
    private void openPenjualan() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Penjualan.fxml");
    }

    @FXML
    private void openPembayaran() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Pembayaran.fxml");
    }

    @FXML
    private void openSetorPajak() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/SetorPajak.fxml");
    }

    @FXML
    private void openPemasaran() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/BiayaPemasaran.fxml");
    }

    @FXML
    private void openPemeliharaan() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/BiayaPemeliharaan.fxml");
    }

    @FXML
    private void openAdministrasi() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/BiayaAdministrasi.fxml");
    }

    @FXML
    private void openInventaris() {
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
    private void openPersediaan() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PersediaanBarang.fxml");
    }

    @FXML
    private void openUtang() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/UtangUsaha.fxml");
    }

    @FXML
    private void openPelunasanUtang() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PelunasanUtang.fxml");
    }

    @FXML
    private void openPiutang() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PiutangUsaha.fxml");
    }

    @FXML
    private void openPelunasanPiutang() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PelunasanPiutang.fxml");
    }

    @FXML
    private void openModal() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/Modal.fxml");
    }

    @FXML
    private void openRekap() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/RekapTotal.fxml");
    }

    @FXML
    private void openPajak() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/PajakBelumDisetor.fxml");
    }

    @FXML
    private void openLabaRugi() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/LabaRugi.fxml");
    }

    @FXML
    private void openJurnal() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/JurnalPembukuan.fxml");
    }

    @FXML
    private void openCashflow() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/CashflowPenjualan.fxml");
    }

    @FXML
    private void openNeraca() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/NeracaKeuangan.fxml");
    }

    @FXML
    private void openLaporanLaba() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/LaporanLabaRugi.fxml");
    }

    @FXML
    private void openSaldo() {
        loadContent("/com/pembukuan_cv_abba_barokah/View/SaldoBankTahunLalu.fxml");
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
