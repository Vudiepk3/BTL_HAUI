package com.example.anassert;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    static final String dbName = "CoVanVaDieuPhoiHP.db";

    static final int dbVersion = 1;

    public DBHelper(Context context){
        super(context,dbName,null,dbVersion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            createTableTaiKhoan(db);
            createTableSinhVien(db);
            createTableHocPhan(db);
            createTableKH(db);
            createTableChiTietKH(db);
            createTableCVHT(db);
            createtblCVHT_HP(db);
            createTableThoiKhoaBieu(db);
            createTableTaiLieu(db);
            createTableLuuTruTaiLieu(db);
            //
            createTableMonDangKy(db);
        createtableDiemThanhPhan(db);
    }

    private void createTableKH(SQLiteDatabase db) {
        try{
            String tblKH = "CREATE TABLE tblKH" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "updateDate TEXT,"+
                    "IDSV INTEGER NOT NULL,"+
                    "FOREIGN KEY (IDSV) REFERENCES tblSinhVien(ID));";
            db.execSQL(tblKH);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }

    private void createTableSinhVien(SQLiteDatabase db) {
        try{
            String tblSinhVien = "CREATE TABLE tblSinhVien" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "maSV TEXT NOT NULL,"+
                    "IDTK INTEGER NOT NULL,"+
                    "FOREIGN KEY (IDTK) REFERENCES tblTaiKhoan(ID));";
            db.execSQL(tblSinhVien);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }

    private void createTableTaiKhoan(SQLiteDatabase db){
        try{
            String tblTaiKhoan = "CREATE TABLE tblTaiKhoan" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "taiKhoan TEXT NOT NULL,"+
                    "matKhau TEXT NOT NULL,"+
                    "hoTen TEXT NOT NULL,"+
                    "role TEXT NOT NULL);";
            db.execSQL(tblTaiKhoan);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }

    private void createTableHocPhan(SQLiteDatabase db){
        try{
            String tblHocPhan = "CREATE TABLE tblHocPhan" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "maHP TEXT NOT NULL,"+
                    "tenHP TEXT NOT NULL,"+
                    "soTin REAL NOT NULL,"+
                    "loaiHP TEXT NOT NULL,"+
                    "tieuChi TEXT NOT NULL,"+
                    "hocKy INTEGER NOT NULL);";
            db.execSQL(tblHocPhan);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }

    private void createTableChiTietKH(SQLiteDatabase db){
        try{
            String tblChiTietKH = "CREATE TABLE tblChiTietKH" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "addDate TEXT,"+
                    "IDKH INTEGER,"+
                    "IDHP INTEGER,"+
                    "FOREIGN KEY (IDKH) REFERENCES tblKH(ID),"+
                    "FOREIGN KEY (IDHP) REFERENCES tblHocPhan(ID));";
            db.execSQL(tblChiTietKH);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }

    //Bảng Cố vấn học tập
    private void createTableCVHT(SQLiteDatabase db){
        try{
            String tblCVHT = "CREATE TABLE tblCVHT" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "FullName TEXT,"+
                    "PhoneNumber TEXT,"+
                    "Email TEXT);";
            db.execSQL(tblCVHT);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }
    private void createtblCVHT_HP(SQLiteDatabase db){
        try{
            String tblCvhtHp = "CREATE TABLE tblCvhtHp" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "CvhtID INTEGER,"+
                    "HpId INTEGER);";
            db.execSQL(tblCvhtHp);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }

    //Vũ Xuân Điệp table Thời khoá biểu
    private void createTableThoiKhoaBieu(SQLiteDatabase db){
        try{
            String tblThoiKhoaBieu = "CREATE TABLE tblThoiKhoaBieu" +
                    "(IDTKB INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "monHoc TEXT NOT NULL,"+
                    "thu DATE NOT NULL,"+
                    "ngay DATE NOT NULL,"+
                    "thongTinGiangVien TEXT NOT NULL,"+
                    "phong TEXT NOT NULL,"+
                    "diaDiem TEXT NOT NULL,"+
                    "caHoc TEXT NOT NULL);";
            db.execSQL(tblThoiKhoaBieu);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }
    private void createTableTaiLieu(SQLiteDatabase db){
        try{
            String tblTaiLieu = "CREATE TABLE tblTaiLieu" +
                    "(IDTL INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "maHP TEXT NOT NULL,"+
                    "monHoc TEXT NOT NULL,"+
                    "URL TEXT NOT NULL,"+
                    "moTa TEXT NOT NULL);";
            db.execSQL(tblTaiLieu);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }
    private void createTableLuuTruTaiLieu(SQLiteDatabase db){
        try{
            String tblLuuTruTaiLieu = "CREATE TABLE tblLuuTruTaiLieu" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "maHP TEXT NOT NULL,"+
                    "monHoc TEXT NOT NULL,"+
                    "URL TEXT NOT NULL,"+
                    "ngayLuu DATE NOT NULL);";
            db.execSQL(tblLuuTruTaiLieu);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }
    ///BuiTienDung
    private void createTableMonDangKy(SQLiteDatabase db){
        try{
            String createtableMonDangKy= "CREATE TABLE tblMonDangKy ("+
                    "maMonDangKy text primary key,"+
                    "tenMonDangKy text,"+
                    "trangThai text,"+
                    "diemTongKetChu text,"+
                    "diemTongKetSo float,"+
                    "kyHoc float,"+
                    "danhGiaHP text,"+
                    "tinChiLyThuyet float,"+
                    "tinChiThucHanh float,"+
                    "tinChi float,"+
                    "phanTramTietNghi text,"+
                    "idSinhVien integer REFERENCES tblSinhVien(ID),"+
                    "maLopMonHoc text"+
                    ")";
            db.execSQL(createtableMonDangKy);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }
    private void createtableDiemThanhPhan(SQLiteDatabase db){
        try{
            String createtableDiemThanhPhan= "CREATE TABLE tblDiemThanhPhan ("+
                    "maDiemThanhPhan text primary key,"+
                    "diemTX1 float,"+
                    "diemTX2 float,"+
                    "diemGiuaKy float,"+
                    "diemCuoiKy float,"+
                    "maMonDangKy text REFERENCES tblMonDangKy(maMonDangKy)"+
                    ")";
            db.execSQL(createtableDiemThanhPhan);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblTaiKhoan");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
