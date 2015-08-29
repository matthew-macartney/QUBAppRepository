package com.example.matthew.qubapp.Model;

import java.io.Serializable;

/**
 * Created by Matthew on 26/08/2015.
 */
public class Offer implements Serializable{

    private int id;
    private String name;
    private String shop;
    private String expiry;
    private float latitude;
    private float longitude;
    private String icon;

    public Offer(){
        super();
    }

    public Offer(int id, String name, String shop, String expiry, float latitude, float longitude, String icon) {
        this.id = id;
        this.name = name;
        this.shop = shop;
        this.expiry = expiry;
        this.latitude = latitude;
        this.longitude = longitude;
        this.icon = icon;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
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

    public String toString(){
        return name;
    }


}
