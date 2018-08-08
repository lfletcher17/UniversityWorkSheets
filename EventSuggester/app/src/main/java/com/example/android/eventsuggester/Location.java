package com.example.android.eventsuggester;

public class Location {

    private int id;
    private String city;
    private String state;
    private String country;
    private String lng;
    private String lat;

    public Location (int id, String city, String country, String lng, String lat) {
        this.id = id;
        this.city = city;
        this.state = null;
        this.country = country;
        this.lng = lng;
        this.lat = lat;
    }

    public Location (int id, String city, String state, String country, String lng, String lat) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public String toString() {
        if (state != null) {
            return this.city + ", " + this.state + ", " + this.country;
        } else {
            return this.city + ", " + this.country;
        }
    }
}
