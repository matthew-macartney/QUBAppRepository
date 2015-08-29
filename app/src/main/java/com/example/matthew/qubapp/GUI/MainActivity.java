package com.example.matthew.qubapp.GUI;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.matthew.qubapp.Database.BeaconDataSource;
import com.example.matthew.qubapp.Model.BeaconOffer;
import com.example.matthew.qubapp.Database.BeaconOfferDataSource;
import com.example.matthew.qubapp.Database.GeneralOfferTable;
import com.example.matthew.qubapp.Database.PreviousOfferDataSource;
import com.example.matthew.qubapp.Database.ProductTable;
import com.example.matthew.qubapp.R;
import com.jaalee.sdk.*;
import com.jaalee.sdk.Beacon;
import com.jaalee.sdk.utils.L;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends FragmentActivity {

    private static final int BEACON_DELAY_TIME = 30;

    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private static final int NOTIFICATION_ID = 123;
    private static final int REQUEST_ENABLE_BT = 1234;

    Region openRegion = new Region("rid", null, null, null);

    private NotificationManager notificationManager;
    private BeaconManager beaconManager;
    private BeaconOfferDataSource myBeaconOfferDB;
    private PreviousOfferDataSource myPreviousOffersDB;
    private BeaconDataSource myBeaconDB;

    public BeaconOffer beaconOffer;

    GeneralOfferTable myGeneralOfferTable;
    ProductTable myProductDB;
    SQLiteDatabase db;
    ListView myListView;
    ImageButton barcodeButton;
    TextView scanFrom;
    private BeaconListAdapter adapter;
    ImageButton gpsButton;
    ImageButton beaconButton;
    Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            OfferListFragment productListFragment = new OfferListFragment();
            fragmentTransaction.add(R.id.fragment_container, productListFragment);
            fragmentTransaction.commit();
        }

        gpsButton = (ImageButton) findViewById(R.id.imageButtonGPS);
        barcodeButton = (ImageButton) findViewById(R.id.imageButtonBeacon);
        beaconButton = (ImageButton)findViewById(R.id.imageButtonBeacon);
        mapButton = (Button) findViewById(R.id.buttonMap);

        myBeaconDB = new BeaconDataSource(this);
        try {
            myBeaconDB.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        myBeaconOfferDB = new BeaconOfferDataSource(this);
        try {
            myBeaconOfferDB.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        myPreviousOffersDB = new PreviousOfferDataSource(this);
        try {
            myPreviousOffersDB.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       // populateListView();
        adapter = new BeaconListAdapter(this);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Configure BeaconManager.
        beaconManager = new BeaconManager(this);
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 0);
        beaconManager.setForegroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 0);
        List<Beacon> foundBeacons;
        connectToService();

        L.enableDebugLogging(true);
    }

    public List<Beacon> filterBeacons(List<Beacon> beacons) {
        List<Beacon> filteredBeacons = new ArrayList<Beacon>(beacons.size());
        for (Beacon beacon : beacons)
        {
//    	only detect the BeaconHelper of certain types
            {
                filteredBeacons.add(beacon);
            }
        }
        return filteredBeacons;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if device supports Bluetooth Low Energy.
        // If device does not support bluetooth let user know.
        if (!beaconManager.hasBluetooth()) {
            Toast.makeText(this, "Sorry! Your device does not have bluetooth!", Toast.LENGTH_LONG).show();
            return;
        }

        // If Bluetooth is not switched on let user enable it.
        if (!beaconManager.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
           connectToService();
        }
    }

    public void connectToService() {

        beaconManager.connect(new ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(openRegion);
                listenForBeacons();
            }
        });
    }

    public void listenForBeacons(){
        beaconManager.setRangingListener(new RangingListener() {

            @Override
            public void onBeaconsDiscovered(final Region region, final List beacons) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // beacons in this list are sorted by distance between device and beacon
                        adapter.clear();
                        List<Beacon> JaaleeBeacons = filterBeacons(beacons);
                        adapter.replaceWith(JaaleeBeacons);

                        if(JaaleeBeacons.size()>0){

                            //get the closest beacon and start monitoring
                            final Beacon beacon = adapter.getItem(0);
                            startMonitoring(beacon);
                            //stop ranging for new beacons while monitoring
                            beaconManager.stopRanging(openRegion);

                        }
                    }
                });
            }
        });
    }

    public void barcodeButtonOnClick(View v) {
        Intent intent = new Intent(this, SimpleScannerActivity.class);
        startActivity(intent);
    }

    public void beaconButtonOnClick(View v) {

        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        PreviousOffersListFragment previousOffersFragment = new PreviousOffersListFragment();
        fragmentTransaction2.replace(R.id.fragment_container, previousOffersFragment);
        fragmentTransaction2.commit();
    }

    public void GPSButtonOnClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        OfferListFragment productListFragment = new OfferListFragment();
        fragmentTransaction.replace(R.id.fragment_container, productListFragment);
        fragmentTransaction.commit();
    }

    public void onMapButtonClick(View v){

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                connectToService();
            } else {
                Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();
                getActionBar().setSubtitle("Bluetooth not enabled");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void postNotification(BeaconOffer beaconOffer) {
        Intent notifyIntent = new Intent(getApplicationContext(), PreviousOfferActivity.class);
        notifyIntent.putExtra("BeaconOffer", beaconOffer);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

                builder.setTicker("New beacon offer received!")
                .setSmallIcon(R.drawable.tag_icon)
                .setContentTitle(beaconOffer.getDescription())
                .setContentText("Click to view offer")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags = notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void startMonitoring(final Beacon beacon){

            final Region beaconRegion= new Region("regionId", beacon.getProximityUUID(), beacon.getMajor(), beacon.getMinor());
            beaconManager.startMonitoring(beaconRegion);

            beaconManager.setMonitoringListener(new MonitoringListener() {
                @Override
                public void onEnteredRegion(Region region) {

                    long tsLong = (System.currentTimeMillis() / 1000);
                    String UUID = beacon.getProximityUUID().toString();
                    Boolean beaconFound = myBeaconDB.beaconQueryDatabase(UUID, beacon.getMajor(), beacon.getMinor());
                    String pattern = "dd/MM/yyyy";
                    SimpleDateFormat format = new SimpleDateFormat(pattern);
                    String date = format.format(new Date());

                    beaconOffer = myBeaconOfferDB.getBeaconOffer(UUID, beacon.getMajor(), beacon.getMinor());

                    Log.d("BEACON RETURNED", beaconOffer.getDescription() + beaconOffer.getExpiry()+ beaconOffer.getStore());

                    if (!beaconFound) {

                        double offerDistance = beaconOffer.getDistance();
                        String distance = Double.toString(Utils.computeAccuracy(beacon));
                        Log.d("DISTANCE", "Distance: " + distance);
                        if (Utils.computeAccuracy(beacon) <= offerDistance) {
                            myBeaconDB.insertBeacon(UUID, beacon.getMajor(), beacon.getMinor(), tsLong);
                            //String description, String store, String UUID, int major, int minor, int distance, String expiry, float latitude, float longitude, String dateReceived, String icon
                            myPreviousOffersDB.insertBeaconOffer(beaconOffer.getDescription(), beaconOffer.getStore(), beaconOffer.getUUID(), beaconOffer.getMajor(), beaconOffer.getMinor(), beaconOffer.getDistance(), beaconOffer.getExpiry(), beaconOffer.getLatitude(), beaconOffer.getLongitude(), date, beaconOffer.getIcon());
                            Log.d("OFFER EXPIRY 1", beaconOffer.getExpiry());
                            beaconManager.stopMonitoring(beaconRegion);
                            connectToService();

                            postNotification(beaconOffer);

                        } else {

                            beaconManager.stopMonitoring(beaconRegion);
                            connectToService();
                        }

                    } else if (beaconFound) {

                        double offerDistance = myBeaconOfferDB.getOfferDistance(UUID, beacon.getMajor(), beacon.getMinor());
                        long timestamp = myBeaconDB.getTimestamp(UUID, beacon.getMajor(), beacon.getMinor());
                        if ((System.currentTimeMillis() / 1000) - timestamp >= BEACON_DELAY_TIME && Utils.computeAccuracy(beacon) <= offerDistance) {

                            myBeaconDB.updateTimestamp(UUID, beacon.getMajor(), beacon.getMinor());
                            myPreviousOffersDB.insertBeaconOffer(beaconOffer.getDescription(), beaconOffer.getStore(), beaconOffer.getUUID(), beaconOffer.getMajor(), beaconOffer.getMinor(), beaconOffer.getDistance(), beaconOffer.getExpiry(), beaconOffer.getLatitude(), beaconOffer.getLongitude(), date, beaconOffer.getIcon());
                            beaconManager.stopMonitoring(beaconRegion);
                            connectToService();

                            postNotification(beaconOffer);

                        }

                    } else {
                        beaconManager.stopMonitoring(beaconRegion);
                        connectToService();
                    }
                }

                @Override
                public void onExitedRegion(Region region) {
                }
            });
    }

}