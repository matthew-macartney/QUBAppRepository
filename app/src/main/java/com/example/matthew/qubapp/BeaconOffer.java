package com.example.matthew.qubapp;

import java.io.Serializable;

/**
 * Created by Matthew on 26/08/2015.
 */
public class BeaconOffer implements Serializable {

    private int id;
    private String description;
    private String store;
    private String UUID;
    private int major;
    private int minor;
    private int distance;
    private String expiry;
    private float latitude;
    private float longitude;
    private String icon;
    private String dateReceived;

    public BeaconOffer(){
        super();
    }

    public BeaconOffer(int id, String description, String store, String UUID, int major, int minor, int distance, String expiry, float latitude, float longitude, String icon, String dateReceived) {
        this.id = id;
        this.description = description;
        this.store = store;
        this.UUID = UUID;
        this.major = major;
        this.minor = minor;
        this.distance = distance;
        this.expiry = expiry;
        this.latitude = latitude;
        this.longitude = longitude;
        this.icon = icon;
        this.dateReceived = dateReceived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }



}
