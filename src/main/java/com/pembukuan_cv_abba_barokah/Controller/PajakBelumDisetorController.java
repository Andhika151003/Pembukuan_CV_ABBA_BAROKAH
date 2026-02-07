package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.PajakBelumDisetorService;
import javafx.fxml.FXML;
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

    private final PajakBelumDisetorService service = new PajakBelumDisetorService();

    private final NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @FXML
    public void initialize() {
        refresh();
    }

    private void refresh() {

        BigDecimal totalPph = service.totalPph11();
        BigDecimal totalSetor = service.totalSetorPajak();
        BigDecimal belumDisetor = service.pajakBelumDisetor();

        lblTotalPph.setText(rupiah.format(totalPph));
        lblTotalSetor.setText(rupiah.format(totalSetor));
        lblBelumDisetor.setText(rupiah.format(belumDisetor));
    }
}