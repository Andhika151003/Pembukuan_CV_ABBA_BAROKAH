package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.SaldoBankTahunLalu;
import com.pembukuan_cv_abba_barokah.Service.SaldoBankTahunLaluService;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class SaldoBankTahunLaluController {

    @FXML
    private TableView<SaldoBankTahunLalu> table;

    @FXML
    private TableColumn<SaldoBankTahunLalu, Integer> colId;
    @FXML
    private TableColumn<SaldoBankTahunLalu, String> colTanggal;
    @FXML
    private TableColumn<SaldoBankTahunLalu, BigDecimal> colTotalSaldo;
    @FXML
    private TableColumn<SaldoBankTahunLalu, String> colKeterangan;

    @FXML
    private DatePicker dpTanggal;
    @FXML
    private TextField tfTotalSaldo;
    @FXML
    private TextArea tfKeterangan;

    private final SaldoBankTahunLaluService service = new SaldoBankTahunLaluService();

    private final ObservableList<SaldoBankTahunLalu> data = FXCollections.observableArrayList();

    private SaldoBankTahunLalu selected;

    private final NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    public void initialize() {

        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getId()));

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));

        colTotalSaldo.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getTotalSaldo()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeterangan()));

        /* FORMAT RUPIAH */
        colTotalSaldo.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(rupiah.format(item));
                }
            }
        });

        table.setItems(data);
        loadData();

        table.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        SaldoBankTahunLalu saldo = new SaldoBankTahunLalu(
                null,
                dpTanggal.getValue(),
                new BigDecimal(tfTotalSaldo.getText()),
                tfKeterangan.getText());

        service.save(saldo);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {

        if (selected == null)
            return;

        selected = new SaldoBankTahunLalu(
                selected.getId(),
                dpTanggal.getValue(),
                new BigDecimal(tfTotalSaldo.getText()),
                tfKeterangan.getText());

        service.update(selected);
        loadData();
        clearForm();
    }

    @FXML
    private void handleDelete() {

        if (selected == null)
            return;

        service.delete(selected.getId());
        loadData();
        clearForm();
    }

    private void isiForm(SaldoBankTahunLalu saldo) {

        if (saldo == null)
            return;

        selected = saldo;

        dpTanggal.setValue(saldo.getTanggal());
        tfTotalSaldo.setText(saldo.getTotalSaldo().toString());
        tfKeterangan.setText(saldo.getKeterangan());
    }

    private void clearForm() {

        selected = null;

        dpTanggal.setValue(LocalDate.now());
        tfTotalSaldo.clear();
        tfKeterangan.clear();

        table.getSelectionModel().clearSelection();
    }
}