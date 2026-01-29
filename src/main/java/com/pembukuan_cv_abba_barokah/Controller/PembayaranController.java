package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Pembayaran;
import com.pembukuan_cv_abba_barokah.Service.PembayaranService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PembayaranController {

    @FXML private TableView<Pembayaran> tablePembayaran;
    @FXML private TableColumn<Pembayaran, Integer> colIdTransaksi;
    @FXML private TableColumn<Pembayaran, String> colTanggal;
    @FXML private TableColumn<Pembayaran, BigDecimal> colJumlah;
    @FXML private TableColumn<Pembayaran, String> colMetode;

    @FXML private TextField tfIdTransaksi;
    @FXML private DatePicker dpTanggal;
    @FXML private TextField tfJumlah;
    @FXML private TextField tfMetode;
    @FXML private TextField tfKeterangan;
    @FXML private TextField tfIdAdministrasi;

    private final PembayaranService service = new PembayaranService();
    private final ObservableList<Pembayaran> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colIdTransaksi.setCellValueFactory(d ->
                new javafx.beans.property.SimpleIntegerProperty(
                        d.getValue().getId_Transaksi()
                ).asObject()
        );
        colTanggal.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getTanggal_Pembayaran().toString()
                )
        );
        colJumlah.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getJumlah_Pembayaran()
                )
        );
        colMetode.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getMetode_Pembayaran()
                )
        );

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAllPembayaran());
        tablePembayaran.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        Pembayaran pembayaran = new Pembayaran(
                Integer.parseInt(tfIdTransaksi.getText()),
                dpTanggal.getValue() != null ? dpTanggal.getValue() : LocalDate.now(),
                new BigDecimal(tfJumlah.getText()),
                tfMetode.getText(),
                tfKeterangan.getText(),
                Integer.parseInt(tfIdAdministrasi.getText())
        );

        if (service.simpanPembayaran(pembayaran)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setId_Transaksi(Integer.parseInt(tfIdTransaksi.getText()));
            selected.setTanggal_Pembayaran(dpTanggal.getValue());
            selected.setJumlah_Pembayaran(new BigDecimal(tfJumlah.getText()));
            selected.setMetode_Pembayaran(tfMetode.getText());
            selected.setKeterangan(tfKeterangan.getText());
            selected.setIdAdministrasi(Integer.parseInt(tfIdAdministrasi.getText()));

            service.updatePembayaran(selected);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        Pembayaran selected = tablePembayaran.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusPembayaran(selected.getId());
            loadData();
        }
    }

    private void clearForm() {
        tfIdTransaksi.clear();
        tfJumlah.clear();
        tfMetode.clear();
        tfKeterangan.clear();
        tfIdAdministrasi.clear();
        dpTanggal.setValue(null);
    }
}