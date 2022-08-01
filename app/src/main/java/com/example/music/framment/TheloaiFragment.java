package com.example.music.framment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.music.R;
import com.example.music.adapter.TheLoaiAdapter;
import com.example.music.dao.CasiDAO;
import com.example.music.dao.TheLoaiDAO;
import com.example.music.model.Casi;
import com.example.music.model.Theloai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TheloaiFragment extends Fragment {
    static TheLoaiDAO dao;
    ArrayList<Theloai> list;
    ListView Lvtheloai;
    Dialog dialog;
    TheLoaiAdapter adapter;
    Theloai item;
    EditText edmaTl,edTenTl,edsearch;
    ImageButton imgtl;
    FloatingActionButton fab;
    Button btnSaveTl,btncancelTL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_theloai, container, false);
        Lvtheloai=v.findViewById(R.id.LVtheloai);
        dao = new TheLoaiDAO(getActivity());
        fab= v.findViewById(R.id.fabTL);
        edsearch=v.findViewById(R.id.edseachtL);
        imgtl=v.findViewById(R.id.imgseachtl);
        imgtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogTl(getActivity());
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
                capnhatLV();
            }
        });
        Lvtheloai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        capnhatLV();
        capnhatLV();
        return  v;
    }
    void capnhatLV() {
        list = (ArrayList<Theloai>) dao.getALL();
        adapter = new TheLoaiAdapter(getActivity(), this, list);
        Lvtheloai.setAdapter(adapter);
    }
    public  void delete(final  String id){
        //su dung Alert
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle("Dlete");
        builder.setMessage("Bạn có muôn xóa không ");
        builder.setCancelable(true);
        builder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delete(id);
                        capnhatLV();
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    protected void openDialog(final Context context,final int type){
        dialog= new Dialog(context);
        dialog.setContentView(R.layout.theloai_dialog);
        edmaTl=dialog.findViewById(R.id.edMaTl);
        edTenTl=dialog.findViewById(R.id.edtenTl);

        btnSaveTl=dialog.findViewById(R.id.btnSaveTL);
        btncancelTL=dialog.findViewById(R.id.btncancelTL);
        //kiem tra type
        edmaTl.setEnabled(false);

        if(type!=0){
            edmaTl.setText(String.valueOf(item.getMaTL()));
            edTenTl.setText("Thể Loại: "+item.getTenTL());

        }
        btncancelTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveTl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item= new Theloai();
                item.setTenTL(edTenTl.getText().toString());
                if(validate()>0){
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm không  thành công",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaTL(Integer.parseInt(edmaTl.getText().toString()));
                        if(dao.updata(item)>0){
                            Toast.makeText(getActivity(),"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(),"sửa không thành công",Toast.LENGTH_SHORT).show();
                        }

                    }
                    capnhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public  int validate(){
        int chek =1;
        if(edTenTl.getText().length()==0){
            Toast.makeText(getActivity(),"bạn Phải nhap đủ thong tin",Toast.LENGTH_SHORT).show();
            chek=-1;
        }
        return  chek;
    }
    protected void openDialogTl(final Context context) {
        dao = new TheLoaiDAO(context);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.theloai_dialogtk);
        edmaTl=dialog.findViewById(R.id.edMaTl);
        edTenTl=dialog.findViewById(R.id.edtenTl);
        btncancelTL=dialog.findViewById(R.id.btncanceltk);

        btncancelTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        item = new Theloai();
        item = dao.getTen(edsearch.getText().toString());
        if (item != null) {
            edmaTl.setText(String.valueOf(item.getMaTL()));
            edTenTl.setText("Thể Loại: "+item.getTenTL());
            dialog.show();
        } else {
            Toast.makeText(context, "Tên thể loại không Đúng. \n Mời nhập lại tên thể loại ", Toast.LENGTH_SHORT).show();
        }
    }
    }