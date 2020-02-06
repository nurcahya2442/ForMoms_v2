package com.example.formoms_v2.adapter.pojo;
public class Imunisasi {

    String id,judul,deskripsi,tanggal;

    public Imunisasi(){

    }

    public Imunisasi(String id, String judul, String deskripsi, String tanggal){
        this.tanggal = tanggal;
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }
}