package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PiutangUsaha;
import com.pembukuan_cv_abba_barokah.Service.PiutangUsahaService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PiutangUsahaController {

    @FXML
    private DatePicker tanggalField;
    @FXML
    private TextField nomorPiutangField;
    @FXML
    private TextField jumlahField;
    @FXML
    private TextField namaPenerimaField;
    @FXML
    private TextField keteranganField;

    @FXML
    private TableView<PiutangUsaha> tablePiutang;
    @FXML
    private TableColumn<PiutangUsaha, String> colTanggal;
    @FXML
    private TableColumn<PiutangUsaha, String> colNomor;
    @FXML
    private TableColumn<PiutangUsaha, BigDecimal> colJumlah;
    @FXML
    private TableColumn<PiutangUsaha, String> colNama;
    @FXML
    private TableColumn<PiutangUsaha, String> colKeterangan;

    private final PiutangUsahaService service = new PiutangUsahaService();
    private final ObservableList<PiutangUsaha> data = FXCollections.observableArrayList();

    private PiutangUsaha selected;

    @FXML
    public void initialize() {

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));

        colNomor.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNomorPiutang()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahPiutang()));

        colNama.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNamaPenerimaPiutang()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeterangan()));

        tablePiutang.setItems(data);
        loadData();

        tablePiutang.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        PiutangUsaha p = new PiutangUsaha(
                tanggalField.getValue(),
                nomorPiutangField.getText(),
                new BigDecimal(jumlahField.getText()),
                namaPenerimaField.getText(),
                keteranganField.getText());

        service.simpan(p);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {

        if (selected == null)
            return;

        PiutangUsaha p = new PiutangUsaha(
                selected.getId(),
                tanggalField.getValue(),
                nomorPiutangField.getText(),
                new BigDecimal(jumlahField.getText()),
                namaPenerimaField.getText(),
                keteranganField.getText());

        service.update(p);
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

    private void isiForm(PiutangUsaha p) {

        if (p == null)
            return;

        selected = p;

        tanggalField.setValue(p.getTanggal());
        nomorPiutangField.setText(p.getNomorPiutang());
        jumlahField.setText(p.getJumlahPiutang().toString());
        namaPenerimaField.setText(p.getNamaPenerimaPiutang());
        keteranganField.setText(p.getKeterangan());
    }

    private void clearForm() {

        selected = null;

        tanggalField.setValue(null);
        nomorPiutangField.clear();
        jumlahField.clear();
        namaPenerimaField.clear();
        keteranganField.clear();
        tablePiutang.getSelectionModel().clearSelection();
    }
}