package com.nhom3.sqliteapplication.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhom3.sqliteapplication.DTO.Person;
import com.nhom3.sqliteapplication.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    DbHelper dbHelper;
    SQLiteDatabase db ;
    public PersonDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Person NHANVIEN){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", NHANVIEN.getName());
        contentValues.put("Phone", NHANVIEN.getPhone());
        contentValues.put("Address", NHANVIEN.getAddress());
        contentValues.put("Sex", NHANVIEN.getSex());
        contentValues.put("Email", NHANVIEN.getEmail());
        contentValues.put("Function", NHANVIEN.getFunction());
        contentValues.put("Birthday",String.valueOf(NHANVIEN.getBirthday()));
        long res = db.insert("PERSONS",null,contentValues);
        return res ;
    }

    public long update(Person NHANVIEN){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", NHANVIEN.getName());
        contentValues.put("Phone", NHANVIEN.getPhone());
        contentValues.put("Address", NHANVIEN.getAddress());
        contentValues.put("Sex", NHANVIEN.getSex());
        contentValues.put("Email", NHANVIEN.getEmail());
        contentValues.put("Function", NHANVIEN.getFunction());
        contentValues.put("Birthday",String.valueOf(NHANVIEN.getBirthday()));
        long res = db.update("PERSONS",contentValues,"Id=?",new String[]{NHANVIEN.getId()+""});
        return res ;
    }

    public int delete(int id){
        long  check = db.delete("PERSONS","Id=?",new String[]{String.valueOf(id)});
        if(check==-1){
            return  0 ;
        }
        return 1 ;
    }

    public ArrayList<Person> getAll(){
        String sql="SELECT * FROM PERSONS";
        return (ArrayList<Person>) getData(sql);
    }

    public Person getID(String id){
        String sql = "SELECT * FROM PERSONS WHERE Id=?";
        List<Person> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Person> getData(String sql, String...selectionArgs) {

        List<Person> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Person obj = new Person();
            obj.setId(Integer.parseInt(c.getString(c.getColumnIndex("Id"))));
            obj.setName(c.getString(c.getColumnIndex("Name")));
            obj.setPhone(c.getString(c.getColumnIndex("Phone")));
            obj.setAddress(c.getString(c.getColumnIndex("Address")));
            obj.setSex(c.getString(c.getColumnIndex("Sex")));
            obj.setEmail(c.getString(c.getColumnIndex("Email")));
            obj.setFunction(c.getString(c.getColumnIndex("Function")));
            obj.setBirthday(c.getString(c.getColumnIndex("Birthday")));

            list.add(obj);
        }
        return list;
    }


}

