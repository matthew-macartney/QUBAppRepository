package com.example.matthew.qubapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

        myOfferReceivedDB = new OfferReceivedTable(context);
    }

    public void open()throws SQLException {

        database = myOfferReceivedDB.getWritableDatabase();
    }

    public void close(){
        myOfferReceivedDB.close();
    }

    public List<BeaconOffer> getAllOffers(){
        List<BeaconOffer> beaconOffers = new ArrayList<>();

        Cursor cursor = database.rawQuery("Select * FROM " + TABLE_NAME_OFFER_RECEIVED, null );

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
        beaconOffer.setExpiry(cursor.getString(7));
        beaconOffer.setLatitude(cursor.getFloat(8));
        beaconOffer.setLongitude(cursor.getFloat(9));
        beaconOffer.setDateReceived(cursor.getString(10));

        return beaconOffer;
    }


}
