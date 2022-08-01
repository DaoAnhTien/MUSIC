package com.example.music.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.music.database.Datahelper;
import com.example.music.model.Casi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CasiDAO {
    private SQLiteDatabase db;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
    public CasiDAO(Context context) {
        Datahelper dbhelper = new Datahelper(context);

        db = dbhelper.getWritableDatabase();

    }
    //them thanh vien vao bang thanh vien
    public long insert(Casi obj){
        ContentValues values= new ContentValues();
        values.put("hoTen",obj.getTenCS());
        values.put("ngaySinh", sdf.format(obj.getNgaysinh()));
        values.put("gioiTinh",obj.getGioiTinh());
        return db.insert("CaSi",null,values);

    }
    //updata thanh vien
    public int updata(Casi obj){
        ContentValues values= new ContentValues();
        values.put("hoTen",obj.getTenCS());
        values.put("ngaySinh", sdf.format(obj.getNgaysinh()));
        values.put("gioiTinh",obj.getGioiTinh());
        return db.update("CaSi",values,"maCS=?",new String[]{String.valueOf(obj.getMaCasi())});
    }
    //delete thanh vien
    public  int delete(String id){
            return db.delete("CaSi","maCS=?",new String[]{id});
    }
    //get tat ca trong bang thnh vien
    public List<Casi> getALL(){
        String sql="SELECT*FROM CaSi";
        return getData(sql);
    }
    //get theo id
    public Casi getID(String id){
        String sql="SELECT * FROM CaSi WHERE maCS=?";
        List<Casi>list= getData(sql,id);
        return list.get(0);
    }
    public Casi getTen(String name){
        String sql="SELECT * FROM CaSi WHERE hoTen=?";
        List<Casi>list= getData(sql,name);
        return list.size()>0 ?list.get(0):null;
    }


    private  List<Casi> getData(String sql,String...selecttionArgs){
        List<Casi> list= new ArrayList<Casi>();
        Cursor c= db.rawQuery(sql,selecttionArgs);
        while (c.moveToNext()){
            Casi obj= new Casi();
            obj.setMaCasi( Integer.parseInt(c.getString(c.getColumnIndex("maCS"))));
            obj.setTenCS(c.getString(c.getColumnIndex("hoTen")));
            obj.setGioiTinh(c.getString(c.getColumnIndex("gioiTinh")));
            try{
                obj.setNgaysinh(sdf.parse(c.getString(c.getColumnIndex("ngaySinh"))));
            }catch (ParseException e){

            }
            list.add(obj);
        }
        return list;
    }

}
