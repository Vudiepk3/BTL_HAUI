package com.example.anassert.SinhVien;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.anassert.DBHelper;

public class SinhVienDAO {

    private DBHelper dbHelper;

    SQLiteDatabase db;

    public SinhVienDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(SinhVienObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSV",item.getMaSV());
        contentValues.put("IDTK",item.getIDTK());
        long res = db.insert("tblSinhVien",null,contentValues);
        return res;
    }

    public long update(int id, SinhVienObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSV",item.getMaSV());
        contentValues.put("IDTK",item.getIDTK());
        long res = db.update("tblSinhVien", contentValues,"ID=?",new String[]{String.valueOf(id)});
        return res;
    }

    public long delete(int id){
        long res = db.delete("tblSinhVien","ID=?",new String[]{String.valueOf(id)});
        if(res == -1 ) return 0;
        return 1;
    }
    @SuppressLint("Range")
    public SinhVienObject getSV(int idtk){

        // Câu truy vấn kiểm tra xem username có tồn tại không
        String query = "SELECT * FROM tblSinhVien WHERE IDTK = ?";
        String[] selectionArgs = {idtk+""};

        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query,selectionArgs);
//        while(cursor.moveToNext()){
//            add.
//        }
//        return list

        if (cursor.moveToFirst()) {
            // Truy cập dữ liệu từ Cursor
            int ID = cursor.getInt(cursor.getColumnIndex("ID"));
            String maSV = cursor.getString(cursor.getColumnIndex("maSV"));
            int IDTK = cursor.getInt(cursor.getColumnIndex("IDTK"));

            SinhVienObject sinhVien = new SinhVienObject(ID, maSV, IDTK);
            cursor.close();
            return sinhVien;
        }
        // Đóng Cursor
        cursor.close();
        return null;
    }
}
