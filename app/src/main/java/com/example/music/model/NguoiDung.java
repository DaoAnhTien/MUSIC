package com.example.music.model;

public class NguoiDung {
    public  String maND;
    public String tenND;
    public String matkhau;

    public NguoiDung() {
    }

    public NguoiDung(String maND, String tenND, String matkhau) {
        this.maND = maND;
        this.tenND = tenND;
        this.matkhau = matkhau;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}

