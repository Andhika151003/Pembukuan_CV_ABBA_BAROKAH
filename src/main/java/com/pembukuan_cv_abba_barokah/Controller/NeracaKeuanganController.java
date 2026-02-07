package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.NeracaKeuanganService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NeracaKeuanganController {

    @FXML
    private ComboBox<String> cbTahun;

    // ASET
    @FXML
    private Label lblBank;
    @FXML
    private Label lblPiutang;
    @FXML
    private Label lblPersediaan;
    @FXML
    private Label lblJumlahAsetLancar;

    @FXML
    private Label lblInventaris;
    @FXML
    private Label lblJumlahAsetTidakLancar;
    @FXML
    private Label lblTotalAset;

    // KEWAJIBAN
    @FXML
    private Label lblUtang;
    @FXML
    private Label lblTotalKewajiban;

    // EKUITAS
    @FXML
    private Label lblEkuitas;
    @FXML
    private Label lblModal;
    @FXML
    private Label lblTotalEkuitas;
    @FXML
    private Label lblJumlahKewajibanEkuitas;

    private final NeracaKeuanganService service = new NeracaKeuanganService();

    @FXML
    public void initialize() {
        cbTahun.getItems().addAll("2025", "2026", "2027");
        cbTahun.setValue("2026");
        loadData();
    }

    @FXML
    public void loadData() {

        String tahun = cbTahun.getValue();

        lblBank.setText(rp(service.bank(tahun)));
        lblPiutang.setText(rp(service.piutang(tahun)));
        lblPersediaan.setText(rp(service.persediaan(tahun)));
        lblJumlahAsetLancar.setText(rp(service.asetLancar(tahun)));

        lblInventaris.setText(rp(service.inventaris(tahun)));
        lblJumlahAsetTidakLancar.setText(rp(service.asetTidakLancar(tahun)));
        lblTotalAset.setText(rp(service.totalAset(tahun)));

        lblUtang.setText(rp(service.utang(tahun)));
        lblTotalKewajiban.setText(rp(service.totalKewajiban(tahun)));

        lblEkuitas.setText(rp(service.ekuitas(tahun)));
        lblModal.setText(rp(service.modal(tahun)));
        lblTotalEkuitas.setText(rp(service.totalEkuitas(tahun)));
        lblJumlahKewajibanEkuitas.setText(
                rp(service.jumlahKewajibanDanEkuitas(tahun)));

    }

    private String rp(BigDecimal value) {
        return NumberFormat
                .getCurrencyInstance(new Locale("id", "ID"))
                .format(value);
    }
}