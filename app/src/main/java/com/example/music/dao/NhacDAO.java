package com.example.music.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.music.database.Datahelper;
import com.example.music.model.Nhac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NhacDAO {
    private SQLiteDatabase db;
    private Context context;
    public static List<Nhac>lsnhac=new ArrayList<>();
    public NhacDAO(Context context) {
        this.context=context;
        Datahelper dbhelper = new Datahelper(context);
        db = dbhelper.getWritableDatabase();

    }
    //them thanh vien vao bang thanh vien
    public long insert(Nhac obj){
        ContentValues values= new ContentValues();
        values.put("MaCS",obj.getMaCasi());
        values.put("MaTL",obj.getMaTheloai());
        values.put("NamRD",obj.getNamRD());
        values.put("tenBH",obj.getTenBH());
        values.put("linkBH",obj.getLinkBH());
        return db.insert("Nhac",null,values);

    }
    //updata thanh vien
    public int updata(Nhac obj){
        ContentValues values= new ContentValues();
        values.put("MaCS",obj.getMaCasi());
        values.put("MaTL",obj.getMaTheloai());
        values.put("NamRD",obj.getNamRD());
        values.put("tenBH",obj.getTenBH());
        values.put("linkBH",obj.getLinkBH());
        return db.update("Nhac",values,"maNhac=?",new String[]{String.valueOf(obj.getMaNhac())});
    }
    //delete thanh vien
    public  int delete(String id){
        return db.delete("Nhac","maNhac=?",new String[]{id});
    }
    //get tat ca trong bang thnh vien
    public List<Nhac> getALL(){
        String sql="SELECT*FROM Nhac";
        return getData(sql);
    }
    //get theo id
    public Nhac getID(String id){
        String sql="SELECT * FROM Nhac WHERE maNhac=?";
        List<Nhac>list= getData(sql,id);
        return list.get(0);
    }
    public Nhac getTen(String name){
        String sql="SELECT * FROM Nhac WHERE tenBH=?";
        List<Nhac>list= getData(sql,name);
        return list.size()>0 ?list.get(0):null;
    }
    private  List<Nhac> getData(String sql,String...selecttionArgs)  {
        List<Nhac> list= new ArrayList<Nhac>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            Nhac obj= new Nhac();
            obj.setMaNhac( Integer.parseInt(c.getString(c.getColumnIndex("maNhac"))));
            obj.setMaTheloai( Integer.parseInt(c.getString(c.getColumnIndex("MaTL"))));
            obj.setMaCasi(Integer.parseInt(c.getString(c.getColumnIndex("MaCS"))));
            obj.setTenBH(c.getString(c.getColumnIndex("tenBH")));
            obj.setNamRD(c.getString(c.getColumnIndex("namRD")));
            obj.setLinkBH(c.getString(c.getColumnIndex("linkBH")));
            list.add(obj);
        }
        return list;
    }
}
