package kelas;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import kelas.data.DataProduk;
import kelas.model.Produk;
import kelas.controller.*;

public class Navigator {

    private final Stage stage;
    private final DataProduk data;
    private Scene scene;

    public Navigator(Stage stage, DataProduk data) {
        this.stage = stage;
        this.data = data;
    }

    private FXMLLoader loader(String nama) {
        return new FXMLLoader(
            getClass().getResource("/kelas/" + nama + ".fxml")
        );
    }

    private void tampil(Parent root) {

        if (scene == null) {
            scene = new Scene(root, 900, 680);

            scene.getStylesheets().add(
                getClass().getResource(
                    "/kelas/styles/app.css"
                ).toExternalForm()
            );

            stage.setScene(scene);
        } else {
            scene.setRoot(root);
        }

        stage.setTitle("Daftar Produk Toko");
        stage.show();
    }

    public void bukaForm() throws IOException {
        FXMLLoader l = loader("form");
        Parent root = l.load();

        l.<FormController>getController()
            .setKonteks(this, data);

        tampil(root);
    }

    public void bukaDaftar() throws IOException {
        FXMLLoader l = loader("daftar");
        Parent root = l.load();

        l.<DaftarController>getController()
            .setKonteks(this, data);

        tampil(root);
    }

    public void bukaDetail(Produk produk) throws IOException {
        FXMLLoader l = loader("detail");
        Parent root = l.load();

        l.<DetailController>getController()
            .setData(this, produk);

        tampil(root);
    }
}
