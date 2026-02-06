package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Service.SetorPajakService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class SetorPajakController {

    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<SetorPajak.JenisPajak> cbJenisPajak;
    @FXML private TextField jumlahField;
    @FXML private TextField periodeField;
    @FXML private TextField buktiField;
    @FXML private ComboBox<Integer> cbIdPenjualan;

    @FXML private TableView<SetorPajak> tablePajak;
    @FXML private TableColumn<SetorPajak, String> colTanggal;
    @FXML private TableColumn<SetorPajak, String> colJenis;
    @FXML private TableColumn<SetorPajak, BigDecimal> colJumlah;
    @FXML private TableColumn<SetorPajak, String> colPeriode;
    @FXML private TableColumn<SetorPajak, Integer> colIdPenjualan;

    private final SetorPajakService service = new SetorPajakService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<SetorPajak> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbJenisPajak.setItems(FXCollections.observableArrayList(
                SetorPajak.JenisPajak.values()
        ));

        refreshComboIdPenjualan();

        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getTanggalSetor().toString()));

        colJenis.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getJenisPajak().name()));

        colJumlah.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getJumlahPajak()));

        colPeriode.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getPeriode()));

        colIdPenjualan.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdPenjualan()));

        tablePajak.setItems(data);

        tablePajak.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> { if (newVal != null) fillForm(newVal); });

        loadData();
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
            alert("Duplikat", "Setor pajak untuk penjualan ini sudah ada.");
            return;
        }

        SetorPajak sp = new SetorPajak(
                tanggalField.getValue(),
                cbJenisPajak.getValue(),
                new BigDecimal(jumlahField.getText()),
                periodeField.getText(),
                buktiField.getText(),
                idPenjualan
        );

        service.simpan(sp);
        loadData();
        refreshComboIdPenjualan();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        SetorPajak sp = tablePajak.getSelectionModel().getSelectedItem();
        if (sp != null) {
            sp.setTanggalSetor(tanggalField.getValue());
            sp.setJenisPajak(cbJenisPajak.getValue());
            sp.setJumlahPajak(new BigDecimal(jumlahField.getText()));
            sp.setPeriode(periodeField.getText());
            sp.setBuktiSetor(buktiField.getText());

            service.update(sp);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleDelete() {
        SetorPajak sp = tablePajak.getSelectionModel().getSelectedItem();
        if (sp != null) {
            service.hapus(sp.getId());
            loadData();
            refreshComboIdPenjualan();
            clearForm();
        }
    }

    private void fillForm(SetorPajak sp) {
        tanggalField.setValue(sp.getTanggalSetor());
        cbJenisPajak.setValue(sp.getJenisPajak());
        jumlahField.setText(sp.getJumlahPajak().toString());
        periodeField.setText(sp.getPeriode());
        buktiField.setText(sp.getBuktiSetor());
        cbIdPenjualan.setValue(sp.getIdPenjualan());
        cbIdPenjualan.setDisable(true);
    }

    private void clearForm() {
        tanggalField.setValue(null);
        cbJenisPajak.setValue(null);
        jumlahField.clear();
        periodeField.clear();
        buktiField.clear();
        cbIdPenjualan.setValue(null);
        cbIdPenjualan.setDisable(false);
        tablePajak.getSelectionModel().clearSelection();
    }

    private void alert(String t, String m) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}