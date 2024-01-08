package com.example.anassert.LuuTruTaiLieu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.anassert.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class LuuTruTaiLieuDAO {
    private DBHelper dbHelper;

    SQLiteDatabase db;

    public LuuTruTaiLieuDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LuuTruTaiLieuObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHP",item.getMaHP());
        contentValues.put("monHoc",item.getMonHoc());
        contentValues.put("ngayLuu",item.getNgayLuu());
        contentValues.put("URL",item.getURL());
        long res = db.insert("tblLuuTruTaiLieu",null,contentValues);
        return res;
    }
    public long update(int id, LuuTruTaiLieuObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHP",item.getMaHP());
        contentValues.put("monHoc",item.getMonHoc());
        contentValues.put("ngayLuu",item.getNgayLuu());
        contentValues.put("URL",item.getURL());
        long res = db.update("tblLuuTruTaiLieu", contentValues,"ID=?",new String[]{String.valueOf(id)});
        return res;
    }
    public long delete(int id){
        long res = db.delete("tblLuuTrTaiLieu","ID=?",new String[]{String.valueOf(id)});
        if(res == -1 ){
            return 0;
        }
        return 1;
    }
    public ArrayList<LuuTruTaiLieuObject> getAll(){
        String sql="SELECT * FROM tblLuuTruTaiLieu";
        return (ArrayList<LuuTruTaiLieuObject>) getData(sql);
    }

    public LuuTruTaiLieuObject getID(String id){
        String sql = "SELECT * FROM tblLuuTruTaiLieu WHERE ID=?";
        List<LuuTruTaiLieuObject> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<LuuTruTaiLieuObject> getData(String sql, String...selectionArgs) {

        List<LuuTruTaiLieuObject> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LuuTruTaiLieuObject obj = new LuuTruTaiLieuObject();
            obj.setID(Integer.parseInt(c.getString(c.getColumnIndex("ID"))));
            obj.setMaHP(c.getString(c.getColumnIndex("maHP")));
            obj.setMonHoc(c.getString(c.getColumnIndex("monHoc")));
            obj.setNgayLuu(c.getString(c.getColumnIndex("ngayLuu")));
            obj.setURL(c.getString(c.getColumnIndex("URL")));
            list.add(obj);
        }
        return list;
    }


}
