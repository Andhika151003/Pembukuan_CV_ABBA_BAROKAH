package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PelunasanUtang;
import com.pembukuan_cv_abba_barokah.Service.PelunasanUtangService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PelunasanUtangController {

    @FXML
    private DatePicker tanggalField;
    @FXML
    private TextField jumlahField;
    @FXML
    private TextField namaField;
    @FXML
    private TextField keteranganField;

    @FXML
    private TableView<PelunasanUtang> table;
    @FXML
    private TableColumn<PelunasanUtang, String> colTanggal;
    @FXML
    private TableColumn<PelunasanUtang, BigDecimal> colJumlah;
    @FXML
    private TableColumn<PelunasanUtang, String> colNama;
    @FXML
    private TableColumn<PelunasanUtang, String> colKeterangan;

    private final PelunasanUtangService service = new PelunasanUtangService();
    private final ObservableList<PelunasanUtang> data = FXCollections.observableArrayList();

    private PelunasanUtang selected;

    @FXML
    public void initialize() {

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahPelunasanUtang()));

        colNama.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNamaPemberiUtang()));

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

        PelunasanUtang p = new PelunasanUtang(
                tanggalField.getValue(),
                new BigDecimal(jumlahField.getText()),
                namaField.getText(),
                keteranganField.getText());

        service.simpan(p);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {

        if (selected == null)
            return;

        PelunasanUtang p = new PelunasanUtang(
                selected.getId(),
                tanggalField.getValue(),
                new BigDecimal(jumlahField.getText()),
                namaField.getText(),
                keteranganField.getText());

        service.update(p);
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

    private void isiForm(PelunasanUtang p) {

        if (p == null)
            return;

        selected = p;

        tanggalField.setValue(p.getTanggal());
        jumlahField.setText(p.getJumlahPelunasanUtang().toString());
        namaField.setText(p.getNamaPemberiUtang());
        keteranganField.setText(p.getKeterangan());
    }

    private void clearForm() {

        selected = null;

        tanggalField.setValue(null);
        jumlahField.clear();
        namaField.clear();
        keteranganField.clear();

        table.getSelectionModel().clearSelection();
    }
}