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
import androidx.fragment.app.FragmentActivity;

import com.example.music.R;
import com.example.music.framment.Top10Fragment;
import com.example.music.model.TopTL;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<TopTL> {
    private Context context;
    Top10Fragment fragment;
    ArrayList<TopTL> list;
    TextView tvTl, tvSoluong;
    ImageView img;

    public TopAdapter(@NonNull Context context, Top10Fragment fragment, ArrayList<TopTL> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item,null);
        }
        final TopTL item= list.get(position);
        if(item!=null){
            tvTl=v.findViewById(R.id.tvTL);
            tvTl.setText("Thể Loại: "+item.getTenTL());
            tvSoluong=v.findViewById(R.id.tvSL);
            tvSoluong.setText("Số lượng"+item.getSoluong());
        }
        return v;
    }
}
