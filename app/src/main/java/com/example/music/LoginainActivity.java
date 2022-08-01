package com.example.music;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.music.dao.NguoiDungDAO;

public class LoginainActivity extends AppCompatActivity {
    EditText edUsername,edPassword;
    Button btnLogin, btncancel;
    CheckBox chekrememberPass;
    String strUser, strPass;
    NguoiDungDAO dao;
    ImageButton imgdk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginain);
        edUsername=findViewById(R.id.edUsername);
        edPassword=findViewById(R.id.edPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btncancel=findViewById(R.id.btnCancel);
        imgdk=findViewById(R.id.imgdk);
        chekrememberPass=findViewById(R.id.chkRememberPass);
        dao = new NguoiDungDAO(this);

        //dao user naem va pass word trong ShaerdPre
        SharedPreferences pref =  getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String User= pref.getString("USERNAME","");
        String Pass=pref.getString("PASSWORD","");
        Boolean rem= pref.getBoolean("REMEMBER",false);

        edUsername.setText(User);
        edPassword.setText(Pass);
        chekrememberPass.setChecked(rem);

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUsername.setText("");
                edPassword.setText("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chekLogin();
            }
        });
        imgdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginainActivity.this, Adduser.class);
                startActivity(intent);
            }
        });


    }
    public  void RememberUser(String u, String p, boolean status){
        SharedPreferences pref =  getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit= pref.edit();
        if(!status){
            // xoa tinh trang truoc do
            edit.clear();

        }else{
            //luu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();

    }
    public void chekLogin(){
        strUser= edUsername.getText().toString();
        strPass=edPassword.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"không được để trống ten đăng nhập và password",Toast.LENGTH_SHORT).show();
        }else {
            if((dao.chekLogin(strUser,strPass)>0)){
                Toast.makeText(getApplicationContext(),"Login thành công",Toast.LENGTH_SHORT).show();
                RememberUser(strUser,strPass,chekrememberPass.isChecked());
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Login không thành công, tên đăng nhập và mật khẩu không đúng                                                                                                                                            ",Toast.LENGTH_SHORT).show();
            }
        }
    }

}