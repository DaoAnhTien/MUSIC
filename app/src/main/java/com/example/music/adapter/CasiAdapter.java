package com.example.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.music.R;
import com.example.music.framment.CasiFragment;
import com.example.music.framment.TheloaiFragment;
import com.example.music.model.Casi;
import com.example.music.model.Theloai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CasiAdapter extends ArrayAdapter<Casi> {
    private Context context;
    CasiFragment fragment;
    private ArrayList<Casi> lists;
    TextView tvmaCS,tvTenCS,tvNgaysinh,tvgiotinh;
    ImageView imgdel;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");

    public CasiAdapter(@NonNull Context context, CasiFragment casiFragment, ArrayList<Casi>lists) {
        super(context, 0,lists);
        this.fragment = fragment;
        this.context = context;
        this.lists = lists;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflate=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflate.inflate(R.layout.casi_item,null);
        }
        final Casi item=lists.get(position);
        if(item!=null){
            tvmaCS=v.findViewById(R.id.tvmacasi);
            tvmaCS.setText("Mã Ca Sĩ: "+item.getMaCasi());
            tvTenCS=v.findViewById(R.id.tvteCS);
            tvTenCS.setText("Tên Ca Sĩ : "+item.getTenCS());
            tvNgaysinh=v.findViewById(R.id.tvngaysinh);
            tvNgaysinh.setText("Ngày Sinh : "+sdf.format(item.getNgaysinh()));
            tvgiotinh=v.findViewById(R.id.tvGT);
            tvgiotinh.setText("Giới Tính : "+item.getGioiTinh());
            imgdel=v.findViewById(R.id.imgDeletecs);

        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phuon thuc xoa
             fragment.delete(String.valueOf(item.getMaCasi()));
            }
        });
        return v;
    }
}
