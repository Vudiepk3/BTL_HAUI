package com.example.anassert.TaiKhoan;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.anassert.DBHelper;

public class TaiKhoanDAO {

    private DBHelper dbHelper;

    SQLiteDatabase db;

    public TaiKhoanDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(TaiKhoanObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("taiKhoan",item.getTaiKhoan());
        contentValues.put("matKhau",item.getMatKhau());
        contentValues.put("hoTen",item.getHoTen());
        contentValues.put("role",item.getRole());
        long res = db.insert("tblTaiKhoan",null,contentValues);
        return res;
    }

    public long update(int id, TaiKhoanObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("taiKhoan",item.getTaiKhoan());
        contentValues.put("matKhau",item.getMatKhau());
        contentValues.put("hoTen",item.getHoTen());
        contentValues.put("role",item.getRole());
        long res = db.update("tblTaiKhoan", contentValues,"ID=?",new String[]{String.valueOf(id)});
        return res;
    }

    public long delete(int id){
        long res = db.delete("tblTaiKhoan","ID=?",new String[]{String.valueOf(id)});
        if(res == -1 ) return 0;
        return 1;
    }
    @SuppressLint("Range")
    public TaiKhoanObject Login(String username, String password){

        // Câu truy vấn kiểm tra xem username có tồn tại không
        String query = "SELECT * FROM tblTaiKhoan WHERE taiKhoan = ? AND matKhau = ?";
        String[] selectionArgs = {username, password};

        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query,selectionArgs);
//        while(cursor.moveToNext()){
//            add.
//        }
//        return list

        if (cursor.moveToFirst()) {
            // Truy cập dữ liệu từ Cursor
            int ID = cursor.getInt(cursor.getColumnIndex("ID"));
            String taiKhoan = cursor.getString(cursor.getColumnIndex("taiKhoan"));
            String matKhau = cursor.getString(cursor.getColumnIndex("matKhau"));
            String hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            String role = cursor.getString(cursor.getColumnIndex("role"));

            TaiKhoanObject user = new TaiKhoanObject(ID,taiKhoan, matKhau, hoTen, role);
            cursor.close();
            return user;
        }
        // Đóng Cursor
        cursor.close();
        return null;
    }
}
