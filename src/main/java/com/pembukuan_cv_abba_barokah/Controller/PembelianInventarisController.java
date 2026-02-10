package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PembelianInventaris;
import com.pembukuan_cv_abba_barokah.Service.PembelianInventarisService;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PembelianInventarisController {

        @FXML
        private DatePicker tanggalField;
        @FXML
        private TextField noPembelianField;
        @FXML
        private TextField namaBarangField;
        @FXML
        private TextField jumlahField;
        @FXML
        private TextField satuanField;
        @FXML
        private TextField hargaSatuanField;
        @FXML
        private TextField ongkosKirimField;
        @FXML
        private TextField keteranganField;
        @FXML
        private TableColumn<PembelianInventaris, Integer> colId;
        @FXML
        private TableColumn<PembelianInventaris, java.time.LocalDate> colTanggal;
        @FXML
        private TableColumn<PembelianInventaris, String> colKeterangan;

        @FXML
        private TableView<PembelianInventaris> tableInventaris;
        @FXML
        private TableColumn<PembelianInventaris, String> colNo;
        @FXML
        private TableColumn<PembelianInventaris, String> colNama;
        @FXML
        private TableColumn<PembelianInventaris, Integer> colJumlah;
        @FXML
        private TableColumn<PembelianInventaris, String> colSatuan;
        @FXML
        private TableColumn<PembelianInventaris, BigDecimal> colHarga;
        @FXML
        private TableColumn<PembelianInventaris, BigDecimal> colOngkir;
        @FXML
        private TableColumn<PembelianInventaris, BigDecimal> colTotal;

        private final PembelianInventarisService service = new PembelianInventarisService();

        private final ObservableList<PembelianInventaris> data = FXCollections.observableArrayList();

        private PembelianInventaris selected;

        @FXML
        public void initialize() {

                colId.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getId()));

                colNo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getNoPembelian()));

                colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getTanggalPembelian()));

                colNama.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getNamaBarang()));

                colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getJumlah()));

                colSatuan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getSatuan()));

                colHarga.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getHargaSatuan()));

                colOngkir.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getOngkosKirim()));

                colTotal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getTotal()));

                colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getKeterangan()));

                tableInventaris.setItems(data);
                loadData();

                tableInventaris.getSelectionModel()
                                .selectedItemProperty()
                                .addListener((obs, o, n) -> isiForm(n));
        }

        private void loadData() {
                data.setAll(service.getAll());
        }

        @FXML
        private void handleSimpan() {

                PembelianInventaris pi = new PembelianInventaris(
                                noPembelianField.getText(),
                                namaBarangField.getText(),
                                Integer.parseInt(jumlahField.getText()),
                                satuanField.getText(),
                                new BigDecimal(hargaSatuanField.getText()),
                                new BigDecimal(ongkosKirimField.getText()),
                                keteranganField.getText(),
                                tanggalField.getValue());

                service.simpan(pi);
                loadData();
                clearForm();
        }

        @FXML
        private void handleUpdate() {

                if (selected == null)
                        return;

                selected = new PembelianInventaris(
                                selected.getId(),
                                noPembelianField.getText(),
                                namaBarangField.getText(),
                                Integer.parseInt(jumlahField.getText()),
                                satuanField.getText(),
                                new BigDecimal(hargaSatuanField.getText()),
                                new BigDecimal(ongkosKirimField.getText()),
                                keteranganField.getText(),
                                tanggalField.getValue());

                service.update(selected);
                loadData();
                clearForm();
        }

        @FXML
        private void handleHapus() {

                if (selected == null)
                        return;

                service.hapus(selected.getId());
                loadData();
                clearForm();
        }

        private void isiForm(PembelianInventaris pi) {
                if (pi == null)
                        return;

                selected = pi;

                noPembelianField.setText(pi.getNoPembelian());
                namaBarangField.setText(pi.getNamaBarang());
                jumlahField.setText(String.valueOf(pi.getJumlah()));
                satuanField.setText(pi.getSatuan());
                hargaSatuanField.setText(pi.getHargaSatuan().toString());
                ongkosKirimField.setText(pi.getOngkosKirim().toString());
                keteranganField.setText(pi.getKeterangan());
                tanggalField.setValue(pi.getTanggalPembelian());
        }

        private void clearForm() {
                selected = null;
                noPembelianField.clear();
                namaBarangField.clear();
                jumlahField.clear();
                satuanField.clear();
                hargaSatuanField.clear();
                ongkosKirimField.clear();
                keteranganField.clear();
                tanggalField.setValue(null);
        }
}