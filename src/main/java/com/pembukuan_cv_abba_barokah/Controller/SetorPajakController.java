package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.SetorPajak;
import com.pembukuan_cv_abba_barokah.Service.SetorPajakService;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;

public class SetorPajakController {

    @FXML
    private DatePicker tanggalField;
    @FXML
    private ComboBox<SetorPajak.JenisPajak> cbJenisPajak;
    @FXML
    private TextField jumlahField;
    @FXML
    private TextArea keteranganField;
    @FXML
    private TextField buktiField;

    @FXML
    private TableView<SetorPajak> tablePajak;
    @FXML
    private TableColumn<SetorPajak, String> colTanggal;
    @FXML
    private TableColumn<SetorPajak, String> colJenis;
    @FXML
    private TableColumn<SetorPajak, BigDecimal> colJumlah;
    @FXML
    private TableColumn<SetorPajak, String> colKeterangan;
    @FXML
    private TableColumn<SetorPajak, String> colBukti;

    private final SetorPajakService service = new SetorPajakService();
    private final ObservableList<SetorPajak> data = FXCollections.observableArrayList();

    private byte[] selectedFileBytes; // untuk BLOB

    @FXML
    public void initialize() {

        cbJenisPajak.setItems(FXCollections.observableArrayList(
                SetorPajak.JenisPajak.values()));

        colTanggal.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getTanggalSetor().toString()));

        colJenis.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getJenisPajak().name()));

        colJumlah.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(
                c.getValue().getJumlahPajak()));

        colKeterangan.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getKeterangan()));

        colBukti.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                c.getValue().getBuktiSetor() != null ? "Ada File" : "-"));

        tablePajak.setItems(data);

        tablePajak.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    if (newVal != null)
                        fillForm(newVal);
                });

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
    }

    // ================= BROWSE FILE =================
    @FXML
    private void handleBrowse() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Pilih Bukti Setor");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File file = chooser.showOpenDialog(null);

        if (file != null) {
            try {
                selectedFileBytes = Files.readAllBytes(file.toPath());
                buktiField.setText(file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ================= SIMPAN =================
    @FXML
    private void handleSimpan() {

        try {

            SetorPajak sp = new SetorPajak(
                    tanggalField.getValue(),
                    cbJenisPajak.getValue(),
                    new BigDecimal(jumlahField.getText()),
                    selectedFileBytes,
                    keteranganField.getText());

            service.simpan(sp);
            loadData();
            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    @FXML
    private void handleUpdate() {

        SetorPajak selected = tablePajak.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        try {
            selected.setTanggalSetor(tanggalField.getValue());
            selected.setJenisPajak(cbJenisPajak.getValue());
            selected.setJumlahPajak(new BigDecimal(jumlahField.getText()));
            selected.setBuktiSetor(selectedFileBytes);
            selected.setKeterangan(keteranganField.getText());

            service.update(selected);
            loadData();
            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    @FXML
    private void handleDelete() {

        SetorPajak selected = tablePajak.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        service.hapus(selected.getId());
        loadData();
        clearForm();
    }

    private void fillForm(SetorPajak sp) {

        tanggalField.setValue(sp.getTanggalSetor());
        cbJenisPajak.setValue(sp.getJenisPajak());
        jumlahField.setText(sp.getJumlahPajak().toString());
        keteranganField.setText(sp.getKeterangan());

        selectedFileBytes = sp.getBuktiSetor();
        buktiField.setText(
                sp.getBuktiSetor() != null ? "File tersimpan di database" : "");
    }

    private void clearForm() {
        tanggalField.setValue(LocalDate.now());
        cbJenisPajak.setValue(null);
        jumlahField.clear();
        keteranganField.clear();
        buktiField.clear();
        selectedFileBytes = null;
        tablePajak.getSelectionModel().clearSelection();
    }
}