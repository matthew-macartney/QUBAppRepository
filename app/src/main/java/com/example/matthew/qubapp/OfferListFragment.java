package com.example.matthew.qubapp;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Matthew on 18/08/2015.
 */
public class OfferListFragment extends Fragment {
    TextView title;
    public ListView myListView;
    GeneralOfferTable myOfferDB;
    private GeneralOfferDataSource datasource;
    private OfferListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view;

        view = inflater.inflate(R.layout.offer_list_fragment, container, false);


        datasource = new GeneralOfferDataSource(this.getActivity());
        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Offer> values  = datasource.getAllOffers();


        adapter = new OfferListAdapter(this.getActivity());
        adapter.replaceWith(values);
        ListView list = (ListView) view.findViewById(R.id.listViewProducts);
        list.setAdapter(adapter);


//        for(values.){
//
//
//        }
//
//        myBeaconOfferDB = GeneralOfferTable.getInstance(OfferListFragment.this.getActivity());
//        Cursor cursor = myBeaconOfferDB.getAllRows();
//
//        String[] fromFieldNames = new String[]{GeneralOfferTable.OFFER_NAME,  GeneralOfferTable.OFFER_SHOP, GeneralOfferTable.OFFER_EXPIRY, GeneralOfferTable.OFFER_ICON};
//        int[] toViewIDs = new int[]{R.id.textViewOfferName12, R.id.textViewShop, R.id.textViewExpires, R.id.imageViewIcon};
//        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(view.getContext(), R.layout.offer_list_layout, cursor, fromFieldNames, toViewIDs, 0);
//        myListView = (ListView) view.findViewById(R.id.listViewProducts);
//        myListView.setAdapter(myCursorAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Cursor  item    = (Cursor) myListView.getItemAtPosition(position);
                String offerName = item.getString(item.getColumnIndex(GeneralOfferTable.OFFER_NAME));

                Intent intent = new Intent(OfferListFragment.this.getActivity(), OfferActivity.class);
                intent.putExtra("Name", offerName);
                startActivity(intent);
            }

        });

        return view;
    }


}
