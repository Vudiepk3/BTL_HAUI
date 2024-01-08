package com.nhom3.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;


import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView back,image1,image2,image3,image4,imageBell;
    ConstraintLayout logo,logo1;
    TextView txtUsername;
    String tenThongTinDangNhap="login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getID();
        getUsername();
        app();
    }
    private void getID(){
        back = findViewById(R.id.imageback);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        txtUsername = findViewById(R.id.txtUsername);
        logo  = findViewById(R.id.logo);
        logo1  = findViewById(R.id.logo1);
        imageBell = findViewById(R.id.imageBell);
    }
    private void getUsername(){
        String username = getIntent().getStringExtra("fullname");
        txtUsername.setText(username);
    }
    private void app(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent backLogin = new Intent(MainActivity.this,Login.class);
                        startActivity(backLogin);
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent information = new Intent(MainActivity.this, PersonMain.class);
                startActivity(information);

            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent information = new Intent(MainActivity.this, SalaryMain.class);
                startActivity(information);

            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent information = new Intent(MainActivity.this, RelaxMain.class);
                startActivity(information);

            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sms();
            }
        });
        imageBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sms();
            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImplicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/46lQ8G9"));
                startActivity(intentImplicit);
            }
        });
        logo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImplicit1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fit.haui.edu.vn/vn"));
                startActivity(intentImplicit1);
            }
        });
    }
    //Thông báo tính năng đang trong quá trình phát triển
    private void Sms() {
        Toast.makeText(MainActivity.this, "Tính năng đang trong quá trình phát triển", Toast.LENGTH_SHORT).show();
    }
    private void saveLoginState(){
        SharedPreferences preferences=getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String username = getIntent().getStringExtra("key_username");
        editor.putString("UserName",username );
        editor.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        String username = getIntent().getStringExtra("key_username");
        String userName = preferences.getString("UserName",username);
        txtUsername.setText(userName);
    }
}