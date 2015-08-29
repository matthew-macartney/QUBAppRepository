package com.example.matthew.qubapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.matthew.qubapp.Model.BeaconOffer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 28/08/2015.
 */
public class BeaconOfferDataSource {

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

    private SQLiteDatabase database;
    private BeaconOfferTable myBeaconOfferDB;
    private String[] allRows = {BeaconOfferTable.OFFER_ID, BeaconOfferTable.OFFER_DESCRIPTION, BeaconOfferTable.OFFER_STORE, BeaconOfferTable.OFFER_UUID, BeaconOfferTable.OFFER_MAJOR, BeaconOfferTable.OFFER_MAJOR, BeaconOfferTable.OFFER_MAX_DISTANCE, BeaconOfferTable.OFFER_EXPIRY, BeaconOfferTable.OFFER_LAT, BeaconOfferTable.OFFER_LON, BeaconOfferTable.OFFER_ICON };

    public BeaconOfferDataSource(Context context){

        myBeaconOfferDB = BeaconOfferTable.getInstance(context);
    }

    public void open()throws SQLException {

        database = myBeaconOfferDB.getWritableDatabase();
    }

    public void close(){
        myBeaconOfferDB.close();
    }

    public List<BeaconOffer> getAllBeaconOffers(){
        List<BeaconOffer> beaconOffers = new ArrayList<>();

        Cursor cursor = database.rawQuery("Select * FROM " + TABLE_NAME_BEACON_OFFER, null );

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            BeaconOffer beaconOffer = cursorToOffer(cursor);
            beaconOffers.add(beaconOffer);
            cursor.moveToNext();
        }

        cursor.close();
        return beaconOffers;
    }

    public BeaconOffer getBeaconOffer(String UUID, int major, int minor){

        String query = "Select * FROM " + TABLE_NAME_BEACON_OFFER + " WHERE " + OFFER_UUID + " = '" + UUID + "' AND " + OFFER_MAJOR + " = '" + major + "' AND " + OFFER_MINOR + " = '" + minor + "'";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();
        BeaconOffer beaconOffer = cursorToOffer(cursor);
        cursor.close();
        return beaconOffer;
    }

    private BeaconOffer cursorToOffer(Cursor cursor) {

//        db.execSQL("CREATE TABLE " + TABLE_NAME_BEACON_OFFER + " (_id INTEGER PRIMARY KEY, " + OFFER_DESCRIPTION + " TEXT, " + OFFER_STORE + " TEXT, " +
//                OFFER_UUID + " TEXT, " + OFFER_MAJOR + " TEXT, " + OFFER_MINOR + " TEXT, " + OFFER_MAX_DISTANCE + " TEXT, "+ OFFER_LAT +" FLOAT, " + OFFER_LON +
//                " FLOAT, " + OFFER_EXPIRY + " TEXT, " + OFFER_ICON +" TEXT);");

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
        beaconOffer.setDateReceived(null);

        return beaconOffer;
    }

    public int getOfferDistance(String UUID, int major, int minor){

        int offerDistance;

        String query = "SELECT " + OFFER_MAX_DISTANCE + " FROM " + TABLE_NAME_BEACON_OFFER + " WHERE "
                +  OFFER_UUID + " = '" + UUID + "' AND "
                + OFFER_MAJOR + " = '" + major + "' AND "
                + OFFER_MINOR + "= '" + minor +"'";

        Cursor cs = database.rawQuery(query, null);
        cs.moveToFirst();
        offerDistance = cs.getInt(cs.getColumnIndex(OFFER_MAX_DISTANCE));
        return offerDistance;
    }



}
