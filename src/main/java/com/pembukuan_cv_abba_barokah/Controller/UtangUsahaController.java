package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;
import com.pembukuan_cv_abba_barokah.Service.UtangUsahaService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class UtangUsahaController {

    @FXML private TextField noUtangField;
    @FXML private DatePicker tanggalUtangField;
    @FXML private DatePicker jatuhTempoField;
    @FXML private TextField jumlahUtangField;
    @FXML private TextField jumlahDibayarField;
    @FXML private ComboBox<Integer> cbIdPembelian;
    @FXML private TextField keteranganField;

    @FXML private TableView<UtangUsaha> table;
    @FXML private TableColumn<UtangUsaha, String> colNo;
    @FXML private TableColumn<UtangUsaha, String> colTanggal;
    @FXML private TableColumn<UtangUsaha, String> colTempo;
    @FXML private TableColumn<UtangUsaha, BigDecimal> colUtang;
    @FXML private TableColumn<UtangUsaha, BigDecimal> colBayar;
    @FXML private TableColumn<UtangUsaha, String> colStatus;

    private final UtangUsahaService service = new UtangUsahaService();
    private final PembelianInventarisService pembelianService =
            new PembelianInventarisService();

    private final ObservableList<UtangUsaha> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbIdPembelian.setItems(FXCollections.observableArrayList(
                pembelianService.getAll().stream()
                        .map(p -> p.getId())
                        .toList()
        ));

        colNo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getNoUtang()));

        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getTanggalUtang().toString()));

        colTempo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getTanggalJatuhTempo().toString()));

        colUtang.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlahUtang()));

        colBayar.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlahDibayar()));

        colStatus.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getStatusUtang().name()));

        table.setItems(data);
        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        UtangUsaha u = new UtangUsaha(
                noUtangField.getText(),
                tanggalUtangField.getValue(),
                jatuhTempoField.getValue(),
                new BigDecimal(jumlahUtangField.getText()),
                new BigDecimal(jumlahDibayarField.getText()),
                keteranganField.getText(),
                cbIdPembelian.getValue()
        );

        service.simpan(u);
        loadData();
    }

    @FXML
    private void handleHapus() {
        UtangUsaha selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapus(selected.getId());
            loadData();
        }
    }
}