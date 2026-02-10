package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Penjualan;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PenjualanController {

    @FXML
    private TableView<Penjualan> tablePenjualan;
    @FXML
    private TableColumn<Penjualan, String> colNoFaktur;
    @FXML
    private TableColumn<Penjualan, String> colCustomer;
    @FXML
    private TableColumn<Penjualan, String> colTanggal;
    @FXML
    private TableColumn<Penjualan, BigDecimal> colTotal;
    @FXML
    private TextField txtNoFaktur;
    @FXML
    private TextField txtNamaCustomer;
    @FXML
    private TextField txtAlamatCustomer;
    @FXML
    private TextField txtTotal;
    @FXML
    private DatePicker dpTanggal;
    @FXML
    private TextArea txtKeterangan;
    @FXML
    private TableColumn<Penjualan, Integer> colId;
    @FXML
    private TableColumn<Penjualan, String> colAlamat;
    @FXML
    private TableColumn<Penjualan, String> colKeterangan;

    private final PenjualanService service = new PenjualanService();
    private final ObservableList<Penjualan> data = FXCollections.observableArrayList();

    private Penjualan selected;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getId()));

        colNoFaktur.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNoFaktur()));

        colCustomer.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNamaCustomer()));

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));

        colAlamat.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getAlamatCustomer()));

        colTotal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getTotal()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeterangan()));

        tablePenjualan.setItems(data);
        loadData();

        tablePenjualan.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, n) -> isiForm(n));
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
                txtKeterangan.getText());

        service.simpan(p);
        loadData();
        clearForm();
    }

    @FXML
    private void handleUpdate() {

        if (selected == null)
            return;

        selected.setNoFaktur(txtNoFaktur.getText());
        selected.setTanggal(dpTanggal.getValue());
        selected.setNamaCustomer(txtNamaCustomer.getText());
        selected.setAlamatCustomer(txtAlamatCustomer.getText());
        selected.setTotal(new BigDecimal(txtTotal.getText()));
        selected.setKeterangan(txtKeterangan.getText());

        service.update(selected);
        loadData();
        clearForm();
    }

    @FXML
    private void handleDelete() {

        if (selected == null)
            return;

        service.hapus(selected.getId());
        loadData();
        clearForm();
    }

    private void isiForm(Penjualan p) {
        if (p == null)
            return;

        selected = p;

        txtNoFaktur.setText(p.getNoFaktur());
        dpTanggal.setValue(p.getTanggal());
        txtNamaCustomer.setText(p.getNamaCustomer());
        txtAlamatCustomer.setText(p.getAlamatCustomer());
        txtTotal.setText(p.getTotal().toString());
        txtKeterangan.setText(p.getKeterangan());
    }

    private void clearForm() {

        selected = null;

        txtNoFaktur.clear();
        dpTanggal.setValue(LocalDate.now());
        txtNamaCustomer.clear();
        txtAlamatCustomer.clear();
        txtTotal.clear();
        txtKeterangan.clear();

        tablePenjualan.getSelectionModel().clearSelection();
    }
}