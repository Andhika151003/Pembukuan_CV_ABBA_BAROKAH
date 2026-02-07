package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import com.pembukuan_cv_abba_barokah.Service.ReturPenjualanService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReturPenjualanController {

    @FXML
    private TextField noReturField;
    @FXML
    private DatePicker tanggalReturField;
    @FXML
    private TextField jumlahReturField;
    @FXML
    private TextField keteranganReturField;
    @FXML
    private ComboBox<Integer> cbIdPenjualan;

    @FXML
    private TableView<ReturPenjualan> tableRetur;
    @FXML
    private TableColumn<ReturPenjualan, String> colNo;
    @FXML
    private TableColumn<ReturPenjualan, String> colTanggal;
    @FXML
    private TableColumn<ReturPenjualan, Integer> colJumlah;
    @FXML
    private TableColumn<ReturPenjualan, String> colKeterangan;
    @FXML
    private TableColumn<ReturPenjualan, Integer> colIdPenjualan;

    private final ReturPenjualanService service = new ReturPenjualanService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<ReturPenjualan> data = FXCollections.observableArrayList();

    private ReturPenjualan selected;

    @FXML
    public void initialize() {

        refreshCombo();

        colNo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNoRetur()));

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggalRetur().toString()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahRetur()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeteranganRetur()));

        colIdPenjualan.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getIdPenjualan()));

        tableRetur.setItems(data);
        loadData();

        tableRetur.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    private void refreshCombo() {
        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream()
                        .map(p -> p.getId())
                        .filter(id -> !service.sudahAdaUntukPenjualan(id))
                        .toList()));
    }

    @FXML
    private void handleSimpan() {

        int idPenjualan = cbIdPenjualan.getValue();

        if (service.sudahAdaUntukPenjualan(idPenjualan)) {
            alert("Duplikat", "Penjualan ini sudah memiliki retur.");
            return;
        }

        ReturPenjualan r = new ReturPenjualan(
                noReturField.getText(),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                keteranganReturField.getText(),
                idPenjualan);

        service.simpan(r);
        loadData();
        refreshCombo();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        if (selected == null)
            return;

        ReturPenjualan r = new ReturPenjualan(
                selected.getId(),
                noReturField.getText(),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                keteranganReturField.getText(),
                selected.getIdPenjualan());

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

    private void isiForm(ReturPenjualan r) {
        if (r == null)
            return;

        selected = r;
        noReturField.setText(r.getNoRetur());
        tanggalReturField.setValue(r.getTanggalRetur());
        jumlahReturField.setText(String.valueOf(r.getJumlahRetur()));
        keteranganReturField.setText(r.getKeteranganRetur());
        cbIdPenjualan.setValue(r.getIdPenjualan());
        cbIdPenjualan.setDisable(true);
    }

    private void clearForm() {
        selected = null;
        noReturField.clear();
        jumlahReturField.clear();
        keteranganReturField.clear();
        tanggalReturField.setValue(null);
        cbIdPenjualan.setDisable(false);
        cbIdPenjualan.setValue(null);
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