package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
import com.pembukuan_cv_abba_barokah.Service.PembayaranService;
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

public class PembayaranController implements Initializable {

    // ===================== TABLE =====================
    @FXML private TableView<Pembayaran> tablePembayaran;
    @FXML private TableColumn<Pembayaran, Integer> colId;
    @FXML private TableColumn<Pembayaran, LocalDate> colTanggal;
    @FXML private TableColumn<Pembayaran, BigDecimal> colJumlah;
    @FXML private TableColumn<Pembayaran, String> colMetode;
    @FXML private TableColumn<Pembayaran, String> colKeterangan;

    // ===================== FORM =====================
    @FXML private TextField txtIdTransaksi;
    @FXML private TextField txtIdPenjualan;
    @FXML private TextField txtIdPembelian;
    @FXML private TextField txtIdUtang;
    @FXML private DatePicker dpTanggal;
    @FXML private TextField txtJumlah;
    @FXML private TextField txtMetode;
    @FXML private TextArea txtKeterangan;

    // ===================== SERVICE =====================
    private final PembayaranService pembayaranService = new PembayaranService();
    private final ObservableList<Pembayaran> dataPembayaran =
            FXCollections.observableArrayList();

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        loadData();

        tablePembayaran.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));

        dpTanggal.setValue(LocalDate.now());
    }

    // ===================== TABLE SETUP =====================
    private void initTable() {

        colId.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getId()
                ).asObject()
        );

        colTanggal.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getTanggal_Pembayaran()
                )
        );

        colJumlah.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getJumlah_Pembayaran()
                )
        );

        colMetode.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getMetode_Pembayaran()
                )
        );

        colKeterangan.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getKeterangan()
                )
        );

        tablePembayaran.setItems(dataPembayaran);
    }

    private void loadData() {
        dataPembayaran.clear();
        dataPembayaran.addAll(pembayaranService.getAllPembayaran());
    }

    // ===================== ACTION =====================

    @FXML
    private void handleSimpan() {
        Pembayaran p = ambilDataForm(false);
        if (p == null) return;

        if (pembayaranService.simpanPembayaran(p)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Pembayaran p = ambilDataForm(true);
        p.setId(selected.getId());

        if (pembayaranService.updatePembayaran(p)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (pembayaranService.hapusPembayaran(selected.getId())) {
            loadData();
            clearForm();
        }
    }

    // ===================== UTIL =====================

    private Pembayaran ambilDataForm(boolean isUpdate) {
        try {
            return new Pembayaran(
                    Integer.parseInt(txtIdTransaksi.getText()),
                    Integer.parseInt(txtIdPenjualan.getText()),
                    Integer.parseInt(txtIdPembelian.getText()),
                    Integer.parseInt(txtIdUtang.getText()),
                    dpTanggal.getValue(),
                    new BigDecimal(txtJumlah.getText()),
                    txtMetode.getText(),
                    txtKeterangan.getText()
            );
        } catch (Exception e) {
            showAlert("Input tidak valid. Periksa kembali data.");
            return null;
        }
    }

    private void isiForm(Pembayaran p) {
        if (p == null) return;

        txtIdTransaksi.setText(String.valueOf(p.getId_Transaksi()));
        txtIdPenjualan.setText(String.valueOf(p.getId_Penjualan()));
        txtIdPembelian.setText(String.valueOf(p.getId_Pembelian()));
        txtIdUtang.setText(String.valueOf(p.getId_Utang()));
        dpTanggal.setValue(p.getTanggal_Pembayaran());
        txtJumlah.setText(p.getJumlah_Pembayaran().toString());
        txtMetode.setText(p.getMetode_Pembayaran());
        txtKeterangan.setText(p.getKeterangan());
    }

    private void clearForm() {
        txtIdTransaksi.clear();
        txtIdPenjualan.clear();
        txtIdPembelian.clear();
        txtIdUtang.clear();
        txtJumlah.clear();
        txtMetode.clear();
        txtKeterangan.clear();
        dpTanggal.setValue(LocalDate.now());
        tablePembayaran.getSelectionModel().clearSelection();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}