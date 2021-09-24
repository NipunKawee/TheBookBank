package com.example.thebookbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "bookbank.db";

    public static final String TABLE_NAME = "payment_table";
    public static final String COL_1 = "MemberID";
    public static final String COL_2 = "ContactNo";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Amount";

    public static final String TABLE_NAME1 = "carddetails_table";
    public static final String COL_5 = "CardNo";
    public static final String COL_6 = "ExpDate";
    public static final String COL_7 = "Code";
    public static final String COL_8 = "Amountt";

    public static final String TABLE_NAME2 = "feedback_table";
    public static final String COL_9 = "Name";
    public static final String COL_10 = "Feedback";

    private String TABLE_CREATE_NAME = "create table " + TABLE_NAME + " (" +
            COL_1 + " TEXT PRIMARY KEY," +
            COL_2 + " TEXT," +
            COL_3 + " TEXT," +
            COL_4 + " REAL)";

    private String TABLE_CREATE_NAME1 = "create table " + TABLE_NAME1 + " (" +
            COL_5 + " TEXT PRIMARY KEY," +
            COL_6 + " TEXT," +
            COL_7 + " TEXT," +
            COL_8 + " REAL)";

    private String TABLE_CREATE_NAME2 = "create table " + TABLE_NAME2 + " (" +
            COL_9 + " TEXT," +
            COL_10 + " TEXT)";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }





    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("create table " + TABLE_NAME +" (MemberID TEXT PRIMARY KEY ,ContactNo TEXT,Email TEXT,Amount REAL)");
        //db.execSQL("create table " + TABLE_NAME1 +" (CardNo TEXT PRIMARY KEY ,ExpDate TEXT,Code TEXT,Amountt REAL)");
        db.execSQL(TABLE_CREATE_NAME);
        db.execSQL(TABLE_CREATE_NAME1);
        db.execSQL(TABLE_CREATE_NAME2);



    }




    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }

   public boolean insertData(String memberid, String contactno, String email, String amount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,memberid);
        contentValues.put(COL_2,contactno);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,amount);
        long result =  db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public boolean insertdata(String name, String feedback){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,name);
        contentValues.put(COL_10,feedback);
        long result =  db.insert(TABLE_NAME2, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
  /*  public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select memberid,contactno,email,amount from "+TABLE_NAME+ " where MemberID ="  +COL_1+ " " ,  null);
        return res;
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select cardno,expdate,code,amountt from "+TABLE_NAME1+" where CardNo ="  +COL_5+ " " ,  null);
        return res;
    }*/
    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+"  ",  null);
        return res;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME2+"  ",  null);
        return res;
    }


    public boolean InsertData(String cardno, String expdate, String code, String amountt){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5,cardno);
        contentValues.put(COL_6,expdate);
        contentValues.put(COL_7,code);
        contentValues.put(COL_8,amountt);
        long result =  db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public boolean updateData(String memberid,String contactno,String email,String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,memberid);
        contentValues.put(COL_2,contactno);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,amount);
        db.update(TABLE_NAME, contentValues, "MemberID = ?",new String[] { memberid});
        return true;
    }
    public boolean Updatedata(String memberid,String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,memberid);
        contentValues.put(COL_4,amount);
        db.update(TABLE_NAME, contentValues, "MemberID = ?",new String[] { memberid});
        return true;
    }
    public boolean updateData(String name,String feedback) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,name);
        contentValues.put(COL_10,feedback);
        db.update(TABLE_NAME, contentValues, "Name = ?",new String[] { name});
        return true;
    }
    public boolean UpdateData(String cardno, String expdate, String code, String amountt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5,cardno);
        contentValues.put(COL_6,expdate);
        contentValues.put(COL_7,code);
        contentValues.put(COL_8, amountt);
        db.update(TABLE_NAME1, contentValues, "CardNo = ?",new String[] { cardno});
        return true;
    }
    public Integer deleteData (String memberid) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "MemberID = ?",new String[] {memberid});
    }
    public Integer deletedata (String cardno) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, "CardNo  = ?",new String[] {cardno});
    }
   public Integer DeleteData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "Name = ?",new String[] {name});
    }

}

