package com.example.matthew.qubapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Matthew on 18/08/2015.
 */
public class BarcodeProductFragment extends Fragment {

    TextView productName;
    TextView productRRP;
    TextView productPrice;
    TextView productSaving;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view;

        view = inflater.inflate(R.layout.barcode_product_layout, container, false);
        productName = (TextView)view.findViewById(R.id.textViewProductName);
        productRRP = (TextView)view.findViewById(R.id.textViewProductRRP);
        productPrice = (TextView)view.findViewById(R.id.textViewProductPrice);
        productSaving = (TextView)view.findViewById(R.id.textViewProductSaving);

        return view;
    }

}
