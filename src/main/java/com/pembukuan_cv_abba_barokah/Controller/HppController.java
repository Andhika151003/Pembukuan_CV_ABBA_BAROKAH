package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;
import com.pembukuan_cv_abba_barokah.Service.HppService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class HppController {

    @FXML private DatePicker tanggalField;
    @FXML private TextField jenisProdukField;
    @FXML private TextField kategoriField;
    @FXML private TextField namaItemField;
    @FXML private TextField kuantitasField;
    @FXML private TextField hargaSatuanField;
    @FXML private TextField keteranganField;
    @FXML private TextField idTransaksiField;

    @FXML private TableView<HargaPokokPenjualan> tableHpp;
    @FXML private TableColumn<HargaPokokPenjualan, String> colTanggal;
    @FXML private TableColumn<HargaPokokPenjualan, String> colJenisProduk;
    @FXML private TableColumn<HargaPokokPenjualan, String> colKategori;
    @FXML private TableColumn<HargaPokokPenjualan, String> colNamaItem;
    @FXML private TableColumn<HargaPokokPenjualan, Integer> colKuantitas;
    @FXML private TableColumn<HargaPokokPenjualan, BigDecimal> colHargaSatuan;
    @FXML private TableColumn<HargaPokokPenjualan, BigDecimal> colTotalHarga;
    @FXML private TableColumn<HargaPokokPenjualan, String> colKeterangan;

    private final HppService hppService = new HppService();
    private final ObservableList<HargaPokokPenjualan> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTanggal.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getTanggal().toString()));
        colJenisProduk.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getJenisProduk()));
        colKategori.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getKategori()));
        colNamaItem.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getNamaItem()));
        colKuantitas.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getKuantitas()));
        colHargaSatuan.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getHargaSatuan()));
        colTotalHarga.setCellValueFactory(c ->
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTotalHarga()));
        colKeterangan.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getKeterangan()));

        loadData();
    }

    private void loadData() {
        data.setAll(hppService.getAllHpp());
        tableHpp.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        hppService.simpanHpp(new HargaPokokPenjualan(
                tanggalField.getValue(),
                jenisProdukField.getText(),
                kategoriField.getText(),
                namaItemField.getText(),
                Integer.parseInt(kuantitasField.getText()),
                new BigDecimal(hargaSatuanField.getText()),
                BigDecimal.ZERO,
                keteranganField.getText(),
                Integer.parseInt(idTransaksiField.getText())
        ));
        loadData();
    }

    @FXML
    private void handleHapus() {
        HargaPokokPenjualan selected = tableHpp.getSelectionModel().getSelectedItem();
        if (selected != null) {
            hppService.hapusHpp(selected.getId());
            loadData();
        }
    }
}