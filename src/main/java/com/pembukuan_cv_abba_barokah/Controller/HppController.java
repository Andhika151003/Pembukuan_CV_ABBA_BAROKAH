package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.HargaPokokPenjualan;
import com.pembukuan_cv_abba_barokah.Service.HppService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class HppController {

    @FXML private DatePicker tanggalField;
    @FXML private ComboBox<HargaPokokPenjualan.JenisHpp> cbJenis;
    @FXML private ComboBox<HargaPokokPenjualan.SubJenisHpp> cbSubJenis;
    @FXML private ComboBox<HargaPokokPenjualan.DetailHpp> cbDetail;
    @FXML private ComboBox<Integer> cbIdPenjualan;

    @FXML private TextField namaItemField;
    @FXML private TextField kuantitasField;
    @FXML private TextField hargaSatuanField;
    @FXML private TextField keteranganField;

    @FXML private TableView<HargaPokokPenjualan> tableHpp;
    @FXML private TableColumn<HargaPokokPenjualan, String> colTanggal;
    @FXML private TableColumn<HargaPokokPenjualan, String> colJenis;
    @FXML private TableColumn<HargaPokokPenjualan, String> colItem;
    @FXML private TableColumn<HargaPokokPenjualan, BigDecimal> colTotal;
    @FXML private TableColumn<HargaPokokPenjualan, Integer> colIdPenjualan;

    private final HppService hppService = new HppService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<HargaPokokPenjualan> data = FXCollections.observableArrayList();
    private HargaPokokPenjualan selectedHpp;

    @FXML
    public void initialize() {

        cbJenis.setItems(FXCollections.observableArrayList(HargaPokokPenjualan.JenisHpp.values()));
        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream().map(p -> p.getId()).toList()
        ));

        cbJenis.setOnAction(e -> {
            if (cbJenis.getValue() == HargaPokokPenjualan.JenisHpp.PEMBELIAN_LANGSUNG) {
                cbSubJenis.setItems(FXCollections.observableArrayList(
                        HargaPokokPenjualan.SubJenisHpp.HARGA_PEROLEHAN_BARANG,
                        HargaPokokPenjualan.SubJenisHpp.BIAYA_LAIN
                ));
            } else {
                cbSubJenis.setItems(FXCollections.observableArrayList(
                        HargaPokokPenjualan.SubJenisHpp.HARGA_BARANG,
                        HargaPokokPenjualan.SubJenisHpp.BIAYA_PRODUKSI
                ));
            }
        });

        cbSubJenis.setOnAction(e -> {
            if (cbSubJenis.getValue() == HargaPokokPenjualan.SubJenisHpp.HARGA_BARANG) {
                cbDetail.setItems(FXCollections.observableArrayList(
                        HargaPokokPenjualan.DetailHpp.BAHAN_1,
                        HargaPokokPenjualan.DetailHpp.BAHAN_2,
                        HargaPokokPenjualan.DetailHpp.BAHAN_3
                ));
            } else {
                cbDetail.setItems(FXCollections.observableArrayList(
                        HargaPokokPenjualan.DetailHpp.TRANSPORT,
                        HargaPokokPenjualan.DetailHpp.UPAH,
                        HargaPokokPenjualan.DetailHpp.ONGKOS_POTONG,
                        HargaPokokPenjualan.DetailHpp.ONGKOS_JAHIT
                ));
            }
        });

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggal().toString()));
        colJenis.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getJenis().name()));
        colItem.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getNamaItem()));
        colTotal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getTotalHarga()));
        colIdPenjualan.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getIdPenjualan()));

        tableHpp.setItems(data);
        tableHpp.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedHpp = newSelection;
                populateForm(newSelection);
            }
        });
        loadData();
    }

    private void loadData() {
        data.setAll(hppService.getAll());
    }

    @FXML
    private void handleSimpan() {

        HargaPokokPenjualan hpp = new HargaPokokPenjualan(
                tanggalField.getValue(),
                cbJenis.getValue(),
                cbSubJenis.getValue(),
                cbDetail.getValue(),
                namaItemField.getText(),
                Integer.parseInt(kuantitasField.getText()),
                new BigDecimal(hargaSatuanField.getText()),
                keteranganField.getText(),
                cbIdPenjualan.getValue()
        );

        hppService.simpan(hpp);
        loadData();
    }

    private void populateForm(HargaPokokPenjualan hpp) {
        tanggalField.setValue(hpp.getTanggal());
        cbJenis.setValue(hpp.getJenis());
        cbSubJenis.setValue(hpp.getSubJenis());
        cbDetail.setValue(hpp.getDetail());
        namaItemField.setText(hpp.getNamaItem());
        kuantitasField.setText(String.valueOf(hpp.getKuantitas()));
        hargaSatuanField.setText(hpp.getHargaSatuan().toString());
        keteranganField.setText(hpp.getKeterangan());
        cbIdPenjualan.setValue(hpp.getIdPenjualan());
    }

    @FXML
    private void handleUpdate() {
        if (selectedHpp != null) {
            HargaPokokPenjualan hpp = new HargaPokokPenjualan(
                    selectedHpp.getId(),
                    tanggalField.getValue(),
                    cbJenis.getValue(),
                    cbSubJenis.getValue(),
                    cbDetail.getValue(),
                    namaItemField.getText(),
                    Integer.parseInt(kuantitasField.getText()),
                    new BigDecimal(hargaSatuanField.getText()),
                    new BigDecimal(hargaSatuanField.getText()).multiply(BigDecimal.valueOf(Integer.parseInt(kuantitasField.getText()))),
                    keteranganField.getText(),
                    cbIdPenjualan.getValue()
            );

            hppService.update(hpp);
            loadData();
        }
    }

    @FXML
    private void handleDelete() {
        if (selectedHpp != null) {
            hppService.delete(selectedHpp.getId());
            loadData();
        }
    }
}
