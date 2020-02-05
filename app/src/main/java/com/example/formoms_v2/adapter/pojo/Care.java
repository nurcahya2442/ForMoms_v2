package com.example.formoms_v2.adapter.pojo;

public class Care {
    private String idCare, picTips, titleTips, namePeople, content, createAt, photoPeople;

    public Care(String idCare, String picTips, String titleTips, String photoPeople, String namePeople, String content, String createAt) {
        this.idCare = idCare;
        this.picTips = picTips;
        this.titleTips = titleTips;
        this.photoPeople = photoPeople;
        this.namePeople = namePeople;
        this.createAt = createAt;
        this.content = content;
    }

    public Care() {}

    public String getIdCare() {
        return idCare;
    }

    public void setIdCare(String idCare) {
        this.idCare = idCare;
    }

    public String getPicTips() {
        return picTips;
    }

    public void setPicTips(String picTips) {
        this.picTips = picTips;
    }

    public String getTitleTips() {
        return titleTips;
    }

    public void setTitleTips(String titleTips) {
        this.titleTips = titleTips;
    }

    public String getNamePeople() {
        return namePeople;
    }

    public void setNamePeople(String namePeople) {
        this.namePeople = namePeople;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getPhotoPeople() {
        return photoPeople;
    }

    public void setPhotoPeople(String photoPeople) {
        this.photoPeople = photoPeople;
    }
}
