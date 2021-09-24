package com.example.bookshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "bookbank.db";
    public static final String TABLE_NAME = "BOOKREQ";

    public DBHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = "CREATE TABLE " + OrderContract.OrderEntry.TABLE_NAME1 + " ("
                + OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  OrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_QUANTITY + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_PRICE + " TEXT NOT NULL);";

        db.execSQL(SQL_TABLE);

        db.execSQL("create Table BOOKREQ(Username TEXT, Bookname TEXT, Author TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists BOOKREQ");
    }

    public Boolean insertData(String Username, String Bookname, String Author){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Username", Username);
        contentValues.put("Bookname", Bookname);
        contentValues.put("Author", Author);
        long result = MyDB.insert("BOOKREQ", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+"  ",  null);
        return res;
    }

    public Integer deleteData (String Username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Username = ?",new String[] {Username});
    }

    public boolean Updatedata(String Username,String bName,String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username",Username);
        contentValues.put("Bookname",bName);
        contentValues.put("Author",author);
        db.update(TABLE_NAME, contentValues, "Username = ?",new String[] {Username});

        return true;


    }
}
