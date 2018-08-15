package com.example.android.eventsuggester;

import kaaes.spotify.webapi.android.models.Artist;
import android.os.Parcel;

public class SpotifyArtist {

    private Artist artist;
    private int score;

    public SpotifyArtist (Artist artist, int Score) {
        this.artist = artist;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
