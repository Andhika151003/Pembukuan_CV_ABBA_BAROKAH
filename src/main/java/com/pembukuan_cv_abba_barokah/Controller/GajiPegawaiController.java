package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.GajiPegawai;
import com.pembukuan_cv_abba_barokah.Service.GajiPegawaiService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GajiPegawaiController implements Initializable {

    // ===================== TABLE =====================
    @FXML private TableView<GajiPegawai> tableGaji;
    @FXML private TableColumn<GajiPegawai, String> colPeriode;
    @FXML private TableColumn<GajiPegawai, BigDecimal> colGajiPokok;
    @FXML private TableColumn<GajiPegawai, BigDecimal> colTunjangan;
    @FXML private TableColumn<GajiPegawai, BigDecimal> colPotongan;
    @FXML private TableColumn<GajiPegawai, BigDecimal> colTotalGaji;
    @FXML private TableColumn<GajiPegawai, LocalDate> colTanggal;
    @FXML private TableColumn<GajiPegawai, GajiPegawai.Status> colStatus;

    // ===================== FORM =====================
    @FXML private TextField txtIdPegawai;
    @FXML private TextField txtPeriode;
    @FXML private TextField txtGajiPokok;
    @FXML private TextField txtTunjangan;
    @FXML private TextField txtPotongan;
    @FXML private DatePicker dpTanggal;
    @FXML private ComboBox<GajiPegawai.Status> cbStatus;

    // ===================== SERVICE =====================
    private final GajiPegawaiService gajiService = new GajiPegawaiService();
    private final ObservableList<GajiPegawai> dataGaji =
            FXCollections.observableArrayList();

    // ===================== INITIALIZE =====================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        initCombo();
        loadData();

        tableGaji.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> isiForm(newVal));

        dpTanggal.setValue(LocalDate.now());
    }

    // ===================== INIT =====================
    private void initCombo() {
        cbStatus.setItems(FXCollections.observableArrayList(
                GajiPegawai.Status.values()
        ));
        cbStatus.setValue(GajiPegawai.Status.BELUM_LUNAS);
    }

    private void initTable() {

        colPeriode.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getPeriode())
        );

        colGajiPokok.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getGaji_pokok())
        );

        colTunjangan.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getTunjangan())
        );

        colPotongan.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getPotongan())
        );

        colTotalGaji.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getTotal_gaji())
        );

        colTanggal.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getTanggal_pembayaran())
        );

        colStatus.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getStatus_pembayaran())
        );

        tableGaji.setItems(dataGaji);
    }

    private void loadData() {
        dataGaji.clear();
        dataGaji.addAll(gajiService.getAll());
    }

    // ===================== ACTION =====================

    @FXML
    private void handleSimpan() {
        GajiPegawai gaji = ambilDataForm(false);
        if (gaji == null) return;

        if (gajiService.bayarGaji(gaji)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        GajiPegawai selected = tableGaji.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        GajiPegawai gaji = ambilDataForm(true);
        gaji.setId(selected.getId());

        if (gajiService.updateStatusGaji(gaji)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        GajiPegawai selected = tableGaji.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (gajiService.hapusRiwayatGaji(selected.getId())) {
            loadData();
            clearForm();
        }
    }

    // ===================== UTIL =====================
    private GajiPegawai ambilDataForm(boolean isUpdate) {
        try {
            return new GajiPegawai(
                    Integer.parseInt(txtIdPegawai.getText()),
                    txtPeriode.getText(),
                    parseBigDecimal(txtGajiPokok.getText()),
                    parseBigDecimal(txtTunjangan.getText()),
                    parseBigDecimal(txtPotongan.getText()),
                    BigDecimal.ZERO, // total dihitung di service
                    dpTanggal.getValue(),
                    cbStatus.getValue()
            );
        } catch (Exception e) {
            showAlert("Input tidak valid. Periksa kembali data.");
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String val) {
        if (val == null || val.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(val.trim());
    }

    private void isiForm(GajiPegawai gaji) {
        if (gaji == null) return;

        txtIdPegawai.setText(String.valueOf(gaji.getId_pegawai()));
        txtPeriode.setText(gaji.getPeriode());
        txtGajiPokok.setText(gaji.getGaji_pokok().toString());
        txtTunjangan.setText(gaji.getTunjangan().toString());
        txtPotongan.setText(gaji.getPotongan().toString());
        dpTanggal.setValue(gaji.getTanggal_pembayaran());
        cbStatus.setValue(gaji.getStatus_pembayaran());
    }

    private void clearForm() {
        txtIdPegawai.clear();
        txtPeriode.clear();
        txtGajiPokok.clear();
        txtTunjangan.clear();
        txtPotongan.clear();
        dpTanggal.setValue(LocalDate.now());
        cbStatus.setValue(GajiPegawai.Status.BELUM_LUNAS);
        tableGaji.getSelectionModel().clearSelection();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}