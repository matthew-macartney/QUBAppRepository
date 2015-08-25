package com.example.matthew.qubapp;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
public class OfferListFragment extends Fragment {
    TextView title;
    public ListView myListView;
    OfferDatabase myOfferDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view;

        view = inflater.inflate(R.layout.offer_list_fragment, container, false);

        myOfferDB = OfferDatabase.getInstance(OfferListFragment.this.getActivity());
        Cursor cursor = myOfferDB.getAllRows();

        String[] fromFieldNames = new String[]{OfferDatabase.OFFER_NAME,  OfferDatabase.OFFER_SHOP, OfferDatabase.OFFER_EXPIRY, OfferDatabase.OFFER_ICON};
        int[] toViewIDs = new int[]{R.id.textViewOfferName12, R.id.textViewShop, R.id.textViewExpires, R.id.imageViewIcon};
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(view.getContext(), R.layout.offer_list_layout, cursor, fromFieldNames, toViewIDs, 0);
        myListView = (ListView) view.findViewById(R.id.listViewProducts);
        myListView.setAdapter(myCursorAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Cursor  item    = (Cursor) myListView.getItemAtPosition(position);
                String offerName = item.getString(item.getColumnIndex(OfferDatabase.OFFER_NAME));

                Intent intent = new Intent(OfferListFragment.this.getActivity(), OfferActivity.class);
                intent.putExtra("Name", offerName);
                startActivity(intent);
            }

        });

        return view;
    }


}
