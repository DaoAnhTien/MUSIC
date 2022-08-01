
package com.example.music.framment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.music.R;
import com.example.music.adapter.CasiAdapter;
import com.example.music.adapter.TheLoaiAdapter;
import com.example.music.dao.CasiDAO;
import com.example.music.dao.TheLoaiDAO;
import com.example.music.model.Casi;
import com.example.music.model.Theloai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CasiFragment extends Fragment {
    static CasiDAO dao;
    ArrayList<Casi> list;
    ListView lvCasi;
    Dialog dialog;
    CasiAdapter adapter;
    Casi item;
    EditText edmaCS, edTenCS, edngaysinh, edGT, edserach;
    FloatingActionButton fab;
    SimpleDateFormat sdf = new SimpleDateFormat();
    Button btnSaveCS, btncancelCS;
    int nam, thang, ngay;
    ImageButton imgngays, imgsearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_casi, container, false);
        lvCasi = v.findViewById(R.id.LVcasi);
        edserach = v.findViewById(R.id.edseach);
        imgsearch = v.findViewById(R.id.imgseach);
        dao = new CasiDAO(getActivity());
        fab = v.findViewById(R.id.fabCS);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
                capnhatLV();
            }
        });
        lvCasi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogtk(getActivity());
            }
        });
        capnhatLV();
        capnhatLV();

        return v;
    }

    void capnhatLV() {
        list = (ArrayList<Casi>) dao.getALL();
        adapter = new CasiAdapter(getActivity(), this, list);
        lvCasi.setAdapter(adapter);
    }

    public void delete(final String id) {
        //su dung Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void openDialog(final Context context, final int type) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.casi_dialog);
        edmaCS = dialog.findViewById(R.id.edMaCS);
        edTenCS = dialog.findViewById(R.id.edtenCS);
        edGT = dialog.findViewById(R.id.edGT);
        edngaysinh = dialog.findViewById(R.id.edngaysinh);
        imgngays = dialog.findViewById(R.id.imgngay);
        btnSaveCS = dialog.findViewById(R.id.btnSaveCS);
        btncancelCS = dialog.findViewById(R.id.btncancelCS);
        imgngays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                nam = c.get(Calendar.YEAR);
                thang = c.get(Calendar.MONTH);
                ngay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0
                        , ngaysinh, nam, thang, ngay);
                d.show();
            }
        });
        //kiem tra type
        edmaCS.setEnabled(false);

        if (type != 0) {
            edmaCS.setText(String.valueOf(item.getMaCasi()));
            edTenCS.setText("Tên ca Sĩ: "+item.getTenCS());
            edngaysinh.setText("Ngày Sinh: "+ sdf.format(item.getNgaysinh()));
            edGT.setText("Giới Tính: "+item.getGioiTinh());

        }
        btncancelCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSaveCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Casi();
                item.setTenCS(edTenCS.getText().toString());
                item.setNgaysinh(new Date());
                item.setGioiTinh(edGT.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm không  thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaCasi(Integer.parseInt(edmaCS.getText().toString()));
                        if (dao.updata(item) > 0) {
                            Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "sửa không thành công", Toast.LENGTH_SHORT).show();
                        }

                    }
                    capnhatLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate() {
        int chek = 1;
        if (edTenCS.getText().length() == 0 || edGT.getText().length() == 0 || edngaysinh.getText().length() == 0) {
            Toast.makeText(getActivity(), "bạn Phải nhap đủ thong tin", Toast.LENGTH_SHORT).show();
            chek = -1;
        }
        return chek;
    }

    DatePickerDialog.OnDateSetListener ngaysinh = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            nam = year;
            thang = month;
            ngay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(nam, thang, ngay);
            edngaysinh.setText(sdf.format(c.getTime()));
        }
    };

    protected void openDialogtk(final Context context) {
        dao= new CasiDAO(context);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.casi_dialogtk);
        edmaCS = dialog.findViewById(R.id.edMaCS);
        edTenCS = dialog.findViewById(R.id.edtenCS);
        edGT = dialog.findViewById(R.id.edGT);
        edngaysinh = dialog.findViewById(R.id.edngaysinh);
        imgngays = dialog.findViewById(R.id.imgngay);
        btncancelCS = dialog.findViewById(R.id.btncancelCS);
        btncancelCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        item= new Casi();
        item = dao.getTen(edserach.getText().toString());
        if (item != null) {
            edmaCS.setText(String.valueOf(item.getMaCasi()));
            edTenCS.setText("Tên ca Sĩ"+item.getTenCS());
            edngaysinh.setText("Ngày Sinh: "+ sdf.format(item.getNgaysinh()));
            edGT.setText("Giới Tính"+item.getGioiTinh());
            dialog.show();
        }else{
            Toast.makeText(context, "Tên ca Sĩ không Đúng. /n Mời nhập lại tên ca sĩ", Toast.LENGTH_SHORT).show();
        }



    }

}