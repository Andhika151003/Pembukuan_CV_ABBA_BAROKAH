package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.RekapTotalService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class RekapTotalController {

    @FXML private Label lblTotalHpp;
    @FXML private Label lblTotalPenjualan;
    @FXML private Label lblTotalPembayaran;
    @FXML private Label lblTotalSetorPajak;
    @FXML private Label lblTotalBiayaPemasaran;

    private final RekapTotalService service = new RekapTotalService();

    @FXML
    public void initialize() {
        refresh();
    }

    private void refresh() {
        lblTotalHpp.setText(rupiah(service.totalHPP()));
        lblTotalPenjualan.setText(rupiah(service.totalPenjualan()));
        lblTotalPembayaran.setText(rupiah(service.totalPembayaranMasuk()));
        lblTotalBiayaPemasaran.setText(rupiah(service.totalBiayaPemasaran()));
        lblTotalSetorPajak.setText(rupiah(service.totalSetorPajak()));
    }

    private String rupiah(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(
                new Locale("id", "ID")
        );
        return format.format(value);
    }
}