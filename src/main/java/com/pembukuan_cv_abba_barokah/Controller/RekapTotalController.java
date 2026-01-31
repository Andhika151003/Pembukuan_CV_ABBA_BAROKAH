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
    @FXML private Label lblTotalPembayaranMasuk;
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
        lblTotalHpp.setText(rupiah(service.getTotalHpp()));
        lblTotalPenjualan.setText(rupiah(service.getTotalPenjualan()));
        lblTotalPembayaranMasuk.setText(rupiah(service.getTotalPembayaranMasuk()));
        lblTotalSetorPajak.setText(rupiah(service.getTotalSetorPajak()));
        lblTotalBiayaPemasaran.setText(rupiah(service.getTotalBiayaPemasaran()));
        lblTotalBiayaOperasional.setText(rupiah(service.getTotalBiaya()));
        lblLabaKotor.setText(rupiah(service.getLabaKotor()));
        lblPph11.setText(rupiah(service.getPph11Persen()));
        lblPajakBelumDisetor.setText(rupiah(service.getPajakBelumDisetor()));
        lblLabaBersih.setText(rupiah(service.getLabaBersih()));
    }

    private String rupiah(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(value);
    }
}