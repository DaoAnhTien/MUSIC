package com.example.music.model;

public class Theloai {
    public  int maTL;
    public  String tenTL;

    public Theloai() {
    }

    public Theloai(int maTL, String tenTL) {
        this.maTL = maTL;
        this.tenTL = tenTL;
    }

    public int getMaTL() {
        return maTL;
    }

    public void setMaTL(int maTL) {
        this.maTL = maTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
}
