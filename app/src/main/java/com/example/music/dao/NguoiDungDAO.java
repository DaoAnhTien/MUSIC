package com.example.music.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.music.database.Datahelper;
import com.example.music.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    public NguoiDungDAO(Context context) {
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();
    }
    public long insert(NguoiDung obj){
        ContentValues values= new ContentValues();
        values.put("maND",obj.getMaND());
        values.put("hoTen",obj.getTenND());
        values.put("matKhau",obj.getMatkhau());
        return  db.insert("NguoiDung" ,null ,values);

    }
    //updata Thuthu
    public int updataPass(NguoiDung obj){
        ContentValues values= new ContentValues();
        values.put("hoTen",obj.getTenND());
        values.put("matKhau",obj.getMatkhau());
        return db.update("NguoiDung",values,"maND=?", new String[]{obj.getMaND()});
    }
    //delete thanh vien
    public  int delete(String id){
        return db.delete("NguoiDung","maND=?",new String[]{id});
    }

    //get tat ca trong bang thnh vien
    public List<NguoiDung> getALL(){
        String sql="SELECT*FROM ThuThu";
        return getData(sql);
    }
    //get theo id
     public NguoiDung getID(String id){
        String sql="SELECT * FROM NguoiDung WHERE maND=?";
        List<NguoiDung>list= getData(sql,id);
        return list.get(0);
    }
     public  List<NguoiDung> getData(String sql ,String...selecttionArgs){
        List<NguoiDung> list= new ArrayList<NguoiDung>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            NguoiDung obj= new NguoiDung();
            obj.setMaND( c.getString(c.getColumnIndex("maND")));
            obj.setTenND(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatkhau(c.getString(c.getColumnIndex("matKhau")));//thieu chữ t
            list.add(obj);
        }
        return list;
    }
    //chek Login
    public int chekLogin(String id,String Password){
        String sql="SELECT * FROM NguoiDung WHERE maND=? AND matKhau=?";
        List<NguoiDung> list=getData(sql,id,Password);
        if(list.size()==0)
            return  -1 ;
        return 1;
    }
}
