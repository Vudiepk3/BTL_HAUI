package com.nhom3.sqliteapplication.Database;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "DemoSQLLite.DB";

    static final int dbVersion = 40;

    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createTablePERSONS = "create table PERSONS(" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Name TEXT NOT NULL," +
                    "Address TEXT NOT NULL," +
                    "Sex TEXT NOT NULL," +
                    "Phone TEXT NOT NULL," +
                    "Email TEXT NOT NULL," +
                    "Function TEXT NOT NULL," +
                    "Birthday DATE NOT NULL)";
            db.execSQL(createTablePERSONS);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
        try{
            String createTableRELAXS= "create table RELAXS(" +
                    "Relaxid INTEGER PRIMARY KEY," +
                    "Id INTEGER NOT NULL ," +
                    "Reason TEXT NOT NULL," +
                    "Day INTERGER NOT NULL," +
                    "Date DATE NOT NULL," +
                    "FOREIGN KEY(Id) REFERENCES PERSONS(Id))";
            db.execSQL(createTableRELAXS);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }

        try{
            String createTableSALARYS = "create table SALARYS(" +
                    "Salaryid INTEGER PRIMARY KEY ," +
                    "Id INTEGER NOT NULL,"+
                    "Ware INTERGER NOT NULL," +
                    "Bonus INTERGER NOT NULL," +
                    "Deduct INTERGER NOT NULL,"+
                    "Datein DATE NOT NULL," +
                    "FOREIGN KEY(Id) REFERENCES PERSONS(Id))";
            db.execSQL(createTableSALARYS);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }
        try{
            String createTableSALARYS = "create table SALARYS(" +
                    "Salaryid INTEGER PRIMARY KEY ," +
                    "Id INTEGER NOT NULL,"+
                    "Ware INTERGER NOT NULL," +
                    "Bonus INTERGER NOT NULL," +
                    "Deduct INTERGER NOT NULL,"+
                    "Datein DATE NOT NULL," +
                    "FOREIGN KEY(Id) REFERENCES PERSONS(Id))";
            db.execSQL(createTableSALARYS);
        }
        catch (Exception e){
            Log.e("Error","There are some problems!");
        }

        try{
            String LOGINS = "CREATE TABLE LOGINS" +
                        "(IDTK INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "Id INTEGER NOT NULL,"+
                        "Username TEXT NOT NULL,"+
                        "Password TEXT NOT NULL,"+
                        "Fullname TEXT NOT NULL,"+
                        "FOREIGN KEY(Id) REFERENCES PERSONS(Id))";
                db.execSQL(LOGINS);
            }
            catch (Exception e){
                Log.e("Error","There are some problems!");
            }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PERSONS");
        db.execSQL("DROP TABLE IF EXISTS RELAXS");
        db.execSQL("DROP TABLE IF EXISTS SALARYS");
        db.execSQL("DROP TABLE IF EXISTS LOGINS");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
