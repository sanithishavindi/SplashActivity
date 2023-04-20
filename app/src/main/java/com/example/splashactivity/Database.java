package com.example.splashactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "DataUser", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table userdetails(name TEXT primary key,age TEXT,gender TEXT,image BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
      DB.execSQL("drop table if exists userdetails");
    }

    public Boolean saveuserdata(String name, String age,String gender){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("age",age);
        contentValues.put("gender",gender);
        //contentValues.put("image",image);

        long result=DB.insert("userdetails",null,contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor gettext(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from userdetails",null);
        return cursor;
    }
}
