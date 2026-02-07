package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Service.UtangUsahaService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class UtangUsahaController {

    @FXML
    private TextField noUtangField;
    @FXML
    private DatePicker tanggalUtangField;
    @FXML
    private TextField jumlahUtangField;
    @FXML
    private TextField pemberiUtangField;
    @FXML
    private TextField keteranganField;

    @FXML
    private TableView<UtangUsaha> tableUtang;
    @FXML
    private TableColumn<UtangUsaha, String> colNo;
    @FXML
    private TableColumn<UtangUsaha, String> colTanggal;
    @FXML
    private TableColumn<UtangUsaha, BigDecimal> colJumlah;
    @FXML
    private TableColumn<UtangUsaha, String> colPemberi;
    @FXML
    private TableColumn<UtangUsaha, String> colKeterangan;

    private final UtangUsahaService service = new UtangUsahaService();
    private final ObservableList<UtangUsaha> data = FXCollections.observableArrayList();

    private UtangUsaha selected;

    @FXML
    public void initialize() {

        colNo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNoUtang()));

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggalUtang().toString()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahUtang()));

        colPemberi.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getPemberiUtang()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeterangan()));

        tableUtang.setItems(data);
        loadData();

        tableUtang.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        UtangUsaha u = new UtangUsaha(
                noUtangField.getText(),
                tanggalUtangField.getValue(),
                new BigDecimal(jumlahUtangField.getText()),
                pemberiUtangField.getText(),
                keteranganField.getText());

        service.simpan(u);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {

        if (selected == null)
            return;

        UtangUsaha u = new UtangUsaha(
                selected.getId(),
                noUtangField.getText(),
                tanggalUtangField.getValue(),
                new BigDecimal(jumlahUtangField.getText()),
                pemberiUtangField.getText(),
                keteranganField.getText());

        service.update(u);
        loadData();
        clearForm();
    }

    @FXML
    private void handleHapus() {

        if (selected == null)
            return;

        service.hapus(selected.getId());
        loadData();
        clearForm();
    }

    private void isiForm(UtangUsaha u) {

        if (u == null)
            return;

        selected = u;

        noUtangField.setText(u.getNoUtang());
        tanggalUtangField.setValue(u.getTanggalUtang());
        jumlahUtangField.setText(u.getJumlahUtang().toString());
        pemberiUtangField.setText(u.getPemberiUtang());
        keteranganField.setText(u.getKeterangan());
    }

    private void clearForm() {

        selected = null;

        noUtangField.clear();
        tanggalUtangField.setValue(null);
        jumlahUtangField.clear();
        pemberiUtangField.clear();
        keteranganField.clear();
        tableUtang.getSelectionModel().clearSelection();
    }
}