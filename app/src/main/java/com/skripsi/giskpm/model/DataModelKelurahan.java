package com.skripsi.giskpm.model;

public class DataModelKelurahan {
    private String id_kelurahan, nama_kelurahan, lat, lng;
    private int jumlahkpm;
    private double proporsi;

    public DataModelKelurahan(String id_kelurahan, String nama_kelurahan, String lat, String lng,
                              int jumlahkpm, double proporsi) {
        this.id_kelurahan = id_kelurahan;
        this.nama_kelurahan = nama_kelurahan;
        this.lat = lat;
        this.lng = lng;
        this.jumlahkpm = jumlahkpm;
        this.proporsi = proporsi;
    }

    public String getId_kelurahan() {
        return id_kelurahan;
    }

    public void setId_kelurahan(String id_kelurahan) {
        this.id_kelurahan = id_kelurahan;
    }

    public String getNama_kelurahan() {
        return nama_kelurahan;
    }

    public void setNama_kelurahan(String nama_kelurahan) {
        this.nama_kelurahan = nama_kelurahan;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getJumlahkpm() {
        return jumlahkpm;
    }

    public void setJumlahkpm(int jumlahkpm) {
        this.jumlahkpm = jumlahkpm;
    }

    public double getProporsi() {
        return proporsi;
    }

    public void setProporsi(double proporsi) {
        this.proporsi = proporsi;
    }
}
