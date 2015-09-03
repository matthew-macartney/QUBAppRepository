package com.example.matthew.qubapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.matthew.qubapp.Model.Offer;
import com.example.matthew.qubapp.Model.Product;

import java.sql.SQLException;

/**
 * Created by Matthew on 28/08/2015.
 */
public class ProductDataSource {

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

    private SQLiteDatabase database;
    private ProductTable myProductDB;

    public ProductDataSource(Context context){

        myProductDB = ProductTable.getInstance(context);
    }

    public void open()throws SQLException {

        database = myProductDB.getWritableDatabase();
    }

    public void close(){

        myProductDB.close();
    }

    public Product getProduct(String barcode) throws Exception{

        Product product = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME_PRODUCT + " WHERE " + PRODUCT_CODE + " = '" + barcode + "'";

            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            product = cursorToProduct(cursor);
            cursor.close();
        }catch(Exception ex){
            Log.d("PRODUCT RETRIEVAL ERROR", "Unable to retrieve product");
        }
        return product;

    }

    private Product cursorToProduct(Cursor cursor) {

        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setDescription(cursor.getString(1));
        product.setBrand(cursor.getString(2));
        product.setCategory(cursor.getString(3));
        product.setRRP(cursor.getInt(4));
        product.setPrice(cursor.getInt(5));
        product.setSaving(cursor.getInt(6));
        product.setBarcode(cursor.getString(7));
        product.setImage(cursor.getString(8));
        product.setLink(cursor.getString(9));
        product.setLinkName(cursor.getString(10));

        return product;
    }
}


