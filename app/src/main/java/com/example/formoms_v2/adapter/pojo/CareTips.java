package com.example.formoms_v2.adapter.pojo;

public class CareTips {
    private int picTips;
    private String titleTips;
    private int photoPeople;
    private String namePeople;

    public CareTips(int picTips, String titleTips, int photoPeople, String namePeople) {
        this.picTips = picTips;
        this.titleTips = titleTips;
        this.photoPeople = photoPeople;
        this.namePeople = namePeople;
    }

    public CareTips() {}

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
