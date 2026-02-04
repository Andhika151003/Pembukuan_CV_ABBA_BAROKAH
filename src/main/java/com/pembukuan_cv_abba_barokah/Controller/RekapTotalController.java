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
    @FXML private Label lblTotalBiayaOperasional;

    @FXML private Label lblLabaKotor;
    @FXML private Label lblPph11;
    @FXML private Label lblPajakBelumDisetor;
    @FXML private Label lblLabaBersih;

    private final RekapTotalService service = new RekapTotalService();

    @FXML
    public void initialize() {
        refresh();
    }

    private void refresh() {
        lblTotalPenjualan.setText(rupiah(service.totalPenjualan()));
        lblTotalHpp.setText(rupiah(service.totalHpp()));
        lblTotalPembayaran.setText(rupiah(service.totalPembayaranMasuk()));
        lblTotalBiayaPemasaran.setText(rupiah(service.totalBiayaPemasaran()));
        lblTotalBiayaOperasional.setText(rupiah(service.totalBiayaOperasional()));
        lblTotalSetorPajak.setText(rupiah(service.totalSetorPajak()));

        lblLabaKotor.setText(rupiah(service.labaKotor()));
        lblPph11.setText(rupiah(service.pph11Persen()));
        lblPajakBelumDisetor.setText(rupiah(service.pajakBelumDisetor()));
        lblLabaBersih.setText(rupiah(service.labaBersih()));
    }

    private String rupiah(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(value);
    }
}