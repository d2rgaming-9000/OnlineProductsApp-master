package com.rajendra.onlineproductsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper{

	//other solution 2
	/*static final String DATABASE_NAME ="ProductDB.DB";
	static final int DATABASE_VERSION = 1;
	
	static final String DATABASE_TABLE = "USERS";
	static final String USER_ID = "_ID";
	static final String USER_NAME = "user_name";
	static final String USER_EMAIL = "email";
	static final String USER_PASSWORD = "pass";

	private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE +"( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_ID + "EMAIL, " + USER_EMAIL + "TEXT NOT NULL, " + USER_PASSWORD + " );";
*/
	//SOLUTION 3
    private Context context;
    private static final String DATABASE_NAME = "Ecommerce.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "product_type";
    private static final String COLUMN_SPECIFIER = "product_specifier";
    private static final String COLUMN_RANGE = "product_range";


    //other solution 1
    public DBHelper(Context context)
    {
        super (context, DATABASE_NAME, null, 1/* sol 1 "Userdata.db", null, 1 */);
   this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    //solution 2
    //DB.execSQL(CREATE_DB_QUERY);

    //sol 1
       // DB.execSQL("create Table Userdetails(usrname TEXT primary key, email TEXT, pass TEXT)");

    //sol 3 for items and products
        String query =
                "CREATE TABLE " +  TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TYPE + " TEXT, " +
                COLUMN_SPECIFIER + " TEXT, "
                + COLUMN_RANGE + " INTEGER);";
        DB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        //sol 1
        //db.execSQL (" DROP TABLE IF EXISTS " + DATABASE_TABLE);

        //SOL 2
        //DB.execSQL("drop Table if exists Userdetails");

        //sol 3
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);
    }

    //sol 3 continuance (items and products)
    void addItems(String type, String specifier, int range){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put (COLUMN_TYPE, type );
        cv.put (COLUMN_SPECIFIER,specifier );
        cv.put (COLUMN_RANGE, range );
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1 )
        {
            Toast.makeText(context, "failed to update items", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "items successfully updated", Toast.LENGTH_SHORT).show();
        }
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
