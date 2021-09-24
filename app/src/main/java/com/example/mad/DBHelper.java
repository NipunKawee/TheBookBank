package com.example.mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "bookbank.db";
    public static final String TABLE_NAME1 = "users";
    public static final String TABLE_NAME2 = "admin";

    public DBHelper(Context context) {
        super(context, "bookbank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT, quantity INTEGER, Regfee INTEGER)");
        MyDB.execSQL("create Table admin(Uname TEXT primary key, Pword TEXT, email TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists admin");
    }

    public Boolean insertData(String username, String password, String quantity, String Regfee){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("quantity", quantity);
        contentValues.put("Regfee", Regfee);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertadminData(String Uname, String Pword, String email, String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Uname", Uname);
        contentValues.put("Pword", Pword);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        long result = MyDB.insert("admin", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkadminusername(String Uname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where Uname = ?", new String[]{Uname});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkadminusernamepassword(String Uname, String Pword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where Uname = ? and Pword = ?", new String[] {Uname,Pword});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean Updatedata(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("password",password);
        //db.rawQuery("UPDATE users SET password=? where username = ?", new String[] {password,username});
        db.update(TABLE_NAME1, contentValues, "username = ?",new String[] {username});

        //db.update(TABLE_NAME,contentValues,username+"= ?",new String[]{username});
        return true;


    }

    public boolean Updateadmindata(String Uname,String Pword,String email,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Uname",Uname);
        contentValues.put("Pword",Pword);
        contentValues.put("email",email);
        contentValues.put("phone",phone);
        db.update(TABLE_NAME2, contentValues, "Uname = ?",new String[] {Uname});


        return true;


    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME1+"  ",  null);
        return res;
    }

    public Integer deleteData (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "username == ?",new String[] {username});
    }

    public Cursor getadmindata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME2+"  ",  null);
        return res;
    }

    public Integer deleteadminData (String Uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "Uname == ?",new String[] {Uname});
    }

}