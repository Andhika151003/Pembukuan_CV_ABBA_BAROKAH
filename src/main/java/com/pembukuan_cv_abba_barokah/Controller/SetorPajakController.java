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

        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream().map(p -> p.getId()).toList()
        ));

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
        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        SetorPajak sp = new SetorPajak(
                tanggalField.getValue(),
                cbJenisPajak.getValue(),
                new BigDecimal(jumlahField.getText()),
                periodeField.getText(),
                buktiField.getText(),
                cbIdPenjualan.getValue()
        );

        service.simpan(sp);
        loadData();
    }
}