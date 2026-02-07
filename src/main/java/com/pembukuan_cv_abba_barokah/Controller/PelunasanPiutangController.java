package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PelunasanPiutang;
import com.pembukuan_cv_abba_barokah.Service.PelunasanPiutangService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PelunasanPiutangController {

    @FXML
    private DatePicker tanggalField;
    @FXML
    private TextField jumlahField;
    @FXML
    private TextField namaField;
    @FXML
    private TextField keteranganField;

    @FXML
    private TableView<PelunasanPiutang> table;
    @FXML
    private TableColumn<PelunasanPiutang, String> colTanggal;
    @FXML
    private TableColumn<PelunasanPiutang, BigDecimal> colJumlah;
    @FXML
    private TableColumn<PelunasanPiutang, String> colNama;
    @FXML
    private TableColumn<PelunasanPiutang, String> colKeterangan;

    private final PelunasanPiutangService service = new PelunasanPiutangService();
    private final ObservableList<PelunasanPiutang> data = FXCollections.observableArrayList();

    private PelunasanPiutang selected;

    @FXML
    public void initialize() {

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahPelunasanPiutang()));

        colNama.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNamaPenerimaPiutang()));

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

        PelunasanPiutang p = new PelunasanPiutang(
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

        PelunasanPiutang p = new PelunasanPiutang(
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

    private void isiForm(PelunasanPiutang p) {

        if (p == null)
            return;

        selected = p;

        tanggalField.setValue(p.getTanggal());
        jumlahField.setText(p.getJumlahPelunasanPiutang().toString());
        namaField.setText(p.getNamaPenerimaPiutang());
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