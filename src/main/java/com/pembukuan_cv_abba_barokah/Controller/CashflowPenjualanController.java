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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class CashflowPenjualanController {

    @FXML private Label bulanTahunLabel;
    @FXML private TableView<Cashflow> table;

    @FXML private TableColumn<Cashflow, String> tanggalCol;
    @FXML private TableColumn<Cashflow, String> fakturCol;
    @FXML private TableColumn<Cashflow, String> nominalCol;
    @FXML private TableColumn<Cashflow, String> bayarCol;
    @FXML private TableColumn<Cashflow, String> pphCol;

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
                "Bulan : " + capitalize(bulan) + " " + now.getYear()
        );

        // ===== SINKRONISASI KOLOM =====
        tanggalCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getBulan() + " " + c.getValue().getTahun()
                )
        );

        fakturCol.setCellValueFactory(c ->
                new SimpleStringProperty("CF-" + c.getValue().getId())
        );

        nominalCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getTotalPemasukan().toString()
                )
        );

        bayarCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getSaldoAkhir().toString()
                )
        );

        pphCol.setCellValueFactory(c -> {
            BigDecimal pph = c.getValue()
                    .getTotalPemasukan()
                    .multiply(BigDecimal.valueOf(0.11));
            return new SimpleStringProperty(pph.toString());
        });

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