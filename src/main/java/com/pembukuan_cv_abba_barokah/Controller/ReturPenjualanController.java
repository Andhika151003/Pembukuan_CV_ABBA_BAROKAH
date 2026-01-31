package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPenjualan;
import com.pembukuan_cv_abba_barokah.Service.ReturPenjualanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReturPenjualanController {

    private final ReturPenjualanService returService = new ReturPenjualanService();
    private final ObservableList<ReturPenjualan> dataRetur = FXCollections.observableArrayList();

    // ===================== FORM =====================
    @FXML private TextField txtNoRetur;
    @FXML private DatePicker dpTanggalRetur;
    @FXML private TextField txtIdPenjualan;
    @FXML private TextField txtIdTransaksi;
    @FXML private TextField txtJumlahRetur;
    @FXML private TextField txtNilaiRetur;
    @FXML private ComboBox<ReturPenjualan.AlasanRetur> cbAlasanRetur;
    @FXML private ComboBox<ReturPenjualan.StatusRetur> cbStatusRetur;
    @FXML private ComboBox<ReturPenjualan.JenisPengembalian> cbJenisPengembalian;
    @FXML private DatePicker dpTanggalPengembalian;
    @FXML private TextField txtKeterangan;

    // ===================== TABLE =====================
    @FXML private TableView<ReturPenjualan> tableRetur;
    @FXML private TableColumn<ReturPenjualan, Integer> colNoRetur;
    @FXML private TableColumn<ReturPenjualan, LocalDate> colTanggal;
    @FXML private TableColumn<ReturPenjualan, Integer> colJumlah;
    @FXML private TableColumn<ReturPenjualan, BigDecimal> colNilai;
    @FXML private TableColumn<ReturPenjualan, ReturPenjualan.StatusRetur> colStatus;

    private ReturPenjualan selectedRetur;

    // ===================== INIT =====================
    @FXML
    public void initialize() {
        cbAlasanRetur.setItems(FXCollections.observableArrayList(ReturPenjualan.AlasanRetur.values()));
        cbStatusRetur.setItems(FXCollections.observableArrayList(ReturPenjualan.StatusRetur.values()));
        cbJenisPengembalian.setItems(FXCollections.observableArrayList(ReturPenjualan.JenisPengembalian.values()));

        colNoRetur.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNo_Retur()).asObject());
        colTanggal.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTanggal_Retur()));
        colJumlah.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getJumlah_Retur()).asObject());
        colNilai.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getNilai_Retur()));
        colStatus.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus_Retur()));

        tableRetur.setItems(dataRetur);
        loadData();

        tableRetur.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> pilihData(newVal)
        );
    }

    private void loadData() {
        dataRetur.setAll(returService.getAll());
    }

    private void pilihData(ReturPenjualan retur) {
        if (retur == null) return;
        selectedRetur = retur;

        txtNoRetur.setText(String.valueOf(retur.getNo_Retur()));
        dpTanggalRetur.setValue(retur.getTanggal_Retur());
        txtIdPenjualan.setText(String.valueOf(retur.getId_Penjualan()));
        txtIdTransaksi.setText(String.valueOf(retur.getid_Transaksi()));
        txtJumlahRetur.setText(String.valueOf(retur.getJumlah_Retur()));
        txtNilaiRetur.setText(retur.getNilai_Retur().toString());
        cbAlasanRetur.setValue(retur.getAlasan_Retur());
        cbStatusRetur.setValue(retur.getStatus_Retur());
        cbJenisPengembalian.setValue(retur.getJenis_Pengembalian());
        dpTanggalPengembalian.setValue(retur.getTanggal_Pengembalian());
        txtKeterangan.setText(retur.getKeterangan_Retur());
    }

    // ===================== ACTION =====================
    @FXML
    private void handleSimpan() {
        ReturPenjualan retur = new ReturPenjualan(
            Integer.parseInt(txtNoRetur.getText()),
            dpTanggalRetur.getValue(),
            Integer.parseInt(txtIdPenjualan.getText()),
            Integer.parseInt(txtIdTransaksi.getText()),
            Integer.parseInt(txtJumlahRetur.getText()),
            new BigDecimal(txtNilaiRetur.getText()),
            cbAlasanRetur.getValue(),
            txtKeterangan.getText(),
            cbStatusRetur.getValue(),
            cbJenisPengembalian.getValue(),
            dpTanggalPengembalian.getValue()
        );

        // sementara pakai idAdministrasi = 1 (kas utama)
        returService.tambahRetur(retur, 1);
        clearForm();
        loadData();
    }

    @FXML
    private void handleUpdate() {
        if (selectedRetur == null) return;

        ReturPenjualan retur = new ReturPenjualan(
            selectedRetur.getId(),
            Integer.parseInt(txtNoRetur.getText()),
            dpTanggalRetur.getValue(),
            Integer.parseInt(txtIdPenjualan.getText()),
            Integer.parseInt(txtIdTransaksi.getText()),
            Integer.parseInt(txtJumlahRetur.getText()),
            new BigDecimal(txtNilaiRetur.getText()),
            cbAlasanRetur.getValue(),
            txtKeterangan.getText(),
            cbStatusRetur.getValue(),
            cbJenisPengembalian.getValue(),
            dpTanggalPengembalian.getValue()
        );

        returService.perbaruiRetur(retur, 1);
        clearForm();
        loadData();
    }

    @FXML
    private void handleHapus() {
        if (selectedRetur == null) return;
        returService.hapusRetur(selectedRetur.getId(), 1);
        clearForm();
        loadData();
    }

    private void clearForm() {
        txtNoRetur.clear();
        txtIdPenjualan.clear();
        txtIdTransaksi.clear();
        txtJumlahRetur.clear();
        txtNilaiRetur.clear();
        txtKeterangan.clear();
        dpTanggalRetur.setValue(null);
        dpTanggalPengembalian.setValue(null);
        cbAlasanRetur.setValue(null);
        cbStatusRetur.setValue(null);
        cbJenisPengembalian.setValue(null);
        selectedRetur = null;
        tableRetur.getSelectionModel().clearSelection();
    }
}