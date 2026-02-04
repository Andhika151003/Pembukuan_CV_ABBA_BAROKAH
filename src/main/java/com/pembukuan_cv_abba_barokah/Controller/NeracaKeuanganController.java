package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.NeracaKeuangan;
import com.pembukuan_cv_abba_barokah.Service.NeracaKeuanganService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.util.Locale;

public class NeracaKeuanganController {

    @FXML private Label lblKasBank, lblPiutang, lblPersediaan, lblTotalAsetLancar;
    @FXML private Label lblAsetTidakLancar, lblJumlahAset;
    @FXML private Label lblUtangUsaha, lblModalDisetor, lblLabaDitahan;
    @FXML private Label lblJumlahEkuitas, lblTotalKewajibanEkuitas;

    private final NeracaKeuanganService service = new NeracaKeuanganService();

    @FXML
    public void initialize() {
        load();
    }

    private void load() {
        NeracaKeuangan n = service.get();

        lblKasBank.setText(rp(n.getKasBank()));
        lblPiutang.setText(rp(n.getPiutangUsaha()));
        lblPersediaan.setText(rp(n.getPersediaanBarang()));
        lblTotalAsetLancar.setText(rp(service.totalAsetLancar(n)));

        lblAsetTidakLancar.setText(rp(n.getAsetTidakLancar()));
        lblJumlahAset.setText(rp(service.jumlahAset(n)));

        lblUtangUsaha.setText(rp(n.getTotalUtang()));
        lblModalDisetor.setText(rp(n.getModalDisetor()));
        lblLabaDitahan.setText(rp(service.labaDitahan(n)));

        lblJumlahEkuitas.setText(rp(service.jumlahEkuitas(n)));
        lblTotalKewajibanEkuitas.setText(rp(service.totalKewajibanDanEkuitas(n)));
    }

    private String rp(Object v) {
        return NumberFormat.getCurrencyInstance(new Locale("id", "ID"))
                .format(v);
    }
}