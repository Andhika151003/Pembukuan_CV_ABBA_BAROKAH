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

    @FXML
    public void initialize() {

        cbMetode.setItems(FXCollections.observableArrayList(
                Pembayaran.MetodePembayaran.values()
        ));

        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream().map(p -> p.getId()).toList()
        ));

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
        tablePembayaran.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleRowSelect();
            }
        });
        loadData();
    }

    private void loadData() {
        data.setAll(pembayaranService.getAll());
    }

    @FXML
    private void handleSimpan() {

        Pembayaran pembayaran = new Pembayaran(
                tanggalField.getValue(),
                new BigDecimal(jumlahField.getText()),
                cbMetode.getValue(),
                keteranganField.getText(),
                cbIdPenjualan.getValue()
        );

        pembayaranService.simpan(pembayaran);
        loadData();
        clearForm();
    }

    private void handleRowSelect() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {
            tanggalField.setValue(selected.getTanggalPembayaran());
            jumlahField.setText(selected.getJumlahPembayaran().toString());
            cbMetode.setValue(selected.getMetodePembayaran());
            keteranganField.setText(selected.getKeterangan());
            cbIdPenjualan.setValue(selected.getIdPenjualan());
        }
    }

    @FXML
    private void handleUpdate() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setTanggalPembayaran(tanggalField.getValue());
            selected.setJumlahPembayaran(new BigDecimal(jumlahField.getText()));
            selected.setMetodePembayaran(cbMetode.getValue());
            selected.setKeterangan(keteranganField.getText());
            selected.setIdPenjualan(cbIdPenjualan.getValue());

            pembayaranService.update(selected);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleDelete() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {
            pembayaranService.hapus(selected.getId());
            loadData();
            clearForm();
        }
    }

    private void clearForm() {
        tanggalField.setValue(null);
        jumlahField.clear();
        cbMetode.setValue(null);
        keteranganField.clear();
        cbIdPenjualan.setValue(null);
        tablePembayaran.getSelectionModel().clearSelection();
    }
}
