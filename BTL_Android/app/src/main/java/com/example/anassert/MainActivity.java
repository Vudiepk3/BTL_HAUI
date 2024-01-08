package com.example.anassert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anassert.SinhVien.SinhVienDAO;
import com.example.anassert.SinhVien.SinhVienObject;

import BUITIENDUNG.ResultObject;
import BUITIENDUNG.RegistedSubjectObject;
//import BUITIENDUNG.Result;
import BUITIENDUNG.ResultActivity;
import LEMINHKHOI.Message;
import PHANHUUHIEU.ReportingAdvisor;
import VUMINHQUAN.StudyPlan;
import VUMINHQUAN.SuggestedSubjects;
import VUXUANDIEP.Document;
import VUXUANDIEP.Timetable;

public class MainActivity extends AppCompatActivity {
    SinhVienDAO sinhVienDAO;
    SinhVienObject getSV;
    ImageView back,imageTimetable,imageDocument,imageMessage,imageResult, btnReport,btnChat, btnEvaluate,imageStudyplane,imageSugsub,imageAnother,imgReport;
    Button btnLater1,btnLater2,btnLater3,btnLater4;
    ConstraintLayout logo,logo1;
    TextView txtUsername;
    private DBHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String username = getIntent().getStringExtra("hoTen");
        String role = getIntent().getStringExtra("role");
        int IDTK = Integer.parseInt(getIntent().getStringExtra("IDTK"));

        super.onCreate(savedInstanceState);
        if(role.equals("student")){
            setContentView(R.layout.activity_main_student);
            sinhVienDAO = new SinhVienDAO(this);
            getSV = sinhVienDAO.getSV(IDTK);
            getWidgetStudent();
            appStudent();
        }
        else{
            setContentView(R.layout.activity_main_teacher);
            getWidgetTeacher();
            appTeacher();
        }
        txtUsername.setText(username);
        getBack();

    }

    private void appTeacher() {
        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intentWarning  = new Intent(MainActivity.this, ReportingAdvisor.class);
                startActivity(intentWarning);
            }
        });
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));

                try {
                    startActivity(emailIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Handle case where no email app is available
                }
            }
        });
        imageAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sms();
            }
        });
        imageSugsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sms();
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImplicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.haui.edu.vn/vn"));
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

    private void appStudent() {
        //
        helper = new DBHelper(this);
        helper.getReadableDatabase();
        clickInsert();
        Intent  intent=getIntent();
        Integer gSV=intent.getIntExtra("idSinhVien",1);
        String maSV=gSV.toString();
        clickResult(maSV);
        //Timetable
        imageTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTimetbable = new Intent(MainActivity.this, Timetable.class);
                startActivity(intentTimetbable);
            }
        });
        //document
        imageDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDocument = new Intent(MainActivity.this, Document.class);
                startActivity(intentDocument);
            }
        });
        //mesage to teacher
        imageMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMesage = new Intent(MainActivity.this, Message.class);
                startActivity(intentMesage);
            }
        });
       //Study plane
        imageStudyplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStudyplane = new Intent(MainActivity.this, StudyPlan.class);
                intentStudyplane.putExtra("IDSV",getSV.getID()+"");
                startActivity(intentStudyplane);
            }

        });
        imageSugsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSugsub = new Intent(MainActivity.this, SuggestedSubjects.class);
                intentSugsub.putExtra("IDSV",getSV.getID()+"");
                startActivity(intentSugsub);
            }
        });
        imageAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sms();
            }
        });
        imgReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Chức năng đang trong quá trình phát triển", Toast.LENGTH_SHORT).show();
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImplicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.haui.edu.vn/vn"));
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

    private void getWidgetStudent() {
        back = findViewById(R.id.imageback);
        txtUsername = findViewById(R.id.txtUsername);
        imageMessage = findViewById(R.id.imageMessage);
        imageTimetable = findViewById(R.id.imageTimetable);
        imageDocument = findViewById(R.id.imageDocument);
        imageResult = findViewById(R.id.imageResult);
        imageStudyplane = findViewById(R.id.btnStudyplane);
        imageSugsub = findViewById(R.id.btnSuggestedsubjects);
        imageAnother = findViewById(R.id.imgAnother);
        imgReport = findViewById(R.id.imgReport);
        logo  = findViewById(R.id.logo);
        logo1  = findViewById(R.id.logo1);
    }

    private void getWidgetTeacher(){
        back = findViewById(R.id.imageback);
        txtUsername = findViewById(R.id.txtUsername);
        logo  = findViewById(R.id.logo);
        logo1  = findViewById(R.id.logo1);
        btnChat = findViewById(R.id.btnChat);
        btnEvaluate = findViewById(R.id.btnEvaluate);
        imageSugsub = findViewById(R.id.btnSuggestedsubjects);
        imageAnother = findViewById(R.id.imgAnother);
    }
    private void getBack(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder backRequest = new AlertDialog.Builder(MainActivity.this);
                backRequest.setTitle("Xác nhận đăng xuất");
                backRequest.setMessage("Bạn có chắc chắn muốn đăng xuất");
                backRequest.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent backLogin = new Intent(MainActivity.this,Login.class);
                        startActivity(backLogin);
                        finish();
                    }
                });
                backRequest.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                backRequest.create().show();
            }

        });
    }
    //Thông báo tính năng đang trong quá trình phát triển
    private void Sms() {
        Toast.makeText(MainActivity.this, "Tính năng đang trong quá trình phát triển", Toast.LENGTH_SHORT).show();
    }
    private void clickResult(String sv) {
        imageResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=sv;

                String select_all="SELECT tenMonDangKy,diemTongKetChu,kyHoc FROM tblMonDangKy  WHERE idSinhVien = "+s+"";
                String select1="SELECT tenMonDangKy,diemTongKetChu,kyHoc FROM tblMonDangKy  WHERE idSinhVien = "+s+" AND kyHoc = 1";
                String select22="SELECT tenMonDangKy,diemTongKetChu,kyHoc FROM tblMonDangKy  WHERE idSinhVien = "+s+" AND kyHoc = 2";
                String select3="SELECT tenMonDangKy,diemTongKetChu,kyHoc FROM tblMonDangKy  WHERE idSinhVien = "+s+" AND kyHoc = 3";
                String select4="SELECT tenMonDangKy,diemTongKetChu,kyHoc FROM tblMonDangKy  WHERE idSinhVien = "+s+" AND kyHoc = 4";
                String select5="SELECT tenMonDangKy,diemTongKetChu,kyHoc FROM tblMonDangKy  WHERE idSinhVien = "+s+" AND kyHoc = 5";
                String select6="SELECT tenMonDangKy,diemTongKetChu,kyHoc FROM tblMonDangKy  WHERE idSinhVien = "+s+" AND kyHoc = 6";

                String selectChiTiet= "SELECT " +
                        "mdk.tenMonDangKy, " +
                        "mdk.maMonDangKy, " +
                        "mdk.tinChi, " +
                        "mdk.trangThai, " +
                        "mdk.maLopMonHoc, " +
                        "dtp.diemTX1, " +
                        "dtp.diemTX2, " +
                        "dtp.diemGiuaKy, " +
                        "mdk.diemTongKetSo, " +
                        "mdk.diemTongKetChu, " +
                        "dtp.diemCuoiKy, " +
                        "mdk.phanTramTietNghi," +
                        "mdk.kyHoc " +
                        "FROM tblMonDangKy mdk " +
                        "LEFT JOIN tblDiemThanhPhan dtp ON mdk.maMonDangKy = dtp.maMonDangKy " +
                        "WHERE mdk.idSinhVien = "+s+" " +
                        "AND mdk.maMonDangKy IS NOT NULL";
                db=helper.getReadableDatabase();
                Cursor c=db.rawQuery(select_all,null);
                Cursor c1=db.rawQuery(select1,null);
                Cursor c22=db.rawQuery(select22,null);
                Cursor c3=db.rawQuery(select3,null);
                Cursor c4=db.rawQuery(select4,null);
                Cursor c5=db.rawQuery(select5,null);
                Cursor c6=db.rawQuery(select6,null);
                Cursor c2=db.rawQuery(selectChiTiet,null);

                RegistedSubjectObject[] arrMonDangKy_all=new RegistedSubjectObject[c.getCount()];
                RegistedSubjectObject[] arrMonDangKy1=new RegistedSubjectObject[c1.getCount()];
                RegistedSubjectObject[] arrMonDangKy2=new RegistedSubjectObject[c22.getCount()];
                RegistedSubjectObject[] arrMonDangKy3=new RegistedSubjectObject[c3.getCount()];
                RegistedSubjectObject[] arrMonDangKy4=new RegistedSubjectObject[c4.getCount()];
                RegistedSubjectObject[] arrMonDangKy5=new RegistedSubjectObject[c5.getCount()];
                RegistedSubjectObject[] arrMonDangKy6=new RegistedSubjectObject[c6.getCount()];
                ResultObject[] arrChiTiet=new ResultObject[c2.getCount()];
                int i=0,h=0,q=0,l=0,z=0;
                int a=0;
                while (c.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c.getString(c.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String diemTongKetChu=c.getString(c.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float kyHoc=c.getFloat(c.getColumnIndex("kyHoc"));
                    arrMonDangKy_all[i]= new RegistedSubjectObject(tenMonDangKy,diemTongKetChu,kyHoc);
                    i++;
                }
                while (c1.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c1.getString(c1.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String diemTongKetChu=c1.getString(c1.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float kyHoc=c1.getFloat(c1.getColumnIndex("kyHoc"));
                    arrMonDangKy1[h]= new RegistedSubjectObject(tenMonDangKy,diemTongKetChu,kyHoc);
                    h++;
                }
                while (c3.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c3.getString(c3.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String diemTongKetChu=c3.getString(c3.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float kyHoc=c3.getFloat(c3.getColumnIndex("kyHoc"));
                    arrMonDangKy3[q]= new RegistedSubjectObject(tenMonDangKy,diemTongKetChu,kyHoc);
                    q++;
                }
                while (c4.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c4.getString(c4.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String diemTongKetChu=c4.getString(c4.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float kyHoc=c4.getFloat(c4.getColumnIndex("kyHoc"));
                    arrMonDangKy4[l]= new RegistedSubjectObject(tenMonDangKy,diemTongKetChu,kyHoc);
                    l++;
                }
                while (c5.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c5.getString(c5.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String diemTongKetChu=c5.getString(c5.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float kyHoc=c5.getFloat(c5.getColumnIndex("kyHoc"));
                    arrMonDangKy5[z]= new RegistedSubjectObject(tenMonDangKy,diemTongKetChu,kyHoc);
                    z++;
                }
                int g=0;
                while (c6.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c6.getString(c6.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String diemTongKetChu=c6.getString(c6.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float kyHoc=c6.getFloat(c6.getColumnIndex("kyHoc"));
                    arrMonDangKy6[g]= new RegistedSubjectObject(tenMonDangKy,diemTongKetChu,kyHoc);
                    g++;
                }


                while (c22.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c22.getString(c22.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String diemTongKetChu=c22.getString(c22.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float kyHoc=c22.getFloat(c22.getColumnIndex("kyHoc"));
                    arrMonDangKy2[a]= new RegistedSubjectObject(tenMonDangKy,diemTongKetChu,kyHoc);
                    a++;
                }
                int j=0;
                while (c2.moveToNext()){
                    @SuppressLint("Range") String tenMonDangKy= c2.getString(c2.getColumnIndex("tenMonDangKy"));
                    @SuppressLint("Range") String maMonDangKy=c2.getString(c2.getColumnIndex("maMonDangKy"));
                    @SuppressLint("Range") String trangThai= c2.getString(c2.getColumnIndex("trangThai"));
                    @SuppressLint("Range") String maLopMonHoc=c2.getString(c2.getColumnIndex("maLopMonHoc"));
                    @SuppressLint("Range") Float diemTX1= c2.getFloat(c2.getColumnIndex("diemTX1"));
                    @SuppressLint("Range") Float tinChi= c2.getFloat(c2.getColumnIndex("tinChi"));
                    @SuppressLint("Range") Float diemTX2=c2.getFloat(c2.getColumnIndex("diemTX2"));
                    @SuppressLint("Range") Float diemGiuaKy= c2.getFloat(c2.getColumnIndex("diemGiuaKy"));
                    @SuppressLint("Range") Float diemTongKetSo=c2.getFloat(c2.getColumnIndex("diemTongKetSo"));
                    @SuppressLint("Range") String diemTongKetChu=c2.getString(c2.getColumnIndex("diemTongKetChu"));
                    @SuppressLint("Range") Float diemCuoiKy=c2.getFloat(c2.getColumnIndex("diemCuoiKy"));
                    @SuppressLint("Range") String phanTramTietNghi= c2.getString(c2.getColumnIndex("phanTramTietNghi"));
                    @SuppressLint("Range") Float kyHoc=c2.getFloat(c2.getColumnIndex("kyHoc"));
                    arrChiTiet[j]= new ResultObject(tenMonDangKy,maMonDangKy,tinChi,trangThai,maLopMonHoc,diemTX1,diemTX2,diemGiuaKy,diemTongKetSo,diemTongKetChu,diemCuoiKy,phanTramTietNghi,kyHoc);
                    j++;
                }

                Intent intent=new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("data_all",arrMonDangKy_all);
                intent.putExtra("data1",arrMonDangKy1);
                intent.putExtra("data2",arrMonDangKy2);
                intent.putExtra("data3",arrMonDangKy3);
                intent.putExtra("data4",arrMonDangKy4);
                intent.putExtra("data5",arrMonDangKy5);
                intent.putExtra("data6",arrMonDangKy6);
                intent.putExtra("data10",arrChiTiet);
                startActivity(intent);
            }
        });
    }
    private void clickInsert() {
        String insert="INSERT INTO tblMonDangKy(maMonDangKy, tenMonDangKy, trangThai, diemTongKetChu, diemTongKetSo, kyHoc, danhGiaHP, tinChiLyThuyet, tinChiThucHanh, tinChi,phanTramTietNghi,idSinhVien,maLopMonHoc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertDiemThanhPhan="INSERT INTO tblDiemThanhPhan(maDiemThanhPhan, diemTX1,diemTX2, diemGiuaKy,diemCuoiKy,maMonDangKy) VALUES (?, ?, ?, ?, ?, ?)";
        db=helper.getWritableDatabase();
        //sv05
        try{
            db.execSQL(insert,new String[]{"GT","Giải Tích","Đã Hoàn Thành","B+","9.1","1","Tốt","2.0","1.0","3.0","0%","5","LP001GT001"});
            db.execSQL(insert,new String[]{"THMLN","Triết Học Mác-Lênin","Học Lại","C","5.9","1","Trung Bình","3.0","0.0","3.0","25%","5","LP001THMLN006"});
            db.execSQL(insert,new String[]{"BR1","Bóng Rổ 1","Đã Hoàn Thành","D+","4.7","1","Trung Bình","2.0","1.0","3.0","10%","5","LP001GT004"});
            db.execSQL(insert,new String[]{"GTLVH","Giao Tiếp Liên Văn Hóa","Đã Hoàn Thành","B+","7.7","1","Tốt","2.0","1.0","3.0","0%","5","LP001GT001"});
            db.execSQL(insert,new String[]{"NMVKT","Nhập Môn Về Kĩ Thuật","Đã Hoàn Thành","A","8.5","1","Tốt","3.0","0.0","3.0","5%","5","LP001GT001"});
            db.execSQL(insert,new String[]{"TACNTTCB1","Tiếng Anh Công Nghệ Thông Tin Cơ Bản 1","Đã Hoàn Thành","B+","7.7","1","Tốt","3.0","0.0","3.0","10%","5","LP001GT002"});
            db.execSQL(insert,new String[]{"DSTT","Đại Số Tuyến Tính","Đã Hoàn Thành","B+","7.9","2","Tốt","2.0","1.0","3.0","5%","5","LP001GT007"});
            db.execSQL(insert,new String[]{"KTS","Kĩ Thuật Số","Đã Hoàn Thành","B+","7.7","2","Tốt","3.0","0.0","3.0","0%","5","LP001KTS003"});
            db.execSQL(insert,new String[]{"KTLT","Kĩ Thuật Lập Trình","Đã Hoàn Thành","A","8.7","2","Tốt","2.0","1.0","3.0","25%","5","LP001KTLT009"});
            db.execSQL(insert,new String[]{"KTCTMLN","Kinh Tế Chính Trị Mác-Lênin","Đã Hoàn Thành","C+","5.7","2","Trung Bình","3.0","0.0","3.0","10%","5","LP001KTCTMLN001"});
            db.execSQL(insert,new String[]{"MMT","Mạng Máy Tính","Đã Hoàn Thành","A","8.7","3","Tốt","3.0","0.0","3.0","0%","5","LP003MMT003"});
            db.execSQL(insert,new String[]{"PTTKPM","Phân Tích Thiết Kế Phần Mềm","Đã Hoàn Thành","C+","6.7","3","Tốt","2.0","1.0","3.0","5%","5","LP001PTTK009"});
            db.execSQL(insert,new String[]{"TTHCM","Tư Tưởng Hồ Chí Minh","Đã Hoàn Thành","D+","4.7","3","Trung Bình","3.0","0.0","3.0","10%","5","LP001TT001"});
            db.execSQL(insert,new String[]{"ANM","An Ninh Mạng","Đã Hoàn Thành","B","7","3","Tốt","2.0","1.0","3.0","5%","5","LP001AN001"});
            db.execSQL(insert,new String[]{"PTDA","Phát Triển Dự Áń","Đã Hoàn Thành","B+","7.7","3","Tốt","3.0","0.0","3.0","0%","5","LP001KSS003"});
            db.execSQL(insert,new String[]{"PT","Lập Trình PyThon","Đã Hoàn Thành","A","9.7","3","Tốt","2.0","1.0","3.0","25%","5","LP001KTLT009"});

            //sv04
            db.execSQL(insert,new String[]{"GT1","Giải Tích","Đã Hoàn Thành","B+","9.1","1","Tốt","2.0","1.0","3.0","0%","4","LP001GT001"});
            db.execSQL(insert,new String[]{"THMLN1","Triết Học Mác-Lênin","Học Lại","C","5.9","1","Trung Bình","3.0","0.0","3.0","25%","4","LP001THMLN006"});
            db.execSQL(insert,new String[]{"BR11","Bóng Rổ 1","Đã Hoàn Thành","D+","4.7","1","Trung Bình","2.0","1.0","3.0","10%","4","LP001GT004"});
            db.execSQL(insert,new String[]{"GTLVH1","Giao Tiếp Liên Văn Hóa","Đã Hoàn Thành","B+","7.7","1","Tốt","2.0","1.0","3.0","0%","4","LP001GT001"});
            db.execSQL(insert,new String[]{"NMVKT1","Nhập Môn Về Kĩ Thuật","Đã Hoàn Thành","A","8.5","1","Tốt","3.0","0.0","3.0","5%","4","LP001GT001"});
            db.execSQL(insert,new String[]{"TACNTTCB11","Tiếng Anh Công Nghệ Thông Tin Cơ Bản 1","Đã Hoàn Thành","B+","7.7","1","Tốt","3.0","0.0","3.0","10%","4","LP001GT002"});
            db.execSQL(insert,new String[]{"DSTT1","Đại Số Tuyến Tính","Đã Hoàn Thành","B+","7.9","2","Tốt","2.0","1.0","3.0","5%","4","LP001GT007"});
            db.execSQL(insert,new String[]{"KTS1","Kĩ Thuật Số","Đã Hoàn Thành","B+","7.7","2","Tốt","3.0","0.0","3.0","0%","4","LP001KTS003"});
            db.execSQL(insert,new String[]{"KTLT1","Kĩ Thuật Lập Trình","Đã Hoàn Thành","A","8.7","2","Tốt","2.0","1.0","3.0","25%","4","LP001KTLT009"});
            db.execSQL(insert,new String[]{"KTCTMLN1","Kinh Tế Chính Trị Mác-Lênin","Đã Hoàn Thành","C+","5.7","2","Trung Bình","3.0","0.0","3.0","10%","4","LP001KTCTMLN001"});
            db.execSQL(insert,new String[]{"MMT1","Mạng Máy Tính","Đã Hoàn Thành","A","8.7","3","Tốt","3.0","0.0","3.0","0%","4","LP003MMT003"});
            db.execSQL(insert,new String[]{"PTTKPM1","Phân Tích Thiết Kế Phần Mềm","Đã Hoàn Thành","C+","6.7","3","Tốt","2.0","1.0","3.0","5%","4","LP001PTTK009"});
            db.execSQL(insert,new String[]{"TTHCM1","Tư Tưởng Hồ Chí Minh","Đã Hoàn Thành","D+","4.7","3","Trung Bình","3.0","0.0","3.0","10%","4","LP001TT001"});
            db.execSQL(insert,new String[]{"ANM1","An Ninh Mạng","Đã Hoàn Thành","B","7","3","Tốt","2.0","1.0","3.0","5%","4","LP001AN001"});
            db.execSQL(insert,new String[]{"PTDA1","Phát Triển Dự Áń","Đã Hoàn Thành","B+","7.7","3","Tốt","3.0","0.0","3.0","0%","4","LP001KSS003"});
            db.execSQL(insert,new String[]{"PT1","Lập Trình PyThon","Đã Hoàn Thành","A","9.7","3","Tốt","2.0","1.0","3.0","25%","4","LP001KTLT009"});

            //sv01
            db.execSQL(insert,new String[]{"GT2","Giải Tích","Đã Hoàn Thành","B+","9.1","1","Tốt","2.0","1.0","3.0","0%","1","LP001GT001"});
            db.execSQL(insert,new String[]{"THMLN2","Triết Học Mác-Lênin","Học Lại","C","5.9","1","Trung Bình","3.0","0.0","3.0","25%","1","LP001THMLN006"});
            db.execSQL(insert,new String[]{"BR12","Bóng Rổ 1","Đã Hoàn Thành","D+","4.7","1","Trung Bình","2.0","1.0","3.0","10%","1","LP001GT004"});
            db.execSQL(insert,new String[]{"GTLVH2","Giao Tiếp Liên Văn Hóa","Đã Hoàn Thành","B+","7.7","1","Tốt","2.0","1.0","3.0","0%","1","LP001GT001"});
            db.execSQL(insert,new String[]{"NMVKT2","Nhập Môn Về Kĩ Thuật","Đã Hoàn Thành","A","8.5","1","Tốt","3.0","0.0","3.0","5%","1","LP001GT001"});
            db.execSQL(insert,new String[]{"TACNTTCB12","Tiếng Anh Công Nghệ Thông Tin Cơ Bản 1","Đã Hoàn Thành","B+","7.7","1","Tốt","3.0","0.0","3.0","10%","1","LP001GT002"});
            db.execSQL(insert,new String[]{"DSTT2","Đại Số Tuyến Tính","Đã Hoàn Thành","B+","7.9","2","Tốt","2.0","1.0","3.0","5%","1","LP001GT007"});
            db.execSQL(insert,new String[]{"KTS2","Kĩ Thuật Số","Đã Hoàn Thành","B+","7.7","2","Tốt","3.0","0.0","3.0","0%","1","LP001KTS003"});
            db.execSQL(insert,new String[]{"KTLT2","Kĩ Thuật Lập Trình","Đã Hoàn Thành","A","8.7","2","Tốt","2.0","1.0","3.0","25%","1","LP001KTLT009"});
            db.execSQL(insert,new String[]{"KTCTMLN2","Kinh Tế Chính Trị Mác-Lênin","Đã Hoàn Thành","C+","5.7","2","Trung Bình","3.0","0.0","3.0","10%","1","LP001KTCTMLN001"});
            db.execSQL(insert,new String[]{"MMT2","Mạng Máy Tính","Đã Hoàn Thành","A","8.7","3","Tốt","3.0","0.0","3.0","0%","1","LP003MMT003"});
            db.execSQL(insert,new String[]{"PTTKPM2","Phân Tích Thiết Kế Phần Mềm","Đã Hoàn Thành","C+","6.7","3","Tốt","2.0","1.0","3.0","5%","1","LP001PTTK009"});
            db.execSQL(insert,new String[]{"TTHCM2","Tư Tưởng Hồ Chí Minh","Đã Hoàn Thành","D+","4.7","3","Trung Bình","3.0","0.0","3.0","10%","1","LP001TT001"});
            db.execSQL(insert,new String[]{"ANM2","An Ninh Mạng","Đã Hoàn Thành","B","7","3","Tốt","2.0","1.0","3.0","5%","1","LP001AN001"});
            db.execSQL(insert,new String[]{"PTDA2","Phát Triển Dự Áń","Đã Hoàn Thành","B+","7.7","3","Tốt","3.0","0.0","3.0","0%","1","LP001KSS003"});
            db.execSQL(insert,new String[]{"PT2","Lập Trình PyThon","Đã Hoàn Thành","A","9.7","3","Tốt","2.0","1.0","3.0","25%","1","LP001KTLT009"});

            //sv02
            db.execSQL(insert,new String[]{"GT3","Giải Tích","Đã Hoàn Thành","B+","9.1","1","Tốt","2.0","1.0","3.0","0%","2","LP001GT001"});
            db.execSQL(insert,new String[]{"THMLN3","Triết Học Mác-Lênin","Học Lại","C","5.9","1","Trung Bình","3.0","0.0","3.0","25%","2","LP001THMLN006"});
            db.execSQL(insert,new String[]{"BR13","Bóng Rổ 1","Đã Hoàn Thành","D+","4.7","1","Trung Bình","2.0","1.0","3.0","10%","2","LP001GT004"});
            db.execSQL(insert,new String[]{"GTLVH3","Giao Tiếp Liên Văn Hóa","Đã Hoàn Thành","B+","7.7","1","Tốt","2.0","1.0","3.0","0%","2","LP001GT001"});
            db.execSQL(insert,new String[]{"NMVKT3","Nhập Môn Về Kĩ Thuật","Đã Hoàn Thành","A","8.5","1","Tốt","3.0","0.0","3.0","5%","2","LP001GT001"});
            db.execSQL(insert,new String[]{"TACNTTCB13","Tiếng Anh Công Nghệ Thông Tin Cơ Bản 1","Đã Hoàn Thành","B+","7.7","1","Tốt","3.0","0.0","3.0","10%","2","LP001GT002"});
            db.execSQL(insert,new String[]{"DSTT3","Đại Số Tuyến Tính","Đã Hoàn Thành","B+","7.9","2","Tốt","2.0","1.0","3.0","5%","2","LP001GT007"});
            db.execSQL(insert,new String[]{"KTS3","Kĩ Thuật Số","Đã Hoàn Thành","B+","7.7","2","Tốt","3.0","0.0","3.0","0%","2","LP001KTS003"});
            db.execSQL(insert,new String[]{"KTLT3","Kĩ Thuật Lập Trình","Đã Hoàn Thành","A","8.7","2","Tốt","2.0","1.0","3.0","25%","2","LP001KTLT009"});
            db.execSQL(insert,new String[]{"KTCTMLN3","Kinh Tế Chính Trị Mác-Lênin","Đã Hoàn Thành","C+","5.7","2","Trung Bình","3.0","0.0","3.0","10%","2","LP001KTCTMLN001"});
            db.execSQL(insert,new String[]{"MMT3","Mạng Máy Tính","Đã Hoàn Thành","A","8.7","3","Tốt","3.0","0.0","3.0","0%","2","LP003MMT003"});
            db.execSQL(insert,new String[]{"PTTKPM3","Phân Tích Thiết Kế Phần Mềm","Đã Hoàn Thành","C+","6.7","3","Tốt","2.0","1.0","3.0","5%","2","LP001PTTK009"});
            db.execSQL(insert,new String[]{"TTHCM3","Tư Tưởng Hồ Chí Minh","Đã Hoàn Thành","D+","4.7","3","Trung Bình","3.0","0.0","3.0","10%","2","LP001TT001"});
            db.execSQL(insert,new String[]{"ANM3","An Ninh Mạng","Đã Hoàn Thành","B","7","3","Tốt","2.0","1.0","3.0","5%","2","LP001AN001"});
            db.execSQL(insert,new String[]{"PTDA3","Phát Triển Dự Áń","Đã Hoàn Thành","B+","7.7","3","Tốt","3.0","0.0","3.0","0%","2","LP001KSS003"});
            db.execSQL(insert,new String[]{"PT3","Lập Trình PyThon","Đã Hoàn Thành","A","9.7","3","Tốt","2.0","1.0","3.0","25%","2","LP001KTLT009"});
///////////////
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPMMT","7.0","8.0","6.0","7.0","MMT"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTTKPM","4.0","9.0","5.0","7.0","PTTKPM"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTDA","6.0","10.0","9.0","7.0","PTDA"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPANM","9.0","10.0","10.0","4.0","ANM"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTTHCM","10.0","10.0","9.0","7.0","TTHCM"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPT","10.0","10.0","9.0","9.0","PT"});
            //====================
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGT","7.0","9.0","5.0","7.0","GT"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTHMLN","5.5","3.0","1.0","7.0","THMLN"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPBR1","7.0","9.0","9.0","7.0","BR1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGTLVH","9.0","9.0","6.0","7.0","GTLVH"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPNMVKT","7.0","9.0","5.0","9.0","NMVKT"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTACNTTCB1","7.0","9.0","5.0","8.0","TACNTTCB1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPDSTT","4.0","10.0","9.0","7.0","DSTT"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTS","9.0","10.0","10.0","5.0","KTS"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTLT","10.0","10.0","9.0","6,0","KTLT"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTCTMLN","10.0","10.0","9.0","7.0","KTCTMLN"});

            //
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPMMT1","7.0","8.0","6.0","7.0","MMT1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTTKPM1","4.0","9.0","5.0","7.0","PTTKPM1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTDA1","6.0","10.0","9.0","7.0","PTDA1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPANM1","9.0","10.0","10.0","4.0","ANM1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTTHCM1","10.0","10.0","9.0","7.0","TTHCM1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPT1","10.0","10.0","9.0","9.0","PT1"});
            //====================
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGT1","7.0","9.0","5.0","7.0","GT1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTHMLN1","5.5","3.0","1.0","7.0","THMLN1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPBR11","7.0","9.0","9.0","7.0","BR11"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGTLVH1","9.0","9.0","6.0","7.0","GTLVH1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPNMVKT1","7.0","9.0","5.0","9.0","NMVKT1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTACNTTCB11","7.0","9.0","5.0","8.0","TACNTTCB11"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPDSTT1","4.0","10.0","9.0","7.0","DSTT1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTS1","9.0","10.0","10.0","5.0","KTS1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTLT1","10.0","10.0","9.0","6,0","KTLT1"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTCTMLN1","10.0","10.0","9.0","7.0","KTCTMLN1"});
            //
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPMMT2","7.0","8.0","6.0","7.0","MMT2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTTKPM2","4.0","9.0","5.0","7.0","PTTKPM2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTDA2","6.0","10.0","9.0","7.0","PTDA2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPANM2","9.0","10.0","10.0","4.0","ANM2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTTHCM2","10.0","10.0","9.0","7.0","TTHCM2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPT2","10.0","10.0","9.0","9.0","PT2"});
            //====================
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGT2","7.0","9.0","5.0","7.0","GT2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTHMLN2","5.5","3.0","1.0","7.0","THMLN2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPBR12","7.0","9.0","9.0","7.0","BR12"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGTLVH2","9.0","9.0","6.0","7.0","GTLVH2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPNMVKT2","7.0","9.0","5.0","9.0","NMVKT2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTACNTTCB12","7.0","9.0","5.0","8.0","TACNTTCB12"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPDSTT2","4.0","10.0","9.0","7.0","DSTT2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTS2","9.0","10.0","10.0","5.0","KTS2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTLT2","10.0","10.0","9.0","6,0","KTLT2"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTCTMLN2","10.0","10.0","9.0","7.0","KTCTMLN2"});
            //
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPMMT3","7.0","8.0","6.0","7.0","MMT3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTTKPM3","4.0","9.0","5.0","7.0","PTTKPM3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPTDA3","6.0","10.0","9.0","7.0","PTDA3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPANM3","9.0","10.0","10.0","4.0","ANM3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTTHCM3","10.0","10.0","9.0","7.0","TTHCM3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPPT3","10.0","10.0","9.0","9.0","PT3"});
            //====================
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGT3","7.0","9.0","5.0","7.0","GT3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTHMLN3","5.5","3.0","1.0","7.0","THMLN3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPBR13","7.0","9.0","9.0","7.0","BR13"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPGTLVH3","9.0","9.0","6.0","7.0","GTLVH3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPNMVKT3","7.0","9.0","5.0","9.0","NMVKT3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPTACNTTCB13","7.0","9.0","5.0","8.0","TACNTTCB13"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPDSTT3","4.0","10.0","9.0","7.0","DSTT3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTS3","9.0","10.0","10.0","5.0","KTS3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTLT3","10.0","10.0","9.0","6,0","KTLT3"});
            db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTCTMLN3","10.0","10.0","9.0","7.0","KTCTMLN3"});
            //db.execSQL(insertDiemThanhPhan,new String[]{"DTPKTLT","10.0","10.0","9.0","KTLT"});

        }
        catch (Exception e){

        }
    }

}