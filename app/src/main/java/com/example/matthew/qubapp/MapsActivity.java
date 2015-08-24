package com.example.matthew.qubapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Float latitude;
    Float longitude;
    OfferDatabase myOfferDB;
    String store;
    GoogleMap googleMap;
    LatLng myPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        String offer = intent.getStringExtra("Name");
        //store = intent.getStringExtra("Store");

        // Getting reference to the SupportMapFragment of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting GoogleMap object from the fragment
        googleMap = fm.getMap();

        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            myPosition = new LatLng(latitude, longitude);

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
            googleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));
        }
    }

//        Log.d("OFFER YEAH!", offer);
//        myOfferDB = OfferDatabase.getInstance(getApplicationContext());
//
//        ArrayList<Float> coordinates = myOfferDB.getCoordinates(offer);
//
//        latitude = coordinates.get(0);
//        longitude = coordinates.get(1);
//
//        Log.d("LATITUDE", String.valueOf(latitude));
//        Log.d("LONGITUDE", String.valueOf(longitude));
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);



//    public ArrayList<Float> getLocation(String offer){
//
//        myOfferDB = OfferDatabase.getInstance(getApplicationContext());
//
//
//
//    }

    @Override
    public void onMapReady(GoogleMap map) {
//        // Add a marker in Sydney, Australia, and move the camera.
//        LatLng marker = new LatLng(latitude, longitude);
//        map.addMarker(new MarkerOptions().position(marker).title(store));
//        map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
    }
}
