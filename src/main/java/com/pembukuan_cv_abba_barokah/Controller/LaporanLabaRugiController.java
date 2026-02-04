package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.LaporanLabaRugi;
import com.pembukuan_cv_abba_barokah.Service.LaporanLabaRugiService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class LaporanLabaRugiController {

    @FXML private Label lblTotalPenjualan;
    @FXML private Label lblReturPenjualan;
    @FXML private Label lblTotalPendapatan;

    @FXML private Label lblTotalHpp;
    @FXML private Label lblLabaKotor;

    @FXML private Label lblBiayaAdministrasi;
    @FXML private Label lblBiayaPemasaran;
    @FXML private Label lblTotalPajak;

    @FXML private Label lblTotalBiayaOperasional;
    @FXML private Label lblLabaBersih;

    private final LaporanLabaRugiService service = new LaporanLabaRugiService();

    @FXML
    public void initialize() {
        load(2026); // default
    }

    private void load(int tahun) {
        LaporanLabaRugi d = service.getByTahun(tahun);
        if (d == null) return;

        lblTotalPenjualan.setText(rp(d.getTotalPenjualan()));
        lblReturPenjualan.setText(rp(d.getTotalReturPenjualan()));
        lblTotalPendapatan.setText(rp(d.getTotalPendapatan()));

        lblTotalHpp.setText(rp(d.getTotalHpp()));
        lblLabaKotor.setText(rp(d.getLabaKotor()));

        lblBiayaAdministrasi.setText(rp(d.getBiayaAdministrasi()));
        lblBiayaPemasaran.setText(rp(d.getBiayaPemasaran()));
        lblTotalPajak.setText(rp(d.getTotalPajak()));

        lblTotalBiayaOperasional.setText(rp(d.getTotalBiayaOperasional()));
        lblLabaBersih.setText(rp(d.getLabaBersih()));
    }

    private String rp(BigDecimal v) {
        if (v == null) v = BigDecimal.ZERO;
        return NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(v);
    }
}