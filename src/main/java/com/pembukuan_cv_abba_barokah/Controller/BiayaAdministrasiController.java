package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Service.AdministrasiService;
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

public class BiayaAdministrasiController implements Initializable {

    // ===================== TABLE =====================
    @FXML private TableView<Administrasi> tableAdministrasi;
    @FXML private TableColumn<Administrasi, LocalDate> colTanggal;
    @FXML private TableColumn<Administrasi, String> colJenis;
    @FXML private TableColumn<Administrasi, String> colDeskripsi;
    @FXML private TableColumn<Administrasi, BigDecimal> colJumlah;
    @FXML private TableColumn<Administrasi, String> colKeterangan;

    // ===================== FORM =====================
    @FXML private DatePicker dpTanggal;
    @FXML private TextField txtJenisAdministrasi;
    @FXML private TextField txtDeskripsi;
    @FXML private TextField txtJumlah;
    @FXML private TextField txtKeterangan;

    // ===================== SERVICE =====================
    private final AdministrasiService administrasiService = new AdministrasiService();
    private final ObservableList<Administrasi> dataAdministrasi =
            FXCollections.observableArrayList();

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        loadData();

        tableAdministrasi.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));

        dpTanggal.setValue(LocalDate.now());
    }

    // ===================== TABLE SETUP =====================
    private void initTable() {

        colTanggal.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getTanggal()
                )
        );

        colJenis.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getJenisAdministrasi()
                )
        );

        colDeskripsi.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDeskripsi()
                )
        );

        colJumlah.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getJumlah()
                )
        );

        colKeterangan.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getKeterangan()
                )
        );

        tableAdministrasi.setItems(dataAdministrasi);
    }

    private void loadData() {
        dataAdministrasi.clear();
        dataAdministrasi.addAll(administrasiService.getAll());
    }

    // ===================== ACTION =====================

    @FXML
    private void handleSimpan() {
        Administrasi admin = ambilDataForm(false);
        if (admin == null) return;

        if (administrasiService.simpanBaru(admin)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        Administrasi selected = tableAdministrasi.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Administrasi admin = ambilDataForm(true);
        admin.setId(selected.getId());

        if (administrasiService.perbaruiData(admin)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        Administrasi selected = tableAdministrasi.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (administrasiService.hapusAdministrasi(selected.getId())) {
            loadData();
            clearForm();
        }
    }

    // ===================== UTIL =====================
    private Administrasi ambilDataForm(boolean isUpdate) {
        try {
            return new Administrasi(
                    dpTanggal.getValue(),
                    txtJenisAdministrasi.getText(),
                    txtDeskripsi.getText(),
                    new BigDecimal(txtJumlah.getText()),
                    txtKeterangan.getText()
            );
        } catch (Exception e) {
            showAlert("Input tidak valid. Periksa kembali data.");
            return null;
        }
    }

    private void isiForm(Administrasi admin) {
        if (admin == null) return;

        dpTanggal.setValue(admin.getTanggal());
        txtJenisAdministrasi.setText(admin.getJenisAdministrasi());
        txtDeskripsi.setText(admin.getDeskripsi());
        txtJumlah.setText(admin.getJumlah().toString());
        txtKeterangan.setText(admin.getKeterangan());
    }

    private void clearForm() {
        dpTanggal.setValue(LocalDate.now());
        txtJenisAdministrasi.clear();
        txtDeskripsi.clear();
        txtJumlah.clear();
        txtKeterangan.clear();
        tableAdministrasi.getSelectionModel().clearSelection();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}