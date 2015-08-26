package com.example.matthew.qubapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.google.android.gms.plus.model.people.Person;
import com.jaalee.sdk.*;
import com.jaalee.sdk.Beacon;

import java.util.ArrayList;
import java.util.Collection;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaalee.sdk.Beacon;
import com.jaalee.sdk.Utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Matthew on 26/08/2015.
 */
public class OfferListAdapter extends BaseAdapter {

    private ArrayList<Offer> offers;
    private LayoutInflater inflater;

    public OfferListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.offers = new ArrayList<Offer>();
    }

    public void replaceWith(Collection<Offer> newOffers) {
        this.offers.clear();
        this.offers.addAll(newOffers);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return offers.size();
    }

    @Override
    public Offer getItem(int position) {
        return offers.get(position);
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

    private void bind(Offer offer, View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(offer.getName());
        holder.shop.setText(offer.getShop());
        holder.expiry.setText(offer.getExpiry());
        holder.icon.setImageResource(Integer.valueOf(offer.getIcon()));

    }

    private View inflateIfRequired(View view, int position, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.offer_list_layout, null);
            view.setTag(new ViewHolder(view));
        }
        return view;
    }

    static class ViewHolder {
        final TextView name;
        final TextView shop;
        final TextView expiry;
        final ImageView icon;


        ViewHolder(View view) {
            name = (TextView)view.findViewById(R.id.textViewOfferName12);
            shop = (TextView)view.findViewById(R.id.textViewShop);
            expiry = (TextView)view.findViewById(R.id.textViewExpires);
            icon = (ImageView)view.findViewById(R.id.imageViewIcon);
        }
    }

}
