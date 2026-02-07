package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Service.AdministrasiService;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BiayaAdministrasiController {

        @FXML
        private DatePicker tanggalField;
        @FXML
        private ComboBox<Administrasi.JenisAdministrasi> cbJenisAdministrasi;
        @FXML
        private TextField jumlahField;
        @FXML
        private TextField keteranganField;

        @FXML
        private TableView<Administrasi> tableAdministrasi;
        @FXML
        private TableColumn<Administrasi, String> colTanggal;
        @FXML
        private TableColumn<Administrasi, String> colJenis;
        @FXML
        private TableColumn<Administrasi, BigDecimal> colJumlah;
        @FXML
        private TableColumn<Administrasi, String> colKeterangan;

        private final AdministrasiService service = new AdministrasiService();
        private final ObservableList<Administrasi> data = FXCollections.observableArrayList();

        private Administrasi selected;

        @FXML
        public void initialize() {

                cbJenisAdministrasi.setItems(FXCollections.observableArrayList(
                                Administrasi.JenisAdministrasi.values()));

                colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getTanggal().toString()));

                colJenis.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getJenisAdministrasi().name()));

                colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getJumlahAdministrasi()));

                colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getKeterangan()));

                tableAdministrasi.setItems(data);

                tableAdministrasi.getSelectionModel()
                                .selectedItemProperty()
                                .addListener((obs, o, n) -> isiForm(n));

                loadData();
        }

        private void loadData() {
                data.setAll(service.getAll());
        }

        @FXML
        private void handleSimpan() {

                Administrasi ads = new Administrasi(
                                tanggalField.getValue(),
                                cbJenisAdministrasi.getValue(),
                                new BigDecimal(jumlahField.getText()),
                                keteranganField.getText());

                service.simpan(ads);
                loadData();
                clearForm();
        }

        @FXML
        private void handleUpdate() {

                if (selected == null)
                        return;

                selected.setTanggal(tanggalField.getValue());
                selected.setJenisAdministrasi(cbJenisAdministrasi.getValue());
                selected.setJumlahAdministrasi(new BigDecimal(jumlahField.getText()));
                selected.setKeterangan(keteranganField.getText());

                service.update(selected);
                loadData();
                clearForm();
        }

        @FXML
        private void handleDelete() {

                if (selected == null)
                        return;

                service.delete(selected.getId());
                loadData();
                clearForm();
        }

        private void isiForm(Administrasi a) {
                if (a == null)
                        return;

                selected = a;

                tanggalField.setValue(a.getTanggal());
                cbJenisAdministrasi.setValue(a.getJenisAdministrasi());
                jumlahField.setText(a.getJumlahAdministrasi().toString());
                keteranganField.setText(a.getKeterangan());
        }

        private void clearForm() {
                selected = null;
                tanggalField.setValue(LocalDate.now());
                cbJenisAdministrasi.setValue(null);
                jumlahField.clear();
                keteranganField.clear();
                tableAdministrasi.getSelectionModel().clearSelection();
        }
}