package com.example.formoms_v2.adapter.pojo;

public class User {
    String uid,nama, email , password, photoprofile,tglLahir,tLahir, depskripsi;

    public String getNama() {
        return nama;
    }
    public User(){}
    public User(String uid, String nama, String email, String password, String photoprofile, String tglLahir, String tLahir, String depskripsi) {
        this.uid = uid;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.photoprofile = photoprofile;
        this.tglLahir = tglLahir;
        this.tLahir = tLahir;
        this.depskripsi = depskripsi;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDepskripsi() {
        return depskripsi;
    }

    public void setDepskripsi(String depskripsi) {
        this.depskripsi = depskripsi;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoprofile() {
        return photoprofile;
    }

    public void setPhotoprofile(String photoprofile) {
        this.photoprofile = photoprofile;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String gettLahir() {
        return tLahir;
    }

    public void settLahir(String tLahir) {
        this.tLahir = tLahir;
    }
}
