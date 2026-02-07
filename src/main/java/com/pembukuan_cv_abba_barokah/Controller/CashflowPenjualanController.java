package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.CashflowPenjualan;
import com.pembukuan_cv_abba_barokah.Service.CashflowPenjualanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CashflowPenjualanController {

        @FXML
        private ComboBox<String> cbBulan;
        @FXML
        private ComboBox<String> cbTahun;

        @FXML
        private TableView<CashflowPenjualan> table;
        @FXML
        private TableColumn<CashflowPenjualan, String> colTanggal;
        @FXML
        private TableColumn<CashflowPenjualan, String> colFaktur;
        @FXML
        private TableColumn<CashflowPenjualan, BigDecimal> colPenjualan;
        @FXML
        private TableColumn<CashflowPenjualan, BigDecimal> colPembayaran;
        @FXML
        private TableColumn<CashflowPenjualan, BigDecimal> colLaba;
        @FXML
        private TableColumn<CashflowPenjualan, BigDecimal> colPph;
        @FXML
        private TableColumn<CashflowPenjualan, Integer> colId;

        private final CashflowPenjualanService service = new CashflowPenjualanService();
        private final ObservableList<CashflowPenjualan> data = FXCollections.observableArrayList();

        private final NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        @FXML
        public void initialize() {

                cbBulan.setItems(FXCollections.observableArrayList(
                                "01", "02", "03", "04", "05", "06",
                                "07", "08", "09", "10", "11", "12"));

                cbTahun.setItems(FXCollections.observableArrayList("2025", "2026", "2027"));

                cbBulan.setValue("02");
                cbTahun.setValue("2026");

                colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getTanggal().toString()));

                colFaktur.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getNoFaktur()));

                colPenjualan.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getTotalPenjualan()));

                colPembayaran.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getPembayaran()));

                colLaba.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getLaba()));

                colPph.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getPph11()));

                colId.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getIdPenjualan()));

                /* ================= FORMAT RUPIAH ================= */

                colPenjualan.setCellFactory(column -> createRupiahCell());
                colPembayaran.setCellFactory(column -> createRupiahCell());
                colLaba.setCellFactory(column -> createRupiahCell());
                colPph.setCellFactory(column -> createRupiahCell());

                table.setItems(data);
                loadData();
        }

        private TableCell<CashflowPenjualan, BigDecimal> createRupiahCell() {
                return new TableCell<>() {
                        @Override
                        protected void updateItem(BigDecimal item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                        setText(null);
                                } else {
                                        setText(rupiah.format(item));
                                }
                        }
                };
        }

        @FXML
        private void loadData() {
                data.setAll(service.getByPeriode(
                                cbBulan.getValue(),
                                cbTahun.getValue()));
        }
}