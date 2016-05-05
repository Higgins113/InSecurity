package com.example.stephen.InSecurity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stephen on 21/03/2016.
 */


public class MyDBHandler extends SQLiteOpenHelper

{
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "userinfo.db";
    public static final String TABLE_NAME = "userinfo";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_ADDRESS = "Address";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query2 = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_USERNAME + " TEXT, " + COLUMN_NAME + " TEXT," + COLUMN_PASSWORD + " TEXT, " + COLUMN_ADDRESS + " TEXT" + ");";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,user.getUsername());
        values.put(COLUMN_NAME,user.getName());
        values.put(COLUMN_PASSWORD,user.getPassword());
        values.put(COLUMN_ADDRESS,user.getAddress());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteUser(String username)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\";   ");
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + ";"  );
        db.close();
    }

    public void getUsername(String username)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "=\"" + username + "\"; ";
        db.execSQL(query);
    }


    public String databaseToString(User user)
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String username = user.getUsername();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = '" + username + "';";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("Username"))!=null)
            {
                dbString += c.getString(c.getColumnIndex("Username"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;

    }

    public String databaseToStringPassword(User user)
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String password = user.getPassword();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PASSWORD + " = '" + password + "';";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("Password"))!=null)
            {
                dbString += c.getString(c.getColumnIndex("Password"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}
