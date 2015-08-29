package com.example.matthew.qubapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.matthew.qubapp.Database.OfferReceivedTable;
import com.example.matthew.qubapp.Model.BeaconOffer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 27/08/2015.
 */
public class PreviousOfferDataSource {

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

    private SQLiteDatabase database;
    private OfferReceivedTable myOfferReceivedDB;
    private String[] allRows = { OfferReceivedTable.OFFER_ID, OfferReceivedTable.OFFER_DESCRIPTION, OfferReceivedTable.OFFER_STORE, OfferReceivedTable.OFFER_UUID, OfferReceivedTable.OFFER_MAJOR, OfferReceivedTable.OFFER_MINOR, OfferReceivedTable.OFFER_MAX_DISTANCE, OfferReceivedTable.OFFER_EXPIRY, OfferReceivedTable.OFFER_LAT, OfferReceivedTable.OFFER_LON, OfferReceivedTable.OFFER_DATE_RECEIVED, OfferReceivedTable.OFFER_ICON };

    public PreviousOfferDataSource(Context context){

        myOfferReceivedDB = OfferReceivedTable.getInstance(context);
    }

    public void open()throws SQLException {

        database = myOfferReceivedDB.getWritableDatabase();
    }

    public void close(){
        myOfferReceivedDB.close();
    }

    public List<BeaconOffer> getAllOffers(){
        List<BeaconOffer> beaconOffers = new ArrayList<>();

        Cursor cursor = database.rawQuery("Select * FROM " + TABLE_NAME_OFFER_RECEIVED + " ORDER BY " + OFFER_ID + " DESC", null );

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            BeaconOffer beaconOffer = cursorToOffer(cursor);
            beaconOffers.add(beaconOffer);
            cursor.moveToNext();
        }

        cursor.close();
        return beaconOffers;
    }

    public BeaconOffer getSelectedOffer(Cursor cursor){

        cursor.moveToFirst();
        BeaconOffer beaconOffer = cursorToOffer(cursor);
        cursor.close();
        return beaconOffer;
    }

    private BeaconOffer cursorToOffer(Cursor cursor) {

        BeaconOffer beaconOffer = new BeaconOffer();
        beaconOffer.setId(cursor.getInt(0));
        beaconOffer.setDescription(cursor.getString(1));
        beaconOffer.setStore(cursor.getString(2));
        beaconOffer.setUUID(cursor.getString(3));
        beaconOffer.setMajor(cursor.getInt(4));
        beaconOffer.setMinor(cursor.getInt(5));
        beaconOffer.setDistance(cursor.getInt(6));
        beaconOffer.setLatitude(cursor.getFloat(7));
        beaconOffer.setLongitude(cursor.getFloat(8));
        beaconOffer.setExpiry(cursor.getString(9));
        beaconOffer.setIcon(cursor.getString(10));
        beaconOffer.setDateReceived(cursor.getString(11));

        Log.d("BEACON OFFER 2", beaconOffer.getExpiry());

        return beaconOffer;
    }

    public void insertBeaconOffer(String description, String store, String UUID, int major, int minor, int distance, String expiry, float latitude, float longitude, String dateReceived, String icon){

        ContentValues contentValues = new ContentValues();

        contentValues.put(OFFER_DESCRIPTION, description);
        contentValues.put(OFFER_STORE, store);
        contentValues.put(OFFER_UUID, UUID);
        contentValues.put(OFFER_MAJOR, major);
        contentValues.put(OFFER_MINOR, minor);
        contentValues.put(OFFER_MAX_DISTANCE, distance);
        contentValues.put(OFFER_EXPIRY, expiry);
        contentValues.put(OFFER_LAT, latitude);
        contentValues.put(OFFER_LON, longitude);
        contentValues.put(OFFER_DATE_RECEIVED, dateReceived);
        contentValues.put(OFFER_ICON, icon);

        database.insert(TABLE_NAME_OFFER_RECEIVED, null, contentValues);
    }

    public void deleteBeaconOffer(String description, String UUID, int major, int minor){

        database.delete(TABLE_NAME_OFFER_RECEIVED, OFFER_DESCRIPTION + " = '" + description + "' AND " + OFFER_UUID + " = '" + UUID + "' AND " + OFFER_MAJOR + " = '" + major + "' AND " + OFFER_MINOR + " = '" + minor + "'", null);
    }


}
