package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
import com.pembukuan_cv_abba_barokah.Service.ReturPembelianService;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReturPembelianController {

    @FXML private TextField noReturField;
    @FXML private DatePicker tanggalReturField;
    @FXML private TextField jumlahReturField;
    @FXML private TextField alasanReturField;
    @FXML private TextField keteranganReturField;
    @FXML private ComboBox<ReturPembelian.StatusRetur> cbStatus;
    @FXML private ComboBox<Integer> cbIdPembelian;

    @FXML private TableView<ReturPembelian> tableRetur;
    @FXML private TableColumn<ReturPembelian, Integer> colNo;
    @FXML private TableColumn<ReturPembelian, Integer> colJumlah;
    @FXML private TableColumn<ReturPembelian, String> colStatus;
    @FXML private TableColumn<ReturPembelian, Integer> colIdPembelian;

    private final ReturPembelianService service = new ReturPembelianService();
    private final PembelianInventarisService pembelianService =
            new PembelianInventarisService();

    private final ObservableList<ReturPembelian> data =
            FXCollections.observableArrayList();

    private ReturPembelian selected;

    @FXML
    public void initialize() {

        cbStatus.setItems(FXCollections.observableArrayList(
                ReturPembelian.StatusRetur.values()));

        refreshComboIdPembelian();

        colNo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getNoReturPembelian()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlahRetur()));

        colStatus.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getStatusRetur().name()));

        colIdPembelian.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
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

    private void refreshComboIdPembelian() {
        cbIdPembelian.setItems(FXCollections.observableArrayList(
                pembelianService.getAll().stream()
                        .map(p -> p.getId())
                        .filter(id -> !service.sudahAdaUntukPembelian(id))
                        .toList()
        ));
    }

    @FXML
    private void handleSimpan() {

        int idPembelian = cbIdPembelian.getValue();

        if (service.sudahAdaUntukPembelian(idPembelian)) {
            alert("Duplikat", "Retur untuk pembelian ini sudah ada.");
            return;
        }

        ReturPembelian r = new ReturPembelian(
                Integer.parseInt(noReturField.getText()),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                alasanReturField.getText(),
                keteranganReturField.getText(),
                cbStatus.getValue(),
                idPembelian
        );

        service.simpan(r);
        loadData();
        refreshComboIdPembelian();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        if (selected == null) return;

        selected = new ReturPembelian(
                selected.getId(),
                Integer.parseInt(noReturField.getText()),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                alasanReturField.getText(),
                keteranganReturField.getText(),
                cbStatus.getValue(),
                selected.getIdPembelian() // FK TETAP
        );

        service.update(selected);
        loadData();
        clearForm();
    }

    @FXML
    private void handleHapus() {
        if (selected == null) return;

        service.hapus(selected.getId());
        loadData();
        refreshComboIdPembelian();
        clearForm();
    }

    private void isiForm(ReturPembelian r) {
        if (r == null) return;

        selected = r;

        noReturField.setText(String.valueOf(r.getNoReturPembelian()));
        tanggalReturField.setValue(r.getTanggalRetur());
        jumlahReturField.setText(String.valueOf(r.getJumlahRetur()));
        alasanReturField.setText(r.getAlasanRetur());
        keteranganReturField.setText(r.getKeteranganRetur());
        cbStatus.setValue(r.getStatusRetur());
        cbIdPembelian.setValue(r.getIdPembelian());
        cbIdPembelian.setDisable(true);
    }

    private void clearForm() {
        selected = null;
        noReturField.clear();
        jumlahReturField.clear();
        alasanReturField.clear();
        keteranganReturField.clear();
        tanggalReturField.setValue(null);
        cbStatus.getSelectionModel().clearSelection();
        cbIdPembelian.setValue(null);
        cbIdPembelian.setDisable(false);
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