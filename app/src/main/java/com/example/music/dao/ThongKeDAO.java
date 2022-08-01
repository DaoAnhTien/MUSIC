package com.example.music.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.music.database.Datahelper;
import com.example.music.model.Theloai;
import com.example.music.model.TopTL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
    public ThongKeDAO(Context context){
        this.context=context;
        Datahelper datahelper=new Datahelper(context);
        db=datahelper.getWritableDatabase();
    }
    //thong ke top 10
    public List<TopTL> getTOP(){
        String sql="SELECT maTL, count(maTL) as soLuong FROM TheLoai GROUP BY maTL ORDER BY soLuong DESC LIMIT 10";
        List<TopTL>list= new ArrayList<>();
        TheLoaiDAO theLoaiDAO= new TheLoaiDAO(context);
        Cursor c=db.rawQuery(sql,null);
        while (c.moveToNext()){
            TopTL top= new TopTL();
            Theloai theloai=theLoaiDAO.getID(c.getString(c.getColumnIndex("maTL")));
            top.setTenTL(theloai.getTenTL());
            top.setSoluong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
}
