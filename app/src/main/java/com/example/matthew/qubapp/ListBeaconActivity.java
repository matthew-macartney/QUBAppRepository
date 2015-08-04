package com.example.matthew.qubapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.jaalee.sdk.Beacon;
import com.jaalee.sdk.BeaconManager;
import com.jaalee.sdk.RangingListener;
import com.jaalee.sdk.Region;
import com.jaalee.sdk.ServiceReadyCallback;
import com.jaalee.sdk.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 04/08/2015.
 */
public class ListBeaconActivity extends Activity {

    private static final String TAG = ListBeaconActivity.class.getSimpleName();

    public static final String EXTRAS_TARGET_ACTIVITY = "extrasTargetActivity";
    public static final String EXTRAS_BEACON = "extrasBeacon";

    private static final int REQUEST_ENABLE_BT = 1234;

    private static final String JAALEE_BEACON_PROXIMITY_UUID = "EBEFD083-70A2-47C8-9837-E7B5634DF524";//Jaalee BEACON Default UUID
    private static final Region ALL_BEACONS_REGION = new Region("rid", null, null, null);

    private BeaconManager beaconManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);




        // Configure verbose debug logging.
        L.enableDebugLogging(false);

        // Configure BeaconManager.
        beaconManager = new BeaconManager(this);
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
                        getActionBar().setSubtitle("Found beacons: " + JaaleeBeacons.size());


                    }
                });
            }
        });
    }

    private List<Beacon> filterBeacons(List<Beacon> beacons) {
        List<Beacon> filteredBeacons = new ArrayList<Beacon>(beacons.size());
        for (Beacon beacon : beacons)
        {
//    	only detect the Beacon of Jaalee
//    	if ( beacon.getProximityUUID().equalsIgnoreCase(JAALEE_BEACON_PROXIMITY_UUID) )
            {
                Log.i("JAALEE", "JAALEE:" + beacon.getBattLevel());
                filteredBeacons.add(beacon);
            }
        }
        return filteredBeacons;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        beaconManager.disconnect();

        super.onDestroy();
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

    @Override
    protected void onStop() {
        beaconManager.stopRanging(ALL_BEACONS_REGION);

        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                connectToService();
            } else {
                Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void connectToService() {


        beaconManager.connect(new ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(ALL_BEACONS_REGION);
            }
        });
    }



}
