package com.example.anassert.HocPhan;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.anassert.DBHelper;

import java.util.ArrayList;

public class HocPhanDAO {

    private DBHelper dbHelper;

    SQLiteDatabase db;

    public HocPhanDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HocPhanObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHP",item.getMaHP());
        contentValues.put("tenHP",item.getTenHP());
        contentValues.put("soTin",item.getSoTin());
        contentValues.put("loaiHP",item.getLoaiHP());
        contentValues.put("tieuChi",item.getTieuChi());
        contentValues.put("hocKy",item.getHocKy());
        long res = db.insert("tblHocPhan",null,contentValues);
        return res;
    }

    public long update(int id, HocPhanObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHP",item.getMaHP());
        contentValues.put("tenHP",item.getTenHP());
        contentValues.put("soTin",item.getSoTin());
        contentValues.put("loaiHP",item.getLoaiHP());
        contentValues.put("tieuChi",item.getTieuChi());
        contentValues.put("hocKy",item.getHocKy());
        long res = db.update("tblHocPhan", contentValues,"ID=?",new String[]{String.valueOf(id)});
        return res;
    }

    public long delete(int id){
        long res = db.delete("tblHocPhan","ID=?",new String[]{String.valueOf(id)});
        if(res == -1 ) return 0;
        return 1;
    }

    @SuppressLint("Range")
    public ArrayList<HocPhanObject> getHP (){
        ArrayList<HocPhanObject> listHP = new ArrayList<>();
        // Câu truy vấn kiểm tra xem username có tồn tại không
        String query = "SELECT * FROM tblHocPhan";

        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query,null);

// Duyệt qua dữ liệu trong Cursor
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy dữ liệu từ cột cần thiết
                int ID = cursor.getInt(cursor.getColumnIndex("ID"));
                String maHP = cursor.getString(cursor.getColumnIndex("maHP"));
                String tenHP = cursor.getString(cursor.getColumnIndex("tenHP"));
                double soTin = cursor.getDouble(cursor.getColumnIndex("soTin"));
                String loaiHP = cursor.getString(cursor.getColumnIndex("loaiHP"));
                String tieuChi = cursor.getString(cursor.getColumnIndex("tieuChi"));
                int hocKy = cursor.getInt(cursor.getColumnIndex("hocKy"));
                HocPhanObject newHP = new HocPhanObject(ID,maHP,tenHP, (float) soTin,loaiHP,tieuChi,hocKy);
                listHP.add(newHP);
                // Thực hiện xử lý với dữ liệu lấy được
            } while (cursor.moveToNext());

            // Đóng Cursor khi không sử dụng nữa
            cursor.close();
        }

        return listHP;
    }


    @SuppressLint("Range")
    public ArrayList<HocPhanObject> getKH (int idkh){
        ArrayList<HocPhanObject> listHP = new ArrayList<>();
        // Câu truy vấn kiểm tra xem username có tồn tại không
        String query = "SELECT tblHocPhan.ID,tblHocPhan.maHP,tblHocPhan.tenHP,tblHocPhan.soTin,tblHocPhan.loaiHP,tblHocPhan.tieuChi,tblHocPhan.hocKy FROM tblHocPhan JOIN tblChiTietKH ON tblHocPhan.ID = tblChiTietKH.IDHP "+
                                                "JOIN tblKH ON tblChiTietKH.IDKH = tblKH.ID "+
                        "WHERE tblChiTietKH.IDKH = ?;";
        String selectionArgs[] = {idkh+""};
        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query,selectionArgs);

// Duyệt qua dữ liệu trong Cursor
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy dữ liệu từ cột cần thiết
                int ID = cursor.getInt(cursor.getColumnIndex("ID"));
                String maHP = cursor.getString(cursor.getColumnIndex("maHP"));
                String tenHP = cursor.getString(cursor.getColumnIndex("tenHP"));
                double soTin = cursor.getDouble(cursor.getColumnIndex("soTin"));
                String loaiHP = cursor.getString(cursor.getColumnIndex("loaiHP"));
                String tieuChi = cursor.getString(cursor.getColumnIndex("tieuChi"));
                int hocKy = cursor.getInt(cursor.getColumnIndex("hocKy"));
                HocPhanObject newHP = new HocPhanObject(ID,maHP,tenHP, (float) soTin,loaiHP,tieuChi,hocKy);
                listHP.add(newHP);
                // Thực hiện xử lý với dữ liệu lấy được
            } while (cursor.moveToNext());

            // Đóng Cursor khi không sử dụng nữa
            cursor.close();
        }

        return listHP;
    }
}
