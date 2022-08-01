package com.example.music.framment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.music.R;
import com.example.music.adapter.CasiSpinnerAdapter;
import com.example.music.adapter.NhacAdapter;
import com.example.music.adapter.TheloaiSpinnerAdapter;
import com.example.music.dao.CasiDAO;
import com.example.music.dao.NhacDAO;
import com.example.music.dao.TheLoaiDAO;
import com.example.music.model.Casi;
import com.example.music.model.Nhac;
import com.example.music.model.Theloai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NhacFragment extends Fragment {
    ListView lvPhieumuon;
    ArrayList<Nhac> list;
    static NhacDAO dao;
    NhacAdapter adapter;
    Nhac item;
    Dialog dialogl;
    FloatingActionButton fab;
    EditText edMaN,edLink,edtenN;
    Spinner spCS, spTL;
    TextView edngay;
    Button btncancel, btnsave;
    ImageView imgngay;
    SimpleDateFormat sdf =  new SimpleDateFormat();

    TheloaiSpinnerAdapter theloaispinerAdapter;
    ArrayList<Theloai> listtheloai;
    TheLoaiDAO theLoaiDAO;

    CasiSpinnerAdapter casiSpinnerAdapter;
    ArrayList<Casi> listcasi;
    CasiDAO casiDAO;

    int matheloai,maCS,tienthue;
    int postionCS,postionTl;
    int nam,thang,ngay;
    ImageButton imgnhac;
    EditText edsearchn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_nhac, container, false);
        lvPhieumuon=v.findViewById(R.id.LVnhac);
        dao= new NhacDAO(getActivity());
        fab=v.findViewById(R.id.fabnhac);
        edsearchn=v.findViewById(R.id.edseachn);
        imgnhac=v.findViewById(R.id.imgseachn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog(getActivity(),0);
            }
        });
        lvPhieumuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item= list.get(position);
                opendialog(getActivity(),1);
                return false;
            }
        });
        imgnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogtk(getActivity());
            }
        });
        capnhatLV();
        return v;
    }
    void capnhatLV(){
        list=(ArrayList<Nhac>) dao.getALL();
        adapter= new NhacAdapter(getActivity(),this,list);
        lvPhieumuon.setAdapter(adapter);
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
    protected void opendialog(final Context context,int type){
        Dialog dialogl= new Dialog(context);
        dialogl.setContentView(R.layout.nhac_dialog);
        edMaN=dialogl.findViewById(R.id.edmanhac);
        spCS=dialogl.findViewById(R.id.spmaCS);
        spTL=dialogl.findViewById(R.id.spTL);
        edngay=dialogl.findViewById(R.id.edngay);
        edtenN=dialogl.findViewById(R.id.edtennhac);
        edLink=dialogl.findViewById(R.id.edlink);
        btncancel=dialogl.findViewById(R.id.btncancelN);
        btnsave=dialogl.findViewById(R.id.btnSaveN);
        edMaN.setEnabled(false);
        //set ngay thue,mac dinh  ngay hien hanh

        theLoaiDAO= new TheLoaiDAO(context);
        listtheloai=  new ArrayList<Theloai>();
        listtheloai=(ArrayList<Theloai>)theLoaiDAO.getALL();
        theloaispinerAdapter= new TheloaiSpinnerAdapter(context,listtheloai);
        spTL.setAdapter(theloaispinerAdapter);
        spTL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matheloai=listtheloai.get(position).getMaTL();
                //Toast.makeText(context,"chọn"+listthanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        casiDAO= new CasiDAO(context);
        listcasi=  new ArrayList<Casi>();
        listcasi=(ArrayList<Casi>)casiDAO.getALL();
        casiSpinnerAdapter= new CasiSpinnerAdapter(context,listcasi);
        spCS.setAdapter(casiSpinnerAdapter);
        spCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maCS=listcasi.get(position).getMaCasi();
                //Toast.makeText(context,"chọn"+listSach.get(position).getTenSach(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogl.dismiss();
            }
        });
        if(type!=0) {
            edMaN.setText(String.valueOf(item.getMaNhac()));
            edtenN.setText("Tên Nhạc: "+item.getTenBH());
            edLink.setText("Link: "+item.getLinkBH());
            for (int i = 0; i < listcasi.size(); i++)
                if (item.getMaCasi() == (listcasi.get(i).getMaCasi())) {
                    postionCS = i;
                }
                spCS.setSelection(postionCS);
            for (int i = 0; i < listtheloai.size(); i++)
                if (item.getMaTheloai() == (listtheloai.get(i).getMaTL())) {
                    postionTl = i;
                }
            spTL.setSelection(postionTl);
                edngay.setText("Năm Ra Đời: "+item.getNamRD());

        }
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item= new Nhac();
                item.setTenBH(edtenN.getText().toString());
                item.setMaTheloai(matheloai);
                item.setMaCasi(maCS);
                item.setNamRD(edngay.getText().toString());
                item.setLinkBH(edLink.getText().toString());

                if(type==0){
                    if(dao.insert(item)>0){
                        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(context,"Thêm không thành  công",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    item.setMaNhac(Integer.parseInt(edMaN.getText().toString()));
                    if(dao.updata(item)>0){
                        Toast.makeText(context,"sửa  thành công",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
                dialogl.dismiss();
                capnhatLV();
            }

        });

        dialogl.show();
    }

    protected void openDialogtk(final Context context) {
        Dialog dialogl = new Dialog(context);
        dialogl.setContentView(R.layout.nhac_dialogtk);
        edMaN = dialogl.findViewById(R.id.edmanhac);
        spCS = dialogl.findViewById(R.id.spmaCS);
        spTL = dialogl.findViewById(R.id.spTL);
        edngay = dialogl.findViewById(R.id.edngay);
        edtenN = dialogl.findViewById(R.id.edtennhac);
        edLink = dialogl.findViewById(R.id.edlink);
        btncancel = dialogl.findViewById(R.id.btncanceltln);
        edMaN.setEnabled(false);
        theLoaiDAO = new TheLoaiDAO(context);
        listtheloai = new ArrayList<Theloai>();
        listtheloai = (ArrayList<Theloai>) theLoaiDAO.getALL();
        theloaispinerAdapter = new TheloaiSpinnerAdapter(context, listtheloai);
        spTL.setAdapter(theloaispinerAdapter);
        spTL.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matheloai = listtheloai.get(position).getMaTL();
                //Toast.makeText(context,"chọn"+listthanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        casiDAO = new CasiDAO(context);
        listcasi = new ArrayList<Casi>();
        listcasi = (ArrayList<Casi>) casiDAO.getALL();
        casiSpinnerAdapter = new CasiSpinnerAdapter(context, listcasi);
        spCS.setAdapter(casiSpinnerAdapter);
        spCS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maCS = listcasi.get(position).getMaCasi();
                //Toast.makeText(context,"chọn"+listSach.get(position).getTenSach(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogl.dismiss();
            }
        });
        item = new Nhac();
        item = dao.getTen(edsearchn.getText().toString());
            if (item != null) {
                edMaN.setText(String.valueOf(item.getMaNhac()));
                edtenN.setText("Tên Nhạc: " + item.getTenBH());
                edLink.setText("Link: " + item.getLinkBH());
                for (int i = 0; i < listcasi.size(); i++)
                    if (item.getMaCasi() == (listcasi.get(i).getMaCasi())) {
                        postionCS = i;
                    }
                spCS.setSelection(postionCS);
                for (int i = 0; i < listtheloai.size(); i++)
                    if (item.getMaTheloai() == (listtheloai.get(i).getMaTL())) {
                        postionTl = i;
                    }
                spTL.setSelection(postionTl);
                edngay.setText("Năm Ra Đời: " + item.getNamRD());
                dialogl.show();
            }else{
                Toast.makeText(context, "không  tìm thấy bài Hát\n Mời nhập lại tên bài hát", Toast.LENGTH_SHORT).show();
            }



    }

}