package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Service.LabaRugiService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class LabaRugiController {

    @FXML private TableView<Penjualan> table;
    @FXML private TableColumn<Penjualan, String> colFaktur;
    @FXML private TableColumn<Penjualan, BigDecimal> colPenjualan;
    @FXML private TableColumn<Penjualan, BigDecimal> colHpp;
    @FXML private TableColumn<Penjualan, BigDecimal> colLaba;

    private final PenjualanService penjualanService = new PenjualanService();
    private final LabaRugiService labaRugiService = new LabaRugiService();

    private final ObservableList<Penjualan> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colFaktur.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNoFaktur()));

        colPenjualan.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTotal()));

        colHpp.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        labaRugiService.totalHppByPenjualan(c.getValue().getId())
                ));

        colLaba.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        labaRugiService.labaRugi(c.getValue())
                ));

        table.setItems(data);
        loadData();
    }

    private void loadData() {
        data.setAll(penjualanService.getAll());
    }

    /* Optional: format rupiah */
    @FXML
    private void formatRupiah() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        colPenjualan.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(BigDecimal val, boolean empty) {
                super.updateItem(val, empty);
                setText(empty ? null : format.format(val));
            }
        });
    }
}