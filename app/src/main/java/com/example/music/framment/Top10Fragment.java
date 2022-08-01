package com.example.music.framment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.music.R;
import com.example.music.adapter.TopAdapter;
import com.example.music.dao.ThongKeDAO;
import com.example.music.model.TopTL;

import java.util.ArrayList;

public class Top10Fragment extends Fragment {
    ListView lvtop;
    ArrayList<TopTL> list;
    TopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v=inflater.inflate(R.layout.fragment_top10, container, false);
        lvtop=v.findViewById(R.id.lvTop);
        ThongKeDAO thongKeDAO= new ThongKeDAO(getActivity());
        list= (ArrayList<TopTL>)thongKeDAO.getTOP();
        adapter= new TopAdapter(getActivity(),this,list);
        lvtop.setAdapter(adapter);
        return v;
    }
}