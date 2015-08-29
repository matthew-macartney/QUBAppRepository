package com.example.matthew.qubapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.matthew.qubapp.Database.BeaconTable;

/**
 * Created by Matthew on 27/08/2015.
 */
public class OfferReceivedTable extends SQLiteOpenHelper{

    private static OfferReceivedTable instance = null;

    public static final String DATABASE_NAME = "OfferReceivedTable.db";

    //offer received table
    public static final String TABLE_NAME_OFFER_RECEIVED = "offer_received_table";
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
    public static final String OFFER_DATE_RECEIVED = "DATE";
    public static final String OFFER_ICON = "ICON";

    public static synchronized OfferReceivedTable getInstance(Context context){
        if (instance == null){
            instance = new OfferReceivedTable(context.getApplicationContext());
        }

        return instance;
    }

    private OfferReceivedTable(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE " + TABLE_NAME_BEACON_OFFER + " (_id INTEGER PRIMARY KEY, " + OFFER_DESCRIPTION + " TEXT, " + OFFER_STORE + " TEXT, " +
         //       OFFER_UUID + " TEXT, " + OFFER_MAJOR + " TEXT, " + OFFER_MINOR + " TEXT, " + OFFER_MAX_DISTANCE + " TEXT, "+ OFFER_LAT +" FLOAT, " + OFFER_LON + " FLOAT, " + OFFER_EXPIRY +" TEXT, " + OFFER_ICON +" TEXT);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_OFFER_RECEIVED + " (_id INTEGER PRIMARY KEY, " + OFFER_DESCRIPTION +", " + OFFER_STORE +" TEXT, " + OFFER_UUID +" TEXT, " + OFFER_MAJOR +" TEXT, " + OFFER_MINOR +" TEXT, " + OFFER_MAX_DISTANCE +" TEXT, " + OFFER_LAT + " FLOAT, " + OFFER_LON + " FLOAT, " + OFFER_EXPIRY + " TEXT, " + OFFER_ICON + " TEXT, " + OFFER_DATE_RECEIVED + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_OFFER_RECEIVED);

        onCreate(db);

    }



}
