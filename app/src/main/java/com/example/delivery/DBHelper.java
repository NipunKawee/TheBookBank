package com.example.delivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "bookbank.db";
    public static final String TABLE_NAME11 = "delivery";
    public static final String TABLE_NAME12 = "Dstates";
    public static final String TABLE_NAME13 = "Feedback";

    public DBHelper(Context context) {

        super(context, "bookbank.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table delivery(username TEXT primary key, Location TEXT, ContactNumber TEXT)");
        MyDB.execSQL("create Table Dstates(username TEXT primary key, States TEXT)");
        MyDB.execSQL("create Table Feedback(Feeaback TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists delivery");
        MyDB.execSQL("drop Table if exists Dstates");
    }

    public Boolean Adddelivery(String username, String Location, String ContactNumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("Location", Location);
        contentValues.put("ContactNumber", ContactNumber);
        long result = MyDB.insert("delivery", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean AddStates(String username, String States){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("States", States);
        long result = MyDB.insert("Dstates", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean Feedback(String Feeaback){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Feeaback", Feeaback);
        long result = MyDB.insert("Feedback", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor viewDelivery() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME12+"  ",  null);
        return res;
    }

    public Integer DeleteStates (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME12, "username == ?",new String[] {username});
    }

    public boolean StatesUpdate(String username,String States) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("States",States);
        db.update(TABLE_NAME12, contentValues, "username = ?",new String[] {username});


        return true;


    }

    public Cursor ViewDeliveryList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME11+"  ",  null);
        return res;
    }

}
