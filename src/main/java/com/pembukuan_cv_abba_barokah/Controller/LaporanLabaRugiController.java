package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Service.LaporanLabaRugiService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import com.pembukuan_cv_abba_barokah.Service.LaporanLabaRugiWordExporter;

public class LaporanLabaRugiController {

    @FXML
    private ComboBox<String> cbTahun;

    @FXML
    private Label lblTotalPenjualan;
    @FXML
    private Label lblReturPenjualan;
    @FXML
    private Label lblTotalPendapatan;

    @FXML
    private Label lblTotalHpp;
    @FXML
    private Label lblReturPembelian;
    @FXML
    private Label lblTotalPembelian;

    @FXML
    private Label lblLabaRugi;

    @FXML
    private Label lblBiayaAdministrasi;
    @FXML
    private Label lblBiayaOperasional;
    @FXML
    private Label lblBiayaPemasaran;
    @FXML
    private Label lblBiayaLain;

    @FXML
    private Label lblTotalBiayaOperasional;
    @FXML
    private Label lblLabaBersih;

    private final LaporanLabaRugiService service = new LaporanLabaRugiService();

    @FXML
    public void initialize() {
        cbTahun.getItems().addAll("2026", "2027", "2028");
        cbTahun.setValue("2026");
        loadData();
    }

    @FXML
    public void loadData() {

        String tahun = cbTahun.getValue();

        BigDecimal penjualan = service.totalPenjualan(tahun);
        BigDecimal returPenjualan = service.totalReturPenjualan(tahun);
        BigDecimal totalPendapatan = service.totalPendapatan(tahun);

        BigDecimal hpp = service.totalHpp(tahun);
        BigDecimal returPembelian = service.totalReturPembelian(tahun);
        BigDecimal totalPembelian = service.totalPembelian(tahun);

        BigDecimal labaKotor = service.labaKotor(tahun);

        BigDecimal biayaAdministrasi = service.totalBiayaAdministrasi(tahun);
        BigDecimal biayaOperasional = service.totalBiayaOperasional(tahun);
        BigDecimal biayaPemasaran = service.totalBiayaPemasaran(tahun);
        BigDecimal biayaLain = service.totalBiayaPemeliharaan(tahun);

        BigDecimal totalBiaya = service.totalBiaya(tahun);
        BigDecimal labaBersih = service.labaBersih(tahun);

        lblTotalPenjualan.setText(rp(penjualan));
        lblReturPenjualan.setText(rp(returPenjualan));
        lblTotalPendapatan.setText(rp(totalPendapatan));

        lblTotalHpp.setText(rp(hpp));
        lblReturPembelian.setText(rp(returPembelian));
        lblTotalPembelian.setText(rp(totalPembelian));

        lblLabaRugi.setText(rp(labaKotor));

        lblBiayaAdministrasi.setText(rp(biayaAdministrasi));
        lblBiayaOperasional.setText(rp(biayaOperasional));
        lblBiayaPemasaran.setText(rp(biayaPemasaran));
        lblBiayaLain.setText(rp(biayaLain));

        lblTotalBiayaOperasional.setText(rp(totalBiaya));
        lblLabaBersih.setText(rp(labaBersih));
    }

    private String rp(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(value);
    }

    @FXML
    private void handleExportWord() {

        String tahun = cbTahun.getValue();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Laporan Laba Rugi");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Word Files", "*.docx"));
        fileChooser.setInitialFileName("Laporan_Laba_Rugi_" + tahun + ".docx");

        Stage stage = (Stage) cbTahun.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                new LaporanLabaRugiWordExporter().export(file, tahun, service);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}