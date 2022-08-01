package com.example.music.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Datahelper extends SQLiteOpenHelper {
    static  final  String dbNAME="MUSIC";
    static  final  int  dvVersion=3;
    //tao bang Nguoi Dung
    static final String creatableNguoiDung=
            "create table NguoiDung("+
                            "maND TEXT PRIMARY KEY,"+
                            "hoTen TEXT NOT NULL,"+
                            "matKhau TEXT NOT NULL)";
    //tao bang  Ca si
    static final String creatableCasi=
            "create table CaSi("+
                    "maCS INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "hoTen TEXT NOT NULL,"+
                    "ngaySinh DATA NOT NULL,"+
                    "gioiTinh TEXT NOT NULL)";

    //tao bang the loai
    static final String creatableTheLoai=
            "create table TheLoai("+
                    "maTL INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "tenTL TEXT NOT NULL)";
    // tạo bảng nhạc
    static final String creatableNhac=
            "create table Nhac("+
                    "maNhac INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "MaTL INTEGER REFERENCES TheLoai(maTL),"+
                    "MaCS INTEGER REFERENCES CaSi(maCS),"+
                    "tenBH TEXT NOT NULL,"+
                    "linkBH TEXT NOT NULL,"+
                    " namRD INTEGER NOT NULL)";
    public Datahelper(@Nullable Context context) {
        super(context, dbNAME, null, dvVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creatableCasi);
        db.execSQL(creatableNguoiDung);
        db.execSQL(creatableTheLoai);
        db.execSQL(creatableNhac);

        db.execSQL(Data_sqlite_mau.INSERT_NGUOIDUNG);
        db.execSQL(Data_sqlite_mau.INSERT_THELOAI);
        db.execSQL(Data_sqlite_mau.INSERT_CASI);
        db.execSQL(Data_sqlite_mau.INSERT_NHAC);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String droptableNguoiDung="drop table if exists NguoiDung";
        db.execSQL(droptableNguoiDung);
        String droptableCasi="drop table if exists CaSi";
        db.execSQL(droptableCasi);
        String droptableTheLoai="drop table if exists TheLoai";
        db.execSQL(droptableTheLoai);
        String droptableNhac="drop table if exists Nhac";
        db.execSQL(droptableNhac);
        onCreate(db);

    }
}
