package com.example.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.music.R;
import com.example.music.model.Theloai;

import java.util.ArrayList;

public class TheloaiSpinnerAdapter extends ArrayAdapter<Theloai> {
    Context context;
    ArrayList<Theloai> lists;
    TextView tvMatheloai, tvTentheloai;
    public TheloaiSpinnerAdapter(@NonNull Context context, ArrayList<Theloai> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.theloai_item_spiner,null);
        }
        final Theloai item= lists.get(position);
        if(item!=null){
            tvMatheloai=v.findViewById(R.id.tvMatheloaiSp);
            tvMatheloai.setText(item.getMaTL()+".");
            tvTentheloai=v.findViewById(R.id.tvTentheloaiSp);
            tvTentheloai.setText(item.getTenTL());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.theloai_item_spiner,null);
        }
        final Theloai item= lists.get(position);
        if(item!=null){
            tvMatheloai=v.findViewById(R.id.tvMatheloaiSp);
            tvMatheloai.setText(item.getMaTL()+".");
            tvTentheloai=v.findViewById(R.id.tvTentheloaiSp);
            tvTentheloai.setText(item.getTenTL());
        }
        return v;
    }
}
