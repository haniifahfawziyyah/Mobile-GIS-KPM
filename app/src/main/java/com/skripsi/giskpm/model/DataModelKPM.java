package com.skripsi.giskpm.model;

public class DataModelKPM {
    private String id_hasil, nama_kelurahankpm ,nik_kpm,nama_kpm,alamatkpm,telpkpm,latitudekpm,longitudekpm;

    public DataModelKPM() {
    }

    public DataModelKPM(String id_hasil, String nama_kelurahankpm, String nik_kpm, String nama_kpm , String alamatkpm, String telpkpm, String latitudekpm, String longitudekpm) {
        this.id_hasil = id_hasil;
        this.nama_kelurahankpm = nama_kelurahankpm;
        this.nik_kpm = nik_kpm;
        this.nama_kpm = nama_kpm;
        this.alamatkpm = alamatkpm;
        this.telpkpm = telpkpm;
        this.latitudekpm=latitudekpm;
        this.longitudekpm=longitudekpm;
    }

    public String getId_kpm() {
        return id_hasil;
    }

    public void setId_kpm(String id_hasil) {
        this.id_hasil = id_hasil;
    }

    public String getNama_kelurahankpm() {
        return nama_kelurahankpm;
    }

    public void setNama_kelurahankpm(String nama_kelurahankpm) {
        this.nama_kelurahankpm = nama_kelurahankpm;
    }

    public String getNik_kpm() {
        return nik_kpm;
    }

    public void setNik_kpm(String nik_kpm) {
        this.nik_kpm = nik_kpm;
    }

    public String getNama_kpm() {
        return nama_kpm;
    }

    public void setNama_kpm(String nama_kpm) {
        this.nama_kpm = nama_kpm;
    }

    public String getAlamatkpm() {
        return alamatkpm;
    }

    public void setAlamatkpm(String alamatkpm) {
        this.alamatkpm = alamatkpm;
    }

    public String getTelpkpm() {
        return telpkpm;
    }

    public void setTelpkpm(String telpkpm) {
        this.telpkpm = telpkpm;
    }

    public String getLatitudekpm() {
        return latitudekpm;
    }

    public void setLatitudekpm(String latitudekpm) {
        this.latitudekpm = latitudekpm;
    }

    public String getLongitudekpm() {
        return longitudekpm;
    }

    public void setLongitudekpm(String longitudekpm) {
        this.longitudekpm = longitudekpm;
    }

}
