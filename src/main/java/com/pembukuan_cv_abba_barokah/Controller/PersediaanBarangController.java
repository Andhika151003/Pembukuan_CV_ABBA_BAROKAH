package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.PersediaanBarang;
import com.pembukuan_cv_abba_barokah.Service.PersediaanBarangService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PersediaanBarangController {

        @FXML
        private DatePicker tanggalField;
        @FXML
        private TextField namaBarangField;
        @FXML
        private TextField jumlahField;
        @FXML
        private TextField hargaSatuanField;
        @FXML
        private TextField keteranganField;

        @FXML
        private TableView<PersediaanBarang> tablePersediaan;
        @FXML
        private TableColumn<PersediaanBarang, String> colTanggal;
        @FXML
        private TableColumn<PersediaanBarang, String> colNama;
        @FXML
        private TableColumn<PersediaanBarang, Integer> colJumlah;
        @FXML
        private TableColumn<PersediaanBarang, BigDecimal> colHarga;
        @FXML
        private TableColumn<PersediaanBarang, BigDecimal> colTotal;
        @FXML
        private TableColumn<PersediaanBarang, String> colKeterangan;

        private final PersediaanBarangService service = new PersediaanBarangService();
        private final ObservableList<PersediaanBarang> data = FXCollections.observableArrayList();

        private PersediaanBarang selected;

        @FXML
        public void initialize() {

                colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getTanggal().toString()));

                colNama.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getNamaBarang()));

                colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getJumlah()));

                colHarga.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getHargaSatuan()));

                colTotal.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getHargaSatuan()
                                                .multiply(BigDecimal.valueOf(
                                                                c.getValue().getJumlah()))));

                colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getKeterangan()));

                tablePersediaan.setItems(data);
                loadData();

                tablePersediaan.getSelectionModel()
                                .selectedItemProperty()
                                .addListener((obs, o, n) -> isiForm(n));
        }

        private void loadData() {
                data.setAll(service.getAll());
        }

        @FXML
        private void handleSimpan() {

                PersediaanBarang p = new PersediaanBarang(
                                tanggalField.getValue(),
                                namaBarangField.getText(),
                                Integer.parseInt(jumlahField.getText()),
                                new BigDecimal(hargaSatuanField.getText()),
                                keteranganField.getText());

                service.simpan(p);
                loadData();
                clearForm();
        }

        @FXML
        private void handleUpdate() {

                if (selected == null)
                        return;

                PersediaanBarang p = new PersediaanBarang(
                                selected.getId(),
                                tanggalField.getValue(),
                                namaBarangField.getText(),
                                Integer.parseInt(jumlahField.getText()),
                                new BigDecimal(hargaSatuanField.getText()),
                                keteranganField.getText());

                service.update(p);
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

        private void isiForm(PersediaanBarang p) {

                if (p == null)
                        return;

                selected = p;

                tanggalField.setValue(p.getTanggal());
                namaBarangField.setText(p.getNamaBarang());
                jumlahField.setText(String.valueOf(p.getJumlah()));
                hargaSatuanField.setText(p.getHargaSatuan().toString());
                keteranganField.setText(p.getKeterangan());
        }

        private void clearForm() {

                selected = null;

                tanggalField.setValue(null);
                namaBarangField.clear();
                jumlahField.clear();
                hargaSatuanField.clear();
                keteranganField.clear();
                tablePersediaan.getSelectionModel().clearSelection();
        }
}