package com.example.android.eventsuggester;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Comparable<Event> {

    private int eventID;
    private SongKickArtist headliner = null;
    private String eventName;
    private String venue;
//    private String date;
    private Date date;
    private String uri;
    private ArrayList<SongKickArtist> performers;

    public Event (int eventId, String eventName, String venue, String date, String uri, ArrayList<SongKickArtist> performers) {
        this.eventID = eventId;
        for (int i = 0; i < performers.size(); i++) {
            if (performers.get(i).getBilling().equals("headline")) {
                this.headliner = performers.get(i);
            }
        }
        this.eventName = eventName;
        this.venue = venue;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        try {
            this.date = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("DATEASSTRING", date.toString());
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public int compareTo(Event o) {
        return this.getDate().compareTo(o.getDate());
    }
}
