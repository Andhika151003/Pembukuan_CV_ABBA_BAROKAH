package com.pembukuan_cv_abba_barokah.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.util.Locale;

public class LabaKotorController {

    @FXML
    private TextField totalPenjualanField;

    @FXML
    private TextField totalHppField;

    @FXML
    private TextField labaKotorField;

    private final NumberFormat rupiah =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    public void initialize() {
        // SIMULASI DATA (NANTI AMBIL DARI REKAP)
        double totalPenjualan = 0;
        double totalHpp = 0;

        tampilkanData(totalPenjualan, totalHpp);
    }

    public void tampilkanData(double totalPenjualan, double totalHpp) {
        double labaKotor = totalPenjualan - totalHpp;

        totalPenjualanField.setText(rupiah.format(totalPenjualan));
        totalHppField.setText(rupiah.format(totalHpp));
        labaKotorField.setText(rupiah.format(labaKotor));
    }
}