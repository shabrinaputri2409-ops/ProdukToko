package kelas.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kelas.model.Produk;

public class DataProduk {

    private final ObservableList<Produk> daftar =
            FXCollections.observableArrayList();

    public ObservableList<Produk> getDaftar() {
        return FXCollections.unmodifiableObservableList(daftar);
    }

    public void tambah(Produk produk) {

        for (Produk p : daftar) {
            if (p.getKodeProduk().equalsIgnoreCase(produk.getKodeProduk())) {
                throw new IllegalArgumentException(
                        "Kode produk sudah digunakan."
                );
            }
        }

        daftar.add(produk);
    }
}
