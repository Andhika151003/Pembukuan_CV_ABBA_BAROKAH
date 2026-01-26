package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class JurnalPembukuanController {

    @FXML private Label bulanTahunLabel;
    @FXML private TableView<JurnalPembukuan> jurnalTable;

    @FXML private TableColumn<JurnalPembukuan, Integer> noCol;
    @FXML private TableColumn<JurnalPembukuan, LocalDate> tanggalCol;
    @FXML private TableColumn<JurnalPembukuan, String> ketCol;
    @FXML private TableColumn<JurnalPembukuan, Double> debetCol;
    @FXML private TableColumn<JurnalPembukuan, Double> kreditCol;

    @FXML private Button btnSimpan;

    private final ObservableList<JurnalPembukuan> data =
            FXCollections.observableArrayList();

    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    private void initialize() {

        // ===== AUTO BULAN & TAHUN SAAT INI =====
        LocalDate now = LocalDate.now();
        String bulan = now.getMonth()
                .getDisplayName(TextStyle.FULL, new Locale("id", "ID"));

        bulanTahunLabel.setText(
                "Bulan : " + capitalize(bulan) + " " + now.getYear() // bulan tahun diambil berdasarkan bulan dan tahun itu juga (real time)
        );

        // ===== TABLE CONFIG =====
        noCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleIntegerProperty(c.getValue().getNo()).asObject());

        tanggalCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggal()));

        ketCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getKeterangan()));

        debetCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getDebet()).asObject());

        kreditCol.setCellValueFactory(c ->
                new javafx.beans.property.SimpleDoubleProperty(c.getValue().getKredit()).asObject());

        debetCol.setCellFactory(col -> rupiahCell());
        kreditCol.setCellFactory(col -> rupiahCell());

        loadJurnalBulanan(now);
    }

    // ===== SIMULASI DATA DARI 13 SUB MENU =====
    private void loadJurnalBulanan(LocalDate bulanAktif) {
        data.clear();

        data.add(new JurnalPembukuan(
                1,
                bulanAktif.withDayOfMonth(1),
                "Penjualan Barang",
                5_000_000,
                0
        ));

        data.add(new JurnalPembukuan(
                2,
                bulanAktif.withDayOfMonth(3),
                "Pembelian Inventaris",
                0,
                2_000_000
        ));

        data.add(new JurnalPembukuan(
                3,
                bulanAktif.withDayOfMonth(7),
                "Biaya Administrasi",
                0,
                750_000
        ));

        data.add(new JurnalPembukuan(
                4,
                bulanAktif.withDayOfMonth(10),
                "HPP Produksi",
                0,
                1_500_000
        ));

        jurnalTable.setItems(data);
    }

    private TableCell<JurnalPembukuan, Double> rupiahCell() {
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

    @FXML
    private void handleSimpan() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Simpan Jurnal Pembukuan");
        alert.setContentText("Apakah data jurnal bulan ini sudah sesuai?");

        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                // backend: export DOCX
                System.out.println("Jurnal bulanan dikirim ke backend");
            }
        });
    }

    private String capitalize(String text) {
        return text.substring(0,1).toUpperCase() + text.substring(1);
    }
}