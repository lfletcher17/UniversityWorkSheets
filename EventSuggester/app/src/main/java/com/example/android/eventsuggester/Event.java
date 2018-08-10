package com.example.android.eventsuggester;

public class Event {
    private String artist;

    public Event (String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return this.artist;
    }
}
