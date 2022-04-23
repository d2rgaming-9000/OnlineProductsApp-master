package com.rajendra.onlineproductsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public  DBManager(Context ctx){
        context = ctx;
    }

    public  DBManager open() throws{
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        dbHelper.close();
    }

    public void inset(String username, String email, String pass){
	ContentValues contentValues = new ContentValues();
	ContentValues.put(DBHelper.USER_NAME, username);
	ContentValues.put(DBHelper.USER_PASSWORD, pass);
	databse.insert(DBHelper.DATABASE_TABLE, null, contentValues);
	
    }
    
    public Cursor fetch() {
    String [] columns = new String[] {
    
    	}
    }
}
