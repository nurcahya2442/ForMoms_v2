package com.example.formoms_v2.adapter.pojo;

public class Album {

    private String idAlbum, picAlbum, nameAlbum, createAt;

    public Album() {}
    public Album(String idAlbum, String nameAlbum, String createAt) {
        this.idAlbum = idAlbum;
        this.picAlbum = picAlbum;
        this.nameAlbum = nameAlbum;
        this.createAt = createAt;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getPicAlbum() {
        return picAlbum;
    }

    public void setPicAlbum(String picAlbum) {
        this.picAlbum = picAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
