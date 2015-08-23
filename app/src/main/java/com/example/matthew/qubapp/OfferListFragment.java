package com.example.matthew.qubapp;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Matthew on 18/08/2015.
 */
public class OfferListFragment extends Fragment {
    TextView title;
    ListView myListView;
    ProductDatabase myProductDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view;

        view = inflater.inflate(R.layout.product_list_fragment, container, false);

        myProductDB = ProductDatabase.getInstance(view.getContext());
        Cursor cursor = myProductDB.getAllRows();

        String[] fromFieldNames = new String[]{ProductDatabase.PRODUCT_NAME,  ProductDatabase.PRODUCT_PRICE, ProductDatabase.PRODUCT_SAVING};
        int[] toViewIDs = new int[]{R.id.textViewProductDes, R.id.textViewPrice, R.id.textViewSaving};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(view.getContext(), R.layout.offer_list_layout, cursor, fromFieldNames, toViewIDs, 0);
        myListView = (ListView) view.findViewById(R.id.listViewProducts);
        myListView.setAdapter(myCursorAdapter);

        return view;
    }


}
