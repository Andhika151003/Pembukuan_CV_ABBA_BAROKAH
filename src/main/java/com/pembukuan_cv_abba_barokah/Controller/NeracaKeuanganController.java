package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.NeracaKeuangan;
import com.pembukuan_cv_abba_barokah.Service.NeracaKeuanganService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NeracaKeuanganController {

    @FXML private TableView<NeracaKeuangan> tableNeraca;
    @FXML private TableColumn<NeracaKeuangan, Integer> colTahun;
    @FXML private TableColumn<NeracaKeuangan, BigDecimal> colTotalAset;
    @FXML private TableColumn<NeracaKeuangan, BigDecimal> colTotalKewajiban;
    @FXML private TableColumn<NeracaKeuangan, BigDecimal> colTotalEkuitas;

    @FXML private DatePicker dpTanggal;
    @FXML private TextField tfTahun;
    @FXML private TextField tfKas;
    @FXML private TextField tfPiutang;
    @FXML private TextField tfPersediaan;
    @FXML private TextField tfPeralatan;
    @FXML private TextField tfTransportasi;
    @FXML private TextField tfAkumulasiPenyusutan;
    @FXML private TextField tfUtangUsaha;
    @FXML private TextField tfUtangJangkaPanjang;
    @FXML private TextField tfModalPemilik;
    @FXML private TextField tfLabaRugi;
    @FXML private TextField tfKeterangan;

    private final NeracaKeuanganService service = new NeracaKeuanganService();
    private final ObservableList<NeracaKeuangan> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTahun.setCellValueFactory(d ->
                new javafx.beans.property.SimpleIntegerProperty(
                        d.getValue().getTahun()
                ).asObject()
        );
        colTotalAset.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotalAset()
                )
        );
        colTotalKewajiban.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotalKewajiban()
                )
        );
        colTotalEkuitas.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotalEkuitas()
                )
        );

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        tableNeraca.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        NeracaKeuangan neraca = new NeracaKeuangan(
                dpTanggal.getValue() != null ? dpTanggal.getValue() : LocalDate.now(),
                Integer.parseInt(tfTahun.getText()),
                new BigDecimal(tfKas.getText()),
                new BigDecimal(tfPiutang.getText()),
                new BigDecimal(tfPersediaan.getText()),
                new BigDecimal(tfPeralatan.getText()),
                new BigDecimal(tfTransportasi.getText()),
                new BigDecimal(tfAkumulasiPenyusutan.getText()),
                new BigDecimal(tfUtangUsaha.getText()),
                new BigDecimal(tfUtangJangkaPanjang.getText()),
                new BigDecimal(tfModalPemilik.getText()),
                new BigDecimal(tfLabaRugi.getText()),
                tfKeterangan.getText()
        );

        boolean berhasil = service.simpanNeraca(neraca);

        if (!berhasil) {
            showAlert(
                    "Neraca Tidak Seimbang",
                    "Total Aset harus sama dengan Total Kewajiban + Total Ekuitas"
            );
            return;
        }

        loadData();
        clearForm();
    }

    @FXML
    private void handleHapus() {
        NeracaKeuangan selected = tableNeraca.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusNeraca(selected.getId());
            loadData();
        }
    }

    private void clearForm() {
        dpTanggal.setValue(null);
        tfTahun.clear();
        tfKas.clear();
        tfPiutang.clear();
        tfPersediaan.clear();
        tfPeralatan.clear();
        tfTransportasi.clear();
        tfAkumulasiPenyusutan.clear();
        tfUtangUsaha.clear();
        tfUtangJangkaPanjang.clear();
        tfModalPemilik.clear();
        tfLabaRugi.clear();
        tfKeterangan.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}