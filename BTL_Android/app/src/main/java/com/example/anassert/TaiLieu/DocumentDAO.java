package com.example.anassert.TaiLieu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.anassert.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {
    private DBHelper dbHelper;

    SQLiteDatabase db;

    public DocumentDAO(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(DocumentObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHP",item.getMaHP());
        contentValues.put("monHoc",item.getMonHoc());
        contentValues.put("moTa",item.getMoTa());
        contentValues.put("URL",item.getURL());
        long res = db.insert("tblTaiLieu",null,contentValues);
        return res;
    }
    public long update(int id, DocumentObject item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHP",item.getMaHP());
        contentValues.put("monHoc",item.getMonHoc());
        contentValues.put("moTa",item.getMoTa());
        contentValues.put("URL",item.getURL());
        long res = db.update("tblTaiLieu", contentValues,"IDTL=?",new String[]{String.valueOf(id)});
        return res;
    }
    public long delete(int id){
        long res = db.delete("tblTaiLieu","IDTL=?",new String[]{String.valueOf(id)});
        if(res == -1 ){
            return 0;
        }
        return 1;
    }
    public ArrayList<DocumentObject> getAll(){
        String sql="SELECT * FROM tblTaiLieu";
        return (ArrayList<DocumentObject>) getData(sql);
    }

    public DocumentObject getIDTL(String id){
        String sql = "SELECT * FROM tblTaiLieu WHERE IDTL=?";
        List<DocumentObject> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<DocumentObject> getData(String sql, String...selectionArgs) {

        List<DocumentObject> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            DocumentObject obj = new  DocumentObject();
            obj.setIDTL(Integer.parseInt(c.getString(c.getColumnIndex("IDTL"))));
            obj.setMaHP(c.getString(c.getColumnIndex("maHP")));
            obj.setMonHoc(c.getString(c.getColumnIndex("monHoc")));
            obj.setMoTa(c.getString(c.getColumnIndex("moTa")));
            obj.setURL(c.getString(c.getColumnIndex("URL")));
            list.add(obj);
        }
        return list;
    }

}
