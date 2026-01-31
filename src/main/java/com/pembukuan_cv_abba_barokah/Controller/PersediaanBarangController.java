package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import com.pembukuan_cv_abba_barokah.Service.PersediaanBarangService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PersediaanBarangController {

    @FXML private DatePicker dpTanggal;
    @FXML private TextField txtNamaBarang;
    @FXML private TextField txtSatuan;
    @FXML private ComboBox<PersediaanBarang.JenisTransaksi> cbJenisTransaksi;
    @FXML private TextField txtJumlahMasuk;
    @FXML private TextField txtJumlahKeluar;
    @FXML private TextField txtHargaSatuan;
    @FXML private TextField txtKeterangan;

    @FXML private TableView<PersediaanBarang> tablePersediaan;
    @FXML private TableColumn<PersediaanBarang, LocalDate> colTanggal;
    @FXML private TableColumn<PersediaanBarang, String> colNama;
    @FXML private TableColumn<PersediaanBarang, String> colJenis;
    @FXML private TableColumn<PersediaanBarang, Integer> colMasuk;
    @FXML private TableColumn<PersediaanBarang, Integer> colKeluar;
    @FXML private TableColumn<PersediaanBarang, BigDecimal> colSaldo;

    private final PersediaanBarangService service = new PersediaanBarangService();
    private final ObservableList<PersediaanBarang> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbJenisTransaksi.setItems(
                FXCollections.observableArrayList(PersediaanBarang.JenisTransaksi.values())
        );

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getTanggal()));
        colNama.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNama_Barang()));
        colJenis.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getJenis_Transaksi().toString()
        ));
        colMasuk.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(
                c.getValue().getJumlah_Masuk()).asObject()
        );
        colKeluar.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(
                c.getValue().getJumlah_Keluar()).asObject()
        );
        colSaldo.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getSaldo_Akhir()
        ));

        loadData();

        tablePersediaan.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> fillForm(newVal)
        );
    }

    private void loadData() {
        data.setAll(service.getAll());
        tablePersediaan.setItems(data);
    }

    private void fillForm(PersediaanBarang p) {
        if (p == null) return;

        dpTanggal.setValue(p.getTanggal());
        txtNamaBarang.setText(p.getNama_Barang());
        txtSatuan.setText(p.getSatuan());
        cbJenisTransaksi.setValue(p.getJenis_Transaksi());
        txtJumlahMasuk.setText(String.valueOf(p.getJumlah_Masuk()));
        txtJumlahKeluar.setText(String.valueOf(p.getJumlah_Keluar()));
        txtHargaSatuan.setText(p.getHarga_Satuan().toString());
        txtKeterangan.setText(p.getKeterangan());
    }

    @FXML
    private void handleSimpan() {
        PersediaanBarang p = new PersediaanBarang(
                dpTanggal.getValue(),
                txtNamaBarang.getText(),
                txtSatuan.getText(),
                cbJenisTransaksi.getValue(),
                Integer.parseInt(txtJumlahMasuk.getText()),
                Integer.parseInt(txtJumlahKeluar.getText()),
                BigDecimal.ZERO, // saldo dihitung SERVICE
                new BigDecimal(txtHargaSatuan.getText()),
                txtKeterangan.getText()
        );

        service.tambahPersediaan(p);
        clearForm();
        loadData();
    }

    @FXML
    private void handleUpdate() {
        PersediaanBarang selected = tablePersediaan.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setTanggal(dpTanggal.getValue());
        selected.setNama_Barang(txtNamaBarang.getText());
        selected.setSatuan(txtSatuan.getText());
        selected.setJenis_Transaksi(cbJenisTransaksi.getValue());
        selected.setJumlah_Masuk(Integer.parseInt(txtJumlahMasuk.getText()));
        selected.setJumlah_Keluar(Integer.parseInt(txtJumlahKeluar.getText()));
        selected.setHarga_Satuan(new BigDecimal(txtHargaSatuan.getText()));
        selected.setKeterangan(txtKeterangan.getText());

        service.perbaruiPersediaan(selected);
        clearForm();
        loadData();
    }

    @FXML
    private void handleHapus() {
        PersediaanBarang selected = tablePersediaan.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        service.hapusPersediaan(selected.getId());
        clearForm();
        loadData();
    }

    private void clearForm() {
        dpTanggal.setValue(null);
        txtNamaBarang.clear();
        txtSatuan.clear();
        cbJenisTransaksi.setValue(null);
        txtJumlahMasuk.clear();
        txtJumlahKeluar.clear();
        txtHargaSatuan.clear();
        txtKeterangan.clear();
        tablePersediaan.getSelectionModel().clearSelection();
    }
}