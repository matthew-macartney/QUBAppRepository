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
    public static final String PRODUCT_LINK = "LINK";
    public static final String PRODUCT_LINK_NAME = "LINKNAME";

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
                + PRODUCT_PRICE + " TEXT, " + PRODUCT_SAVING + " TEXT, " + PRODUCT_CODE + " TEXT, " + PRODUCT_IMAGE + " TEXT, " + PRODUCT_LINK + " TEXT ," + PRODUCT_LINK_NAME + " TEXT);");

        ContentValues contentValues = new ContentValues();

        contentValues.put(PRODUCT_NAME, "Wilson Tennis Racket");
        contentValues.put(PRODUCT_BRAND, "Wilson");
        contentValues.put(PRODUCT_CAT, "Sports");
        contentValues.put(PRODUCT_RRP, 120.00);
        contentValues.put(PRODUCT_PRICE, 80.00);
        contentValues.put(PRODUCT_SAVING, 33);
        contentValues.put(PRODUCT_CODE, "9771234567003");
        contentValues.put(PRODUCT_IMAGE, R.drawable.wilson_racket);
        contentValues.put(PRODUCT_LINK, "http://www.decathlon.co.uk/pro-staff-95-adult-tennis-racket-id_8328074.html");
        contentValues.put(PRODUCT_LINK_NAME, "www.decathlon.co.uk");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Levis Men's Jeans");
        contentValues.put(PRODUCT_BRAND, "Levis");
        contentValues.put(PRODUCT_CAT, "Menswear");
        contentValues.put(PRODUCT_RRP, 60.00);
        contentValues.put(PRODUCT_PRICE, 48.00);
        contentValues.put(PRODUCT_SAVING, 20);
        contentValues.put(PRODUCT_CODE, "5060249470113");
        contentValues.put(PRODUCT_IMAGE, R.drawable.levisjeans);
        contentValues.put(PRODUCT_LINK, "http://www.levi.com/GB/en_GB/mens-jeans/p/045110763");
        contentValues.put(PRODUCT_LINK_NAME, "www.levi.com/GB");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Adidas Men's Running Trainers");
        contentValues.put(PRODUCT_BRAND, "Adidas");
        contentValues.put(PRODUCT_CAT, "Sports");
        contentValues.put(PRODUCT_RRP, 60.00);
        contentValues.put(PRODUCT_PRICE, 42.00);
        contentValues.put(PRODUCT_SAVING, 30);
        contentValues.put(PRODUCT_CODE, "9780735623873");
        contentValues.put(PRODUCT_IMAGE, R.drawable.adidastrainers);
        contentValues.put(PRODUCT_LINK, "http://www.adidas.co.uk/run9tis-shoes/F98281.html");
        contentValues.put(PRODUCT_LINK_NAME, "www.adidas.co.uk");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Next Women's Black Cardigan");
        contentValues.put(PRODUCT_BRAND, "Next");
        contentValues.put(PRODUCT_CAT, "Womenswear");
        contentValues.put(PRODUCT_RRP, 30.00);
        contentValues.put(PRODUCT_PRICE, 15.00);
        contentValues.put(PRODUCT_SAVING, 50);
        contentValues.put(PRODUCT_CODE, "123456789005");
        contentValues.put(PRODUCT_IMAGE, R.drawable.blackcardigan);
        contentValues.put(PRODUCT_LINK, "http://www.next.co.uk/x6d96s7#879335x56");
        contentValues.put(PRODUCT_LINK_NAME, "www.next.co.uk");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Red Herring Men's Brown Leather Shoes");
        contentValues.put(PRODUCT_BRAND, "Red Herring");
        contentValues.put(PRODUCT_CAT, "Men's Footwear");
        contentValues.put(PRODUCT_RRP, 100.00);
        contentValues.put(PRODUCT_PRICE, 30.00);
        contentValues.put(PRODUCT_SAVING, 70);
        contentValues.put(PRODUCT_CODE, "5014016150821");
        contentValues.put(PRODUCT_IMAGE, R.drawable.brownshoes);
        contentValues.put(PRODUCT_LINK, "http://www.debenhams.com/webapp/wcs/stores/servlet/prod_10701_10001_084010481970_-1");
        contentValues.put(PRODUCT_LINK_NAME, "www.debenhams.com");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Debenhams Leather Recliner Chair & Stool");
        contentValues.put(PRODUCT_BRAND, "Debenhams");
        contentValues.put(PRODUCT_CAT, "Furniture");
        contentValues.put(PRODUCT_RRP, 750.00);
        contentValues.put(PRODUCT_PRICE, 375.00);
        contentValues.put(PRODUCT_SAVING, 50);
        contentValues.put(PRODUCT_CODE, "850006000012");
        contentValues.put(PRODUCT_IMAGE, R.drawable.leatherrecliner);
        contentValues.put(PRODUCT_LINK, "http://www.debenhams.com/webapp/wcs/stores/servlet/prod_10701_10001_320007101660_-1?pdpsku=7100301");
        contentValues.put(PRODUCT_LINK_NAME, "www.debenhams.com");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Lego Ultra Agents Stealth Patrol");
        contentValues.put(PRODUCT_BRAND, "Lego");
        contentValues.put(PRODUCT_CAT, "Toys");
        contentValues.put(PRODUCT_RRP, 40.00);
        contentValues.put(PRODUCT_PRICE, 24.00);
        contentValues.put(PRODUCT_SAVING, 40);
        contentValues.put(PRODUCT_CODE, "671860013624");
        contentValues.put(PRODUCT_IMAGE, R.drawable.lego);
        contentValues.put(PRODUCT_LINK, "http://www.lego.com/en-gb/ultraagents/products/70169-agent-stealth-patrol");
        contentValues.put(PRODUCT_LINK_NAME, "www.lego.com");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Canon Black Powershot sx610 Camera");
        contentValues.put(PRODUCT_BRAND, "Canon");
        contentValues.put(PRODUCT_CAT, "Cameras & camcorders");
        contentValues.put(PRODUCT_RRP, 192.85);
        contentValues.put(PRODUCT_PRICE, 135.00);
        contentValues.put(PRODUCT_SAVING, 30);
        contentValues.put(PRODUCT_CODE, "9781234567897");
        contentValues.put(PRODUCT_IMAGE, R.drawable.canoncamera);
        contentValues.put(PRODUCT_LINK, "http://www.johnlewis.com/canon-powershot-sx610-hs-digital-camera-hd-1080p-20-2mp-18x-optical-zoom-wi-fi-nfc-gps-3-screen/p1875398?sku=234251088&kpid=234251088&s_kenid=47151112-e915-4352-8884-90fe993bd369&s_kwcid=402x25438&tmad=c&tmcampid=73&kpid=234251088");
        contentValues.put(PRODUCT_LINK_NAME, "www.johnlewis.com");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Dyson DC44 Origin Cordless Vacuum Cleaner");
        contentValues.put(PRODUCT_BRAND, "Dyson");
        contentValues.put(PRODUCT_CAT, "Vacuum cleaners");
        contentValues.put(PRODUCT_RRP, 250.00);
        contentValues.put(PRODUCT_PRICE, 200.00);
        contentValues.put(PRODUCT_SAVING, 20);
        contentValues.put(PRODUCT_CODE, "123456789012");
        contentValues.put(PRODUCT_IMAGE, R.drawable.dysonvacuum);
        contentValues.put(PRODUCT_LINK, "http://www.currys.co.uk/gbuk/home-appliances/floorcare/vacuum-cleaners/dyson-dc44-origin-cordless-vacuum-cleaner-silver-10127000-pdt.html?gclid=CKPf2a3a28cCFcSRGwodhgMK2A&srcid=198&cmpid=ppc~gg~~~Exact&mctag=gg_goog_7904&s_kwcid=AL!3391!3!61011142404!!!g!121781171124!&ef_id=VeYgUgAABbISXTTy:20150903203000:s");
        contentValues.put(PRODUCT_LINK_NAME, "www.currys.co.uk");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

        contentValues.put(PRODUCT_NAME, "Jeff Banks Charcoal Suit Jacket");
        contentValues.put(PRODUCT_BRAND, "Jeff Banks");
        contentValues.put(PRODUCT_CAT, "Menswear");
        contentValues.put(PRODUCT_RRP, 115.00);
        contentValues.put(PRODUCT_PRICE, 65.00);
        contentValues.put(PRODUCT_SAVING, 43);
        contentValues.put(PRODUCT_CODE, "9781855683006");
        contentValues.put(PRODUCT_IMAGE, R.drawable.suitjacket);
        contentValues.put(PRODUCT_LINK, "http://www.jeffbanksstores.co.uk/stvdio-by-jeff-banks-grey-check-2-button-front-tailored-fit-ivy-league-suit-jacket-4109");
        contentValues.put(PRODUCT_LINK_NAME, "www.jeffbanksstores.co.uk");
        db.insert(TABLE_NAME_PRODUCT, null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCT);

        onCreate(db);
    }

}
