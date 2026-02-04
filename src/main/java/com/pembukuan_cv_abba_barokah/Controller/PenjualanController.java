package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PenjualanController implements Initializable {

    @FXML private TableView<Penjualan> tablePenjualan;
    @FXML private TableColumn<Penjualan, String> colNoFaktur;
    @FXML private TableColumn<Penjualan, String> colCustomer;
    @FXML private TableColumn<Penjualan, BigDecimal> colTotal;
    @FXML private TableColumn<Penjualan, Penjualan.StatusPembayaran> colStatus;

    @FXML private TextField txtNoFaktur;
    @FXML private TextField txtNamaCustomer;
    @FXML private TextField txtAlamatCustomer;
    @FXML private TextField txtTotal;
    @FXML private DatePicker dpTanggal;
    @FXML private ComboBox<Penjualan.MetodePembayaran> cbMetode;
    @FXML private ComboBox<Penjualan.StatusPembayaran> cbStatus;
    @FXML private TextArea txtKeterangan;

    private final PenjualanService service = new PenjualanService();
    private final ObservableList<Penjualan> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbMetode.setItems(FXCollections.observableArrayList(
                Penjualan.MetodePembayaran.values()));
        cbStatus.setItems(FXCollections.observableArrayList(
                Penjualan.StatusPembayaran.values()));

        colNoFaktur.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getNoFaktur()));
        colCustomer.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getNamaCustomer()));
        colTotal.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(
                d.getValue().getTotal()));
        colStatus.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(
                d.getValue().getStatus()));

        tablePenjualan.setItems(data);
        tablePenjualan.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleRowSelect();
            }
        });
        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    @FXML
    private void handleSimpan() {

        Penjualan p = new Penjualan(
                txtNoFaktur.getText(),
                dpTanggal.getValue(),
                txtNamaCustomer.getText(),
                txtAlamatCustomer.getText(),
                new BigDecimal(txtTotal.getText()),
                cbMetode.getValue(),
                cbStatus.getValue(),
                txtKeterangan.getText()
        );

        service.simpan(p);
        loadData();
        clearForm();
    }

    private void handleRowSelect() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtNoFaktur.setText(selected.getNoFaktur());
            dpTanggal.setValue(selected.getTanggal());
            txtNamaCustomer.setText(selected.getNamaCustomer());
            txtAlamatCustomer.setText(selected.getAlamatCustomer());
            txtTotal.setText(selected.getTotal().toString());
            cbMetode.setValue(selected.getMetode());
            cbStatus.setValue(selected.getStatus());
            txtKeterangan.setText(selected.getKeterangan());
        }
    }

    @FXML
    private void handleUpdate() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNoFaktur(txtNoFaktur.getText());
            selected.setTanggal(dpTanggal.getValue());
            selected.setNamaCustomer(txtNamaCustomer.getText());
            selected.setAlamatCustomer(txtAlamatCustomer.getText());
            selected.setTotal(new BigDecimal(txtTotal.getText()));
            selected.setMetode(cbMetode.getValue());
            selected.setStatus(cbStatus.getValue());
            selected.setKeterangan(txtKeterangan.getText());

            service.update(selected);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleDelete() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapus(selected.getId());
            loadData();
            clearForm();
        }
    }

    private void clearForm() {
        txtNoFaktur.clear();
        dpTanggal.setValue(null);
        txtNamaCustomer.clear();
        txtAlamatCustomer.clear();
        txtTotal.clear();
        cbMetode.setValue(null);
        cbStatus.setValue(null);
        txtKeterangan.clear();
        tablePenjualan.getSelectionModel().clearSelection();
    }
}
