package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.ReturPembelian;
import com.pembukuan_cv_abba_barokah.Service.ReturPembelianService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class ReturPembelianController {

    @FXML private TextField txtNoRetur;
    @FXML private DatePicker dpTanggalRetur;
    @FXML private TextField txtIdPembelian;
    @FXML private TextField txtJumlahRetur;
    @FXML private TextField txtNilaiRetur;
    @FXML private ComboBox<ReturPembelian.AlasanRetur> cbAlasanRetur;
    @FXML private ComboBox<ReturPembelian.StatusRetur> cbStatusRetur;
    @FXML private TextField txtKeterangan;

    @FXML private TableView<ReturPembelian> tableRetur;
    @FXML private TableColumn<ReturPembelian, Integer> colNoRetur;
    @FXML private TableColumn<ReturPembelian, String> colTanggal;
    @FXML private TableColumn<ReturPembelian, Integer> colJumlah;
    @FXML private TableColumn<ReturPembelian, BigDecimal> colNilai;
    @FXML private TableColumn<ReturPembelian, ReturPembelian.StatusRetur> colStatus;

    private final ReturPembelianService service = new ReturPembelianService();
    private final ObservableList<ReturPembelian> data = FXCollections.observableArrayList();

    // sementara hardcode, nanti bisa diganti dropdown kas/bank
    private final int ID_ADMINISTRASI_DEFAULT = 1;

    @FXML
    public void initialize() {
        cbAlasanRetur.setItems(FXCollections.observableArrayList(ReturPembelian.AlasanRetur.values()));
        cbStatusRetur.setItems(FXCollections.observableArrayList(ReturPembelian.StatusRetur.values()));

        colNoRetur.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(
                c.getValue().getNo_Retur_Pembelian()).asObject());
        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal_Retur().toString()));
        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(
                c.getValue().getJumlah_Retur()).asObject());
        colNilai.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getNilai_Retur()));
        colStatus.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getStatus_Retur()));

        tableRetur.setItems(data);
        loadData();

        tableRetur.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> fillForm(newVal)
        );
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    private void fillForm(ReturPembelian r) {
        if (r == null) return;

        txtNoRetur.setText(String.valueOf(r.getNo_Retur_Pembelian()));
        dpTanggalRetur.setValue(r.getTanggal_Retur());
        txtIdPembelian.setText(String.valueOf(r.getId_Pembelian()));
        txtJumlahRetur.setText(String.valueOf(r.getJumlah_Retur()));
        txtNilaiRetur.setText(r.getNilai_Retur().toString());
        cbAlasanRetur.setValue(r.getAlasan_Retur());
        cbStatusRetur.setValue(r.getStatus_Retur());
        txtKeterangan.setText(r.getKeterangan_Retur());
    }

    @FXML
    private void handleSimpan() {
        ReturPembelian retur = new ReturPembelian(
                Integer.parseInt(txtNoRetur.getText()),
                dpTanggalRetur.getValue(),
                Integer.parseInt(txtIdPembelian.getText()),
                Integer.parseInt(txtJumlahRetur.getText()),
                new BigDecimal(txtNilaiRetur.getText()),
                cbAlasanRetur.getValue(),
                txtKeterangan.getText(),
                cbStatusRetur.getValue()
        );

        if (service.tambahRetur(retur, ID_ADMINISTRASI_DEFAULT)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        ReturPembelian selected = tableRetur.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        ReturPembelian updated = new ReturPembelian(
                selected.getId(),
                Integer.parseInt(txtNoRetur.getText()),
                dpTanggalRetur.getValue(),
                Integer.parseInt(txtIdPembelian.getText()),
                Integer.parseInt(txtJumlahRetur.getText()),
                new BigDecimal(txtNilaiRetur.getText()),
                cbAlasanRetur.getValue(),
                txtKeterangan.getText(),
                cbStatusRetur.getValue()
        );

        if (service.perbaruiRetur(updated, ID_ADMINISTRASI_DEFAULT)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        ReturPembelian selected = tableRetur.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (service.hapusRetur(selected.getId(), ID_ADMINISTRASI_DEFAULT)) {
            loadData();
            clearForm();
        }
    }

    private void clearForm() {
        txtNoRetur.clear();
        dpTanggalRetur.setValue(null);
        txtIdPembelian.clear();
        txtJumlahRetur.clear();
        txtNilaiRetur.clear();
        cbAlasanRetur.setValue(null);
        cbStatusRetur.setValue(null);
        txtKeterangan.clear();
        tableRetur.getSelectionModel().clearSelection();
    }
}