package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.CashflowPenjualan;
import com.pembukuan_cv_abba_barokah.Service.CashflowPenjualanService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.util.Locale;

public class CashflowPenjualanController {

    @FXML private ComboBox<String> cbBulan;
    @FXML private ComboBox<String> cbTahun;

    @FXML private TableView<CashflowPenjualan> table;
    @FXML private TableColumn<CashflowPenjualan, String> colTanggal;
    @FXML private TableColumn<CashflowPenjualan, String> colNoFaktur;
    @FXML private TableColumn<CashflowPenjualan, Number> colPenjualan;
    @FXML private TableColumn<CashflowPenjualan, Number> colPembayaran;
    @FXML private TableColumn<CashflowPenjualan, Number> colPph;

    private final CashflowPenjualanService service = new CashflowPenjualanService();

    @FXML
    public void initialize() {

        cbBulan.setItems(FXCollections.observableArrayList(
                "01","02","03","04","05","06","07","08","09","10","11","12"
        ));
        cbTahun.setItems(FXCollections.observableArrayList(
                "2024","2025","2026","2027"
        ));

        cbBulan.setValue("01");
        cbTahun.setValue("2026");

        colTanggal.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getTanggal().toString()
                )
        );

        colNoFaktur.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getNoFaktur()
                )
        );

        colPenjualan.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getNominalPenjualan()
                )
        );

        colPembayaran.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getNominalPembayaran()
                )
        );

        colPph.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getPph11()
                )
        );

        formatRupiah(colPenjualan);
        formatRupiah(colPembayaran);
        formatRupiah(colPph);

        loadData();
    }

    @FXML
    private void loadData() {
        table.setItems(
                FXCollections.observableArrayList(
                        service.getByPeriode(cbBulan.getValue(), cbTahun.getValue())
                )
        );
    }

    private void formatRupiah(TableColumn<CashflowPenjualan, Number> col) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        col.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Number value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? "" : nf.format(value));
            }
        });
    }
}