package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import com.pembukuan_cv_abba_barokah.Service.JurnalPembukuanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class JurnalPembukuanController {

        @FXML
        private ComboBox<String> cbBulan;
        @FXML
        private ComboBox<String> cbTahun;

        @FXML
        private TableView<JurnalPembukuan> table;
        @FXML
        private TableColumn<JurnalPembukuan, String> colTanggal;
        @FXML
        private TableColumn<JurnalPembukuan, String> colMenu;
        @FXML
        private TableColumn<JurnalPembukuan, String> colKeterangan;
        @FXML
        private TableColumn<JurnalPembukuan, BigDecimal> colDebit;
        @FXML
        private TableColumn<JurnalPembukuan, BigDecimal> colKredit;

        /* ===== TOTAL LABEL ===== */
        @FXML
        private Label lblTotalDebit;
        @FXML
        private Label lblTotalKredit;
        @FXML
        private Label lblSaldo;

        private final JurnalPembukuanService service = new JurnalPembukuanService();
        private final ObservableList<JurnalPembukuan> data = FXCollections.observableArrayList();

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

                colMenu.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getNamaMenu()));

                colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getKeterangan()));

                colDebit.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getDebit()));

                colKredit.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getKredit()));

                table.setItems(data);
                loadData();
        }

        @FXML
        private void loadData() {

                data.setAll(service.getByPeriode(
                                cbBulan.getValue(),
                                cbTahun.getValue()));

                hitungTotal();
        }

        /* ================= HITUNG TOTAL ================= */

        private void hitungTotal() {

                BigDecimal totalDebit = data.stream()
                                .map(JurnalPembukuan::getDebit)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal totalKredit = data.stream()
                                .map(JurnalPembukuan::getKredit)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal saldo = totalDebit.subtract(totalKredit);

                lblTotalDebit.setText(rupiah.format(totalDebit));
                lblTotalKredit.setText(rupiah.format(totalKredit));
                lblSaldo.setText(rupiah.format(saldo));
        }
}