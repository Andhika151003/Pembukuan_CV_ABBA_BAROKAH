package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PembelianLangsung;
import com.pembukuan_cv_abba_barokah.Service.PembelianLangsungService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PembelianLangsungController {

    @FXML private DatePicker tanggalField;
    @FXML private TextField hargaField;
    @FXML private TextField transportasiField;
    @FXML private TextField upahField;
    @FXML private TextField keteranganField;
    @FXML private ComboBox<Integer> cbIdPenjualan;

    @FXML private TableView<PembelianLangsung> table;
    @FXML private TableColumn<PembelianLangsung, String> colTanggal;
    @FXML private TableColumn<PembelianLangsung, BigDecimal> colHarga;
    @FXML private TableColumn<PembelianLangsung, BigDecimal> colTransportasi;
    @FXML private TableColumn<PembelianLangsung, BigDecimal> colUpah;
    @FXML private TableColumn<PembelianLangsung, Integer> colIdPenjualan;
    @FXML private TableColumn<PembelianLangsung, String> colKeterangan;
    @FXML private TableColumn<PembelianLangsung, BigDecimal> colTotal;

    @FXML private Label lblTotal;

    private final PembelianLangsungService service = new PembelianLangsungService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<PembelianLangsung> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        refreshComboIdPenjualan();

        colTanggal.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getTanggal().toString()));

        colHarga.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getHargaPerolehanLangsung()));

        colTransportasi.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getTransportasi()));

        colUpah.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getUpah()));

        colIdPenjualan.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getIdPenjualan()));

        colKeterangan.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getKeterangan()));

        colTotal.setCellValueFactory(c -> {
            PembelianLangsung p = c.getValue();
            BigDecimal total = p.getHargaPerolehanLangsung()
                    .add(p.getTransportasi())
                    .add(p.getUpah());
            return new SimpleObjectProperty<>(total);
        });

        table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) fillForm(newVal);
                }
        );

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        hitungTotalKeseluruhan();
    }

    /* ================= CRUD ================= */

    @FXML
    private void handleSimpan() {

        int idPenjualan = cbIdPenjualan.getValue();

        if (service.sudahAdaUntukPenjualan(idPenjualan)) {
            showAlert("Data sudah ada",
                    "Pembelian langsung untuk penjualan ini sudah tercatat.");
            return;
        }

        PembelianLangsung p = new PembelianLangsung(
                tanggalField.getValue(),
                new BigDecimal(hargaField.getText()),
                new BigDecimal(transportasiField.getText()),
                new BigDecimal(upahField.getText()),
                keteranganField.getText(),
                idPenjualan
        );

        service.simpan(p);
        loadData();
        refreshComboIdPenjualan();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        PembelianLangsung p = table.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.setTanggal(tanggalField.getValue());
            p.setHargaPerolehanLangsung(new BigDecimal(hargaField.getText()));
            p.setTransportasi(new BigDecimal(transportasiField.getText()));
            p.setUpah(new BigDecimal(upahField.getText()));
            p.setKeterangan(keteranganField.getText());

            service.update(p);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleDelete() {
        PembelianLangsung p = table.getSelectionModel().getSelectedItem();
        if (p != null) {
            service.hapus(p.getId());
            loadData();
            refreshComboIdPenjualan();
            clearForm();
        }
    }

    /* ================= UTIL ================= */

    private void fillForm(PembelianLangsung p) {
        tanggalField.setValue(p.getTanggal());
        hargaField.setText(p.getHargaPerolehanLangsung().toString());
        transportasiField.setText(p.getTransportasi().toString());
        upahField.setText(p.getUpah().toString());
        keteranganField.setText(p.getKeterangan());
        cbIdPenjualan.setValue(p.getIdPenjualan());

        cbIdPenjualan.setDisable(true); // FK tidak boleh diubah
    }

    private void clearForm() {
        tanggalField.setValue(null);
        hargaField.clear();
        transportasiField.clear();
        upahField.clear();
        keteranganField.clear();
        cbIdPenjualan.setValue(null);
        cbIdPenjualan.setDisable(false);
        table.getSelectionModel().clearSelection();
    }

    private void hitungTotalKeseluruhan() {
        BigDecimal total = data.stream()
                .map(p -> p.getHargaPerolehanLangsung()
                        .add(p.getTransportasi())
                        .add(p.getUpah()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        lblTotal.setText("Total Pembelian Langsung : Rp " + total);
    }

    private void refreshComboIdPenjualan() {
        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream()
                        .map(p -> p.getId())
                        .filter(id -> !service.sudahAdaUntukPenjualan(id))
                        .toList()
        ));
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}