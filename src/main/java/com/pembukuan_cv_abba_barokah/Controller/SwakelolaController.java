package com.pembukuan_cv_abba_barokah.Controller;

import com.pembukuan_cv_abba_barokah.Model.Swakelola;
import com.pembukuan_cv_abba_barokah.Service.SwakelolaService;
import com.pembukuan_cv_abba_barokah.Service.PenjualanService;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class SwakelolaController {

    @FXML private DatePicker tanggalField;
    @FXML private TextField bahan1Field;
    @FXML private TextField bahan2Field;
    @FXML private TextField bahan3Field;
    @FXML private TextField ongkosPotongField;
    @FXML private TextField ongkosJahitField;
    @FXML private TextField lainLainField;
    @FXML private TextField transportasiField;
    @FXML private TextField keteranganField;
    @FXML private ComboBox<Integer> cbIdPenjualan;

    @FXML private TableView<Swakelola> table;
    @FXML private TableColumn<Swakelola, String> colTanggal;
    @FXML private TableColumn<Swakelola, BigDecimal> colBahan1;
    @FXML private TableColumn<Swakelola, BigDecimal> colBahan2;
    @FXML private TableColumn<Swakelola, BigDecimal> colBahan3;
    @FXML private TableColumn<Swakelola, BigDecimal> colPotong;
    @FXML private TableColumn<Swakelola, BigDecimal> colJahit;
    @FXML private TableColumn<Swakelola, BigDecimal> colLain;
    @FXML private TableColumn<Swakelola, BigDecimal> colTransportasi;
    @FXML private TableColumn<Swakelola, Integer> colIdPenjualan;
    @FXML private TableColumn<Swakelola, String> colKeterangan;
    @FXML private TableColumn<Swakelola, BigDecimal> colTotal;

    @FXML private Label lblTotal;

    private final SwakelolaService service = new SwakelolaService();
    private final PenjualanService penjualanService = new PenjualanService();
    private final ObservableList<Swakelola> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        refreshComboIdPenjualan();

        colTanggal.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getTanggal().toString()));

        colBahan1.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getBahan1()));
        colBahan2.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getBahan2()));
        colBahan3.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getBahan3()));
        colPotong.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getOngkosTukangPotong()));
        colJahit.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getOngkosTukangJahit()));
        colLain.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getLainLain()));
        colTransportasi.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTransportasi()));
        colIdPenjualan.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getIdPenjualan()));
        colKeterangan.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getKeterangan()));

        colTotal.setCellValueFactory(c -> {
            Swakelola s = c.getValue();
            BigDecimal total =
                    s.getBahan1().add(s.getBahan2()).add(s.getBahan3())
                    .add(s.getOngkosTukangPotong())
                    .add(s.getOngkosTukangJahit())
                    .add(s.getLainLain())
                    .add(s.getTransportasi());
            return new SimpleObjectProperty<>(total);
        });

        table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, o, n) -> { if (n != null) fillForm(n); });

        loadData();
    }

    private void loadData() {
        data.setAll(service.getAll());
        hitungTotal();
    }

    @FXML
    private void handleSimpan() {

        int idPenjualan = cbIdPenjualan.getValue();

        if (service.sudahAdaUntukPenjualan(idPenjualan)) {
            alert("Data sudah ada",
                  "Swakelola untuk penjualan ini sudah tercatat.");
            return;
        }

        Swakelola s = new Swakelola(
                tanggalField.getValue(),
                new BigDecimal(bahan1Field.getText()),
                new BigDecimal(bahan2Field.getText()),
                new BigDecimal(bahan3Field.getText()),
                new BigDecimal(ongkosPotongField.getText()),
                new BigDecimal(ongkosJahitField.getText()),
                new BigDecimal(lainLainField.getText()),
                new BigDecimal(transportasiField.getText()),
                keteranganField.getText(),
                idPenjualan
        );

        service.simpan(s);
        loadData();
        refreshComboIdPenjualan();
        clearForm();
    }

    @FXML
    private void handleUpdate() {
        Swakelola s = table.getSelectionModel().getSelectedItem();
        if (s != null) {
            s.setTanggal(tanggalField.getValue());
            s.setBahan1(new BigDecimal(bahan1Field.getText()));
            s.setBahan2(new BigDecimal(bahan2Field.getText()));
            s.setBahan3(new BigDecimal(bahan3Field.getText()));
            s.setOngkosTukangPotong(new BigDecimal(ongkosPotongField.getText()));
            s.setOngkosTukangJahit(new BigDecimal(ongkosJahitField.getText()));
            s.setLainLain(new BigDecimal(lainLainField.getText()));
            s.setTransportasi(new BigDecimal(transportasiField.getText()));
            s.setKeterangan(keteranganField.getText());

            service.update(s);
            loadData();
            clearForm();
        }
    }

    @FXML
    private void handleDelete() {
        Swakelola s = table.getSelectionModel().getSelectedItem();
        if (s != null) {
            service.hapus(s.getId());
            loadData();
            refreshComboIdPenjualan();
            clearForm();
        }
    }

    private void fillForm(Swakelola s) {
        tanggalField.setValue(s.getTanggal());
        bahan1Field.setText(s.getBahan1().toString());
        bahan2Field.setText(s.getBahan2().toString());
        bahan3Field.setText(s.getBahan3().toString());
        ongkosPotongField.setText(s.getOngkosTukangPotong().toString());
        ongkosJahitField.setText(s.getOngkosTukangJahit().toString());
        lainLainField.setText(s.getLainLain().toString());
        transportasiField.setText(s.getTransportasi().toString());
        keteranganField.setText(s.getKeterangan());
        cbIdPenjualan.setValue(s.getIdPenjualan());
        cbIdPenjualan.setDisable(true);
    }

    private void clearForm() {
        tanggalField.setValue(null);
        bahan1Field.clear();
        bahan2Field.clear();
        bahan3Field.clear();
        ongkosPotongField.clear();
        ongkosJahitField.clear();
        lainLainField.clear();
        transportasiField.clear();
        keteranganField.clear();
        cbIdPenjualan.setValue(null);
        cbIdPenjualan.setDisable(false);
        table.getSelectionModel().clearSelection();
    }

    private void hitungTotal() {
        BigDecimal total = data.stream()
                .map(s ->
                        s.getBahan1().add(s.getBahan2()).add(s.getBahan3())
                        .add(s.getOngkosTukangPotong())
                        .add(s.getOngkosTukangJahit())
                        .add(s.getLainLain())
                        .add(s.getTransportasi())
                ).reduce(BigDecimal.ZERO, BigDecimal::add);

        lblTotal.setText("Total Swakelola : Rp " + total);
    }

    private void refreshComboIdPenjualan() {
        cbIdPenjualan.setItems(FXCollections.observableArrayList(
                penjualanService.getAll().stream()
                        .map(p -> p.getId())
                        .filter(id -> !service.sudahAdaUntukPenjualan(id))
                        .toList()
        ));
    }

    private void alert(String t, String m) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}