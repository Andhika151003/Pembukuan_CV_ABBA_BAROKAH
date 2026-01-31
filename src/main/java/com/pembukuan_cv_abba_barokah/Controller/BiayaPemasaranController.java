package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Service.BiayaPemasaranService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BiayaPemasaranController implements Initializable {

    // ===================== TABLE =====================
    @FXML private TableView<BiayaPemasaran> tableBiayaPemasaran;
    @FXML private TableColumn<BiayaPemasaran, LocalDate> colTanggal;
    @FXML private TableColumn<BiayaPemasaran, String> colDeskripsi;
    @FXML private TableColumn<BiayaPemasaran, Integer> colJumlah;
    @FXML private TableColumn<BiayaPemasaran, BiayaPemasaran.ExpenseCategory> colKategori;
    @FXML private TableColumn<BiayaPemasaran, BiayaPemasaran.MarketingExpenseType> colMarketingType;

    // ===================== FORM =====================
    @FXML private DatePicker dpTanggal;
    @FXML private TextField txtDeskripsi;
    @FXML private TextField txtJumlah;
    @FXML private ComboBox<BiayaPemasaran.ExpenseCategory> cbKategori;
    @FXML private ComboBox<BiayaPemasaran.MarketingExpenseType> cbMarketingType;

    // ===================== SERVICE =====================
    private final BiayaPemasaranService biayaService = new BiayaPemasaranService();
    private final ObservableList<BiayaPemasaran> dataBiaya =
            FXCollections.observableArrayList();

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initTable();
        loadData();

        tableBiayaPemasaran.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));

        dpTanggal.setValue(LocalDate.now());
    }

    // ===================== INIT =====================
    private void initComboBox() {
        cbKategori.setItems(FXCollections.observableArrayList(
                BiayaPemasaran.ExpenseCategory.values()
        ));
        cbMarketingType.setItems(FXCollections.observableArrayList(
                BiayaPemasaran.MarketingExpenseType.values()
        ));
    }

    private void initTable() {

        colTanggal.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getTanggal()
                )
        );

        colDeskripsi.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDeskripsi()
                )
        );

        colJumlah.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getJumlah()
                ).asObject()
        );

        colKategori.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getCategory()
                )
        );

        colMarketingType.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getMarketingType()
                )
        );

        tableBiayaPemasaran.setItems(dataBiaya);
    }

    private void loadData() {
        dataBiaya.clear();
        dataBiaya.addAll(biayaService.getAll());
    }

    // ===================== ACTION =====================

    @FXML
    private void handleSimpan() {
        BiayaPemasaran biaya = ambilDataForm(false);
        if (biaya == null) return;

        if (biayaService.tambahBiayaPemasaran(biaya)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        BiayaPemasaran selected = tableBiayaPemasaran.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        BiayaPemasaran biaya = ambilDataForm(true);
        biaya.setId(selected.getId());

        if (biayaService.perbaruiBiayaPemasaran(biaya)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        BiayaPemasaran selected = tableBiayaPemasaran.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (biayaService.hapusBiayaPemasaran(selected.getId())) {
            loadData();
            clearForm();
        }
    }

    // ===================== UTIL =====================
    private BiayaPemasaran ambilDataForm(boolean isUpdate) {
        try {
            return new BiayaPemasaran(
                    dpTanggal.getValue(),
                    txtDeskripsi.getText(),
                    Integer.parseInt(txtJumlah.getText()),
                    cbKategori.getValue(),
                    cbMarketingType.getValue()
            );
        } catch (Exception e) {
            showAlert("Input tidak valid. Periksa kembali data.");
            return null;
        }
    }

    private void isiForm(BiayaPemasaran biaya) {
        if (biaya == null) return;

        dpTanggal.setValue(biaya.getTanggal());
        txtDeskripsi.setText(biaya.getDeskripsi());
        txtJumlah.setText(String.valueOf(biaya.getJumlah()));
        cbKategori.setValue(biaya.getCategory());
        cbMarketingType.setValue(biaya.getMarketingType());
    }

    private void clearForm() {
        dpTanggal.setValue(LocalDate.now());
        txtDeskripsi.clear();
        txtJumlah.clear();
        cbKategori.getSelectionModel().clearSelection();
        cbMarketingType.getSelectionModel().clearSelection();
        tableBiayaPemasaran.getSelectionModel().clearSelection();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}