package com.example.android.eventsuggester;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Map;

import kaaes.spotify.webapi.android.models.Artist;

public class ArtistLoader extends AsyncTaskLoader<Map<Artist, Integer>> {

    private SpotifyHandler mSpotifyHandler;
    //THIS WILL ACTUALLY BE STORED AS A PREFERENCE
    private String mLocationSkID;
    private Map<Artist,Integer> cachedData;

    public ArtistLoader (Context context, SpotifyHandler spotifyHandler) {
        super(context);
        mSpotifyHandler = spotifyHandler;
    }

    @Override
    protected void onStartLoading() {
        if (cachedData == null) {
            forceLoad();
        } else {
            super.deliverResult(cachedData);
        }

    }

    @Override
    public Map<Artist, Integer> loadInBackground() {
        cachedData = mSpotifyHandler.buildArtists();
        return cachedData;
    }

    public void deliverResult (Map<Artist, Integer> data) {
        cachedData = data;
        super.deliverResult(data);
    }
}
