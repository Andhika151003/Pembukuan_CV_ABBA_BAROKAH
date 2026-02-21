package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.RekapTotalService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class RekapTotalController {

    @FXML
    private ComboBox<Integer> cbTahun;
    @FXML
    private Label lblTotalHpp;
    @FXML
    private Label lblTotalPenjualan;
    @FXML
    private Label lblTotalPembayaran;
    @FXML
    private Label lblTotalSetorPajak;
    @FXML
    private Label lblTotalBiayaPemasaran;

    private final RekapTotalService service = new RekapTotalService();

    @FXML
    public void initialize() {
        cbTahun.getItems().addAll(2026, 2027, 2028);
        cbTahun.setValue(2026);

        cbTahun.setOnAction(e -> refresh());
        refresh();
    }

    private void refresh() {

        Integer tahun = cbTahun.getValue();
    
        lblTotalHpp.setText(rupiah(service.totalHPP(tahun)));
        lblTotalPenjualan.setText(rupiah(service.totalPenjualan(tahun)));
        lblTotalPembayaran.setText(rupiah(service.totalPembayaranMasuk(tahun)));
        lblTotalBiayaPemasaran.setText(rupiah(service.totalBiayaPemasaran(tahun)));
        lblTotalSetorPajak.setText(rupiah(service.totalSetorPajak(tahun)));
    }    

    private String rupiah(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(
                new Locale("id", "ID"));
        return format.format(value);
    }
}