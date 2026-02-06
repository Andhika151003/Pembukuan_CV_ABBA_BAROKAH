package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.NeracaKeuanganService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NeracaKeuanganController {

    @FXML private ComboBox<String> cbTahun;

    @FXML private Label lblBank;
    @FXML private Label lblPiutang;
    @FXML private Label lblPersediaan;
    @FXML private Label lblTotalAsetLancar;

    @FXML private Label lblInventaris;
    @FXML private Label lblTotalAsetTidakLancar;

    @FXML private Label lblJumlahAset;
    @FXML private Label lblUtangUsaha;
    @FXML private Label lblEkuitas;
    @FXML private Label lblJumlahKewajibanEkuitas;

    private final NeracaKeuanganService service = new NeracaKeuanganService();
    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    public void initialize() {
        cbTahun.getItems().addAll("2024", "2025", "2026");
        cbTahun.setValue("2026");
        loadData();
    }

    @FXML
    private void loadData() {

        lblBank.setText(format(service.bankSaldo()));
        lblPiutang.setText(format(service.piutangUsaha()));
        lblPersediaan.setText(format(service.persediaanBarang()));
        lblTotalAsetLancar.setText(format(service.totalAsetLancar()));

        lblInventaris.setText(format(service.totalInventaris()));
        lblTotalAsetTidakLancar.setText(format(service.totalAsetTidakLancar()));

        lblJumlahAset.setText(format(service.totalAset()));

        lblUtangUsaha.setText(format(service.totalUtangUsaha()));
        lblEkuitas.setText(format(service.ekuitas()));
        lblJumlahKewajibanEkuitas.setText(format(service.jumlahKewajibanDanEkuitas()));
    }

    private String format(BigDecimal nilai) {
        return rupiah.format(nilai);
    }
}