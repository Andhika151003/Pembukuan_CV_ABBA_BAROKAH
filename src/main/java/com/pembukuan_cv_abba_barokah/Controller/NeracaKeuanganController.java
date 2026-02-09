package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.NeracaKeuanganService;
import com.pembukuan_cv_abba_barokah.Service.NeracaKeuanganWordExporter;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

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

    @FXML
    private void handleExportWord() {

        String tahun = cbTahun.getValue();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Neraca Keuangan");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Word Files", "*.docx"));
        fileChooser.setInitialFileName("Neraca_Keuangan_" + tahun + ".docx");

        Stage stage = (Stage) cbTahun.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {

                NeracaKeuanganWordExporter.export(
                        file,
                        tahun,

                        lblBank.getText(),
                        lblPiutang.getText(),
                        lblPersediaan.getText(),
                        lblJumlahAsetLancar.getText(),
                        lblInventaris.getText(),
                        lblJumlahAsetTidakLancar.getText(),
                        lblTotalAset.getText(),

                        lblUtang.getText(),
                        lblTotalKewajiban.getText(),
                        lblEkuitas.getText(),
                        lblModal.getText(),
                        lblTotalEkuitas.getText(),
                        lblJumlahKewajibanEkuitas.getText());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}