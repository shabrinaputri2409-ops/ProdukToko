package kelas.model;

public class Produk {

    private String kodeProduk;
    private String namaProduk;
    private String kategori;
    private double harga;

    public Produk(String kodeProduk, String namaProduk,
                  String kategori, double harga) {

        if (kodeProduk == null || kodeProduk.isBlank()) {
            throw new IllegalArgumentException("Kode produk wajib diisi.");
        }

        if (namaProduk == null || namaProduk.isBlank()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong.");
        }

        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari nol.");
        }

        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }
}
