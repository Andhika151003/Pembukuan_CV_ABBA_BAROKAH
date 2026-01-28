package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Cashflow;
import com.pembukuan_cv_abba_barokah.Service.CashflowService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class CashflowPenjualanController {

    @FXML
    private Label bulanTahunLabel;

    @FXML
    private TableView<Cashflow> table;

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

        // ===== LOAD DATA DARI SQLITE =====
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