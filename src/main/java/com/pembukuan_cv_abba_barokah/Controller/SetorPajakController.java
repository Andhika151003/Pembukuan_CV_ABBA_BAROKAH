package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Service.SetorPajakService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SetorPajakController implements Initializable {

    // ===================== TABLE =====================
    @FXML private TableView<SetorPajak> tableSetorPajak;
    @FXML private TableColumn<SetorPajak, LocalDate> colTanggal;
    @FXML private TableColumn<SetorPajak, String> colJenis;
    @FXML private TableColumn<SetorPajak, BigDecimal> colJumlah;
    @FXML private TableColumn<SetorPajak, String> colPeriode;
    @FXML private TableColumn<SetorPajak, String> colBukti;

    // ===================== FORM =====================
    @FXML private DatePicker dpTanggal;
    @FXML private TextField txtJenisPajak;
    @FXML private TextField txtJumlah;
    @FXML private TextField txtPeriode;
    @FXML private TextField txtBuktiSetor;

    // ===================== SERVICE =====================
    private final SetorPajakService pajakService = new SetorPajakService();
    private final ObservableList<SetorPajak> dataPajak =
            FXCollections.observableArrayList();

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        loadData();

        tableSetorPajak.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));

        dpTanggal.setValue(LocalDate.now());
    }

    // ===================== TABLE SETUP =====================
    private void initTable() {

        colTanggal.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getTanggal_Setor()
                )
        );

        colJenis.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getJenis_Pajak()
                )
        );

        colJumlah.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getJumlah_Pajak()
                )
        );

        colPeriode.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getPeriode()
                )
        );

        colBukti.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getBukti_Setor()
                )
        );

        tableSetorPajak.setItems(dataPajak);
    }

    private void loadData() {
        dataPajak.clear();
        dataPajak.addAll(pajakService.getAllPajak());
    }

    // ===================== ACTION =====================

    @FXML
    private void handleSimpan() {
        SetorPajak pajak = ambilDataForm(false);
        if (pajak == null) return;

        if (pajakService.simpanSetorPajak(pajak)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        SetorPajak selected = tableSetorPajak.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        SetorPajak pajak = ambilDataForm(true);
        pajak.setId(selected.getId());

        if (pajakService.updatePajak(pajak)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        SetorPajak selected = tableSetorPajak.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (pajakService.hapusPajak(selected.getId())) {
            loadData();
            clearForm();
        }
    }

    // ===================== UTIL =====================

    private SetorPajak ambilDataForm(boolean isUpdate) {
        try {
            return new SetorPajak(
                    dpTanggal.getValue(),
                    txtJenisPajak.getText(),
                    new BigDecimal(txtJumlah.getText()),
                    txtPeriode.getText(),
                    txtBuktiSetor.getText()
            );
        } catch (Exception e) {
            showAlert("Input tidak valid. Periksa kembali data.");
            return null;
        }
    }

    private void isiForm(SetorPajak pajak) {
        if (pajak == null) return;

        dpTanggal.setValue(pajak.getTanggal_Setor());
        txtJenisPajak.setText(pajak.getJenis_Pajak());
        txtJumlah.setText(pajak.getJumlah_Pajak().toString());
        txtPeriode.setText(pajak.getPeriode());
        txtBuktiSetor.setText(pajak.getBukti_Setor());
    }

    private void clearForm() {
        dpTanggal.setValue(LocalDate.now());
        txtJenisPajak.clear();
        txtJumlah.clear();
        txtPeriode.clear();
        txtBuktiSetor.clear();
        tableSetorPajak.getSelectionModel().clearSelection();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}