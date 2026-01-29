package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Pegawai;
import com.pembukuan_cv_abba_barokah.Service.PegawaiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PegawaiController {

    @FXML private TableView<Pegawai> tablePegawai;
    @FXML private TableColumn<Pegawai, String> colNama;
    @FXML private TableColumn<Pegawai, String> colJabatan;
    @FXML private TableColumn<Pegawai, Pegawai.StatusPegawai> colStatusPegawai;
    @FXML private TableColumn<Pegawai, Pegawai.StatusAktif> colStatusAktif;

    @FXML private TextField tfNama;
    @FXML private TextField tfJabatan;
    @FXML private TextField tfGajiPokok;
    @FXML private TextField tfIdAdministrasi;
    @FXML private DatePicker dpTanggalMasuk;
    @FXML private ComboBox<Pegawai.StatusPegawai> cbStatusPegawai;
    @FXML private ComboBox<Pegawai.StatusAktif> cbStatusAktif;

    private final PegawaiService service = new PegawaiService();
    private final ObservableList<Pegawai> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNama.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getNama()
                )
        );
        colJabatan.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getJabatan()
                )
        );
        colStatusPegawai.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getStatus_Pegawai()
                )
        );
        colStatusAktif.setCellValueFactory(d ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        d.getValue().getStatus()
                )
        );

        cbStatusPegawai.setItems(
                FXCollections.observableArrayList(Pegawai.StatusPegawai.values())
        );
        cbStatusAktif.setItems(
                FXCollections.observableArrayList(Pegawai.StatusAktif.values())
        );

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAllPegawai());
        tablePegawai.setItems(data);
    }

    @FXML
    private void handleSimpan() {
        Pegawai pegawai = new Pegawai(
                tfNama.getText(),
                tfJabatan.getText(),
                cbStatusPegawai.getValue(),
                new BigDecimal(tfGajiPokok.getText()),
                dpTanggalMasuk.getValue() != null ? dpTanggalMasuk.getValue() : LocalDate.now(),
                cbStatusAktif.getValue(),
                Integer.parseInt(tfIdAdministrasi.getText())
        );

        if (service.simpanPegawai(pegawai)) {
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleUpdate() {
        Pegawai selected = tablePegawai.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setNama(tfNama.getText());
            selected.setJabatan(tfJabatan.getText());
            selected.setStatus_Pegawai(cbStatusPegawai.getValue());
            selected.setGaji_Pokok(new BigDecimal(tfGajiPokok.getText()));
            selected.setTanggal_Masuk(dpTanggalMasuk.getValue());
            selected.setStatus(cbStatusAktif.getValue());
            selected.setIdAdministrasi(Integer.parseInt(tfIdAdministrasi.getText()));

            service.perbaruiPegawai(selected);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleHapus() {
        Pegawai selected = tablePegawai.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusPegawai(selected.getId());
            loadData();
        }
    }

    private void clearForm() {
        tfNama.clear();
        tfJabatan.clear();
        tfGajiPokok.clear();
        tfIdAdministrasi.clear();
        dpTanggalMasuk.setValue(null);
        cbStatusPegawai.setValue(null);
        cbStatusAktif.setValue(null);
    }
}