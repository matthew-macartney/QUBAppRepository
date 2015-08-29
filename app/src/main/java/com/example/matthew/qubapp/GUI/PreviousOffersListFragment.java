package com.example.matthew.qubapp.GUI;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.matthew.qubapp.Database.PreviousOfferDataSource;
import com.example.matthew.qubapp.Model.BeaconOffer;
import com.example.matthew.qubapp.R;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Matthew on 18/08/2015.
 */
public class PreviousOffersListFragment extends Fragment {

    private PreviousOfferDataSource datasource;
    private PreviousOfferListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;

        view = inflater.inflate(R.layout.previous_offers_fragment, container, false);

        datasource = new PreviousOfferDataSource(this.getActivity());
        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<BeaconOffer> values = datasource.getAllOffers();

        adapter = new PreviousOfferListAdapter(this.getActivity());
        adapter.replaceWith(values);
        ListView list = (ListView) view.findViewById(R.id.listViewPreviousOffers);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                BeaconOffer beaconOffer = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), PreviousOfferActivity.class);
                intent.putExtra("BeaconOffer", beaconOffer);
                startActivity(intent);
            }

        });


        return view;
    }

}
