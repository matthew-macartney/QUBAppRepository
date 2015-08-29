package com.example.matthew.qubapp.GUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.matthew.qubapp.Model.BeaconOffer;
import com.example.matthew.qubapp.R;

/**
 * Created by Matthew on 26/08/2015.
 */
public class PreviousOfferListAdapter extends BaseAdapter {

    private ArrayList<BeaconOffer> beaconOffers;
    private LayoutInflater inflater;

    public PreviousOfferListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.beaconOffers = new ArrayList<BeaconOffer>();
    }

    public void replaceWith(Collection<BeaconOffer> newBeaconOffers) {
        this.beaconOffers.clear();
        this.beaconOffers.addAll(newBeaconOffers);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return beaconOffers.size();
    }

    @Override
    public BeaconOffer getItem(int position) {

        return beaconOffers.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflateIfRequired(view, position, parent);
        bind(getItem(position), view);
        return view;
    }

    private void bind(BeaconOffer beaconOffer, View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(beaconOffer.getDescription());
        holder.store.setText(beaconOffer.getStore());
        holder.received.setText(beaconOffer.getDateReceived());
        holder.icon.setImageResource(Integer.valueOf(beaconOffer.getIcon()));

    }

    private View inflateIfRequired(View view, int position, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.history_list_layout, null);
            view.setTag(new ViewHolder(view));
        }
        return view;
    }

    static class ViewHolder {
        final TextView name;
        final TextView store;
        final TextView received;
        final ImageView icon;


        ViewHolder(View view) {
            name = (TextView)view.findViewById(R.id.previousOfferName2);
            store = (TextView)view.findViewById(R.id.previousOfferStore);
            received = (TextView)view.findViewById(R.id.previousOfferDateReceived);
            icon = (ImageView)view.findViewById(R.id.imageViewPreviousOfferIcon);
        }
    }

}

