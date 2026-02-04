package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import com.pembukuan_cv_abba_barokah.Service.PersediaanBarangService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PersediaanBarangController {

    @FXML private DatePicker tanggalField;
    @FXML private TextField namaBarangField;
    @FXML private TextField satuanField;
    @FXML private ComboBox<PersediaanBarang.JenisTransaksi> cbJenis;
    @FXML private TextField jumlahField;
    @FXML private TextField hargaSatuanField;
    @FXML private TextField keteranganField;

    @FXML private TableView<PersediaanBarang> table;
    @FXML private TableColumn<PersediaanBarang, String> colTanggal;
    @FXML private TableColumn<PersediaanBarang, String> colNama;
    @FXML private TableColumn<PersediaanBarang, String> colJenis;
    @FXML private TableColumn<PersediaanBarang, Integer> colJumlah;
    @FXML private TableColumn<PersediaanBarang, BigDecimal> colHarga;

    private final PersediaanBarangService service = new PersediaanBarangService();
    private final ObservableList<PersediaanBarang> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbJenis.setItems(FXCollections.observableArrayList(
                PersediaanBarang.JenisTransaksi.values()
        ));

        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getTanggal().toString()));

        colNama.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getNamaBarang()));

        colJenis.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getJenisTransaksi().name()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlah()));

        colHarga.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getHargaSatuan()));

        table.setItems(data);
        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        PersediaanBarang p = new PersediaanBarang(
                tanggalField.getValue(),
                namaBarangField.getText(),
                satuanField.getText(),
                cbJenis.getValue(),
                Integer.parseInt(jumlahField.getText()),
                new BigDecimal(hargaSatuanField.getText()),
                keteranganField.getText()
        );

        service.simpan(p);
        loadData();
    }

    @FXML
    private void handleHapus() {
        PersediaanBarang selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapus(selected.getId());
            loadData();
        }
    }
}