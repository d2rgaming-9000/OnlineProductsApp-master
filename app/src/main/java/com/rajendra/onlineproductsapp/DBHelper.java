package com.rajendra.onlineproductsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context)
    {
        super (context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(usrname TEXT primary key, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertuserdata(String usrname, String email, String pass)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usrname", usrname);
        contentValues.put("email", email);
        contentValues.put("pass", pass);

            long result = DB.update("Userdetails", contentValues, "usrname=?", new String[]{usrname});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
    }

    public boolean updateuserdata(String usrname, String email)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usrname", usrname);
        contentValues.put("email", email);
        Cursor cursor = DB.rawQuery("select * from Userdetails where name = ?", new String[]{usrname});

        if(cursor.getCount()>0) {

            long result = DB.update("Userdetails", contentValues, "usrname=?", new String[]{usrname});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else {return false;}
    }

        public boolean deleteuserdata(String usrname)
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("select * from Userdetails where name = ?", new String[]{usrname});

            if(cursor.getCount()>0) {

                long result = DB.delete("Userdetails", "usrname=?", new String[]{usrname});
                if (result == 1) {
                    return false;
                } else {
                    return true;
                }
            }else {return false;}
        }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from Userdetails", null);
        return cursor;
    }
}
