package com.example.matthew.qubapp;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Matthew on 18/08/2015.
 */
public class PreviousOffersFragment extends Fragment {

    AppDatabase myAppDB;
    ListView previousOffers;
    TextView previous;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view;

        view = inflater.inflate(R.layout.previous_offers_fragment, container, false);

        previousOffers = (ListView)view.findViewById(R.id.listViewPreviousOffers);
        previous = (TextView)view.findViewById(R.id.textViewPreviousOffers);

        myAppDB = AppDatabase.getInstance(view.getContext());
        Cursor cursor = myAppDB.getAllRows();

        String[] fromFieldNames = new String[]{AppDatabase.OFFER_DES,  AppDatabase.OFFER_DATE_RECEIVED, AppDatabase.OFFER_STORE};
        int[] toViewIDs = new int[]{R.id.textViewHistoryDes, R.id.textViewDate, R.id.textViewStore};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(view.getContext(), R.layout.history_list_layout, cursor, fromFieldNames, toViewIDs, 0);
        previousOffers = (ListView) view.findViewById(R.id.listViewPreviousOffers);
        previousOffers.setAdapter(myCursorAdapter);

        previousOffers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Cursor item = (Cursor) previousOffers.getItemAtPosition(position);
                String offerName = item.getString(item.getColumnIndex(AppDatabase.OFFER_DES));

                Log.d("OFFER BEACON RECEIVED", offerName);
                Intent intent = new Intent(PreviousOffersFragment.this.getActivity(), PreviousOfferActivity.class);
                intent.putExtra("Name", offerName);
                startActivity(intent);
            }

        });

        return view;
    }
}
