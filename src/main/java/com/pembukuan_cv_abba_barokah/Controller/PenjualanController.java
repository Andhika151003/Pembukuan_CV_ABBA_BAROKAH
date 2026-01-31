package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.beans.property.SimpleIntegerProperty;
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

public class PenjualanController implements Initializable {

    // ===================== TABLE =====================
    @FXML
    private TableView<Penjualan> tablePenjualan;
    @FXML
    private TableColumn<Penjualan, Integer> colNo;
    @FXML
    private TableColumn<Penjualan, String> colCustomer;
    @FXML
    private TableColumn<Penjualan, BigDecimal> colTotal;
    @FXML
    private TableColumn<Penjualan, Penjualan.StatusPembayaran> colStatus;
    @FXML
    private TableColumn<Penjualan, LocalDate> colTanggal;
    @FXML
    private TableColumn<Penjualan, Penjualan.MetodePembayaran> colMetode;
    @FXML
    private TableColumn<Penjualan, String> colKeterangan;

    // ===================== FORM =====================
    @FXML
    private TextField txtNoPenjualan;
    @FXML
    private TextField txtNamaCustomer;
    @FXML
    private TextField txtAlamatCustomer;
    @FXML
    private TextField txtTotal;
    @FXML
    private DatePicker dpTanggal;
    @FXML
    private ComboBox<Penjualan.MetodePembayaran> cbMetode;
    @FXML
    private ComboBox<Penjualan.StatusPembayaran> cbStatus;
    @FXML
    private TextArea txtKeterangan;

    // ===================== BUTTON =====================
    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnHapus;

    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<Penjualan> dataPenjualan = FXCollections.observableArrayList();

    private int idAdministrasiAktif = 1; // contoh: kas utama

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        initComboBox();
        loadData();

        tablePenjualan.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));
    }

    private void initTable() {

        colNo.setCellValueFactory(data -> new SimpleIntegerProperty(
                data.getValue().getNo_Penjualan()).asObject());

        colTanggal.setCellValueFactory(data -> new SimpleObjectProperty<>(
                data.getValue().getTanggal_Penjualan()));

        colCustomer.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getNama_Customer()));

        colTotal.setCellValueFactory(data -> new SimpleObjectProperty<>(
                data.getValue().getTotal_Penjualan()));

        colMetode.setCellValueFactory(data -> new SimpleObjectProperty<>(
                data.getValue().getMetode_Pembayaran()));

        colStatus.setCellValueFactory(data -> new SimpleObjectProperty<>(
                data.getValue().getStatus_Pembayaran()));

        colKeterangan.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getKeterangan()));

        tablePenjualan.setItems(dataPenjualan);
    }

    private void initComboBox() {
        cbMetode.setItems(FXCollections.observableArrayList(
                Penjualan.MetodePembayaran.values()));
        cbStatus.setItems(FXCollections.observableArrayList(
                Penjualan.StatusPembayaran.values()));
    }

    private void loadData() {
        dataPenjualan.clear();
        dataPenjualan.addAll(penjualanService.getAll());
    }

    // ===================== CRUD ACTION =====================

    @FXML
    private void handleSimpan() {
        Penjualan p = ambilDataForm(false);
        if (p == null)
            return;

        boolean sukses = penjualanService.tambahPenjualan(p, idAdministrasiAktif);
        if (sukses) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        Penjualan p = ambilDataForm(true);
        p.setId(selected.getId());

        boolean sukses = penjualanService.perbaruiPenjualan(p, idAdministrasiAktif);
        if (sukses) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        boolean sukses = penjualanService.hapusPenjualan(
                selected.getId(), idAdministrasiAktif);

        if (sukses) {
            loadData();
            clearForm();
        }
    }

    // ===================== UTIL =====================

    private Penjualan ambilDataForm(boolean isUpdate) {
        try {
            return new Penjualan(
                    isUpdate ? 0 : 0,
                    Integer.parseInt(txtNoPenjualan.getText()),
                    dpTanggal.getValue(),
                    txtNamaCustomer.getText(),
                    txtAlamatCustomer.getText(),
                    new BigDecimal(txtTotal.getText()),
                    cbMetode.getValue(),
                    cbStatus.getValue(),
                    txtKeterangan.getText());
        } catch (Exception e) {
            showAlert("Input tidak valid");
            return null;
        }
    }

    private void isiForm(Penjualan p) {
        if (p == null)
            return;

        txtNoPenjualan.setText(String.valueOf(p.getNo_Penjualan()));
        dpTanggal.setValue(p.getTanggal_Penjualan());
        txtNamaCustomer.setText(p.getNama_Customer());
        txtAlamatCustomer.setText(p.getAlamat_Customer());
        txtTotal.setText(p.getTotal_Penjualan().toString());
        cbMetode.setValue(p.getMetode_Pembayaran());
        cbStatus.setValue(p.getStatus_Pembayaran());
        txtKeterangan.setText(p.getKeterangan());
    }

    private void clearForm() {
        txtNoPenjualan.clear();
        txtNamaCustomer.clear();
        txtAlamatCustomer.clear();
        txtTotal.clear();
        txtKeterangan.clear();
        dpTanggal.setValue(LocalDate.now());
        cbMetode.getSelectionModel().clearSelection();
        cbStatus.getSelectionModel().clearSelection();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.show();
    }
}