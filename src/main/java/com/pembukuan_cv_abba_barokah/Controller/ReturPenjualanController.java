package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import com.pembukuan_cv_abba_barokah.Service.ReturPenjualanService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReturPenjualanController {

    @FXML private TextField noReturField;
    @FXML private DatePicker tanggalReturField;
    @FXML private TextField jumlahReturField;
    @FXML private TextField alasanReturField;
    @FXML private TextField keteranganReturField;
    @FXML private ComboBox<ReturPenjualan.StatusRetur> cbStatus;
    @FXML private ComboBox<ReturPenjualan.JenisPengembalian> cbJenis;
    @FXML private ComboBox<Integer> cbIdPenjualan;

    @FXML private TableView<ReturPenjualan> tableRetur;
    @FXML private TableColumn<ReturPenjualan, String> colNo;
    @FXML private TableColumn<ReturPenjualan, Integer> colJumlah;
    @FXML private TableColumn<ReturPenjualan, String> colStatus;
    @FXML private TableColumn<ReturPenjualan, Integer> colIdPenjualan;

    private final ReturPenjualanService service = new ReturPenjualanService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<ReturPenjualan> data = FXCollections.observableArrayList();

    private ReturPenjualan selected;

    @FXML
    public void initialize() {

        cbStatus.setItems(FXCollections.observableArrayList(
                ReturPenjualan.StatusRetur.values()));

        cbJenis.setItems(FXCollections.observableArrayList(
                ReturPenjualan.JenisPengembalian.values()));

        refreshComboIdPenjualan();

        colNo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNoRetur()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getJumlahRetur()));

        colStatus.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getStatusRetur().name()));

        colIdPenjualan.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdPenjualan()));

        tableRetur.setItems(data);
        loadData();

        tableRetur.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    private void refreshComboIdPenjualan() {
        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream()
                        .map(p -> p.getId())
                        .filter(id -> !service.sudahAdaUntukPenjualan(id))
                        .toList()
        ));
    }

    @FXML
    private void handleSimpan() {

        int idPenjualan = cbIdPenjualan.getValue();

        if (service.sudahAdaUntukPenjualan(idPenjualan)) {
            alert("Duplikat", "Retur untuk penjualan ini sudah ada.");
            return;
        }

        ReturPenjualan r = new ReturPenjualan(
                noReturField.getText(),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                alasanReturField.getText(),
                keteranganReturField.getText(),
                cbStatus.getValue(),
                cbJenis.getValue(),
                idPenjualan
        );

        service.simpan(r);
        loadData();
        refreshComboIdPenjualan();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        if (selected == null) return;

        selected = new ReturPenjualan(
                selected.getId(),
                noReturField.getText(),
                tanggalReturField.getValue(),
                Integer.parseInt(jumlahReturField.getText()),
                alasanReturField.getText(),
                keteranganReturField.getText(),
                cbStatus.getValue(),
                cbJenis.getValue(),
                selected.getIdPenjualan() // FK TETAP
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
        refreshComboIdPenjualan();
        clearForm();
    }

    private void isiForm(ReturPenjualan r) {
        if (r == null) return;

        selected = r;

        noReturField.setText(r.getNoRetur());
        tanggalReturField.setValue(r.getTanggalRetur());
        jumlahReturField.setText(String.valueOf(r.getJumlahRetur()));
        alasanReturField.setText(r.getAlasanRetur());
        keteranganReturField.setText(r.getKeteranganRetur());
        cbStatus.setValue(r.getStatusRetur());
        cbJenis.setValue(r.getJenisPengembalian());
        cbIdPenjualan.setValue(r.getIdPenjualan());
        cbIdPenjualan.setDisable(true);
    }

    private void clearForm() {
        selected = null;
        noReturField.clear();
        jumlahReturField.clear();
        alasanReturField.clear();
        keteranganReturField.clear();
        tanggalReturField.setValue(null);
        cbStatus.getSelectionModel().clearSelection();
        cbJenis.getSelectionModel().clearSelection();
        cbIdPenjualan.setValue(null);
        cbIdPenjualan.setDisable(false);
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