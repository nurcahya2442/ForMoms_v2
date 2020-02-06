package com.example.formoms_v2.ui;
public class Album {

    String albumId, albumName, createdAt;
    public Album(){}
    public Album(String albumId, String albumName, String createdAt) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.createdAt = createdAt;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}