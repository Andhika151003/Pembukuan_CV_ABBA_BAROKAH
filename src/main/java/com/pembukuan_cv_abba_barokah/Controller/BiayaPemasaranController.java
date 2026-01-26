package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BiayaPemasaranController {

    // ===== IDENTITAS TRANSAKSI =====
    @FXML private DatePicker tanggalField;
    @FXML private TextField nomorTransaksiField;
    @FXML private TextField namaBarangField;
    @FXML private ComboBox<String> kategoriBox;

    // ===== INFORMASI PEMBELIAN =====
    @FXML private TextField jumlahField;
    @FXML private TextField satuanField;
    @FXML private TextField hargaSatuanField;
    @FXML private TextField totalPembelianField;

    // ===== BIAYA TAMBAHAN =====
    @FXML private TextField biayaTransportField;
    @FXML private TextField biayaLainField;
    @FXML private TextField totalBiayaTambahanField;
    @FXML private TextField totalBarangContohField;

    // ===== PEMBAYARAN & KETERANGAN =====
    @FXML private ComboBox<String> metodeBayarBox;
    @FXML private TextArea keteranganArea;

    @FXML private Button btnSimpan;

    // ===== INIT =====
    @FXML
    private void initialize() {

        // KATEGORI BARANG CONTOH
        kategoriBox.getItems().addAll(
                "Sample Produksi",
                "Contoh Display",
                "Barang Uji Kualitas"
        );

        // METODE PEMBAYARAN
        metodeBayarBox.getItems().addAll(
                "Tunai",
                "Transfer Bank",
                "E-Wallet"
        );

        // AUTO HITUNG
        jumlahField.textProperty().addListener((a, b, c) -> hitungTotal());
        hargaSatuanField.textProperty().addListener((a, b, c) -> hitungTotal());
        biayaTransportField.textProperty().addListener((a, b, c) -> hitungTotal());
        biayaLainField.textProperty().addListener((a, b, c) -> hitungTotal());

        totalPembelianField.setEditable(false);
        totalBiayaTambahanField.setEditable(false);
        totalBarangContohField.setEditable(false);
    }

    // ===== HITUNG TOTAL =====
    private void hitungTotal() {

        double jumlah = parseDouble(jumlahField.getText());
        double harga = parseDouble(hargaSatuanField.getText());
        double transport = parseDouble(biayaTransportField.getText());
        double lain = parseDouble(biayaLainField.getText());

        double totalPembelian = jumlah * harga;
        double totalBiaya = transport + lain;
        double totalAkhir = totalPembelian + totalBiaya;

        totalPembelianField.setText(formatRupiah(totalPembelian));
        totalBiayaTambahanField.setText(formatRupiah(totalBiaya));
        totalBarangContohField.setText(formatRupiah(totalAkhir));
    }

    // ===== SIMPAN =====
    @FXML
    private void handleSimpan() {

        System.out.println("=== DATA PEMBELIAN BARANG CONTOH ===");
        System.out.println("Tanggal        : " + tanggalField.getValue());
        System.out.println("No Transaksi   : " + nomorTransaksiField.getText());
        System.out.println("Nama Barang    : " + namaBarangField.getText());
        System.out.println("Kategori       : " + kategoriBox.getValue());
        System.out.println("Jumlah         : " + jumlahField.getText());
        System.out.println("Satuan         : " + satuanField.getText());
        System.out.println("Harga Satuan   : " + hargaSatuanField.getText());
        System.out.println("Total Barang   : " + totalBarangContohField.getText());
        System.out.println("Metode Bayar   : " + metodeBayarBox.getValue());
        System.out.println("Keterangan     : " + keteranganArea.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText("Data pembelian barang contoh berhasil disimpan.");
        alert.showAndWait();

        clearForm();
    }

    // ===== CLEAR FORM =====
    private void clearForm() {

        tanggalField.setValue(null);
        nomorTransaksiField.clear();
        namaBarangField.clear();
        kategoriBox.getSelectionModel().clearSelection();

        jumlahField.clear();
        satuanField.clear();
        hargaSatuanField.clear();
        totalPembelianField.clear();

        biayaTransportField.clear();
        biayaLainField.clear();
        totalBiayaTambahanField.clear();
        totalBarangContohField.clear();

        metodeBayarBox.getSelectionModel().clearSelection();
        keteranganArea.clear();
    }

    // ===== UTIL =====
    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0;
        }
    }

    private String formatRupiah(double value) {
        return String.format("Rp %,.0f", value).replace(",", ".");
    }
}