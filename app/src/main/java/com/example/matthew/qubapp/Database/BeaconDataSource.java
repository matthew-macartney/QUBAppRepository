package com.example.matthew.qubapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.matthew.qubapp.Model.Beacon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 28/08/2015.
 */
public class BeaconDataSource {

    //Beacon Table
    public static final String TABLE_NAME_BEACON = "beacon_table";
    public static final String BEACON_ID = "_id";
    public static final String BEACON_UUID = "UUID";
    public static final String BEACON_MAJOR = "MAJOR";
    public static final String BEACON_MINOR = "MINOR";
    public static final String BEACON_TIMESTAMP = "TIMESTAMP";

    private static final String TAG = "Returned Beacons";

    private SQLiteDatabase database;
    private BeaconTable myBeaconDB;
    private String[] allRows = {BeaconOfferTable.OFFER_ID, BeaconOfferTable.OFFER_DESCRIPTION, BeaconOfferTable.OFFER_STORE, BeaconOfferTable.OFFER_UUID, BeaconOfferTable.OFFER_MAJOR, BeaconOfferTable.OFFER_MAJOR, BeaconOfferTable.OFFER_MAX_DISTANCE, BeaconOfferTable.OFFER_EXPIRY, BeaconOfferTable.OFFER_LAT, BeaconOfferTable.OFFER_LON, BeaconOfferTable.OFFER_ICON };

    public BeaconDataSource(Context context){

        myBeaconDB = BeaconTable.getInstance(context);
    }

    public void open()throws SQLException {

        database = myBeaconDB.getWritableDatabase();
    }

    public void close(){

        myBeaconDB.close();
    }

    public List<Beacon> getAllBeacons(){
        List<Beacon> beacons = new ArrayList<>();

        Cursor cursor = database.rawQuery("Select * FROM " + TABLE_NAME_BEACON, null );

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Beacon beacon = cursorToOffer(cursor);
            beacons.add(beacon);
            cursor.moveToNext();
        }

        cursor.close();
        return beacons;
    }

    public Beacon getSelectedBeacon(Cursor cursor){

        cursor.moveToFirst();
        Beacon beacon = cursorToOffer(cursor);
        cursor.close();
        return beacon;
    }

    private Beacon cursorToOffer(Cursor cursor) {

        Beacon beacon = new Beacon();

        beacon.setId(cursor.getInt(0));
        beacon.setUUID(cursor.getString(1));
        beacon.setMajor(cursor.getInt(2));
        beacon.setMinor(cursor.getInt(3));

        return beacon;
    }

    public boolean beaconQueryDatabase(String UUID, int major, int minor) {

        database = myBeaconDB.getReadableDatabase();

        String query = "SELECT " + BEACON_UUID + " FROM " + TABLE_NAME_BEACON + " WHERE "
                + BEACON_UUID + " = '" + UUID + "' AND "
                + BEACON_MAJOR + " = '" + major + "' AND "
                + BEACON_MINOR + " = '" + minor + "'";

        Cursor cs = database.rawQuery(query, null);
        cs.moveToFirst();
        Log.d(TAG, "Cursor count: " + cs.getCount());

        if (cs.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void insertBeacon(String UUID, int major, int minor, long timestamp){

        ContentValues contentValues = new ContentValues();

        contentValues.put(BEACON_UUID, UUID);
        contentValues.put(BEACON_MAJOR, major);
        contentValues.put(BEACON_MINOR, minor);
        contentValues.put(BEACON_TIMESTAMP, timestamp);
        database.insert(TABLE_NAME_BEACON, null, contentValues);
        Log.d("INSERTED", "timestamp: " + timestamp);
    }

    public long getTimestamp(String UUID, int major, int minor){

        long timestamp;

        String query = "SELECT " + BEACON_TIMESTAMP + " FROM " + TABLE_NAME_BEACON + " WHERE "
                + BEACON_UUID + "= '" + UUID + "' AND "
                + BEACON_MAJOR + "= '" + major + "' AND "
                + BEACON_MINOR + "= '" + minor + "'";

        Cursor cs = database.rawQuery(query, null);
        cs.moveToFirst();
        timestamp =  cs.getLong(cs.getColumnIndex(BEACON_TIMESTAMP));
        Log.d(TAG, "Timestamp retrieved: " + timestamp);

        return timestamp;
    }

    public void updateTimestamp(String UUID, int major, int minor){

        database.execSQL("UPDATE " + TABLE_NAME_BEACON + " SET " + BEACON_TIMESTAMP + " = " + System.currentTimeMillis() / 1000 + " WHERE "
                + BEACON_UUID + "= '" + UUID + "' AND "
                + BEACON_MAJOR + "= '" + major + "' AND "
                + BEACON_MINOR + "= '" + minor + "'");

        Log.d(TAG, "" + minor+ "Timestamp updated to " + System.currentTimeMillis() / 1000);

    }


}
