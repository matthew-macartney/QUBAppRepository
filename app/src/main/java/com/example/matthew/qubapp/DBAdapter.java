package com.example.matthew.qubapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Matthew on 28/07/2015.
 */
public class DBAdapter {

    public static final String DATABASE_NAME = "Product.db";
    public static final String TABLE_NAME = "product_table";
    public static final String PRODUCT_ID = "ID";
    public static final String PRODUCT_NAME = "NAME";
    public static final String PRODUCT_BRAND = "BRAND";
    public static final String PRODUCT_CAT = "CATEGORY";
    public static final String PRODUCT_RRP = "RRP";
    public static final String PRODUCT_PRICE = "PRICE";
    public static final String PRODUCT_SAVING = "SAVING";

    public static final String[] SOME_KEYS = new String[]{PRODUCT_NAME, PRODUCT_BRAND,};

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;
    private final Context context;

    /////////////////////////////////////////////////////////////////////
    //	Public methods:
    /////////////////////////////////////////////////////////////////////

    public DBAdapter(Context ctx, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(ctx , null, null, 1);
    }

    // Open the database connection.
    public DBAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    // Add a new set of values to the database.
    public long insertRow(String name, String brand, String category, int RRP, int price, int saving) {
		/*
		 * CHANGE 3:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(PRODUCT_NAME, name);
        initialValues.put(PRODUCT_BRAND, brand);
        initialValues.put(PRODUCT_CAT, category);
        initialValues.put(PRODUCT_RRP, RRP);
        initialValues.put(PRODUCT_PRICE, price);
        initialValues.put(PRODUCT_SAVING, saving);

        // Insert it into the database.
        return db.insert(TABLE_NAME, null, initialValues);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = PRODUCT_ID + "=" + rowId;
        return db.delete(TABLE_NAME, where, null) != 0;
    }

    public void deleteAll() {
        Cursor c = getSomeRows();
        long rowId = c.getColumnIndexOrThrow(PRODUCT_ID);
        if (c.moveToFirst()) {
            do {
                deleteRow(c.getLong((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
    }

    // Return all data in the database.
    public Cursor getSomeRows() {
        String where = null;
        Cursor c = db.query(true, TABLE_NAME, SOME_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Get a specific row (by rowId)
    public Cursor getRow(long rowId) {
        String where = PRODUCT_ID + "=" + rowId;
        Cursor c = db.query(true, TABLE_NAME, SOME_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String name, String brand, String category, int RRP, int price, int saving) {
        String where = PRODUCT_ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues newValues = new ContentValues();
        newValues.put(PRODUCT_NAME, name);
        newValues.put(PRODUCT_BRAND, brand);
        newValues.put(PRODUCT_CAT, category);
        newValues.put(PRODUCT_RRP, RRP);
        newValues.put(PRODUCT_PRICE, price);
        newValues.put(PRODUCT_SAVING, saving);

        // Insert it into the database.
        return db.update(TABLE_NAME, newValues, where, null) != 0;
    }
}
