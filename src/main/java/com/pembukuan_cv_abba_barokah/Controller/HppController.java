package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;
import com.pembukuan_cv_abba_barokah.Service.HppService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HppController {

    @FXML private DatePicker dpTanggal;
    @FXML private TextField txtJenisProduk;
    @FXML private TextField txtKategori;
    @FXML private TextField txtNamaItem;
    @FXML private TextField txtKuantitas;
    @FXML private TextField txtHargaSatuan;
    @FXML private TextField txtKeterangan;
    @FXML private TextField txtIdAdministrasi;

    @FXML private TableView<HargaPokokPenjualan> tableHpp;
    @FXML private TableColumn<HargaPokokPenjualan, Integer> colId;
    @FXML private TableColumn<HargaPokokPenjualan, LocalDate> colTanggal;
    @FXML private TableColumn<HargaPokokPenjualan, String> colJenisProduk;
    @FXML private TableColumn<HargaPokokPenjualan, String> colKategori;
    @FXML private TableColumn<HargaPokokPenjualan, String> colNamaItem;
    @FXML private TableColumn<HargaPokokPenjualan, Integer> colKuantitas;
    @FXML private TableColumn<HargaPokokPenjualan, BigDecimal> colHargaSatuan;
    @FXML private TableColumn<HargaPokokPenjualan, BigDecimal> colTotalHarga;

    private final HppService hppService = new HppService();
    private final ObservableList<HargaPokokPenjualan> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getId()).asObject());
        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggal()));
        colJenisProduk.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getJenisProduk()));
        colKategori.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getKategori()));
        colNamaItem.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNamaItem()));
        colKuantitas.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getKuantitas()).asObject());
        colHargaSatuan.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getHargaSatuan()));
        colTotalHarga.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTotalHarga()));

        loadData();

        tableHpp.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) fillForm(newVal);
                }
        );
    }

    private void loadData() {
        data.setAll(hppService.getAllHpp());
        tableHpp.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        HargaPokokPenjualan hpp = new HargaPokokPenjualan(
                dpTanggal.getValue(),
                txtJenisProduk.getText(),
                txtKategori.getText(),
                txtNamaItem.getText(),
                Integer.parseInt(txtKuantitas.getText()),
                new BigDecimal(txtHargaSatuan.getText()),
                BigDecimal.ZERO,
                txtKeterangan.getText(),
                Integer.parseInt(txtIdAdministrasi.getText())
        );

        if (hppService.simpanHpp(hpp)) {
            loadData();
            handleReset();
        }
    }

    @FXML
    private void handleUpdate() {
        HargaPokokPenjualan selected = tableHpp.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setTanggal(dpTanggal.getValue());
        selected.setJenisProduk(txtJenisProduk.getText());
        selected.setKategori(txtKategori.getText());
        selected.setNamaItem(txtNamaItem.getText());
        selected.setKuantitas(Integer.parseInt(txtKuantitas.getText()));
        selected.setHargaSatuan(new BigDecimal(txtHargaSatuan.getText()));
        selected.setKeterangan(txtKeterangan.getText());
        selected.setIdAdministrasi(Integer.parseInt(txtIdAdministrasi.getText()));

        hppService.updateHpp(selected);
        loadData();
    }

    @FXML
    private void handleHapus() {
        HargaPokokPenjualan selected = tableHpp.getSelectionModel().getSelectedItem();
        if (selected != null) {
            hppService.hapusHpp(selected.getId());
            loadData();
            handleReset();
        }
    }

    @FXML
    private void handleReset() {
        dpTanggal.setValue(null);
        txtJenisProduk.clear();
        txtKategori.clear();
        txtNamaItem.clear();
        txtKuantitas.clear();
        txtHargaSatuan.clear();
        txtKeterangan.clear();
        txtIdAdministrasi.clear();
        tableHpp.getSelectionModel().clearSelection();
    }

    private void fillForm(HargaPokokPenjualan hpp) {
        dpTanggal.setValue(hpp.getTanggal());
        txtJenisProduk.setText(hpp.getJenisProduk());
        txtKategori.setText(hpp.getKategori());
        txtNamaItem.setText(hpp.getNamaItem());
        txtKuantitas.setText(String.valueOf(hpp.getKuantitas()));
        txtHargaSatuan.setText(hpp.getHargaSatuan().toString());
        txtKeterangan.setText(hpp.getKeterangan());
        txtIdAdministrasi.setText(String.valueOf(hpp.getIdAdministrasi()));
    }
}