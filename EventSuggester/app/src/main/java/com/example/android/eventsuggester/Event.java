package com.example.android.eventsuggester;

import android.util.Log;

import java.util.ArrayList;

public class Event {

    private int eventID;
    private SongKickArtist headliner = null;
    private String eventName;
    private String venue;
    private String date;
    private String uri;
    private ArrayList<SongKickArtist> performers;

    public Event (int eventId, String eventName, String venue, String date, String uri, ArrayList<SongKickArtist> performers) {
        this.eventID = eventId;
        for (int i = 0; i < performers.size(); i++) {
            if (performers.get(i).getBilling().equals("headline")) {
                Log.d("HEADLINERFOUND", performers.get(i).getName());
                this.headliner = performers.get(i);
            }
        }
        this.eventName = eventName;
        this.venue = venue;
        this.date = date;
        this.uri = uri;
        this.performers = performers;
    }

    @Override
    public String toString() {
        return this.eventName;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public SongKickArtist getHeadliner() {
        return headliner;
    }

    public void setHeadliner(SongKickArtist headliner) {
        this.headliner = headliner;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ArrayList<SongKickArtist> getPerformers() {
        return performers;
    }

    public void setPerformers(ArrayList<SongKickArtist> performers) {
        this.performers = performers;
    }
}
