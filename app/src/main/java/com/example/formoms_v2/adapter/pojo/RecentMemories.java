package com.example.formoms_v2.adapter.pojo;

public class RecentMemories {
    String photoId, photoUrl, albumId, createdAt;

    public RecentMemories(String photoId, String photoUrl, String albumId, String createdAt) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.albumId = albumId;
        this.createdAt = createdAt;
    }

    public RecentMemories() {
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
