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
import com.example.music.framment.TheloaiFragment;
import com.example.music.model.Theloai;

import java.util.ArrayList;

public class TheLoaiAdapter extends ArrayAdapter<Theloai> {
    private Context context;
    TheloaiFragment fragment;
    private ArrayList<Theloai> lists;
    TextView tvMaLoai,tvTenLoai;
    ImageView imgdel;
//    LoaiSachDAO dao;

    public TheLoaiAdapter(@NonNull  Context context, TheloaiFragment fragment, ArrayList<Theloai> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflate=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflate.inflate(R.layout.theloai_item,null);
        }
        final Theloai item=lists.get(position);
        if(item!=null){
            tvMaLoai=v.findViewById(R.id.tvMatheloai);
            tvMaLoai.setText("Mã Thể Loại: "+item.getMaTL());
            tvTenLoai=v.findViewById(R.id.tvTenTL);
            tvTenLoai.setText("Tên Thể Loai: "+item.getTenTL());
            imgdel=v.findViewById(R.id.imgDelete);

        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //phuon thuc xoa
                fragment.delete(String.valueOf(item.getMaTL()));
            }
        });
        return v;
    }
}
