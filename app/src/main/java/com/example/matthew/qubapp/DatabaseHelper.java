package com.example.matthew.qubapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matthew on 22/07/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Product.db";
    public static final String TABLE_NAME = "product_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BRAND";
    public static final String COL_4 = "CATEGORY";
    public static final String COL_5 = "RRP";
    public static final String COL_6 = "PRICE";
    public static final String COL_7 = "SAVING";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT, BRAND TEXT, CATEGORY TEXT, RRP TEXT, PRICE TEXT, SAVING TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String brand, String category, String RRP, String price, String saving) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, brand);
        contentValues.put(COL_4, category);
        contentValues.put(COL_5, RRP);
        contentValues.put(COL_6, price);
        contentValues.put(COL_7, saving);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
