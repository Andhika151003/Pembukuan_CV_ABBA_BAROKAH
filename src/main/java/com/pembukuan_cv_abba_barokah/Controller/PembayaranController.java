package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
import com.pembukuan_cv_abba_barokah.Service.PembayaranService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PembayaranController {

    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<Pembayaran.MetodePembayaran> cbMetode;
    @FXML private ComboBox<Integer> cbIdPenjualan;

    @FXML private TextField jumlahField;
    @FXML private TextField keteranganField;

    @FXML private TableView<Pembayaran> tablePembayaran;
    @FXML private TableColumn<Pembayaran, String> colTanggal;
    @FXML private TableColumn<Pembayaran, BigDecimal> colJumlah;
    @FXML private TableColumn<Pembayaran, String> colMetode;
    @FXML private TableColumn<Pembayaran, Integer> colIdPenjualan;

    private final PembayaranService pembayaranService = new PembayaranService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<Pembayaran> data = FXCollections.observableArrayList();

    /* ================= INITIALIZE ================= */

    @FXML
    public void initialize() {

        cbMetode.setItems(FXCollections.observableArrayList(
                Pembayaran.MetodePembayaran.values()
        ));

        refreshComboIdPenjualan();

        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getTanggalPembayaran().toString()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlahPembayaran()));

        colMetode.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getMetodePembayaran().name()));

        colIdPenjualan.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdPenjualan()));

        tablePembayaran.setItems(data);

        tablePembayaran.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        handleRowSelect();
                    }
                });

        loadData();
    }

    /* ================= LOAD DATA ================= */

    private void loadData() {
        data.setAll(pembayaranService.getAll());
    }

    private void refreshComboIdPenjualan() {
        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream()
                        .map(p -> p.getId())
                        .filter(id -> !pembayaranService.sudahAdaUntukPenjualan(id))
                        .toList()
        ));
    }

    /* ================= SIMPAN ================= */

    @FXML
    private void handleSimpan() {

        int idPenjualan = cbIdPenjualan.getValue();

        if (pembayaranService.sudahAdaUntukPenjualan(idPenjualan)) {
            showAlert(
                    "Data sudah ada",
                    "Pembayaran untuk ID Penjualan ini sudah tercatat."
            );
            return;
        }

        Pembayaran pembayaran = new Pembayaran(
                tanggalField.getValue(),
                new BigDecimal(jumlahField.getText()),
                cbMetode.getValue(),
                keteranganField.getText(),
                idPenjualan
        );

        pembayaranService.simpan(pembayaran);
        loadData();
        refreshComboIdPenjualan();
        clearForm();
    }

    /* ================= SELECT ROW ================= */

    private void handleRowSelect() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {
            tanggalField.setValue(selected.getTanggalPembayaran());
            jumlahField.setText(selected.getJumlahPembayaran().toString());
            cbMetode.setValue(selected.getMetodePembayaran());
            keteranganField.setText(selected.getKeterangan());
            cbIdPenjualan.setValue(selected.getIdPenjualan());

            // FK dikunci karena UNIQUE
            cbIdPenjualan.setDisable(true);
        }
    }

    /* ================= UPDATE ================= */

    @FXML
    private void handleUpdate() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {

            selected.setTanggalPembayaran(tanggalField.getValue());
            selected.setJumlahPembayaran(new BigDecimal(jumlahField.getText()));
            selected.setMetodePembayaran(cbMetode.getValue());
            selected.setKeterangan(keteranganField.getText());
            // ❌ id_penjualan TIDAK diubah (UNIQUE)

            pembayaranService.update(selected);
            loadData();
            clearForm();
        }
    }

    /* ================= DELETE ================= */

    @FXML
    private void handleDelete() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {
            pembayaranService.hapus(selected.getId());
            loadData();
            refreshComboIdPenjualan();
            clearForm();
        }
    }

    /* ================= UTIL ================= */

    private void clearForm() {
        tanggalField.setValue(null);
        jumlahField.clear();
        cbMetode.setValue(null);
        keteranganField.clear();
        cbIdPenjualan.setValue(null);
        cbIdPenjualan.setDisable(false);
        tablePembayaran.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}