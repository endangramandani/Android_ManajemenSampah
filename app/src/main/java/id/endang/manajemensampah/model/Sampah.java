package id.endang.manajemensampah.model;

public class Sampah {

    private int id;
    private String jenis;
    private String kategori;
    private double berat;
    private String tanggal;

    public Sampah() {
    }

    public Sampah(int id, String jenis, String kategori, double berat, String tanggal) {
        this.id = id;
        this.jenis = jenis;
        this.kategori = kategori;
        this.berat = berat;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}