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

    @FXML private TableView<Modal> tableModal;
    @FXML private TableColumn<Modal, String> colTanggal;
    @FXML private TableColumn<Modal, Modal.JenisModal> colJenis;
    @FXML private TableColumn<Modal, BigDecimal> colJumlah;
    @FXML private TableColumn<Modal, String> colPemilik;
    @FXML private TableColumn<Modal, BigDecimal> colSaldo;

    @FXML private DatePicker dpTanggal;
    @FXML private ComboBox<Modal.JenisModal> cbJenisModal;
    @FXML private TextField tfJumlah;
    @FXML private TextField tfNamaPemilik;
    @FXML private TextField tfKeterangan;

    private final ModalService service = new ModalService();
    private final ObservableList<Modal> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTanggal.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getTanggal().toString()
                )
        );
        colJenis.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getJenis_Modal()
                )
        );
        colJumlah.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getJumlah()
                )
        );
        colPemilik.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getNama_Pemilik()
                )
        );
        colSaldo.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getSaldo_Modal()
                )
        );

        cbJenisModal.setItems(
                FXCollections.observableArrayList(Modal.JenisModal.values())
        );

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        tableModal.setItems(data);
    }

    @FXML
    private void handleTambahModal() {
        Modal modal = new Modal(
                dpTanggal.getValue() != null ? dpTanggal.getValue() : LocalDate.now(),
                cbJenisModal.getValue(),
                new BigDecimal(tfJumlah.getText()),
                tfNamaPemilik.getText(),
                tfKeterangan.getText(),
                BigDecimal.ZERO // saldo dihitung di Service
        );

        if (service.tambahModal(modal)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        Modal selected = tableModal.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusModal(selected.getId());
            loadData();
        }
    }

    private void clearForm() {
        dpTanggal.setValue(null);
        cbJenisModal.setValue(null);
        tfJumlah.clear();
        tfNamaPemilik.clear();
        tfKeterangan.clear();
    }
}