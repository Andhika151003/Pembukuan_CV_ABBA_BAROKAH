package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.NeracaLabaRugi;
import com.pembukuan_cv_abba_barokah.Service.NeracaLabaRugiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class NeracaLabaRugiController {

    @FXML private TableView<NeracaLabaRugi> tableLabaRugi;
    @FXML private TableColumn<NeracaLabaRugi, Integer> colTahun;
    @FXML private TableColumn<NeracaLabaRugi, BigDecimal> colPendapatan;
    @FXML private TableColumn<NeracaLabaRugi, BigDecimal> colHpp;
    @FXML private TableColumn<NeracaLabaRugi, BigDecimal> colLabaKotor;
    @FXML private TableColumn<NeracaLabaRugi, BigDecimal> colBiaya;
    @FXML private TableColumn<NeracaLabaRugi, BigDecimal> colPajak;
    @FXML private TableColumn<NeracaLabaRugi, BigDecimal> colLabaBersih;

    @FXML private TextField tfTahun;
    @FXML private TextField tfIdAdministrasi;

    @FXML private Label lblPendapatan;
    @FXML private Label lblHpp;
    @FXML private Label lblLabaKotor;
    @FXML private Label lblBiaya;
    @FXML private Label lblPajak;
    @FXML private Label lblLabaBersih;

    private final NeracaLabaRugiService service = new NeracaLabaRugiService();
    private final ObservableList<NeracaLabaRugi> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTahun.setCellValueFactory(d ->
                new javafx.beans.property.SimpleIntegerProperty(
                        d.getValue().getTahun()
                ).asObject()
        );
        colPendapatan.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotal_Pendapatan()
                )
        );
        colHpp.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotal_HPP()
                )
        );
        colLabaKotor.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getLaba_Kotor()
                )
        );
        colBiaya.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotal_Biaya_Operasional()
                )
        );
        colPajak.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getPajak()
                )
        );
        colLabaBersih.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getLaba_Bersih()
                )
        );

        loadData();
    }

    private void loadData() {
        data.setAll(service.getSemuaRiwayat());
        tableLabaRugi.setItems(data);
    }

    @FXML
    private void handleHitung() {
        int tahun = Integer.parseInt(tfTahun.getText());
        int idAdmin = Integer.parseInt(tfIdAdministrasi.getText());

        NeracaLabaRugi laporan = service.hitungLabaRugiTahunan(tahun, idAdmin);

        // tampilkan hasil ke label
        lblPendapatan.setText(laporan.getTotal_Pendapatan().toString());
        lblHpp.setText(laporan.getTotal_HPP().toString());
        lblLabaKotor.setText(laporan.getLaba_Kotor().toString());
        lblBiaya.setText(laporan.getTotal_Biaya_Operasional().toString());
        lblPajak.setText(laporan.getPajak().toString());
        lblLabaBersih.setText(laporan.getLaba_Bersih().toString());

        // simpan ke database
        service.simpanLaporan(laporan);
        loadData();
    }

    @FXML
    private void handleHapus() {
        NeracaLabaRugi selected = tableLabaRugi.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.getSemuaRiwayat().remove(selected);
            loadData();
        }
    }
}