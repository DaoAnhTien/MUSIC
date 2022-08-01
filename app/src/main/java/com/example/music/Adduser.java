package com.example.music;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.music.dao.NguoiDungDAO;
import com.example.music.model.NguoiDung;
import com.google.android.material.textfield.TextInputEditText;

public class Adduser extends AppCompatActivity {
    Button btnsave, btncancel;
    TextInputEditText edpass,edrepass;
    EditText eduser,edhoten;
    Toolbar toolbar;
    NguoiDungDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        btncancel=findViewById(R.id.btnCancleadduser);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Đăng kí người Dùng");
        btnsave=findViewById(R.id.btnsaveadduser);
        eduser=findViewById(R.id.edusernameadd);
        edhoten=findViewById(R.id.edhoTenadd);
        edpass=findViewById(R.id.edpassadd);
        edrepass=findViewById(R.id.edrepasadd);

        //
        dao= new NguoiDungDAO(getApplicationContext());
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eduser.setText("");
                edhoten.setText("");
                edpass.setText("");
                edrepass.setText("");
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguoiDung nguoiDung= new NguoiDung();
                nguoiDung.setMaND(eduser.getText().toString());
                nguoiDung.setTenND(edhoten.getText().toString());
                nguoiDung.setMatkhau(edpass.getText().toString());
                if(validate()>0){
                    if(dao.insert(nguoiDung)>0){
                        Toast.makeText(getApplicationContext(),"lưu thành công",Toast.LENGTH_SHORT).show();
                        eduser.setText("");
                        edhoten.setText("");
                        edpass.setText("");
                        edrepass.setText("");
                        Intent intent= new Intent(Adduser.this,LoginainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"lưu thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int validate(){
        int chek=1;
        if(eduser.getText().length()==0||edhoten.getText().length()==0||edpass.getText().length()==0||edrepass.getText().length()==0){
            Toast.makeText(getApplicationContext(), "Ban phai nhap du thong tin", Toast.LENGTH_SHORT).show();
            chek=-1;
        }else{
            String pass=edpass.getText().toString();
            String repass=edrepass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getApplicationContext(), "mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                chek=-1;
            }
        }
        return chek;
    }
    }
