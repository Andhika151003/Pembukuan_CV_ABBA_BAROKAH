package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PembelianInventarisController {

    /* ================= FORM ================= */

    @FXML private DatePicker tanggalField;
    @FXML private TextField noPembelianField;
    @FXML private TextField jenisInventarisField;
    @FXML private TextField namaBarangField;
    @FXML private TextField jumlahField;
    @FXML private TextField satuanField;
    @FXML private TextField hargaSatuanField;
    @FXML private TextField ongkosKirimField;
    @FXML private ComboBox<PembelianInventaris.MetodePembayaran> cbMetode;
    @FXML private ComboBox<PembelianInventaris.StatusPembelian> cbStatus;
    @FXML private TextField keteranganField;

    /* ================= TABLE ================= */

    @FXML private TableView<PembelianInventaris> tableInventaris;
    @FXML private TableColumn<PembelianInventaris, Integer> colNoPembelian;
    @FXML private TableColumn<PembelianInventaris, String> colNamaBarang;
    @FXML private TableColumn<PembelianInventaris, Integer> colJumlah;
    @FXML private TableColumn<PembelianInventaris, BigDecimal> colHarga;
    @FXML private TableColumn<PembelianInventaris, String> colMetode;
    @FXML private TableColumn<PembelianInventaris, String> colStatus;

    private final PembelianInventarisService service = new PembelianInventarisService();
    private final ObservableList<PembelianInventaris> data = FXCollections.observableArrayList();

    private PembelianInventaris selected;

    /* ================= INIT ================= */

    @FXML
    public void initialize() {

        cbMetode.setItems(FXCollections.observableArrayList(
                PembelianInventaris.MetodePembayaran.values()
        ));

        cbStatus.setItems(FXCollections.observableArrayList(
                PembelianInventaris.StatusPembelian.values()
        ));

        colNoPembelian.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getNoPembelian()));

        colNamaBarang.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNamaBarang()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getJumlah()));

        colHarga.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getHargaSatuan()));

        colMetode.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getMetodePembayaran().name()));

        colStatus.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getStatusPembelian().name()));

        tableInventaris.setItems(data);
        loadData();

        tableInventaris.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    /* ================= ACTION ================= */

    @FXML
    private void handleSimpan() {

        PembelianInventaris pi = new PembelianInventaris(
                Integer.parseInt(noPembelianField.getText()),
                jenisInventarisField.getText(),
                namaBarangField.getText(),
                Integer.parseInt(jumlahField.getText()),
                Integer.parseInt(satuanField.getText()),
                new BigDecimal(hargaSatuanField.getText()),
                new BigDecimal(ongkosKirimField.getText()),
                cbMetode.getValue(),
                cbStatus.getValue(),
                keteranganField.getText(),
                tanggalField.getValue()
        );

        service.simpan(pi);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        if (selected == null) return;

        PembelianInventaris pi = new PembelianInventaris(
                selected.getId(),
                Integer.parseInt(noPembelianField.getText()),
                jenisInventarisField.getText(),
                namaBarangField.getText(),
                Integer.parseInt(jumlahField.getText()),
                Integer.parseInt(satuanField.getText()),
                new BigDecimal(hargaSatuanField.getText()),
                new BigDecimal(ongkosKirimField.getText()),
                cbMetode.getValue(),
                cbStatus.getValue(),
                keteranganField.getText(),
                tanggalField.getValue()
        );

        service.update(pi);
        loadData();
        clearForm();
    }

    @FXML
    private void handleHapus() {
        if (selected == null) return;

        service.hapus(selected.getId());
        loadData();
        clearForm();
    }

    /* ================= UTIL ================= */

    private void isiForm(PembelianInventaris pi) {
        if (pi == null) return;
        selected = pi;

        noPembelianField.setText(String.valueOf(pi.getNoPembelian()));
        jenisInventarisField.setText(pi.getJenisInventaris());
        namaBarangField.setText(pi.getNamaBarang());
        jumlahField.setText(String.valueOf(pi.getJumlah()));
        satuanField.setText(String.valueOf(pi.getSatuan()));
        hargaSatuanField.setText(pi.getHargaSatuan().toString());
        ongkosKirimField.setText(pi.getOngkosKirim().toString());
        cbMetode.setValue(pi.getMetodePembayaran());
        cbStatus.setValue(pi.getStatusPembelian());
        keteranganField.setText(pi.getKeterangan());
        tanggalField.setValue(pi.getTanggalPembelian());
    }

    private void clearForm() {
        selected = null;
        noPembelianField.clear();
        jenisInventarisField.clear();
        namaBarangField.clear();
        jumlahField.clear();
        satuanField.clear();
        hargaSatuanField.clear();
        ongkosKirimField.clear();
        keteranganField.clear();
        tanggalField.setValue(null);
        cbMetode.getSelectionModel().clearSelection();
        cbStatus.getSelectionModel().clearSelection();
    }
}