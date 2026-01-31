package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PembelianInventarisController {

    // ===================== FORM =====================
    @FXML private TextField txtNoPembelian;
    @FXML private DatePicker dpTanggalPembelian;
    @FXML private ComboBox<PembelianInventaris.JenisInventaris> cbJenisInventaris;
    @FXML private TextField txtNamaBarang;
    @FXML private TextField txtJumlah;
    @FXML private TextField txtSatuan;
    @FXML private TextField txtHargaSatuan;
    @FXML private TextField txtOngkosKirim;
    @FXML private ComboBox<PembelianInventaris.MetodePembayaran> cbMetodePembayaran;
    @FXML private ComboBox<PembelianInventaris.StatusPembelian> cbStatus;
    @FXML private TextField txtKeterangan;

    // ===================== TABLE =====================
    @FXML private TableView<PembelianInventaris> tableInventaris;
    @FXML private TableColumn<PembelianInventaris, Integer> colNo;
    @FXML private TableColumn<PembelianInventaris, LocalDate> colTanggal;
    @FXML private TableColumn<PembelianInventaris, String> colNama;
    @FXML private TableColumn<PembelianInventaris, Integer> colJumlah;
    @FXML private TableColumn<PembelianInventaris, String> colSatuan;
    @FXML private TableColumn<PembelianInventaris, BigDecimal> colTotal;
    @FXML private TableColumn<PembelianInventaris, String> colStatus;

    private final PembelianInventarisService service = new PembelianInventarisService();
    private final ObservableList<PembelianInventaris> data = FXCollections.observableArrayList();

    private PembelianInventaris selected;

    // ===================== INIT =====================
    @FXML
    public void initialize() {
        cbJenisInventaris.setItems(FXCollections.observableArrayList(PembelianInventaris.JenisInventaris.values()));
        cbMetodePembayaran.setItems(FXCollections.observableArrayList(PembelianInventaris.MetodePembayaran.values()));
        cbStatus.setItems(FXCollections.observableArrayList(PembelianInventaris.StatusPembelian.values()));

        colNo.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getNoPembelian()).asObject());
        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggalPembelian()));
        colNama.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNamaBarang()));
        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getJumlah()).asObject());
        colSatuan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getSatuan()));
        colTotal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTotalHarga()));
        colStatus.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus().toString()));

        loadData();

        tableInventaris.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selected = newVal;
                fillForm(newVal);
            }
        });
    }

    // ===================== LOAD =====================
    private void loadData() {
        data.setAll(service.getAll());
        tableInventaris.setItems(data);
    }

    // ===================== FORM =====================
    private void fillForm(PembelianInventaris p) {
        txtNoPembelian.setText(String.valueOf(p.getNoPembelian()));
        dpTanggalPembelian.setValue(p.getTanggalPembelian());
        cbJenisInventaris.setValue(p.getJenisInventaris());
        txtNamaBarang.setText(p.getNamaBarang());
        txtJumlah.setText(String.valueOf(p.getJumlah()));
        txtSatuan.setText(p.getSatuan());
        txtHargaSatuan.setText(p.getHargaSatuan().toString());
        txtOngkosKirim.setText(p.getOngkosKirim().toString());
        cbMetodePembayaran.setValue(p.getMetodePembayaran());
        cbStatus.setValue(p.getStatus());
        txtKeterangan.setText(p.getKeterangan());
    }

    private void clearForm() {
        txtNoPembelian.clear();
        dpTanggalPembelian.setValue(null);
        cbJenisInventaris.setValue(null);
        txtNamaBarang.clear();
        txtJumlah.clear();
        txtSatuan.clear();
        txtHargaSatuan.clear();
        txtOngkosKirim.clear();
        cbMetodePembayaran.setValue(null);
        cbStatus.setValue(null);
        txtKeterangan.clear();
        selected = null;
    }

    // ===================== ACTION =====================
    @FXML
    private void handleSimpan() {
        PembelianInventaris p = new PembelianInventaris(
                Integer.parseInt(txtNoPembelian.getText()),
                dpTanggalPembelian.getValue(),
                cbJenisInventaris.getValue(),
                txtNamaBarang.getText(),
                Integer.parseInt(txtJumlah.getText()),
                txtSatuan.getText(),
                new BigDecimal(txtHargaSatuan.getText()),
                new BigDecimal(txtOngkosKirim.getText()),
                BigDecimal.ZERO,
                cbMetodePembayaran.getValue(),
                cbStatus.getValue(),
                txtKeterangan.getText()
        );

        service.tambahPembelian(p);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        if (selected == null) return;

        PembelianInventaris p = new PembelianInventaris(
                selected.getId(),
                Integer.parseInt(txtNoPembelian.getText()),
                dpTanggalPembelian.getValue(),
                cbJenisInventaris.getValue(),
                txtNamaBarang.getText(),
                Integer.parseInt(txtJumlah.getText()),
                txtSatuan.getText(),
                new BigDecimal(txtHargaSatuan.getText()),
                new BigDecimal(txtOngkosKirim.getText()),
                BigDecimal.ZERO,
                cbMetodePembayaran.getValue(),
                cbStatus.getValue(),
                txtKeterangan.getText()
        );

        service.perbaruiPembelian(p);
        loadData();
        clearForm();
    }

    @FXML
    private void handleHapus() {
        if (selected == null) return;

        service.hapusPembelian(selected.getId());
        loadData();
        clearForm();
    }
}