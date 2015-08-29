package com.example.matthew.qubapp.GUI;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matthew.qubapp.Database.GeneralOfferTable;
import com.example.matthew.qubapp.Model.Offer;
import com.example.matthew.qubapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
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

        Log.d("CO_ORDINATES", offer.getLatitude() + " " + offer.getLongitude());

        // Getting reference to the SupportMapFragment of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting GoogleMap object from the fragment
        googleMap = fm.getMap();
        
        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        Location location = locationManager.getLastKnownLocation(provider);

        onMapReady(googleMap);


//            // Getting latitude of the current location
//            double latitude = location.getLatitude();
//
//            // Getting longitude of the current location
//            double longitude = location.getLongitude();
//
//            LatLng latLng = new LatLng(offer.getLatitude(), offer.getLongitude());
//
//            // Creating a LatLng object for the current location
//
//            myPosition = new LatLng(latitude, longitude);
//
//            googleMap.addMarker(new MarkerOptions().position(latLng).title(offer.getShop()));
//
//            LatLngBounds bounds = googleMap.getProjection().getVisibleRegion().latLngBounds;
////
//          googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

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




        LatLng latLng = new LatLng(offer.getLatitude(), offer.getLongitude());

//        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng ).zoom(13).build();
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.addMarker(new MarkerOptions().position(latLng).title(offer.getShop()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


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
