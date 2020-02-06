package com.example.formoms_v2.adapter.pojo;

public class AlbumPhoto {
    String photoId, photoUrl, createdAt;

    public AlbumPhoto(){}

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AlbumPhoto(String photoId, String photoUrl, String createdAt) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
    }
}
