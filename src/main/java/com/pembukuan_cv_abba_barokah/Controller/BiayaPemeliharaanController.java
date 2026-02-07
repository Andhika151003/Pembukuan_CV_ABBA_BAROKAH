package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemeliharaan;
import com.pembukuan_cv_abba_barokah.Service.BiayaPemeliharaanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class BiayaPemeliharaanController {

    @FXML
    private DatePicker tanggalField;
    @FXML
    private TextField jumlahField;
    @FXML
    private TextField keteranganField;

    @FXML
    private TableView<BiayaPemeliharaan> table;
    @FXML
    private TableColumn<BiayaPemeliharaan, String> colTanggal;
    @FXML
    private TableColumn<BiayaPemeliharaan, BigDecimal> colJumlah;
    @FXML
    private TableColumn<BiayaPemeliharaan, String> colKeterangan;

    private final BiayaPemeliharaanService service = new BiayaPemeliharaanService();
    private final ObservableList<BiayaPemeliharaan> data = FXCollections.observableArrayList();

    private BiayaPemeliharaan selected;

    @FXML
    public void initialize() {

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahBiayaPemeliharaan()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeterangan()));

        table.setItems(data);
        loadData();

        table.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        BiayaPemeliharaan b = new BiayaPemeliharaan(
                tanggalField.getValue(),
                new BigDecimal(jumlahField.getText()),
                keteranganField.getText());

        service.simpan(b);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {

        if (selected == null)
            return;

        BiayaPemeliharaan b = new BiayaPemeliharaan(
                selected.getId(),
                tanggalField.getValue(),
                new BigDecimal(jumlahField.getText()),
                keteranganField.getText());

        service.update(b);
        loadData();
        clearForm();
    }

    @FXML
    private void handleDelete() {

        if (selected == null)
            return;

        service.hapus(selected.getId());
        loadData();
        clearForm();
    }

    private void isiForm(BiayaPemeliharaan b) {

        if (b == null)
            return;

        selected = b;

        tanggalField.setValue(b.getTanggal());
        jumlahField.setText(b.getJumlahBiayaPemeliharaan().toString());
        keteranganField.setText(b.getKeterangan());
    }

    private void clearForm() {

        selected = null;

        tanggalField.setValue(null);
        jumlahField.clear();
        keteranganField.clear();

        table.getSelectionModel().clearSelection();
    }
}