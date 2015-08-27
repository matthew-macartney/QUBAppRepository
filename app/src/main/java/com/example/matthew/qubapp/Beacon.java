package com.example.matthew.qubapp;

/**
 * Created by Matthew on 26/08/2015.
 */
public class Beacon {

    private int id;
    private String UUID;
    private int major;
    private int minor;
    private long timestamp;

    public Beacon(){
        super();
    }

    public Beacon(int id, String UUID, int major, int minor, long timestamp){
        this.id = id;
        this.UUID = UUID;
        this.major = major;
        this.minor = minor;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }


}
