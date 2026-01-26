package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.util.HashMap;
import java.util.Map;

// import com.sikeuabba.model.PenjualanItem;

public class PenjualanController {

    // ===== AUTO TRANSAKSI =====
    @FXML
    private ComboBox<String> noFakturCombo;
    @FXML
    private TextField tanggalField;

    // ===== HEADER =====
    @FXML
    private TextField pembeliField;
    @FXML
    private ComboBox<String> jenisPenjualanBox;

    // ===== INPUT DETAIL BARANG =====
    @FXML
    private TextField barangField;
    @FXML
    private TextField jumlahField;
    @FXML
    private TextField hargaField;

    // ===== TOTAL =====
    @FXML
    private Label totalItemLabel;
    @FXML
    private Label totalHargaLabel;
    @FXML
    private Label totalBayarLabel;

    @FXML
    private Button btnSimpan;

    // ===== TABLE =====
    @FXML
    private TableView<PenjualanItem> tableBarang;
    @FXML
    private TableColumn<PenjualanItem, String> colBarang;
    @FXML
    private TableColumn<PenjualanItem, Integer> colJumlah;
    @FXML
    private TableColumn<PenjualanItem, Double> colHarga;
    @FXML
    private TableColumn<PenjualanItem, Double> colSubtotal;

    private final ObservableList<PenjualanItem> itemList = FXCollections.observableArrayList();

    // ===== SIMULASI DATA TRANSAKSI (NANTI GANTI DB) =====
    private final Map<String, String> transaksiMap = new HashMap<>();

    // ===== INIT =====
    @FXML
    private void initialize() {
        jenisPenjualanBox.getItems().addAll("Tunai", "Kredit");

        colBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tanggalField.setText("");

        // ===== EDIT JUMLAH =====
        colJumlah.setCellFactory(TextFieldTableCell.forTableColumn(
                new IntegerStringConverter()));
// 
        colJumlah.setOnEditCommit(e -> {
            PenjualanItem item = e.getRowValue();
            if (e.getNewValue() > 0) {
                item.setJumlah(e.getNewValue());
                updateTotal();
            }
        });

        // ===== EDIT HARGA =====
        colHarga.setCellFactory(TextFieldTableCell.forTableColumn(
                new DoubleStringConverter()));

        colHarga.setOnEditCommit(e -> {
            PenjualanItem item = e.getRowValue();
            if (e.getNewValue() > 0) {
                item.setHarga(e.getNewValue());
                updateTotal();
            }
        });

        // ===== FORMAT RUPIAH (DISPLAY) =====
        colHarga.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : formatRupiah(value));
            }
        });

        colSubtotal.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : formatRupiah(value));
            }
        });

        tableBarang.setItems(itemList);
        tableBarang.setEditable(true);

        updateTotal();
        validateForm();

        pembeliField.textProperty().addListener((a, b, c) -> validateForm());
        jenisPenjualanBox.valueProperty().addListener((a, b, c) -> validateForm());

        // ===== DATA DUMMY TRANSAKSI =====
        transaksiMap.put("INV-001", "2026-01-10");
        transaksiMap.put("INV-002", "2026-01-12");

        noFakturCombo.setItems(
                FXCollections.observableArrayList(transaksiMap.keySet()));

        // AUTO ISI TANGGAL SAAT FAKTUR DIPILIH
        noFakturCombo.setOnAction(e -> {
            String faktur = noFakturCombo.getValue();
            tanggalField.setText(transaksiMap.getOrDefault(faktur, ""));
        });
    }

    // ===== TAMBAH BARANG =====
    @FXML
    private void handleTambahBarang() {
        try {
            String nama = barangField.getText();
            int jumlah = Integer.parseInt(jumlahField.getText());
            double harga = Double.parseDouble(hargaField.getText());

            if (nama.isBlank() || jumlah <= 0 || harga <= 0)
                return;

            itemList.add(new PenjualanItem(nama, jumlah, harga));

            barangField.clear();
            jumlahField.clear();
            hargaField.clear();

            updateTotal();
            validateForm();

        } catch (NumberFormatException ignored) {
        }
    }

    // ===== HAPUS BARANG =====
    @FXML
    private void handleHapusBarang() {
        PenjualanItem selected = tableBarang.getSelectionModel().getSelectedItem();

        if (selected != null) {
            itemList.remove(selected);
            updateTotal();
            validateForm();
        }
    }

    // ===== HITUNG TOTAL =====
    private void updateTotal() {
        int totalItem = 0;
        double totalHarga = 0;

        for (PenjualanItem item : itemList) {
            totalItem += item.getJumlah();
            totalHarga += item.getSubtotal();
        }

        totalItemLabel.setText(String.valueOf(totalItem));
        totalHargaLabel.setText(formatRupiah(totalHarga));
        totalBayarLabel.setText(formatRupiah(totalHarga));
    }

    // ===== VALIDASI =====
    private void validateForm() {
        boolean valid = !pembeliField.getText().isBlank() &&
                jenisPenjualanBox.getValue() != null &&
                !itemList.isEmpty();

        btnSimpan.setDisable(!valid);
    }

    // ===== SIMPAN =====
    @FXML
    private void handleSimpan() {
        System.out.println("Penjualan siap disimpan (header + detail)");
        System.out.println("Faktur  : " + noFakturCombo.getValue());
        System.out.println("Tanggal : " + tanggalField.getText());
    }

    // ===== FORMAT =====
    private String formatRupiah(double value) {
        return String.format("Rp %,.0f", value).replace(",", ".");
    }
}