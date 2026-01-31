package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.UtangUsaha;
import com.pembukuan_cv_abba_barokah.Service.UtangUsahaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UtangUsahaController {

    @FXML private TextField txtNoUtang;
    @FXML private DatePicker dpTanggalUtang;
    @FXML private DatePicker dpJatuhTempo;
    @FXML private TextField txtIdPembelian;
    @FXML private TextField txtJumlahUtang;
    @FXML private TextField txtJumlahDibayar;
    @FXML private ComboBox<UtangUsaha.StatusUtang> cbStatusUtang;
    @FXML private TextField txtKeterangan;

    @FXML private TableView<UtangUsaha> tableUtang;
    @FXML private TableColumn<UtangUsaha, Integer> colNoUtang;
    @FXML private TableColumn<UtangUsaha, LocalDate> colTanggal;
    @FXML private TableColumn<UtangUsaha, LocalDate> colJatuhTempo;
    @FXML private TableColumn<UtangUsaha, BigDecimal> colSisa;
    @FXML private TableColumn<UtangUsaha, String> colStatus;

    private final UtangUsahaService service = new UtangUsahaService();
    private final ObservableList<UtangUsaha> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbStatusUtang.setItems(
                FXCollections.observableArrayList(UtangUsaha.StatusUtang.values())
        );

        colNoUtang.setCellValueFactory(c -> 
                new javafx.beans.property.SimpleIntegerProperty(c.getValue().getNo_Utang()).asObject()
        );
        colTanggal.setCellValueFactory(c -> 
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggal_Utang())
        );
        colJatuhTempo.setCellValueFactory(c -> 
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggal_Jatuh_Tempo())
        );
        colSisa.setCellValueFactory(c -> 
                new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getSisa_Utang())
        );
        colStatus.setCellValueFactory(c -> 
                new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus_Utang().toString())
        );

        loadData();

        tableUtang.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> fillForm(newVal)
        );
    }

    private void loadData() {
        data.setAll(service.getAll());
        tableUtang.setItems(data);
    }

    private void fillForm(UtangUsaha u) {
        if (u == null) return;

        txtNoUtang.setText(String.valueOf(u.getNo_Utang()));
        dpTanggalUtang.setValue(u.getTanggal_Utang());
        dpJatuhTempo.setValue(u.getTanggal_Jatuh_Tempo());
        txtIdPembelian.setText(String.valueOf(u.getid_Pembelian()));
        txtJumlahUtang.setText(u.getJumlah_Utang().toString());
        txtJumlahDibayar.setText(u.getJumlah_Dibayar().toString());
        cbStatusUtang.setValue(u.getStatus_Utang());
        txtKeterangan.setText(u.getKeterangan());
    }

    @FXML
    private void handleSimpan() {
        BigDecimal jumlahUtang = new BigDecimal(txtJumlahUtang.getText());
        BigDecimal jumlahDibayar = new BigDecimal(txtJumlahDibayar.getText());
        BigDecimal sisa = jumlahUtang.subtract(jumlahDibayar);

        UtangUsaha utang = new UtangUsaha(
                Integer.parseInt(txtNoUtang.getText()),
                dpTanggalUtang.getValue(),
                dpJatuhTempo.getValue(),
                Integer.parseInt(txtIdPembelian.getText()),
                jumlahUtang,
                jumlahDibayar,
                sisa,
                cbStatusUtang.getValue(),
                txtKeterangan.getText()
        );

        service.tambahUtang(utang);
        clearForm();
        loadData();
    }

    @FXML
    private void handleUpdate() {
        UtangUsaha selected = tableUtang.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setNo_Utang(Integer.parseInt(txtNoUtang.getText()));
        selected.setTanggal_Utang(dpTanggalUtang.getValue());
        selected.setTanggal_Jatuh_Tempo(dpJatuhTempo.getValue());
        selected.setid_Pembelian(Integer.parseInt(txtIdPembelian.getText()));

        BigDecimal jumlahUtang = new BigDecimal(txtJumlahUtang.getText());
        BigDecimal jumlahDibayar = new BigDecimal(txtJumlahDibayar.getText());

        selected.setJumlah_Utang(jumlahUtang);
        selected.setJumlah_Dibayar(jumlahDibayar);
        selected.setSisa_Utang(jumlahUtang.subtract(jumlahDibayar));
        selected.setStatus_Utang(cbStatusUtang.getValue());
        selected.setKeterangan(txtKeterangan.getText());

        // idAdministrasi = disuplai dari konteks (misalnya kas utama = 1)
        service.bayarUtang(selected, 1);

        clearForm();
        loadData();
    }

    @FXML
    private void handleHapus() {
        UtangUsaha selected = tableUtang.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        service.hapusUtang(selected.getId(), 1);
        clearForm();
        loadData();
    }

    private void clearForm() {
        txtNoUtang.clear();
        dpTanggalUtang.setValue(null);
        dpJatuhTempo.setValue(null);
        txtIdPembelian.clear();
        txtJumlahUtang.clear();
        txtJumlahDibayar.clear();
        cbStatusUtang.setValue(null);
        txtKeterangan.clear();
        tableUtang.getSelectionModel().clearSelection();
    }
}