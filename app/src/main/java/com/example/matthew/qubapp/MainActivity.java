package com.example.matthew.qubapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.jaalee.sdk.Utils;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.jaalee.sdk.*;
import com.jaalee.sdk.Beacon;
import com.jaalee.sdk.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private static final int NOTIFICATION_ID = 123;
    private static final int REQUEST_ENABLE_BT = 1234;
    Region beaconRegion = new Region("rid", null, null, null);
    private NotificationManager notificationManager;

    private BeaconManager beaconManager;

    ProductDatabase myProductDB;
    OfferDatabase myOfferDB;
    SQLiteDatabase db;
    ListView myListView;
    ImageButton barcode;
    TextView scanFrom;
    private BeaconListAdapter adapter;
    AppDatabase appDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        barcode = (ImageButton) findViewById(R.id.imageButtonBarcode);
        scanFrom = (TextView) findViewById(R.id.textViewScanFrom);
        findViewById(R.id.imageButtonBeacon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListBeaconActivity.class);
                intent.putExtra(ListBeaconActivity.EXTRAS_TARGET_ACTIVITY, NotifyDemoActivity.class.getName());
                startActivity(intent);
            }
        });
        myProductDB = new ProductDatabase(this, null, null, 7);
        myOfferDB = new OfferDatabase(this, null, null, 1);
        appDb = new AppDatabase(this, null, null, 1);
        populateListView();

        adapter = new BeaconListAdapter(this);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Configure BeaconManager.
        beaconManager = new BeaconManager(this);
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1), 0);
        List<Beacon> JaaleeBeacons;

            beaconManager.setRangingListener(new RangingListener() {

                @Override
                public void onBeaconsDiscovered(Region region, final List beacons) {
                    // Note that results are not delivered on UI thread.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Note that beacons reported here are already sorted by estimated
                            // distance between device and beacon.

                            List<Beacon> JaaleeBeacons = filterBeacons(beacons);
                            adapter.replaceWith(JaaleeBeacons);
                            if(adapter.getCount()>0){
                                int nextBeacon = 0;
                                //beaconManager.stopRanging(beaconRegion);
                                final Beacon beacon = adapter.getItem(nextBeacon++);
                                startMonitoring(beacon);
                            }
                        }
                    });
                }


            });



        L.enableDebugLogging(true);
    }

    private List<Beacon> filterBeacons(List<Beacon> beacons) {
        List<Beacon> filteredBeacons = new ArrayList<Beacon>(beacons.size());
        for (Beacon beacon : beacons)
        {
//    	only detect the BeaconHelper of Jaalee
//    	if ( beacon.getProximityUUID().equalsIgnoreCase(JAALEE_BEACON_PROXIMITY_UUID) )
            {
                Log.i("JAALEE", "JAALEE:"+beacon.getBattLevel());
                filteredBeacons.add(beacon);
            }
        }
        return filteredBeacons;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if device supports Bluetooth Low Energy.
        if (!beaconManager.hasBluetooth()) {
            Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
            return;
        }

        // If Bluetooth is not enabled, let user enable it.
        if (!beaconManager.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
           connectToService();
        }
    }

    public void connectToService() {
        final Region myRegion = beaconRegion;
        beaconManager.connect(new ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(myRegion);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return super.onOptionsItemSelected(item);
    }


    public void queryDatabase() {
        String dbString = myOfferDB.databaseToString();
        scanFrom.setText(dbString);

    }


    private void populateListView() {

        Cursor cursor = myProductDB.getSomeRows();

        String[] fromFieldNames = new String[]{ProductDatabase.PRODUCT_NAME,  ProductDatabase.PRODUCT_PRICE, ProductDatabase.PRODUCT_RRP, ProductDatabase.PRODUCT_SAVING};
        int[] toViewIDs = new int[]{R.id.textViewProductDes, R.id.textViewPrice, R.id.textViewRRP, R.id.textViewSaving};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.offer_layout, cursor, fromFieldNames, toViewIDs, 0);
        myListView = (ListView) findViewById(R.id.listViewFromDB);
        myListView.setAdapter(myCursorAdapter);
    }



    public void barcodeButtonOnClick(View v) {
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZBarScannerActivity.class);
            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
                    String result = data.getStringExtra(ZBarConstants.SCAN_RESULT);
                    String product = myProductDB.barcodeQueryDatabase(result);
                    Toast.makeText(this, "Product = " + product, Toast.LENGTH_SHORT).show();
                } else if(resultCode == RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
                    if(!TextUtils.isEmpty(error)) {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

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

//    public void iBeaconButtonOnClick(View v){
//        Intent intent = new Intent(this, NotifyDemoActivity.class);
//        startActivity(intent);
//    }

    private void postNotification(String msg) {
        Intent notifyIntent = new Intent(MainActivity.this, NotifyDemoActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(
                MainActivity.this,
                0,
                new Intent[]{notifyIntent},
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(MainActivity.this)
                .setSmallIcon(R.drawable.tag_icon)
                .setContentTitle(msg)
                .setContentText("Click to view offer")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    public void startMonitoring(final Beacon beacon){

            beaconRegion = new Region("regionId", beacon.getProximityUUID(), beacon.getMajor(), beacon.getMinor());
            beaconManager.startMonitoring(beaconRegion);

            beaconManager.setMonitoringListener(new MonitoringListener() {
                @Override
                public void onEnteredRegion(Region region) {

                    long tsLong = (System.currentTimeMillis()/1000);
                    String UUID = beacon.getProximityUUID().toString();
                    Boolean beaconFound = appDb.beaconQueryDatabase(UUID, beacon.getMajor(), beacon.getMinor());

                    if (!beaconFound) {

                        postNotification(myOfferDB.getOffer(UUID, beacon.getMajor(), beacon.getMinor()));
                        String distance = String.format("(%.2fm)", Utils.computeAccuracy(beacon));
                        Log.d("DISTANCE", "DIstance: " + distance);
                        appDb.insertBeacon(UUID, beacon.getMajor(), beacon.getMinor(), tsLong);
                        beaconManager.stopMonitoring(beaconRegion);
                        connectToService();

                    }else if(beaconFound){
                        long timestamp = appDb.getTimestamp(UUID, beacon.getMajor(), beacon.getMinor());
                        Log.d("TIMESTAMP", "Timestamp: " + timestamp);
                        String distance = String.format("(%.2fm)", Utils.computeAccuracy(beacon));
                        Log.d("DISTANCE", "DIstance: " + distance);
                        if((System.currentTimeMillis()/1000) - timestamp >= 30){
                            appDb.updateTimestamp(UUID, beacon.getMajor(), beacon.getMinor());
                            postNotification(myOfferDB.getOffer(UUID, beacon.getMajor(), beacon.getMinor()));
                            beaconManager.stopMonitoring(beaconRegion);
                            connectToService();
                        }else{
                            beaconManager.stopMonitoring(beaconRegion);
                            connectToService();
                        }

                    }else{
                        beaconManager.stopMonitoring(beaconRegion);
                        connectToService();
                    }
                }

                @Override
                public void onExitedRegion(Region region) {
                    region = new Region("rid", null, null, null);
                    beaconManager.startRanging(region);

                }
            });


    }


}