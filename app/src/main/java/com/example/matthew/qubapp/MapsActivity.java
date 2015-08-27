package com.example.matthew.qubapp;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Float latitude;
    Float longitude;
    GeneralOfferTable myOfferDB;
    String store;
    GoogleMap googleMap;
    LatLng myPosition;
    TextView offerName;
    TextView offerShop;
    TextView offerExpiry;
    ImageView offerIcon;

    public Offer offer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();


        offer = (Offer)intent.getSerializableExtra("Offer");



        offerName = (TextView)findViewById(R.id.offerNameMap);
        offerShop = (TextView)findViewById(R.id.offerShopMap);
        offerExpiry = (TextView)findViewById(R.id.offerExpiryMap);
        offerIcon = (ImageView)findViewById(R.id.offerIconMap);

        setOfferDetails(offer);

//        setOfferDetails(offer);

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


        //if(gpsIsNotOn) {
            Location location = locationManager.getLastKnownLocation(provider);
        //}


        if (location != null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(offer.getLatitude(), offer.getLongitude());

            myPosition = new LatLng(latitude, longitude);

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 13));

            googleMap.addMarker(new MarkerOptions().position(latLng).title(offer.getShop()));

            LatLngBounds bounds = googleMap.getProjection().getVisibleRegion().latLngBounds;

            //future work
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
//            LatLng poiSelectedLatLng = new LatLng(myPosition.latitude
//                    + 20, myPosition.longitude);
//
//            googleMap.animateCamera(CameraUpdateFactory.newLatLng(poiSelectedLatLng));
        }
    }


//        myBeaconOfferDB = GeneralOfferTable.getInstance(getApplicationContext());
//
//        ArrayList<Float> coordinates = myBeaconOfferDB.getCoordinates(offer);
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
//        myBeaconOfferDB = GeneralOfferTable.getInstance(getApplicationContext());
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

    public void setOfferDetails(Offer offer){

        offerName.setText(offer.getName());
        offerShop.setText(offer.getShop());
        offerExpiry.setText(offer.getExpiry());
        offerIcon.setImageResource(Integer.valueOf(offer.getIcon()));

    }

//    public void setBeaconOfferDetails(BeaconOffer beaconOffer){
//
//        offerName.setText(beaconOffer.getDescription());
//        offerShop.setText(beaconOffer.getStore());
//        offerExpiry.setText(beaconOffer.getExpiry());
//        offerIcon.setImageResource(Integer.valueOf(beaconOffer.getIcon()));
//
//    }
}
