package com.nhom3.sqliteapplication.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhom3.sqliteapplication.DTO.Relax;
import com.nhom3.sqliteapplication.DTO.Salary;
import com.nhom3.sqliteapplication.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {
    DbHelper dbHelper;
    SQLiteDatabase db ;
    public SalaryDAO(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Salary person){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Salaryid",person.getSalaryid());
        contentValues.put("Id",person.getId());
        contentValues.put("Ware",person.getWare());
        contentValues.put("Bonus",person.getBonus());
        contentValues.put("Deduct",person.getDeduct());
        contentValues.put("Datein",String.valueOf(person.getDatein()));
        long res = db.insert("SALARYS",null,contentValues);
        return res ;
    }

    public long update(Salary person){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ware",person.getWare());
        contentValues.put("Bonus",person.getBonus());
        contentValues.put("Deduct",person.getDeduct());
        contentValues.put("Datein",String.valueOf(person.getDatein()));
        long res = db.update("SALARYS",contentValues,"Salaryid=?",new String[]{person.getSalaryid()+""});
        return res ;
    }

    public int delete(int salaryid){
        long  check = db.delete("SALARYS","Salaryid=?",new String[]{String.valueOf(salaryid)});
        if(check==-1){
            return  0 ;
        }
        return 1 ;
    }

    public ArrayList<Salary> getAll(){
        String sql="SELECT * FROM SALARYS";
        return (ArrayList<Salary>) getData(sql);
    }

    public Salary getSalaryid(String salaryid){
        String sql = "SELECT * FROM SALARYS WHERE Salaryid=?";
        List<Salary> list = getData(sql,salaryid);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<Salary> getData(String sql, String...selectionArgs) {

        List<Salary> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Salary obj = new Salary();
            obj.setSalaryid(Integer.parseInt(c.getString(c.getColumnIndex("Salaryid"))));
            obj.setId(Integer.parseInt(c.getString(c.getColumnIndex("Id"))));
            obj.setWare(c.getString(c.getColumnIndex("Ware")));
            obj.setBonus(c.getString(c.getColumnIndex("Bonus")));
            obj.setDeduct(c.getString(c.getColumnIndex("Deduct")));
            obj.setDatein(c.getString(c.getColumnIndex("Datein")));
            list.add(obj);
        }
        return list;
    }

}
