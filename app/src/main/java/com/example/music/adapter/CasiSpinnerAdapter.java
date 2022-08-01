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
import com.example.music.model.Casi;
import com.example.music.model.Theloai;

import java.util.ArrayList;

public class CasiSpinnerAdapter extends ArrayAdapter<Casi> {
    Context context;
    ArrayList<Casi> lists;
    TextView tvMaCS, tvTenCS;
    public CasiSpinnerAdapter(@NonNull Context context, ArrayList<Casi> lists) {
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
            v=inflater.inflate(R.layout.casi_item_spiner,null);
        }
        final Casi item= lists.get(position);
        if(item!=null){
            tvMaCS=v.findViewById(R.id.tvmaCS);
            tvMaCS.setText(item.getMaCasi()+".");
            tvTenCS=v.findViewById(R.id.tvTenCS);
            tvTenCS.setText(item.getTenCS());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.casi_item_spiner,null);
        }
        final Casi item= lists.get(position);
        if(item!=null){
            tvMaCS=v.findViewById(R.id.tvmaCS);
            tvMaCS.setText(item.getMaCasi()+".");
            tvTenCS=v.findViewById(R.id.tvTenCS);
            tvTenCS.setText(item.getTenCS());
        }
        return v;
    }
}
