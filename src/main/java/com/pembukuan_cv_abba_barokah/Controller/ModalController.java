package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Modal;
import com.pembukuan_cv_abba_barokah.Service.ModalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ModalController {

    @FXML private DatePicker dpTanggal;
    @FXML private ComboBox<Modal.JenisModal> cbJenisModal;
    @FXML private TextField txtJumlah;
    @FXML private TextField txtNamaPemilik;
    @FXML private TextField txtKeterangan;

    @FXML private TableView<Modal> tableModal;
    @FXML private TableColumn<Modal, LocalDate> colTanggal;
    @FXML private TableColumn<Modal, String> colJenis;
    @FXML private TableColumn<Modal, BigDecimal> colJumlah;
    @FXML private TableColumn<Modal, BigDecimal> colSaldo;
    @FXML private TableColumn<Modal, String> colPemilik;

    private final ModalService service = new ModalService();
    private final ObservableList<Modal> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbJenisModal.setItems(
                FXCollections.observableArrayList(Modal.JenisModal.values())
        );

        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggal())
        );
        colJenis.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getJenis_Modal().toString())
        );
        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getJumlah())
        );
        colSaldo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getSaldo_Modal())
        );
        colPemilik.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNama_Pemilik())
        );

        loadData();

        tableModal.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> fillForm(newVal)
        );
    }

    private void loadData() {
        data.setAll(service.getAll());
        tableModal.setItems(data);
    }

    private void fillForm(Modal m) {
        if (m == null) return;

        dpTanggal.setValue(m.getTanggal());
        cbJenisModal.setValue(m.getJenis_Modal());
        txtJumlah.setText(m.getJumlah().toString());
        txtNamaPemilik.setText(m.getNama_Pemilik());
        txtKeterangan.setText(m.getKeterangan());
    }

    @FXML
    private void handleSimpan() {
        Modal modal = new Modal(
                dpTanggal.getValue(),
                cbJenisModal.getValue(),
                new BigDecimal(txtJumlah.getText()),
                txtNamaPemilik.getText(),
                txtKeterangan.getText(),
                BigDecimal.ZERO // saldo dihitung otomatis di Service
        );

        service.tambahModal(modal);
        clearForm();
        loadData();
    }

    @FXML
    private void handleHapus() {
        Modal selected = tableModal.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        service.hapusModal(selected.getId());
        clearForm();
        loadData();
    }

    private void clearForm() {
        dpTanggal.setValue(null);
        cbJenisModal.setValue(null);
        txtJumlah.clear();
        txtNamaPemilik.clear();
        txtKeterangan.clear();
        tableModal.getSelectionModel().clearSelection();
    }
}