package com.example.matthew.qubapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.matthew.qubapp.R;

/**
 * Created by Matthew on 22/07/2015.
 */
public class ProductTable extends SQLiteOpenHelper {

    private static ProductTable instance = null;

    public static final String DATABASE_NAME = "ProductTable.db";

    //Product Table
    public static final String TABLE_NAME_PRODUCT = "product_table";
    public static final String PRODUCT_ID = "_id";
    public static final String PRODUCT_NAME = "NAME";
    public static final String PRODUCT_BRAND = "BRAND";
    public static final String PRODUCT_CAT = "CATEGORY";
    public static final String PRODUCT_RRP = "RRP";
    public static final String PRODUCT_PRICE = "PRICE";
    public static final String PRODUCT_SAVING = "SAVING";
    public static final String PRODUCT_CODE = "BARCODE";
    public static final String PRODUCT_IMAGE = "IMAGE";

    public static synchronized ProductTable getInstance(Context context){
        if (instance == null){
            instance = new ProductTable(context.getApplicationContext());
        }
        return instance;
    }

    public ProductTable(Context context) {
        super(context, DATABASE_NAME, null, 7);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_PRODUCT + " (" + PRODUCT_ID + " INTEGER PRIMARY KEY, " + PRODUCT_NAME
                + " TEXT, " + PRODUCT_BRAND + " TEXT, " + PRODUCT_CAT + " TEXT, " + PRODUCT_RRP + " TEXT, "
                + PRODUCT_PRICE + " TEXT, " + PRODUCT_SAVING + " TEXT, " + PRODUCT_CODE + " TEXT, " + PRODUCT_IMAGE + " TEXT);");

        ContentValues contentValues = new ContentValues();

        contentValues.put(PRODUCT_NAME, "Wilson Tennis Racket");
        contentValues.put(PRODUCT_BRAND, "Wilson");
        contentValues.put(PRODUCT_CAT, "Sports");
        contentValues.put(PRODUCT_RRP, 120.00);
        contentValues.put(PRODUCT_PRICE, 80.00);
        contentValues.put(PRODUCT_SAVING, 33);
        contentValues.put(PRODUCT_CODE, "9771234567003");
        contentValues.put(PRODUCT_IMAGE, R.drawable.wilson_racket);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Levis Men's Jeans");
        contentValues.put(PRODUCT_BRAND, "Levis");
        contentValues.put(PRODUCT_CAT, "Menswear");
        contentValues.put(PRODUCT_RRP, 60.00);
        contentValues.put(PRODUCT_PRICE, 48.00);
        contentValues.put(PRODUCT_SAVING, 20);
        contentValues.put(PRODUCT_CODE, "5060249470113");
        contentValues.put(PRODUCT_IMAGE, R.drawable.levisjeans);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);
        //fix
        contentValues.put(PRODUCT_NAME, "Adidas Men's Running Trainers");
        contentValues.put(PRODUCT_BRAND, "Adidas");
        contentValues.put(PRODUCT_CAT, "Sports");
        contentValues.put(PRODUCT_RRP, 60.00);
        contentValues.put(PRODUCT_PRICE, 42.00);
        contentValues.put(PRODUCT_SAVING, 30);
        contentValues.put(PRODUCT_CODE, "9780735623873");
        contentValues.put(PRODUCT_IMAGE, R.drawable.adidastrainers);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Next Women's Black Cardigan");
        contentValues.put(PRODUCT_BRAND, "Next");
        contentValues.put(PRODUCT_CAT, "Womenswear");
        contentValues.put(PRODUCT_RRP, 30.00);
        contentValues.put(PRODUCT_PRICE, 15.00);
        contentValues.put(PRODUCT_SAVING, 50);
        contentValues.put(PRODUCT_CODE, "123456789005");
        contentValues.put(PRODUCT_IMAGE, R.drawable.blackcardigan);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Red Herring Men's Brown Leather Shoes");
        contentValues.put(PRODUCT_BRAND, "Red Herring");
        contentValues.put(PRODUCT_CAT, "Men's Footwear");
        contentValues.put(PRODUCT_RRP, 100.00);
        contentValues.put(PRODUCT_PRICE, 30.00);
        contentValues.put(PRODUCT_SAVING, 70);
        contentValues.put(PRODUCT_CODE, "5014016150821");
        contentValues.put(PRODUCT_IMAGE, R.drawable.brownshoes);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Debenhams Leather Recliner Chair & Stool");
        contentValues.put(PRODUCT_BRAND, "Debenhams");
        contentValues.put(PRODUCT_CAT, "Furniture");
        contentValues.put(PRODUCT_RRP, 400.00);
        contentValues.put(PRODUCT_PRICE, 280.00);
        contentValues.put(PRODUCT_SAVING, 30);
        contentValues.put(PRODUCT_CODE, "850006000012");
        contentValues.put(PRODUCT_IMAGE, R.drawable.leatherrecliner);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Lego Ultra Agents Stealth Patrol");
        contentValues.put(PRODUCT_BRAND, "Lego");
        contentValues.put(PRODUCT_CAT, "Toys");
        contentValues.put(PRODUCT_RRP, 40.00);
        contentValues.put(PRODUCT_PRICE, 24.00);
        contentValues.put(PRODUCT_SAVING, 40);
        contentValues.put(PRODUCT_CODE, "671860013624");
        contentValues.put(PRODUCT_IMAGE, R.drawable.lego);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Canon Black Powershot sx610 Camera");
        contentValues.put(PRODUCT_BRAND, "Canon");
        contentValues.put(PRODUCT_CAT, "Cameras & camcorders");
        contentValues.put(PRODUCT_RRP, 180.00);
        contentValues.put(PRODUCT_PRICE, 126.00);
        contentValues.put(PRODUCT_SAVING, 30.00);
        contentValues.put(PRODUCT_CODE, "9781234567897");
        contentValues.put(PRODUCT_IMAGE, R.drawable.canoncamera);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Dyson DC44 Origin Cordless Vacuum Cleaner");
        contentValues.put(PRODUCT_BRAND, "Dyson");
        contentValues.put(PRODUCT_CAT, "Vacuum cleaners");
        contentValues.put(PRODUCT_RRP, 269.00);
        contentValues.put(PRODUCT_PRICE, 209.00);
        contentValues.put(PRODUCT_SAVING, 22);
        contentValues.put(PRODUCT_CODE, "123456789012");
        contentValues.put(PRODUCT_IMAGE, R.drawable.dysonvacuum);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Jeff Banks Charcoal Suit Jacket");
        contentValues.put(PRODUCT_BRAND, "Jeff Banks");
        contentValues.put(PRODUCT_CAT, "Menswear");
        contentValues.put(PRODUCT_RRP, 95.00);
        contentValues.put(PRODUCT_PRICE, 57.00);
        contentValues.put(PRODUCT_SAVING, 40);
        contentValues.put(PRODUCT_CODE, "9781855683006");
        contentValues.put(PRODUCT_IMAGE, R.drawable.suitjacket);
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCT);

        onCreate(db);
    }

}
