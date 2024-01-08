package com.example.anassert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import com.example.anassert.SinhVien.SinhVienDAO;
import com.example.anassert.SinhVien.SinhVienObject;
import com.example.anassert.TaiKhoan.TaiKhoanDAO;
import com.example.anassert.TaiKhoan.TaiKhoanObject;


public class Login extends AppCompatActivity {
    Button btn_Login,btnSignup,ok_btn;

    EditText editUsername, editPassword;
    TextView txtMiss;

    TaiKhoanDAO taiKhoanDAO;
    CheckBox chkLuuThongTin;
    String tenThongTinDangNhap="login";
    private ImageButton cancelButton;

    //
    SinhVienDAO sinhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        taiKhoanDAO = new TaiKhoanDAO(this);
        //
        sinhVienDAO = new SinhVienDAO(this);
        //
        getID();
        app();
    }

    private void app() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLoginState();
                if(editUsername.length() == 0 || editPassword.length() == 0){
                    Toast.makeText(Login.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    String username = editUsername.getText().toString().trim();
                    String password = editPassword.getText().toString().trim();
                    TaiKhoanObject user = taiKhoanDAO.Login(username,password);
                    if(user != null){
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        //
                        SinhVienObject svien=sinhVienDAO.getSV(user.getID());
                        //
                        Intent intentMain =  new Intent(Login.this,MainActivity.class);
                        intentMain.putExtra("IDTK",user.getID()+"");
                        intentMain.putExtra("hoTen",user.getHoTen());
                        intentMain.putExtra("role",user.getRole());
                        intentMain.putExtra("taiKhoan",user.getTaiKhoan());
                        intentMain.putExtra("idSinhVien",user.getID());
                        startActivity(intentMain);
                        finish();
                    }
                    else{
                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txtMiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View alertCustomDialog = LayoutInflater.from(Login.this).inflate(R.layout.custom_miss,null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);

                alertDialog.setView(alertCustomDialog);
                cancelButton = (ImageButton) alertCustomDialog.findViewById(R.id.cancelID);
                ok_btn = (Button) alertCustomDialog.findViewById(R.id.ok_btn_id);
                final  AlertDialog dialog = alertDialog.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        Toast.makeText(Login.this, "Cố lên.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void sms(){
        Toast.makeText(Login.this, "Chức năng đang trong quá trình phát triển", Toast.LENGTH_SHORT).show();
    }
    private void getID(){
        btn_Login =  findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        editUsername = findViewById(R.id.editUsername) ;
        editPassword = findViewById(R.id.editPassword);
        txtMiss  = findViewById(R.id.txtMiss);
        chkLuuThongTin = findViewById(R.id.chkLuuThongTin);
    }
    private void saveLoginState(){
        SharedPreferences preferences=getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("UserName", editUsername.getText().toString());
        editor.putString("PassWord", editPassword.getText().toString());
        editor.putBoolean("Save", chkLuuThongTin.isChecked());
        editor.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        String userName = preferences.getString("UserName","");
        String pass = preferences.getString("PassWord","");
        boolean save = preferences.getBoolean("Save",false);
        if (save) {
            editUsername.setText(userName);
            editPassword.setText(pass);
            chkLuuThongTin.setChecked(save);
        }
    }
}