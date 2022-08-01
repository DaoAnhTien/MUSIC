package com.example.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.music.dao.NguoiDungDAO;
import com.example.music.framment.CasiFragment;
import com.example.music.framment.ChangePassFragment;
import com.example.music.framment.NhacFragment;
import com.example.music.framment.TheloaiFragment;
import com.example.music.framment.Top10Fragment;
import com.example.music.model.NguoiDung;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mheaderview;
    NguoiDungDAO NguoiDungDAO;
    TextView edUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar1);
        drawer = findViewById(R.id.draw_layout);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        NavigationView nv= findViewById(R.id.nvView);
        mheaderview=nv.getHeaderView(0);
        edUser=mheaderview.findViewById(R.id.txtUser);
        Intent i=getIntent();
        String user =i.getStringExtra("user");
        NguoiDungDAO = new NguoiDungDAO(getBaseContext());
        NguoiDung nguoiDung= NguoiDungDAO.getID(user);
        String username=nguoiDung.getTenND();
        edUser.setText("Wellcome: "+username+" ! ") ;
        FragmentManager fragmentManager=getSupportFragmentManager();
        NhacFragment nhacFragment= new NhacFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.flcontent,nhacFragment)
                .commit();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_theloai:
                        setTitle("Thể Loại");
                        TheloaiFragment theloaiFragment= new TheloaiFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,theloaiFragment)
                                .commit();

                        break;
                    case R.id.nav_nhac:
                        setTitle("Nhạc");
                        NhacFragment nhacFragment= new NhacFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,nhacFragment)
                                .commit();
                        break;
                    case R.id.nav_casi:
                        setTitle("Ca sĩ");
                        CasiFragment casiFragment= new CasiFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,casiFragment)
                                .commit();

                        break;

                    case R.id.Sub_nghemhieu:
                        setTitle("nghe Nhiều Nhất");
                        Top10Fragment top10Fragment= new Top10Fragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,top10Fragment   )
                                .commit();
                        break;
                    case R.id.Sub_Pass:
                        setTitle("Đỏi mật Khẩu");
                        ChangePassFragment changePassFragment= new ChangePassFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,changePassFragment   )
                                .commit();
                        break;
                    case R.id.Sub_logout:
                        Intent i= new Intent(MainActivity.this,LoginainActivity.class);
                        startActivity(i);
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id=item.getItemId();
        if(id== android.R.id.home)
        drawer.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}