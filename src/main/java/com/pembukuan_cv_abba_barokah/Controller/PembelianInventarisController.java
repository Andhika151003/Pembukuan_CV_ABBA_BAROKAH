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

    @FXML private TableView<PembelianInventaris> tableInventaris;
    @FXML private TableColumn<PembelianInventaris, Integer> colNoPembelian;
    @FXML private TableColumn<PembelianInventaris, String> colNamaBarang;
    @FXML private TableColumn<PembelianInventaris, Integer> colJumlah;
    @FXML private TableColumn<PembelianInventaris, BigDecimal> colTotal;
    @FXML private TableColumn<PembelianInventaris, PembelianInventaris.MetodePembayaran> colMetode;

    @FXML private TextField tfNoPembelian;
    @FXML private DatePicker dpTanggal;
    @FXML private ComboBox<PembelianInventaris.JenisInventaris> cbJenis;
    @FXML private TextField tfNamaBarang;
    @FXML private TextField tfJumlah;
    @FXML private TextField tfSatuan;
    @FXML private TextField tfHargaSatuan;
    @FXML private TextField tfOngkosKirim;
    @FXML private ComboBox<PembelianInventaris.MetodePembayaran> cbMetode;
    @FXML private ComboBox<PembelianInventaris.StatusPembelian> cbStatus;
    @FXML private TextField tfKeterangan;
    @FXML private TextField tfIdAdministrasi;

    private final PembelianInventarisService service = new PembelianInventarisService();
    private final ObservableList<PembelianInventaris> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNoPembelian.setCellValueFactory(d ->
                new javafx.beans.property.SimpleIntegerProperty(
                        d.getValue().getNoPembelian()
                ).asObject()
        );
        colNamaBarang.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getNamaBarang()
                )
        );
        colJumlah.setCellValueFactory(d ->
                new javafx.beans.property.SimpleIntegerProperty(
                        d.getValue().getJumlah()
                ).asObject()
        );
        colTotal.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotalHarga()
                )
        );
        colMetode.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getMetodePembayaran()
                )
        );

        cbJenis.setItems(FXCollections.observableArrayList(PembelianInventaris.JenisInventaris.values()));
        cbMetode.setItems(FXCollections.observableArrayList(PembelianInventaris.MetodePembayaran.values()));
        cbStatus.setItems(FXCollections.observableArrayList(PembelianInventaris.StatusPembelian.values()));

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        tableInventaris.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        PembelianInventaris beli = new PembelianInventaris(
                Integer.parseInt(tfNoPembelian.getText()),
                dpTanggal.getValue() != null ? dpTanggal.getValue() : LocalDate.now(),
                cbJenis.getValue(),
                tfNamaBarang.getText(),
                Integer.parseInt(tfJumlah.getText()),
                tfSatuan.getText(),
                new BigDecimal(tfHargaSatuan.getText()),
                new BigDecimal(tfOngkosKirim.getText()),
                BigDecimal.ZERO, // dihitung di Service
                cbMetode.getValue(),
                cbStatus.getValue(),
                tfKeterangan.getText(),
                Integer.parseInt(tfIdAdministrasi.getText())
        );

        if (service.tambahPembelian(beli)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        PembelianInventaris selected = tableInventaris.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNoPembelian(Integer.parseInt(tfNoPembelian.getText()));
            selected.setTanggalPembelian(dpTanggal.getValue());
            selected.setJenisInventaris(cbJenis.getValue());
            selected.setNamaBarang(tfNamaBarang.getText());
            selected.setJumlah(Integer.parseInt(tfJumlah.getText()));
            selected.setSatuan(tfSatuan.getText());
            selected.setHargaSatuan(new BigDecimal(tfHargaSatuan.getText()));
            selected.setOngkosKirim(new BigDecimal(tfOngkosKirim.getText()));
            selected.setMetodePembayaran(cbMetode.getValue());
            selected.setStatus(cbStatus.getValue());
            selected.setKeterangan(tfKeterangan.getText());
            selected.setIdAdministrasi(Integer.parseInt(tfIdAdministrasi.getText()));

            service.perbaruiPembelian(selected);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        PembelianInventaris selected = tableInventaris.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusPembelian(selected.getId());
            loadData();
        }
    }

    private void clearForm() {
        tfNoPembelian.clear();
        tfNamaBarang.clear();
        tfJumlah.clear();
        tfSatuan.clear();
        tfHargaSatuan.clear();
        tfOngkosKirim.clear();
        tfKeterangan.clear();
        tfIdAdministrasi.clear();
        dpTanggal.setValue(null);
        cbJenis.setValue(null);
        cbMetode.setValue(null);
        cbStatus.setValue(null);
    }
}