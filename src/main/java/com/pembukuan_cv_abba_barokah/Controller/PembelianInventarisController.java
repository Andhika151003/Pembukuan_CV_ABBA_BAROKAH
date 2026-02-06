package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PembelianInventarisController {

    @FXML private DatePicker tanggalField;
    @FXML private TextField noPembelianField;
    @FXML private TextField jenisInventarisField;
    @FXML private TextField namaBarangField;
    @FXML private TextField jumlahField;
    @FXML private TextField satuanField;
    @FXML private TextField hargaSatuanField;
    @FXML private TextField ongkosKirimField;
    @FXML private TextField keteranganField;

    @FXML private TableView<PembelianInventaris> tableInventaris;
    @FXML private TableColumn<PembelianInventaris, String> colNoPembelian;
    @FXML private TableColumn<PembelianInventaris, String> colNamaBarang;
    @FXML private TableColumn<PembelianInventaris, Integer> colJumlah;
    @FXML private TableColumn<PembelianInventaris, BigDecimal> colHarga;

    private final PembelianInventarisService service = new PembelianInventarisService();
    private final ObservableList<PembelianInventaris> data = FXCollections.observableArrayList();

    private PembelianInventaris selected;

    @FXML
    public void initialize() {
        colNoPembelian.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNoPembelian()));

        colNamaBarang.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNamaBarang()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getJumlah()));

        colHarga.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getHargaSatuan()));

        tableInventaris.setItems(data);
        loadData();

        tableInventaris.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {
        PembelianInventaris pi = new PembelianInventaris(
                noPembelianField.getText(),
                jenisInventarisField.getText(),
                namaBarangField.getText(),
                Integer.parseInt(jumlahField.getText()),
                satuanField.getText(),
                new BigDecimal(hargaSatuanField.getText()),
                new BigDecimal(ongkosKirimField.getText()),
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
                noPembelianField.getText(),
                jenisInventarisField.getText(),
                namaBarangField.getText(),
                Integer.parseInt(jumlahField.getText()),
                satuanField.getText(),
                new BigDecimal(hargaSatuanField.getText()),
                new BigDecimal(ongkosKirimField.getText()),
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

    private void isiForm(PembelianInventaris pi) {
        if (pi == null) return;
        selected = pi;

        noPembelianField.setText(pi.getNoPembelian());
        jenisInventarisField.setText(pi.getJenisInventaris());
        namaBarangField.setText(pi.getNamaBarang());
        jumlahField.setText(String.valueOf(pi.getJumlah()));
        satuanField.setText(pi.getSatuan());
        hargaSatuanField.setText(pi.getHargaSatuan().toString());
        ongkosKirimField.setText(pi.getOngkosKirim().toString());
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
    }
}
