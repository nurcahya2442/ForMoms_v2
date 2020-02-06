package com.example.formoms_v2.adapter.pojo;

public class Album {

    private String AlbumId, albumName, createdAt;

    public Album() {}

    public Album(String albumId, String albumName, String createdAt) {
        AlbumId = albumId;
        this.albumName = albumName;
        this.createdAt = createdAt;
    }

    public String getAlbumId() {
        return AlbumId;
    }

    public void setAlbumId(String albumId) {
        AlbumId = albumId;
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

    public void setCreateAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
