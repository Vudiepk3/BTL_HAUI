package com.example.anassert.ThoiKhoaBieu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.anassert.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieuDAO {
    private DBHelper dbHelper;

    SQLiteDatabase db;

    public ThoiKhoaBieuDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThoiKhoaBieuObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("thu",item.getThu());
        contentValues.put("ngay",item.getNgay());
        contentValues.put("thongTinGiangVien",item.getThongTinGiangVien());
        contentValues.put("diaDiem",item.getDiaDiem());
        contentValues.put("phong",item.getPhong());
        contentValues.put("monHoc",item.getMonHoc());
        contentValues.put("caHoc",item.getCaHoc());
        long res = db.insert("tblThoiKhoaBieu",null,contentValues);
        return res;
    }
    public long update(int idtkb, ThoiKhoaBieuObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("thu",item.getThu());
        contentValues.put("ngay",item.getNgay());
        contentValues.put("thongTinGiangVien",item.getThongTinGiangVien());
        contentValues.put("diaDiem",item.getDiaDiem());
        contentValues.put("phong",item.getPhong());
        contentValues.put("monHoc",item.getMonHoc());
        contentValues.put("caHoc",item.getCaHoc());
        long res = db.update("tblThoiKhoaBieu", contentValues,"IDTKB=?",new String[]{String.valueOf(idtkb)});
        return res;
    }
    public long delete(int idtkb){
        long res = db.delete("tblThoiKhoaBieu","IDTKB=?",new String[]{String.valueOf(idtkb)});
        if(res == -1 ){
            return 0;
        }
        return 1;
    }
    public ArrayList<ThoiKhoaBieuObject> getAll(){
        String sql="SELECT * FROM tblThoiKhoaBieu";
        return (ArrayList<ThoiKhoaBieuObject>) getData(sql);
    }

    public ThoiKhoaBieuObject getIDTKB(String id){
        String sql = "SELECT * FROM tblThoiKhoaBieu WHERE IDTKB=?";
        List<ThoiKhoaBieuObject> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<ThoiKhoaBieuObject> getData(String sql, String...selectionArgs) {

        List<ThoiKhoaBieuObject> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThoiKhoaBieuObject obj = new ThoiKhoaBieuObject();
            obj.setIDTKB(Integer.parseInt(c.getString(c.getColumnIndex("IDTKB"))));
            obj.setThu(c.getString(c.getColumnIndex("thu")));
            obj.setNgay(c.getString(c.getColumnIndex("ngay")));
            obj.setThongTinGiangVien(c.getString(c.getColumnIndex("thongTinGiangVien")));
            obj.setPhong(c.getString(c.getColumnIndex("phong")));
            obj.setDiaDiem(c.getString(c.getColumnIndex("diaDiem")));
            obj.setCaHoc(c.getString(c.getColumnIndex("caHoc")));
            obj.setMonHoc(c.getString(c.getColumnIndex("monHoc")));
            list.add(obj);
        }
        return list;
    }

}
