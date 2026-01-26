package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.NeracaLabaRugi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LaporanNeracaController implements Initializable {

    @FXML private Label labelTahun;

    @FXML private Label bank;
    @FXML private Label piutangLain;
    @FXML private Label persediaan;
    @FXML private Label totalAsetLancar;

    @FXML private Label biayaInventaris;
    @FXML private Label biayaLain;
    @FXML private Label totalAsetTidakLancar;

    @FXML private Label totalAset;

    @FXML private Label utangUsaha;

    @FXML private Label ekuitas;
    @FXML private Label modalDisetor;
    @FXML private Label jumlahEkuitas;

    @FXML private Label totalKewajibanEkuitas;

    private final NumberFormat rp =
            NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelTahun.setText("Tahun : 2026");

        LaporanNeraca n = new LaporanNeraca();

        // Contoh data
        n.bank = 5_000_000;
        n.piutangLain = 2_000_000;
        n.persediaan = 3_000_000;

        n.biayaInventaris = 1_500_000;
        n.biayaLain = 500_000;

        n.utangUsaha = 4_000_000;
        n.ekuitas = 5_000_000;
        n.modalDisetor = 3_000_000;

        bank.setText(rp(n.bank));
        piutangLain.setText(rp(n.piutangLain));
        persediaan.setText(rp(n.persediaan));
        totalAsetLancar.setText(rp(n.getTotalAsetLancar()));

        biayaInventaris.setText(rp(n.biayaInventaris));
        biayaLain.setText(rp(n.biayaLain));
        totalAsetTidakLancar.setText(rp(n.getTotalAsetTidakLancar()));

        totalAset.setText(rp(n.getTotalAset()));

        utangUsaha.setText(rp(n.utangUsaha));

        ekuitas.setText(rp(n.ekuitas));
        modalDisetor.setText(rp(n.modalDisetor));
        jumlahEkuitas.setText(rp(n.getJumlahEkuitas()));

        totalKewajibanEkuitas.setText(rp(n.getTotalKewajibanEkuitas()));
    }

    private String rp(double nilai) {
        return rp.format(nilai);
    }
}