package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Cashflow;
import com.pembukuan_cv_abba_barokah.Service.CashflowService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class CashflowPenjualanController {

    @FXML private Label bulanTahunLabel;
    @FXML private TableView<Cashflow> table;

    @FXML private TableColumn<Cashflow, String> bulanCol;
    @FXML private TableColumn<Cashflow, String> pemasukanCol;
    @FXML private TableColumn<Cashflow, String> pengeluaranCol;
    @FXML private TableColumn<Cashflow, String> saldoAwalCol;
    @FXML private TableColumn<Cashflow, String> saldoAkhirCol;

    private final ObservableList<Cashflow> data =
            FXCollections.observableArrayList();

    private final CashflowService cashflowService =
            new CashflowService();

    @FXML
    private void initialize() {

        // ===== HEADER BULAN & TAHUN =====
        LocalDate now = LocalDate.now();
        String bulan = now.getMonth()
                .getDisplayName(TextStyle.FULL, new Locale("id", "ID"));

        bulanTahunLabel.setText(
                "Cashflow Bulan " + capitalize(bulan) + " " + now.getYear()
        );

        // ===== SINKRONISASI KOLOM DENGAN MODEL =====
        bulanCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getBulan() + " " + c.getValue().getTahun()
                )
        );

        pemasukanCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getTotalPemasukan().toString()
                )
        );

        pengeluaranCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getTotalPengeluaran().toString()
                )
        );

        saldoAwalCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getSaldoAwal().toString()
                )
        );

        saldoAkhirCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getSaldoAkhir().toString()
                )
        );

        // ===== LOAD DATA =====
        loadCashflowFromDatabase();
    }

    private void loadCashflowFromDatabase() {
        data.clear();
        List<Cashflow> cashflows =
                cashflowService.getAllCashflow();
        data.addAll(cashflows);
        table.setItems(data);
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}