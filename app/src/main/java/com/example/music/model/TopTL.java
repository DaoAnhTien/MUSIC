package com.example.music.model;

public class TopTL {
    public  String tenTL;
    public int soluong;

    public TopTL() {
    }

    public TopTL(String tenTL, int soluong) {
        this.tenTL = tenTL;
        this.soluong = soluong;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
