package com.example.matthew.qubapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matthew on 22/07/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Product.db";
    public static final String TABLE_NAME = "product_table";
    public static final String PRODUCT_ID = "ID";
    public static final String PRODUCT_NAME = "NAME";
    public static final String PRODUCT_BRAND = "BRAND";
    public static final String PRODUCT_CAT = "CATEGORY";
    public static final String PRODUCT_RRP = "RRP";
    public static final String PRODUCT_PRICE = "PRICE";
    public static final String PRODUCT_SAVING = "SAVING";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRODUCT_NAME, "Shoes");
        contentValues.put(PRODUCT_BRAND, "brand");
        contentValues.put(PRODUCT_CAT, "ategory");
        contentValues.put(PRODUCT_RRP, "RRP");
        contentValues.put(PRODUCT_PRICE, "price");
        contentValues.put(PRODUCT_SAVING, "saving");
        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Shoes");
        contentValues.put(PRODUCT_BRAND, "brand");
        contentValues.put(PRODUCT_CAT, "ategory");
        contentValues.put(PRODUCT_RRP, "RRP");
        contentValues.put(PRODUCT_PRICE, "price");
        contentValues.put(PRODUCT_SAVING, "saving");
        db.insert(TABLE_NAME, null, contentValues);

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

    public void insertData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCT_NAME, "Shoes");
        contentValues.put(PRODUCT_BRAND, "brand");
        contentValues.put(PRODUCT_CAT, "ategory");
        contentValues.put(PRODUCT_RRP, "RRP");
        contentValues.put(PRODUCT_PRICE, "price");
        contentValues.put(PRODUCT_SAVING, "saving");
        db.insert(TABLE_NAME, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Shoes");
        contentValues.put(PRODUCT_BRAND, "brand");
        contentValues.put(PRODUCT_CAT, "ategory");
        contentValues.put(PRODUCT_RRP, "RRP");
        contentValues.put(PRODUCT_PRICE, "price");
        contentValues.put(PRODUCT_SAVING, "saving");
        db.insert(TABLE_NAME, null, contentValues);

    }



}
