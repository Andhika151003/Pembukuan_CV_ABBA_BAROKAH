package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Cashflow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class CashflowPenjualanController {

    @FXML private Label bulanTahunLabel;

    @FXML private TableView<CashflowPenjualan> table;
    @FXML private TableColumn<CashflowPenjualan, LocalDate> tanggalCol;
    @FXML private TableColumn<CashflowPenjualan, String> fakturCol;
    @FXML private TableColumn<CashflowPenjualan, Double> nominalCol;
    @FXML private TableColumn<CashflowPenjualan, Double> bayarCol;
    @FXML private TableColumn<CashflowPenjualan, Double> pphCol;

    private final ObservableList<CashflowPenjualan> data =
            FXCollections.observableArrayList();

    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    private void initialize() {

        // ===== AUTO BULAN & TAHUN =====
        LocalDate now = LocalDate.now();
        String bulan = now.getMonth()
                .getDisplayName(TextStyle.FULL, new Locale("id", "ID"));

        bulanTahunLabel.setText(
                "Bulan : " + capitalize(bulan) + " " + now.getYear()
        );

        // ===== TABLE CONFIG =====
        tanggalCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggal()));

        fakturCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNoFaktur()));

        nominalCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getNominalPenjualan()).asObject());

        bayarCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPembayaran()).asObject());

        pphCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPph11()).asObject());

        nominalCol.setCellFactory(col -> rupiahCell());
        bayarCol.setCellFactory(col -> rupiahCell());
        pphCol.setCellFactory(col -> rupiahCell());

        loadCashflowBulanan(now);
    }

    // ===== SIMULASI DATA (NANTI GANTI DB / SERVICE) =====
    private void loadCashflowBulanan(LocalDate bulanAktif) {
        data.clear();

        data.add(new CashflowPenjualan(
                bulanAktif.withDayOfMonth(2),
                "INV-001",
                5_000_000,
                5_000_000,
                550_000
        ));

        data.add(new CashflowPenjualan(
                bulanAktif.withDayOfMonth(8),
                "INV-002",
                3_000_000,
                2_000_000,
                330_000
        ));

        table.setItems(data);
    }

    private TableCell<CashflowPenjualan, Double> rupiahCell() {
        return new TableCell<>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null || value == 0) {
                    setText("");
                } else {
                    setText(rupiah.format(value));
                }
            }
        };
    }

    private String capitalize(String text) {
        return text.substring(0,1).toUpperCase() + text.substring(1);
    }
}