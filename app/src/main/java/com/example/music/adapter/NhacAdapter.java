package com.example.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.music.R;
import com.example.music.dao.CasiDAO;
import com.example.music.dao.NhacDAO;
import com.example.music.dao.TheLoaiDAO;
import com.example.music.framment.NhacFragment;
import com.example.music.model.Casi;
import com.example.music.model.Nhac;
import com.example.music.model.Theloai;

import java.util.List;

public class NhacAdapter extends ArrayAdapter<Nhac> {
    private  Context context;
    NhacFragment fragment;
    List<Nhac> list;
    TextView tvManhac,tvTennhac,tvCS,tvNamRD,tvlinkBH,tvtheloai;
    ImageView imgdel;

    public NhacAdapter(@NonNull  Context context, NhacFragment fragment, List<Nhac> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.list = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.nhac_item,null);
        }
        final Nhac item=list.get(position);
        if(item!=null){
            tvManhac=v.findViewById(R.id.tvMaNhac);
            tvManhac.setText("Mã Nhạc: "+item.getMaNhac());
            TheLoaiDAO theLoaiDAO= new TheLoaiDAO(context);
            Theloai theloai= theLoaiDAO.getID(String.valueOf(item.getMaTheloai()));
            tvtheloai=v.findViewById(R.id.tvtenTlN);
            tvtheloai.setText("Tên Thể Loại :"+theloai.getTenTL());
            CasiDAO casiDao= new CasiDAO(context);
            Casi casi= casiDao.getID(String.valueOf(item.getMaCasi()));
            tvCS=v.findViewById(R.id.tvTenCS);
            tvCS.setText("Tên Ca sĩ :"+casi.getTenCS());
            tvTennhac=v.findViewById(R.id.tvtenNhac);
            tvTennhac.setText("Tên Nhạc: "+item.getTenBH());
            tvNamRD=v.findViewById(R.id.tvnamRD);
            tvNamRD.setText("Năm RĐ"+item.getNamRD());
            tvlinkBH=v.findViewById(R.id.tvLInkBH);
            tvlinkBH.setText("Link bài hát:"+item.getLinkBH());
            imgdel=v.findViewById(R.id.imgDeleteNhac);
        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phuong thuc xoa
               fragment.delete(String.valueOf(item.getMaNhac()));
            }
        });




        return v;
    }

}
