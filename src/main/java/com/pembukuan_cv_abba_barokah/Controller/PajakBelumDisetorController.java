package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.PajakBelumDisetorService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class PajakBelumDisetorController {

    @FXML
    private Label lblTotalPph;
    @FXML
    private Label lblTotalSetor;
    @FXML
    private Label lblBelumDisetor;
    @FXML
    private ComboBox<Integer> cbTahun;
    @FXML
    private Label lblTitle;

    private final PajakBelumDisetorService service = new PajakBelumDisetorService();

    private final NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    public void initialize() {

        cbTahun.getItems().addAll(2026, 2027, 2028);
        cbTahun.setValue(2026);
        cbTahun.setOnAction(e -> refresh());

        refresh();
    }

    private void refresh() {

        Integer tahun = cbTahun.getValue();

        lblTitle.setText("Pajak yang Belum Disetor " + tahun);

        BigDecimal totalPph = service.totalPph11(tahun);
        BigDecimal totalSetor = service.totalSetorPajak(tahun);
        BigDecimal belumDisetor = service.pajakBelumDisetor(tahun);

        lblTotalPph.setText(rupiah.format(totalPph));
        lblTotalSetor.setText(rupiah.format(totalSetor));
        lblBelumDisetor.setText(rupiah.format(belumDisetor));
    }
}