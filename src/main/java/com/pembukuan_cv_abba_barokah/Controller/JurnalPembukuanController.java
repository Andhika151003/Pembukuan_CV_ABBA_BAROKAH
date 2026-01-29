package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.JurnalPembukuan;
import com.pembukuan_cv_abba_barokah.Service.JurnalPembukuanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class JurnalPembukuanController {

    @FXML private TableView<JurnalPembukuan> tableJurnal;
    @FXML private TableColumn<JurnalPembukuan, String> colTanggal;
    @FXML private TableColumn<JurnalPembukuan, String> colNomor;
    @FXML private TableColumn<JurnalPembukuan, JurnalPembukuan.JenisTransaksi> colJenis;
    @FXML private TableColumn<JurnalPembukuan, JurnalPembukuan.Kategori> colKategori;
    @FXML private TableColumn<JurnalPembukuan, BigDecimal> colDebit;
    @FXML private TableColumn<JurnalPembukuan, BigDecimal> colKredit;

    @FXML private ComboBox<JurnalPembukuan.Kategori> cbKategori;
    @FXML private ComboBox<Month> cbBulan;
    @FXML private TextField tfTahun;

    @FXML private Label lblTotalDebit;
    @FXML private Label lblTotalKredit;

    private final JurnalPembukuanService service = new JurnalPembukuanService();
    private final ObservableList<JurnalPembukuan> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTanggal.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getTanggal().toString()
        ));
        colNomor.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getNomorJurnal()
        ));
        colJenis.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(
                d.getValue().getJenisTransaksi()
        ));
        colKategori.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(
                d.getValue().getKategori()
        ));
        colDebit.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(
                d.getValue().getDebit()
        ));
        colKredit.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(
                d.getValue().getKredit()
        ));

        cbKategori.setItems(FXCollections.observableArrayList(JurnalPembukuan.Kategori.values()));
        cbBulan.setItems(FXCollections.observableArrayList(Month.values()));

        loadAll();
    }

    private void loadAll() {
        data.setAll(service.getAllJurnal());
        tableJurnal.setItems(data);
        hitungTotal(data);
    }

    @FXML
    private void handleFilter() {
        List<JurnalPembukuan> filtered = service.getAllJurnal();

        if (cbKategori.getValue() != null) {
            filtered = service.getJurnalByKategori(cbKategori.getValue());
        }

        if (cbBulan.getValue() != null && !tfTahun.getText().isEmpty()) {
            int bulan = cbBulan.getValue().getValue();
            int tahun = Integer.parseInt(tfTahun.getText());
            filtered = service.getJurnalByPeriode(bulan, tahun);
        }

        data.setAll(filtered);
        hitungTotal(filtered);
    }

    @FXML
    private void handleHapus() {
        JurnalPembukuan selected = tableJurnal.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusJurnal(selected.getId());
            loadAll();
        }
    }

    private void hitungTotal(List<JurnalPembukuan> list) {
        lblTotalDebit.setText(
                service.hitungTotalDebit(list).toString()
        );
        lblTotalKredit.setText(
                service.hitungTotalKredit(list).toString()
        );
    }
}