package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PersediaanBarangController {

    @FXML private TableView<?> table;
    @FXML private TextField adjustField;
    @FXML private TextArea ketArea;
    @FXML private Button btnSimpan;

    @FXML
    private void initialize() {
        // nanti isi dari transaksi pembelian & penjualan
    }

    @FXML
    private void handleSimpan() {
        System.out.println("Penyesuaian stok disimpan");
        // Jurnal:
        // Beban Selisih Persediaan
        //    Persediaan Barang
    }
}