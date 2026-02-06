package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Administrasi;
import com.pembukuan_cv_abba_barokah.Model.BiayaPemasaran;
import com.pembukuan_cv_abba_barokah.Service.AdministrasiService;
import com.pembukuan_cv_abba_barokah.Service.BiayaPemasaranService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class BiayaAdministrasiController {

        @FXML
        private DatePicker tanggalField;
        @FXML
        private ComboBox<Administrasi.JenisAdministrasi> cbJenisAdministrasi;
        @FXML
        private TextField deskripsiField;
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
        private TableColumn<Administrasi, String> colDeskripsi;
        @FXML
        private TableColumn<Administrasi, BigDecimal> colJumlah;
        @FXML
        private TableColumn<Administrasi, String> colKeterangan;

        private final AdministrasiService service = new AdministrasiService();
        private final ObservableList<Administrasi> data = FXCollections.observableArrayList();
        private Administrasi selected;

        @FXML
        public void initialize() {

                colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getTanggal().toString()));

                cbJenisAdministrasi.setItems(FXCollections.observableArrayList(
                                Administrasi.JenisAdministrasi.values()));

                colJenis.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getJenisAdministrasi().toString()));

                colDeskripsi.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getDeskripsi()));

                colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                                c.getValue().getJumlahAdministrasi()));

                colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                                c.getValue().getKeterangan()));

                tableAdministrasi.setItems(data);
                loadData();
        }

        private void loadData() {
                data.setAll(service.getAll());
        }

        @FXML
        private void handleTableClick() {

                selected = tableAdministrasi.getSelectionModel().getSelectedItem();
                if (selected == null)
                        return;

                tanggalField.setValue(selected.getTanggal());
                cbJenisAdministrasi.setValue(selected.getJenisAdministrasi());
                deskripsiField.setText(selected.getDeskripsi());
                jumlahField.setText(selected.getJumlahAdministrasi().toString());
                keteranganField.setText(selected.getKeterangan());
        }

        @FXML
        private void handleSimpan() {

                Administrasi ads = new Administrasi(
                                tanggalField.getValue(),
                                cbJenisAdministrasi.getValue(),
                                deskripsiField.getText(),
                                new BigDecimal(jumlahField.getText()),
                                keteranganField.getText());

                service.simpan(ads);
                loadData();
        }

        @FXML
        private void handleUpdate() {

                if (selected == null)
                        return;

                Administrasi ads = new Administrasi(
                                selected.getId(),
                                tanggalField.getValue(),
                                cbJenisAdministrasi.getValue(),
                                deskripsiField.getText(),
                                new BigDecimal(jumlahField.getText()),
                                keteranganField.getText());

                service.update(ads);
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

        private void clearForm() {
                tanggalField.setValue(null);
                cbJenisAdministrasi.setValue(null);
                deskripsiField.clear();
                jumlahField.clear();
                keteranganField.clear();
                selected = null;
        }

}