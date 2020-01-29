package com.example.formoms_v2.adapter.pojo;

public class Care {
    private String idCare;
    private int picTips;
    private String titleTips;
    private int photoPeople;
    private String namePeople;
    private String content;
    private String createAt;

    public Care(String idCare, int picTips, String titleTips, int photoPeople, String namePeople, String content, String createAt) {
        this.idCare = idCare;
        this.picTips = picTips;
        this.titleTips = titleTips;
        this.photoPeople = photoPeople;
        this.namePeople = namePeople;
        this.createAt = createAt;
        this.content = content;
    }

    public Care() {}

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

    public String getIdCare() {
        return idCare;
    }

    public void setIdCare(String idCare) {
        this.idCare = idCare;
    }

    public int getPicTips() {
        return picTips;
    }

    public void setPicTips(int picTips) {
        this.picTips = picTips;
    }

    public int getPhotoPeople() {
        return photoPeople;
    }

    public void setPhotoPeople(int photoPeople) {
        this.photoPeople = photoPeople;
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
}
