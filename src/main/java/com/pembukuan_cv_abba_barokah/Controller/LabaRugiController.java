package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Service.*;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class LabaRugiController {

    @FXML
    private TableView<Penjualan> table;
    @FXML
    private TableColumn<Penjualan, String> colFaktur;
    @FXML
    private TableColumn<Penjualan, BigDecimal> colPenjualan;
    @FXML
    private TableColumn<Penjualan, BigDecimal> colHpp;
    @FXML
    private TableColumn<Penjualan, BigDecimal> colLaba;
    @FXML
    private TableColumn<Penjualan, BigDecimal> colPph;

    private final PenjualanService penjualanService = new PenjualanService();
    private final LabaRugiService labaRugiService = new LabaRugiService();

    private final ObservableList<Penjualan> data = FXCollections.observableArrayList();

    private final NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    public void initialize() {

        colFaktur.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNoFaktur()));

        colPenjualan
                .setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTotal()));

        colHpp.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                labaRugiService.totalHppByPenjualan(c.getValue().getId())));

        colLaba.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                labaRugiService.labaRugi(c.getValue())));

        colPph.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                labaRugiService.hitungPph(c.getValue())));

        formatRupiah(colPenjualan);
        formatRupiah(colHpp);
        formatRupiah(colLaba);
        formatRupiah(colPph);

        table.setItems(data);
        loadData();
    }

    private void loadData() {
        data.setAll(penjualanService.getAll());
    }

    private void formatRupiah(TableColumn<Penjualan, BigDecimal> col) {
        col.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : rupiah.format(value));
            }
        });
    }
}