package com.example.matthew.qubapp;


import com.jaalee.sdk.*;
import com.jaalee.sdk.Beacon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 28/08/2015.
 */
public class BeaconFinder {

    Region openRegion = new Region("rid", null, null, null);

    private BeaconManager beaconManager;

    public BeaconFinder(){
        super();
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




}
