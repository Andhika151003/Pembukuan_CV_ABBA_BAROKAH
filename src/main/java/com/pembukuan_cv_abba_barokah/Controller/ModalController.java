package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Modal;
import com.pembukuan_cv_abba_barokah.Service.ModalService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class ModalController {

    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<Modal.JenisModal> cbJenisModal;
    @FXML private TextField jumlahField;
    @FXML private TextField namaPemilikField;
    @FXML private TextField keteranganField;

    @FXML private TableView<Modal> table;
    @FXML private TableColumn<Modal, String> colTanggal;
    @FXML private TableColumn<Modal, String> colJenis;
    @FXML private TableColumn<Modal, BigDecimal> colJumlah;
    @FXML private TableColumn<Modal, String> colPemilik;

    private final ModalService service = new ModalService();
    private final ObservableList<Modal> data = FXCollections.observableArrayList();

    private Modal selected;

    @FXML
    public void initialize() {

        cbJenisModal.setItems(FXCollections.observableArrayList(
                Modal.JenisModal.values()
        ));

        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getTanggal().toString()));

        colJenis.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getJenisModal().name()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlah()));

        colPemilik.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getNamaPemilik()));

        table.setItems(data);
        table.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    private void isiForm(Modal m) {
        if (m == null) return;
        selected = m;
        tanggalField.setValue(m.getTanggal());
        cbJenisModal.setValue(m.getJenisModal());
        jumlahField.setText(m.getJumlah().toString());
        namaPemilikField.setText(m.getNamaPemilik());
        keteranganField.setText(m.getKeterangan());
    }

    @FXML
    private void handleSimpan() {
        Modal modal = new Modal(
                tanggalField.getValue(),
                cbJenisModal.getValue(),
                new BigDecimal(jumlahField.getText()),
                namaPemilikField.getText(),
                keteranganField.getText()
        );

        service.simpan(modal);
        clear();
        loadData();
    }

    @FXML
    private void handleUpdate() {
        if (selected == null) return;

        Modal modal = new Modal(
                selected.getId(),
                tanggalField.getValue(),
                cbJenisModal.getValue(),
                new BigDecimal(jumlahField.getText()),
                namaPemilikField.getText(),
                keteranganField.getText()
        );

        service.update(modal);
        clear();
        loadData();
    }

    @FXML
    private void handleHapus() {
        if (selected == null) return;
        service.hapus(selected.getId());
        clear();
        loadData();
    }

    private void clear() {
        selected = null;
        tanggalField.setValue(null);
        cbJenisModal.getSelectionModel().clearSelection();
        jumlahField.clear();
        namaPemilikField.clear();
        keteranganField.clear();
        table.getSelectionModel().clearSelection();
    }
}