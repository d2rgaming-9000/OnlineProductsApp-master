package com.rajendra.onlineproductsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper{

	//other solution 1
	static final String DATABASE_NAME ="ProductDB.DB";
	static final int DATABASE_VERSION = 1;
	
	static final String DATABASE_TABLE = "USERS";
	static final String USER_ID = "_ID";
	static final String USER_NAME = "user_name";
	static final String USER_EMAIL = "email";
	static final String USER_PASSWORD = "pass";

	private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE +"( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_ID + "EMAIL, " + USER_EMAIL + "TEXT NOT NULL, " + USER_PASSWORD + " );";
	
	//other solution2
    public DBHelper(Context context)
    {
        super (context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    //solution 1
    db.execSQL(CREATE_DB_QUERY);
    
    //sol 2
        DB.execSQL("create Table Userdetails(usrname TEXT primary key, email TEXT, pass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        //sol 1
        db.execSQL (" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        
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

    public boolean updateuserdata(String usrname, String email, String pass)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usrname", usrname);
        contentValues.put("email", email);
        contentValues.put("pass", pass);


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
