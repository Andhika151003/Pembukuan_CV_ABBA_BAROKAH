package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Service.AdministrasiService;
import com.pembukuan_cv_abba_barokah.Service.BiayaPemasaranService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class BiayaAdministrasiController {

    @FXML
    private DatePicker tanggalField;
    @FXML
    private ComboBox<Administrasi.JenisAdministrasi> cbJenisAdministrasi;
    @FXML
    private TextField deskripsiField;
    @FXML
    private TextField jumlahField;
    @FXML
    private TextField keteranganField;

    @FXML
    private TableView<Administrasi> tableAdministrasi;
    @FXML
    private TableColumn<Administrasi, String> colTanggal;
    @FXML
    private TableColumn<Administrasi, String> colJenis;
    @FXML
    private TableColumn<Administrasi, String> colDeskripsi;
    @FXML
    private TableColumn<Administrasi, BigDecimal> colJumlah;
    @FXML
    private TableColumn<Administrasi, String> colKeterangan;

    private final AdministrasiService service = new AdministrasiService();
    private final ObservableList<Administrasi> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));

        cbJenisAdministrasi.setItems(FXCollections.observableArrayList(
                Administrasi.JenisAdministrasi.values()));

        colDeskripsi.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getDeskripsi()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahAdministrasi()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeterangan()));

        tableAdministrasi.setItems(data);
        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        Administrasi ads = new Administrasi(
                tanggalField.getValue(),
                cbJenisAdministrasi.getValue(),
                deskripsiField.getText(),
                new BigDecimal(jumlahField.getText()),
                keteranganField.getText()
        );

        service.simpan(ads);
        loadData();
    }

}