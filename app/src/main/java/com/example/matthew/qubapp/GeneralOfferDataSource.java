package com.example.matthew.qubapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 26/08/2015.
 */
public class GeneralOfferDataSource {

    private SQLiteDatabase database;
    private GeneralOfferTable myGeneralOfferDB;
    private String[] allRows = { GeneralOfferTable.OFFER_ID, GeneralOfferTable.OFFER_NAME, GeneralOfferTable.OFFER_SHOP, GeneralOfferTable.OFFER_EXPIRY, GeneralOfferTable.OFFER_LAT, GeneralOfferTable.OFFER_LON };

    public GeneralOfferDataSource(Context context){

        myGeneralOfferDB = new GeneralOfferTable(context);
    }

    public void open()throws SQLException{

        database = myGeneralOfferDB.getWritableDatabase();
    }

    public void close(){
        myGeneralOfferDB.close();
    }

    public List<Offer> getAllOffers(){
        List<Offer> offers = new ArrayList<>();

        Cursor cursor = database.rawQuery("Select * FROM general_offer_table", null );

                cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Offer offer = cursorToOffer(cursor);
            offers.add(offer);
            cursor.moveToNext();
        }

        cursor.close();
        return offers;
    }

    private Offer cursorToOffer(Cursor cursor) {

        Offer offer = new Offer();
        offer.setId(cursor.getInt(0));
        offer.setName(cursor.getString(1));
        offer.setShop(cursor.getString(2));
        offer.setExpiry(cursor.getString(3));
        offer.setLatitude(cursor.getFloat(4));
        offer.setLongitude(cursor.getFloat(5));
        offer.setIcon(cursor.getString(6));

        return offer;
    }


}
