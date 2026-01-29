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

    @FXML private TableView<Penjualan> tablePenjualan;
    @FXML private TableColumn<Penjualan, String> colNo;
    @FXML private TableColumn<Penjualan, String> colCustomer;
    @FXML private TableColumn<Penjualan, BigDecimal> colTotal;
    @FXML private TableColumn<Penjualan, Penjualan.StatusPembayaran> colStatus;

    @FXML private TextField tfNoPenjualan;
    @FXML private DatePicker dpTanggal;
    @FXML private TextField tfCustomer;
    @FXML private TextField tfAlamat;
    @FXML private TextField tfTotal;
    @FXML private ComboBox<Penjualan.MetodePembayaran> cbMetode;
    @FXML private ComboBox<Penjualan.StatusPembayaran> cbStatus;
    @FXML private TextField tfKeterangan;
    @FXML private TextField tfIdAdministrasi;

    private final PenjualanService service = new PenjualanService();
    private final ObservableList<Penjualan> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNo.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getNo_Penjualan()
                )
        );
        colCustomer.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getNama_Customer()
                )
        );
        colTotal.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getTotal_Penjualan()
                )
        );
        colStatus.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getStatus_Pembayaran()
                )
        );

        cbMetode.setItems(FXCollections.observableArrayList(Penjualan.MetodePembayaran.values()));
        cbStatus.setItems(FXCollections.observableArrayList(Penjualan.StatusPembayaran.values()));

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        tablePenjualan.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        Penjualan jual = new Penjualan(
                tfNoPenjualan.getText(),
                dpTanggal.getValue() != null ? dpTanggal.getValue() : LocalDate.now(),
                tfCustomer.getText(),
                tfAlamat.getText(),
                new BigDecimal(tfTotal.getText()),
                cbMetode.getValue(),
                cbStatus.getValue(),
                tfKeterangan.getText()
        );

        int idAdministrasi = Integer.parseInt(tfIdAdministrasi.getText());
        if (service.tambahPenjualan(jual, idAdministrasi)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNo_Penjualan(tfNoPenjualan.getText());
            selected.setTanggal_Penjualan(dpTanggal.getValue());
            selected.setNama_Customer(tfCustomer.getText());
            selected.setAlamat_Customer(tfAlamat.getText());
            selected.setTotal_Penjualan(new BigDecimal(tfTotal.getText()));
            selected.setMetode_Pembayaran(cbMetode.getValue());
            selected.setStatus_Pembayaran(cbStatus.getValue());
            selected.setKeterangan(tfKeterangan.getText());

            int idAdministrasi = Integer.parseInt(tfIdAdministrasi.getText());
            service.perbaruiPenjualan(selected, idAdministrasi);

            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        Penjualan selected = tablePenjualan.getSelectionModel().getSelectedItem();
        if (selected != null) {
            int idAdministrasi = Integer.parseInt(tfIdAdministrasi.getText());
            service.hapusPenjualan(selected.getId(), idAdministrasi);
            loadData();
        }
    }

    private void clearForm() {
        tfNoPenjualan.clear();
        tfCustomer.clear();
        tfAlamat.clear();
        tfTotal.clear();
        tfKeterangan.clear();
        tfIdAdministrasi.clear();
        dpTanggal.setValue(null);
        cbMetode.setValue(null);
        cbStatus.setValue(null);
    }
}