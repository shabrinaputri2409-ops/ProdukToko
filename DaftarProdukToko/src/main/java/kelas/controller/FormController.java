package kelas.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class FormController {

    @FXML
    private TextField kodeField, namaField, hargaField;

    @FXML
    private ComboBox<String> kategoriBox;

    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;

    @FXML
    private void initialize() {
        kategoriBox.getItems().addAll("Makanan", "Minuman");
        kategoriBox.setValue("Makanan");
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
    }

    @FXML
    private void simpan() {
        try {
            double harga = Double.parseDouble(hargaField.getText().trim());

            Produk produk = new Produk(
                kodeField.getText(),
                namaField.getText(),
                kategoriBox.getValue(),
                harga
            );

            data.tambah(produk);
            navigator.bukaDetail(produk);

        } catch (NumberFormatException e) {
            pesanLabel.setText("Harga harus berupa angka.");
        } catch (IllegalArgumentException e) {
            pesanLabel.setText(e.getMessage());
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka halaman detail.");
            e.printStackTrace();
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            pesanLabel.setText("Gagal membuka daftar produk.");
            e.printStackTrace();
        }
    }
}
