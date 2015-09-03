package com.example.matthew.qubapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.matthew.qubapp.R;

/**
 * Created by Matthew on 26/08/2015.
 */
public class BeaconOfferTable extends SQLiteOpenHelper {

    private static BeaconOfferTable instance = null;

    public static final String DATABASE_NAME = "BeaconOfferDatabase.db";

    //Beacon Offers Table
    public static final String TABLE_NAME_BEACON_OFFER = "offer_table";
    public static final String OFFER_ID = "_id";
    public static final String OFFER_DESCRIPTION = "DESCRIPTION";
    public static final String OFFER_STORE = "STORE";
    public static final String OFFER_UUID = "UUID";
    public static final String OFFER_MAJOR = "MAJOR";
    public static final String OFFER_MINOR = "MINOR";
    public static final String OFFER_MAX_DISTANCE = "DISTANCE";
    public static final String OFFER_EXPIRY = "EXPIRY";
    public static final String OFFER_LAT = "LATITUDE";
    public static final String OFFER_LON = "LONGITUDE";
    public static final String OFFER_ICON = "ICON";

    public SQLiteDatabase db;


    public static synchronized BeaconOfferTable getInstance(Context context) {
        if (instance == null) {
            instance = new BeaconOfferTable(context.getApplicationContext());
        }

        return instance;
    }

    private BeaconOfferTable(Context context) {
        super(context, DATABASE_NAME, null, 8);
        SQLiteDatabase db = this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME_BEACON_OFFER + " (_id INTEGER PRIMARY KEY, " + OFFER_DESCRIPTION + " TEXT, " + OFFER_STORE + " TEXT, " +
                OFFER_UUID + " TEXT, " + OFFER_MAJOR + " TEXT, " + OFFER_MINOR + " TEXT, " + OFFER_MAX_DISTANCE + " TEXT, " + OFFER_LAT + " FLOAT, " + OFFER_LON +
                " FLOAT, " + OFFER_EXPIRY + " TEXT, " + OFFER_ICON + " TEXT);");

        ContentValues contentValues = new ContentValues();

        contentValues.put(OFFER_DESCRIPTION, "2 for 1 pizzas in store just for you!");
        contentValues.put(OFFER_STORE, "Tesco");
        contentValues.put(OFFER_UUID, "ebefd083-70a2-47c8-9837-e7b5634df524");
        contentValues.put(OFFER_MAJOR, 1);
        contentValues.put(OFFER_MINOR, 1);
        contentValues.put(OFFER_MAX_DISTANCE, 2);
        contentValues.put(OFFER_EXPIRY, "21 November 2015");
        contentValues.put(OFFER_ICON, R.drawable.tesco_icon);
        contentValues.put(OFFER_LAT, 54.550295);
        contentValues.put(OFFER_LON, -5.920678);
        db.insert(TABLE_NAME_BEACON_OFFER, null, contentValues);

        contentValues.put(OFFER_DESCRIPTION, "2 for 1 beers in store just for you!");
        contentValues.put(OFFER_STORE, "Tesco");
        contentValues.put(OFFER_UUID, "ebefd083-70a2-47c8-9837-e7b5634df524");
        contentValues.put(OFFER_MAJOR, 1);
        contentValues.put(OFFER_MINOR, 2);
        contentValues.put(OFFER_MAX_DISTANCE, 2);
        contentValues.put(OFFER_EXPIRY, "30 October 2015");
        contentValues.put(OFFER_ICON, R.drawable.tesco_icon);
        contentValues.put(OFFER_LAT, 54.550295);
        contentValues.put(OFFER_LON, -5.920678);
        db.insert(TABLE_NAME_BEACON_OFFER, null, contentValues);

        contentValues.put(OFFER_DESCRIPTION, "Half price chocolate in store just for you!");
        contentValues.put(OFFER_STORE, "Tesco");
        contentValues.put(OFFER_UUID, "ebefd083-70a2-47c8-9837-e7b5634df524");
        contentValues.put(OFFER_MAJOR, 1);
        contentValues.put(OFFER_MINOR, 3);
        contentValues.put(OFFER_MAX_DISTANCE, 2);
        contentValues.put(OFFER_EXPIRY, "25 November 2015");
        contentValues.put(OFFER_ICON, R.drawable.tesco_icon);
        contentValues.put(OFFER_LAT, 54.550295);
        contentValues.put(OFFER_LON, -5.920678);
        db.insert(TABLE_NAME_BEACON_OFFER, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BEACON_OFFER);

        onCreate(db);
    }
}

//    public String databaseToString(){
//        String dbString;
//        db = this.getReadableDatabase();
//        String query = "SELECT " + OFFER_DESCRIPTION + " FROM " + TABLE_NAME_BEACON_OFFER + " WHERE " + OFFER_MINOR + "= '1';";
//
//        Cursor cs = db.rawQuery(query, null);
//        cs.moveToFirst();
//        dbString = cs.getString(cs.getColumnIndex(OFFER_DESCRIPTION));
//
//        return dbString;
//    }
//
//    public String getOfferDes(String UUID, int major, int minor){
//
//        String offerDes = null;
//        db = this.getReadableDatabase();
//        String query = "SELECT " + OFFER_DESCRIPTION + " FROM " + TABLE_NAME_BEACON_OFFER + " WHERE "
//                +  OFFER_UUID + " = '" + UUID + "' AND "
//                + OFFER_MAJOR + " = '" + major + "' AND "
//                + OFFER_MINOR + "= '" + minor +"'";
//
//        Cursor cs = db.rawQuery(query, null);
//        cs.moveToFirst();
//        offerDes = cs.getString(cs.getColumnIndex(OFFER_DESCRIPTION));
//        return offerDes;
//    }
//
//    public String getOfferStore(String UUID, int major, int minor){
//
//        String offerStore = null;
//        db = this.getReadableDatabase();
//        String query = "SELECT " + OFFER_STORE + " FROM " + TABLE_NAME_BEACON_OFFER + " WHERE "
//                +  OFFER_UUID + " = '" + UUID + "' AND "
//                + OFFER_MAJOR + " = '" + major + "' AND "
//                + OFFER_MINOR + "= '" + minor +"'";
//
//        Cursor cs = db.rawQuery(query, null);
//        cs.moveToFirst();
//        offerStore = cs.getString(cs.getColumnIndex(OFFER_STORE));
//        return offerStore;
//    }
//
//    public int getOfferDistance(String UUID, int major, int minor){
//
//        int offerDistance;
//        db = this.getReadableDatabase();
//        String query = "SELECT " + OFFER_MAX_DISTANCE + " FROM " + TABLE_NAME_BEACON_OFFER + " WHERE "
//                +  OFFER_UUID + " = '" + UUID + "' AND "
//                + OFFER_MAJOR + " = '" + major + "' AND "
//                + OFFER_MINOR + "= '" + minor +"'";
//
//        Cursor cs = db.rawQuery(query, null);
//        cs.moveToFirst();
//        offerDistance = cs.getInt(cs.getColumnIndex(OFFER_MAX_DISTANCE));
//        return offerDistance;
//    }
//
////    public Cursor getAllRows() {
////        db = this.getReadableDatabase();
////        Cursor c = db.rawQuery("Select distinct " + OFFER_ID + " as _id, " + OFFER_NAME + ", " + OFFER_SHOP + ", " + OFFER_EXPIRY + ", " + OFFER_ICON + " from " + TABLE_NAME_GENERAL_OFFER, null);
////        if (c != null) {
////            c.moveToFirst();
////        }
////        return c;
////    }
//
//
//    public ArrayList<String> getBeaconOfferDetails(String name){
//
//        ArrayList<String> details = new ArrayList<>();
//        db = this.getReadableDatabase();
//
//        String query = "SELECT " + OFFER_STORE + ", " + OFFER_EXPIRY + " FROM "+  TABLE_NAME_BEACON_OFFER + " WHERE " + OFFER_DESCRIPTION + " = '" +name + "'";
//
//        Cursor cs4 = db.rawQuery(query, null);
//        cs4.moveToFirst();
//        details.add(0, cs4.getString(cs4.getColumnIndex(OFFER_STORE)));
//        details.add(1, cs4.getString(cs4.getColumnIndex(OFFER_EXPIRY)));
//
//
//        return details;
//    }
//
////    public ArrayList<Float> getCoordinates(String offer){
////
////        ArrayList<Float> coordinates = new ArrayList<>();
////        db = this.getReadableDatabase();
////
////        String query = "SELECT " + OFFER_LAT + ", " + OFFER_LON + " FROM "+  TABLE_NAME_GENERAL_OFFER + " WHERE " + OFFER_NAME + " = '" + offer + "'";
////        Cursor cs5 = db.rawQuery(query, null);
////        cs5.moveToFirst();
////
////        coordinates.add(0, cs5.getFloat(cs5.getColumnIndex(OFFER_LAT)));
////        coordinates.add(1, cs5.getFloat(cs5.getColumnIndex(OFFER_LON)));
////
////        return coordinates;
////
////    }
//
//}
