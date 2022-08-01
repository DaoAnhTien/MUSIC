package com.example.music.model;

public class Nhac {
    public int maNhac;
    public int maTheloai;
    public  int maCasi;
    public String tenBH;
    public String NamRD;
    public String LinkBH;

    public Nhac() {
    }

    public Nhac(int maNhac, int maTheloai, int maCasi, String tenBH, String namRD, String linkBH) {
        this.maNhac = maNhac;
        this.maTheloai = maTheloai;
        this.maCasi = maCasi;
        this.tenBH = tenBH;
        NamRD = namRD;
        LinkBH = linkBH;
    }

    public int getMaNhac() {
        return maNhac;
    }

    public void setMaNhac(int maNhac) {
        this.maNhac = maNhac;
    }

    public int getMaTheloai() {
        return maTheloai;
    }

    public void setMaTheloai(int maTheloai) {
        this.maTheloai = maTheloai;
    }

    public int getMaCasi() {
        return maCasi;
    }

    public void setMaCasi(int maCasi) {
        this.maCasi = maCasi;
    }

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    public String getNamRD() {
        return NamRD;
    }

    public void setNamRD(String namRD) {
        NamRD = namRD;
    }

    public String getLinkBH() {
        return LinkBH;
    }

    public void setLinkBH(String linkBH) {
        LinkBH = linkBH;
    }
}
