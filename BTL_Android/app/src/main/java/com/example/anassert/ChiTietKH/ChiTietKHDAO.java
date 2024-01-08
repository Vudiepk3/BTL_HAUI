package com.example.anassert.ChiTietKH;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.anassert.DBHelper;

import java.util.ArrayList;

public class ChiTietKHDAO {

    private DBHelper dbHelper;

    SQLiteDatabase db;

    public ChiTietKHDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ChiTietKHObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("addDate",item.getAddDate());
        contentValues.put("IDKH",item.getIDKH());
        contentValues.put("IDHP",item.getIDHP());
        long res = db.insert("tblChiTietKH",null,contentValues);
        return res;
    }

    public long update(int id, ChiTietKHObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("addDate",item.getAddDate());
        contentValues.put("IDKH",item.getIDKH());
        contentValues.put("IDHP",item.getIDHP());
        long res = db.update("tblChiTietKH", contentValues,"ID=?",new String[]{String.valueOf(id)});
        return res;
    }

    public long delete(int idkh){
        long res = db.delete("tblChiTietKH","IDKH=?",new String[]{String.valueOf(idkh)});
        if(res == -1 ) return 0;
        return 1;
    }

    public long deleteSubject(int idkh,int idhp){
        long res = db.delete("tblChiTietKH","IDKH=? AND IDHP=?",new String[]{String.valueOf(idkh),String.valueOf(idhp)});
        if(res == -1 ) return 0;
        return 1;
    }

    @SuppressLint("Range")
    public ArrayList<ChiTietKHObject> getHP (){
        ArrayList<ChiTietKHObject> listChiTietHP = new ArrayList<>();
        // Câu truy vấn kiểm tra xem username có tồn tại không
        String query = "SELECT * FROM tblChiTietKH";

        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query,null);

// Duyệt qua dữ liệu trong Cursor
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Lấy dữ liệu từ cột cần thiết
                int ID = cursor.getInt(cursor.getColumnIndex("ID"));
                String addDate = cursor.getString(cursor.getColumnIndex("addDate"));
                int IDKH = cursor.getInt(cursor.getColumnIndex("IDKH"));
                int IDHP = cursor.getInt(cursor.getColumnIndex("IDHP"));
                ChiTietKHObject newChiTietKH = new ChiTietKHObject(ID, addDate,IDKH,IDHP);
                listChiTietHP.add(newChiTietKH);
                // Thực hiện xử lý với dữ liệu lấy được
            } while (cursor.moveToNext());

            // Đóng Cursor khi không sử dụng nữa
            cursor.close();
        }

        return listChiTietHP;
    }
}
