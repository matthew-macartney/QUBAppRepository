package com.example.matthew.qubapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.jaalee.sdk.BeaconManager;
import com.jaalee.sdk.Region;
import com.jaalee.sdk.ServiceReadyCallback;

/**
 * Created by Matthew on 05/08/2015.
 */
public class BeaconHelper extends Activity {

    private static final String TAG = ListBeaconActivity.class.getSimpleName();

    public static final String EXTRAS_TARGET_ACTIVITY = "extrasTargetActivity";
    public static final String EXTRAS_BEACON = "extrasBeacon";

    private static final int REQUEST_ENABLE_BT = 1234;

    private static final String JAALEE_BEACON_PROXIMITY_UUID = "EBEFD083-70A2-47C8-9837-E7B5634DF524";//Jaalee BEACON Default UUID
    private static final Region ALL_BEACONS_REGION = new Region("rid", null, null, null);

    BeaconManager beaconManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void connectToService(Region region) {
        final Region myRegion = region;
        beaconManager.connect(new ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(myRegion);
            }
        });
    }
}
