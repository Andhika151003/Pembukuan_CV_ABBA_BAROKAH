package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Service.UtangUsahaService;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class UtangUsahaController {

    @FXML private TextField noUtangField;
    @FXML private DatePicker tanggalUtangField;
    @FXML private DatePicker tanggalJatuhTempoField;
    @FXML private TextField jumlahUtangField;
    @FXML private TextField jumlahDibayarField;
    @FXML private ComboBox<UtangUsaha.StatusUtang> cbStatus;
    @FXML private TextField keteranganField;
    @FXML private ComboBox<Integer> cbIdPembelian;

    @FXML private TableView<UtangUsaha> tableUtang;
    @FXML private TableColumn<UtangUsaha, String> colNo;
    @FXML private TableColumn<UtangUsaha, String> colStatus;
    @FXML private TableColumn<UtangUsaha, Integer> colIdPembelian;

    private final UtangUsahaService service = new UtangUsahaService();
    private final PembelianInventarisService pembelianService =
            new PembelianInventarisService();

    private final ObservableList<UtangUsaha> data =
            FXCollections.observableArrayList();

    private UtangUsaha selected;

    @FXML
    public void initialize() {

        cbStatus.setItems(FXCollections.observableArrayList(
                UtangUsaha.StatusUtang.values()));

        refreshComboIdPembelian();

        colNo.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getNoUtang()));

        colStatus.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(
                        c.getValue().getStatusUtang().name()));

        colIdPembelian.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        c.getValue().getIdPembelian()));

        tableUtang.setItems(data);
        loadData();

        tableUtang.getSelectionModel()
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
            alert("Duplikat", "Utang untuk pembelian ini sudah ada.");
            return;
        }

        UtangUsaha u = new UtangUsaha(
                noUtangField.getText(),
                tanggalUtangField.getValue(),
                tanggalJatuhTempoField.getValue(),
                new BigDecimal(jumlahUtangField.getText()),
                new BigDecimal(jumlahDibayarField.getText()),
                cbStatus.getValue(),
                keteranganField.getText(),
                idPembelian
        );

        service.simpan(u);
        loadData();
        refreshComboIdPembelian();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        if (selected == null) return;

        selected = new UtangUsaha(
                selected.getId(),
                noUtangField.getText(),
                tanggalUtangField.getValue(),
                tanggalJatuhTempoField.getValue(),
                new BigDecimal(jumlahUtangField.getText()),
                new BigDecimal(jumlahDibayarField.getText()),
                cbStatus.getValue(),
                keteranganField.getText(),
                selected.getIdPembelian()
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

    private void isiForm(UtangUsaha u) {
        if (u == null) return;

        selected = u;

        noUtangField.setText(u.getNoUtang());
        tanggalUtangField.setValue(u.getTanggalUtang());
        tanggalJatuhTempoField.setValue(u.getTanggalJatuhTempo());
        jumlahUtangField.setText(u.getJumlahUtang().toString());
        jumlahDibayarField.setText(u.getJumlahDibayar().toString());
        cbStatus.setValue(u.getStatusUtang());
        keteranganField.setText(u.getKeterangan());
        cbIdPembelian.setValue(u.getIdPembelian());
        cbIdPembelian.setDisable(true);
    }

    private void clearForm() {
        selected = null;
        noUtangField.clear();
        jumlahUtangField.clear();
        jumlahDibayarField.clear();
        keteranganField.clear();
        tanggalUtangField.setValue(null);
        tanggalJatuhTempoField.setValue(null);
        cbStatus.getSelectionModel().clearSelection();
        cbIdPembelian.setValue(null);
        cbIdPembelian.setDisable(false);
        tableUtang.getSelectionModel().clearSelection();
    }

    private void alert(String t, String m) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}
