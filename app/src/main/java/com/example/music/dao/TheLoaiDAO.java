package com.example.music.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.music.database.Datahelper;
import com.example.music.model.Theloai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    public TheLoaiDAO(Context context) {
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();

    }
    //them thanh vien vao bang thanh vien
    public long insert(Theloai obj){
        ContentValues values= new ContentValues();
        values.put("tenTL",obj.getTenTL());
        return db.insert("TheLoai",null,values);

    }
    //updata The loai
    public int updata(Theloai obj){
        ContentValues values= new ContentValues();
        values.put("tenTL",obj.getTenTL());
        return db.update("TheLoai",values,"maTL=?",new String[]{String.valueOf(obj.getMaTL())});
    }
    //delete the loai
    public  int delete(String id){
        return db.delete("TheLoai","maTL=?",new String[]{id});
    }
    //get tat ca trong bang the loai
    public List<Theloai> getALL(){
        String sql="SELECT*FROM TheLoai";
        return getData(sql);
    }
    //get theo id
    public Theloai getID(String id){
        String sql="SELECT * FROM TheLoai WHERE maTL=?";
        List<Theloai>list= getData(sql,id);
        return list.get(0);
    }
    public Theloai getTen(String name){
        String sql="SELECT * FROM TheLoai WHERE tenTL=?";
        List<Theloai>list= getData(sql,name);
        return list.size()>0 ?list.get(0):null;
    }

    private  List<Theloai> getData(String sql,String...selecttionArgs){
        List<Theloai> list= new ArrayList<Theloai>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            Theloai obj= new Theloai();
            obj.setMaTL( Integer.parseInt(c.getString(c.getColumnIndex("maTL"))));
            obj.setTenTL(c.getString(c.getColumnIndex("tenTL")));
            list.add(obj);
        }
        return list;
    }

}
