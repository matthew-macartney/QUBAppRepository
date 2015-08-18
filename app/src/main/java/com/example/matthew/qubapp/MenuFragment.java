package com.example.matthew.qubapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dm.zbar.android.scanner.ZBarScannerActivity;

/**
 * Created by Matthew on 18/08/2015.
 */
public class MenuFragment extends Fragment {

    ImageButton gpsButton;
    ImageButton barcodeButton;
    ImageButton beaconButton;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view;

            view = inflater.inflate(R.layout.menu_fragment, container, false);
            barcodeButton = (ImageButton)view.findViewById(R.id.imageButtonBarcode);
            beaconButton = (ImageButton)view.findViewById(R.id.imageButtonBeacon);
            gpsButton = (ImageButton)view.findViewById(R.id.imageButtonGPS);

            return view;
        }
}
