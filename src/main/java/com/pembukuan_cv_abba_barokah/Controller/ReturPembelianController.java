package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
import com.pembukuan_cv_abba_barokah.Service.ReturPembelianService;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReturPembelianController {

    @FXML
    private TextField noReturField;
    @FXML
    private DatePicker tanggalReturField;
    @FXML
    private TextField jumlahReturField;
    @FXML
    private TextField keteranganReturField;
    @FXML
    private ComboBox<Integer> cbIdPembelian;

    @FXML
    private TableView<ReturPembelian> tableRetur;
    @FXML
    private TableColumn<ReturPembelian, Integer> colNo;
    @FXML
    private TableColumn<ReturPembelian, String> colTanggal;
    @FXML
    private TableColumn<ReturPembelian, Integer> colJumlah;
    @FXML
    private TableColumn<ReturPembelian, String> colKeterangan;
    @FXML
    private TableColumn<ReturPembelian, Integer> colIdPembelian;

    private final ReturPembelianService service = new ReturPembelianService();
    private final PembelianInventarisService pembelianService = new PembelianInventarisService();

    private final ObservableList<ReturPembelian> data = FXCollections.observableArrayList();

    private ReturPembelian selected;

    @FXML
    public void initialize() {

        refreshCombo();

        colNo.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getNoReturPembelian()));

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggalRetur().toString()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahRetur()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeteranganRetur()));

        colIdPembelian.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getIdPembelian()));

        tableRetur.setItems(data);
        loadData();

        tableRetur.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    private void refreshCombo() {
        cbIdPembelian.setItems(FXCollections.observableArrayList(
                pembelianService.getAll().stream()
                        .map(p -> p.getId())
                        .filter(id -> !service.sudahAdaUntukPembelian(id))
                        .toList()));
    }

    @FXML
    private void handleSimpan() {

        int idPembelian = cbIdPembelian.getValue();

        if (service.sudahAdaUntukPembelian(idPembelian)) {
            alert("Duplikat", "Pembelian ini sudah memiliki retur.");
            return;
        }

        ReturPembelian r = new ReturPembelian(
                Integer.parseInt(noReturField.getText()),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                keteranganReturField.getText(),
                idPembelian);

        service.simpan(r);
        loadData();
        refreshCombo();
        clearForm();
    }

    @FXML
    private void handleUpdate() {

        if (selected == null)
            return;

        ReturPembelian r = new ReturPembelian(
                selected.getId(),
                Integer.parseInt(noReturField.getText()),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                keteranganReturField.getText(),
                selected.getIdPembelian());

        service.update(r);
        loadData();
        clearForm();
    }

    @FXML
    private void handleHapus() {
        if (selected == null)
            return;

        service.hapus(selected.getId());
        loadData();
        refreshCombo();
        clearForm();
    }

    private void isiForm(ReturPembelian r) {

        if (r == null)
            return;

        selected = r;

        noReturField.setText(String.valueOf(r.getNoReturPembelian()));
        tanggalReturField.setValue(r.getTanggalRetur());
        jumlahReturField.setText(String.valueOf(r.getJumlahRetur()));
        keteranganReturField.setText(r.getKeteranganRetur());
        cbIdPembelian.setValue(r.getIdPembelian());
        cbIdPembelian.setDisable(true);
    }

    private void clearForm() {
        selected = null;
        noReturField.clear();
        jumlahReturField.clear();
        keteranganReturField.clear();
        tanggalReturField.setValue(null);
        cbIdPembelian.setDisable(false);
        cbIdPembelian.setValue(null);
        tableRetur.getSelectionModel().clearSelection();
    }

    private void alert(String t, String m) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}