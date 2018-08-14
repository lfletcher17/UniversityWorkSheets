package com.example.android.eventsuggester;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Artist;

public class EventLoader extends AsyncTaskLoader<ArrayList<Event>> {

    private SpotifyHandler mSpotifyHandler;
    //THIS WILL ACTUALLY BE STORED AS A PREFERENCE
    private String mLocationSkID;
    private Map<Artist, Integer> mSpotifyArtists;
    private ArrayList<Event> cachedData;


    public EventLoader(Context context, SpotifyHandler spotifyHandler, String skLocation) {
        super(context);
        mSpotifyHandler = spotifyHandler;
        this.mLocationSkID = skLocation;
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
    public ArrayList<Event> loadInBackground() {

        //GET THE ARTISTS
        mSpotifyArtists = mSpotifyHandler.buildArtists();
        ArrayList<Event> results = new ArrayList<Event>();
        //THIS SCORE WILL BE USED FOR DETERMININING RECOMMENDATIONS ETC.
        double averageScore = SpotifyHandler.getMeanScore(mSpotifyArtists);

        //LOOP THE ARTIST AND GET ANY EVENTS FEATURING THEM
        for (Map.Entry entry : mSpotifyArtists.entrySet()) {

            Artist artist = (Artist) entry.getKey();
            Log.d("CALLINGSONGKICK", "EVENTS FOR" + artist.name);
            String result = null;

            //GET THIS ARTISTS EVENTS AS STRING
            try {
                result = SongKickUtils.getResponseFromHttpUrl(SongKickUtils.buildEventSearchUrl(mLocationSkID, artist.name));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //USE ARTIST STRING TO GET EVENT OBJECT
            ArrayList<Event> events = null;
            try {
                events = SongKickUtils.getEvent(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //LOOP EVENTS AND ADD THEM TO RESULTS
            Log.d("got events ", "for");
            for (int i = 0; i < events.size(); i++) {
                results.add(events.get(i));
            }
        }

        //RETURN THE EVENTS
        return results;
    }


    public void deliverResult (ArrayList<Event> data) {
        cachedData = data;
        super.deliverResult(data);
    }
}
