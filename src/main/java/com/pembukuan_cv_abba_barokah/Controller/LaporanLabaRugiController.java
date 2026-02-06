package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.RekapTotalService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class LaporanLabaRugiController {

    @FXML
    private ComboBox<String> cbTahun;

    @FXML
    private Label lblTotalPenjualan;
    @FXML
    private Label lblReturPenjualan;
    @FXML
    private Label lblTotalPendapatan;

    @FXML
    private Label lblTotalHpp;
    @FXML
    private Label lblReturPembelian;
    @FXML
    private Label lblTotalPembelian;

    @FXML
    private Label lblLabaRugi;

    @FXML
    private Label lblBiayaAdministrasi;
    @FXML
    private Label lblBiayaOperasional;
    @FXML
    private Label lblBiayaPemasaran;
    @FXML
    private Label lblBiayaLain;

    @FXML
    private Label lblTotalBiayaOperasional;
    @FXML
    private Label lblLabaBersih;

    private final RekapTotalService rekapService = new RekapTotalService();

    @FXML
    public void initialize() {
        cbTahun.getItems().addAll("2024", "2025", "2026");
        cbTahun.setValue("2026");
        loadData();
    }

    @FXML
    public void loadData() {

        BigDecimal totalPenjualan = rekapService.totalPenjualan();
        BigDecimal returPenjualan = BigDecimal.ZERO;

        BigDecimal totalPendapatan = totalPenjualan.subtract(returPenjualan);

        BigDecimal totalHpp = rekapService.totalHPP();
        BigDecimal returPembelian = BigDecimal.ZERO;

        BigDecimal totalPembelian = totalHpp.subtract(returPembelian);

        BigDecimal labaRugi = totalPendapatan.subtract(totalPembelian);

        BigDecimal biayaAdministrasi = BigDecimal.ZERO;
        BigDecimal biayaPemasaran = rekapService.totalBiayaPemasaran();
        BigDecimal biayaOperasional = BigDecimal.ZERO;
        BigDecimal biayaLain = BigDecimal.ZERO;

        BigDecimal totalBiayaOperasional = biayaAdministrasi
                .add(biayaPemasaran)
                .add(biayaOperasional)
                .add(biayaLain);

        BigDecimal labaBersih = labaRugi.subtract(totalBiayaOperasional);

        // ===== SET KE LABEL =====
        lblTotalPenjualan.setText(rp(totalPenjualan));
        lblReturPenjualan.setText(rp(returPenjualan));
        lblTotalPendapatan.setText(rp(totalPendapatan));

        lblTotalHpp.setText(rp(totalHpp));
        lblReturPembelian.setText(rp(returPembelian));
        lblTotalPembelian.setText(rp(totalPembelian));

        lblLabaRugi.setText(rp(labaRugi));

        lblBiayaAdministrasi.setText(rp(biayaAdministrasi));
        lblBiayaOperasional.setText(rp(biayaOperasional));
        lblBiayaPemasaran.setText(rp(biayaPemasaran));
        lblBiayaLain.setText(rp(biayaLain));

        lblTotalBiayaOperasional.setText(rp(totalBiayaOperasional));
        lblLabaBersih.setText(rp(labaBersih));
    }

    private String rp(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(value);
    }
}