package com.example.music.model;

import java.util.Date;

public class Casi {
    public int maCasi;
    public String tenCS;
    public String GioiTinh;
    public Date ngaysinh;

    public Casi() {
    }

    public Casi(int maCasi, String tenCS, String gioiTinh, Date ngaysinh) {
        this.maCasi = maCasi;
        this.tenCS = tenCS;
        GioiTinh = gioiTinh;
        this.ngaysinh = ngaysinh;
    }

    public int getMaCasi() {
        return maCasi;
    }

    public void setMaCasi(int maCasi) {
        this.maCasi = maCasi;
    }

    public String getTenCS() {
        return tenCS;
    }

    public void setTenCS(String tenCS) {
        this.tenCS = tenCS;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
