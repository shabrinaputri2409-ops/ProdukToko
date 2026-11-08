package kelas.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {

    @FXML private TableView<Produk> tabel;
    @FXML private TableColumn<Produk, String> kodeCol;
    @FXML private TableColumn<Produk, String> namaCol;
    @FXML private TableColumn<Produk, String> kategoriCol;
    @FXML private TableColumn<Produk, Double> hargaCol;

    @FXML private TextField cariField;
    @FXML private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;

    private final ObservableList<Produk> dataTampilan =
            FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        kodeCol.setCellValueFactory(
                new PropertyValueFactory<>("kodeProduk"));

        namaCol.setCellValueFactory(
                new PropertyValueFactory<>("namaProduk"));

        kategoriCol.setCellValueFactory(
                new PropertyValueFactory<>("kategori"));

        hargaCol.setCellValueFactory(
                new PropertyValueFactory<>("harga"));

        hargaCol.setCellFactory(col ->
            new TableCell<Produk, Double>() {
                @Override
                protected void updateItem(Double harga, boolean empty) {
                    super.updateItem(harga, empty);

                    if (empty || harga == null) {
                        setText(null);
                    } else {
                        setText(NumberFormat.getCurrencyInstance(
                                new Locale("id", "ID")).format(harga));
                    }
                }
            });

        tabel.setItems(dataTampilan);
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        tampilkanSemua();
    }

    @FXML
    private void cari() {

        String kata = cariField.getText().trim().toLowerCase();

        dataTampilan.setAll(
            data.getDaftar().filtered(produk ->
                produk.getNamaProduk()
                      .toLowerCase()
                      .contains(kata)
            )
        );

        pesanLabel.setText(
                "Jumlah produk ditemukan: " + dataTampilan.size());
    }

    @FXML
    private void tampilkanSemua() {
        cariField.clear();
        dataTampilan.setAll(data.getDaftar());

        pesanLabel.setText(
                "Jumlah produk: " + dataTampilan.size());
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void detail() {

        Produk produk = tabel.getSelectionModel().getSelectedItem();

        if (produk == null) {
            pesanLabel.setText("Pilih produk terlebih dahulu!");
            return;
        }

        try {
            navigator.bukaDetail(produk);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
