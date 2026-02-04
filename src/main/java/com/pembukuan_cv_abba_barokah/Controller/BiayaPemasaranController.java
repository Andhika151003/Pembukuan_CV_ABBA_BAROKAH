package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Service.BiayaPemasaranService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class BiayaPemasaranController {

    @FXML private DatePicker tanggalField;
    @FXML private TextField deskripsiField;
    @FXML private TextField jumlahField;
    @FXML private TextField kategoriField;
    @FXML private ComboBox<BiayaPemasaran.MarketingType> cbMarketingType;

    @FXML private TableView<BiayaPemasaran> tablePemasaran;
    @FXML private TableColumn<BiayaPemasaran, String> colTanggal;
    @FXML private TableColumn<BiayaPemasaran, String> colDeskripsi;
    @FXML private TableColumn<BiayaPemasaran, BigDecimal> colJumlah;
    @FXML private TableColumn<BiayaPemasaran, String> colKategori;
    @FXML private TableColumn<BiayaPemasaran, String> colType;

    private final BiayaPemasaranService service = new BiayaPemasaranService();
    private final ObservableList<BiayaPemasaran> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbMarketingType.setItems(FXCollections.observableArrayList(
                BiayaPemasaran.MarketingType.values()
        ));

        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getTanggal().toString()));

        colDeskripsi.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getDeskripsi()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlahPemasaran()));

        colKategori.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getKategori()));

        colType.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getMarketingType().name()));

        tablePemasaran.setItems(data);
        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        BiayaPemasaran bp = new BiayaPemasaran(
                tanggalField.getValue(),
                deskripsiField.getText(),
                new BigDecimal(jumlahField.getText()),
                kategoriField.getText(),
                cbMarketingType.getValue()
        );

        service.simpan(bp);
        loadData();
    }
}