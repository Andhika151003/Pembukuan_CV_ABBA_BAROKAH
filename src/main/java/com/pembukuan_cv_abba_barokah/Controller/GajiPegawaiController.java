package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.GajiPegawai;
import com.pembukuan_cv_abba_barokah.Service.GajiPegawaiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GajiPegawaiController {

    @FXML private TableView<GajiPegawai> tableGaji;
    @FXML private TableColumn<GajiPegawai, Integer> colIdPegawai;
    @FXML private TableColumn<GajiPegawai, String> colPeriode;
    @FXML private TableColumn<GajiPegawai, BigDecimal> colTotal;
    @FXML private TableColumn<GajiPegawai, GajiPegawai.Status> colStatus;

    @FXML private TextField tfIdPegawai;
    @FXML private TextField tfPeriode;
    @FXML private TextField tfGajiPokok;
    @FXML private TextField tfTunjangan;
    @FXML private TextField tfPotongan;
    @FXML private TextField tfIdAdministrasi;
    @FXML private DatePicker dpTanggal;
    @FXML private ComboBox<GajiPegawai.Status> cbStatus;

    private final GajiPegawaiService service = new GajiPegawaiService();
    private final ObservableList<GajiPegawai> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colIdPegawai.setCellValueFactory(d -> new javafx.beans.property.SimpleIntegerProperty(d.getValue().getId_pegawai()).asObject());
        colPeriode.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getPeriode()));
        colTotal.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getTotal_gaji()));
        colStatus.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getStatus_pembayaran()));

        cbStatus.setItems(FXCollections.observableArrayList(GajiPegawai.Status.values()));

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        tableGaji.setItems(data);
    }

    @FXML
    private void handleBayarGaji() {
        GajiPegawai gaji = new GajiPegawai(
                Integer.parseInt(tfIdPegawai.getText()),
                tfPeriode.getText(),
                new BigDecimal(tfGajiPokok.getText()),
                new BigDecimal(tfTunjangan.getText()),
                new BigDecimal(tfPotongan.getText()),
                BigDecimal.ZERO,
                dpTanggal.getValue() != null ? dpTanggal.getValue() : LocalDate.now(),
                cbStatus.getValue(),
                Integer.parseInt(tfIdAdministrasi.getText())
        );

        if (service.bayarGaji(gaji)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        GajiPegawai selected = tableGaji.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusRiwayatGaji(selected.getId());
            loadData();
        }
    }

    private void clearForm() {
        tfIdPegawai.clear();
        tfPeriode.clear();
        tfGajiPokok.clear();
        tfTunjangan.clear();
        tfPotongan.clear();
        tfIdAdministrasi.clear();
        dpTanggal.setValue(null);
        cbStatus.setValue(null);
    }
}
