package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PenjualanController {

    private static final int DEFAULT_ID_ADMINISTRASI = 1;

    @FXML
    private TableView<Penjualan> tablePenjualan;
    @FXML
    private TableColumn<Penjualan, String> colNo;
    @FXML
    private TableColumn<Penjualan, String> colCustomer;
    @FXML
    private TableColumn<Penjualan, BigDecimal> colTotal;
    @FXML
    private TableColumn<Penjualan, Penjualan.StatusPembayaran> colStatus;
    @FXML
    private TableColumn<Penjualan, String> colKeterangan;
    @FXML
    private TableColumn<Penjualan, String> colTanggal;
    @FXML
    private TableColumn<Penjualan, String> colAlamat;
    @FXML
    private TableColumn<Penjualan, Penjualan.MetodePembayaran> colMetode;

    @FXML
    private TextField tfNoPenjualan;
    @FXML
    private DatePicker dpTanggal;
    @FXML
    private TextField tfCustomer;
    @FXML
    private TextField tfAlamat;
    @FXML
    private TextField tfTotal;
    @FXML
    private ComboBox<Penjualan.MetodePembayaran> cbMetode;
    @FXML
    private ComboBox<Penjualan.StatusPembayaran> cbStatus;
    @FXML
    private TextField tfKeterangan;

    private final PenjualanService service = new PenjualanService();
    private final ObservableList<Penjualan> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNo.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNo_Penjualan()));

        colCustomer.setCellValueFactory(
                d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNama_Customer()));
        colTotal.setCellValueFactory(
                d -> new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getTotal_Penjualan()));
        colStatus.setCellValueFactory(
                d -> new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getStatus_Pembayaran()));
        colKeterangan.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getKeterangan()));

        colTanggal.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getTanggal_Penjualan().toString()));

        colAlamat.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getAlamat_Customer()));

        colMetode.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(
                d.getValue().getMetode_Pembayaran()));

        cbMetode.setItems(FXCollections.observableArrayList(Penjualan.MetodePembayaran.values()));
        cbStatus.setItems(FXCollections.observableArrayList(Penjualan.StatusPembayaran.values()));

        tablePenjualan.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, selected) -> {
                    if (selected != null) {
                        tfNoPenjualan.setText(selected.getNo_Penjualan());
                        dpTanggal.setValue(selected.getTanggal_Penjualan());
                        tfCustomer.setText(selected.getNama_Customer());
                        tfAlamat.setText(selected.getAlamat_Customer());
                        tfTotal.setText(selected.getTotal_Penjualan().toString());
                        cbMetode.setValue(selected.getMetode_Pembayaran());
                        cbStatus.setValue(selected.getStatus_Pembayaran());
                        tfKeterangan.setText(selected.getKeterangan());
                    }
                });

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        tablePenjualan.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        if (!isValid())
            return;

        Penjualan jual = new Penjualan(
                tfNoPenjualan.getText(),
                dpTanggal.getValue() != null ? dpTanggal.getValue() : LocalDate.now(),
                tfCustomer.getText(),
                tfAlamat.getText(),
                new BigDecimal(tfTotal.getText()),
                cbMetode.getValue(),
                cbStatus.getValue(),
                tfKeterangan.getText());

        service.tambahPenjualan(jual, DEFAULT_ID_ADMINISTRASI);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected == null || !isValid())
            return;

        selected.setNo_Penjualan(tfNoPenjualan.getText());
        selected.setTanggal_Penjualan(dpTanggal.getValue());
        selected.setNama_Customer(tfCustomer.getText());
        selected.setAlamat_Customer(tfAlamat.getText());
        selected.setTotal_Penjualan(new BigDecimal(tfTotal.getText()));
        selected.setMetode_Pembayaran(cbMetode.getValue());
        selected.setStatus_Pembayaran(cbStatus.getValue());
        selected.setKeterangan(tfKeterangan.getText());

        service.perbaruiPenjualan(selected, DEFAULT_ID_ADMINISTRASI);
        loadData();
        clearForm();
    }

    @FXML
    private void handleHapus() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmasi");
        confirm.setHeaderText(null);
        confirm.setContentText("Hapus data penjualan ini?");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            service.hapusPenjualan(selected.getId(), DEFAULT_ID_ADMINISTRASI);
            loadData();
            clearForm();
        }
    }

    private boolean isValid() {
        return !tfNoPenjualan.getText().isBlank()
                && !tfCustomer.getText().isBlank()
                && !tfTotal.getText().isBlank()
                && cbMetode.getValue() != null
                && cbStatus.getValue() != null;
    }

    private void clearForm() {
        tfNoPenjualan.clear();
        tfCustomer.clear();
        tfAlamat.clear();
        tfTotal.clear();
        tfKeterangan.clear();
        dpTanggal.setValue(null);
        cbMetode.setValue(null);
        cbStatus.setValue(null);
        tablePenjualan.getSelectionModel().clearSelection();
    }
}