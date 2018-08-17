package com.example.android.eventsuggester;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Artist;

public class EventLoader extends AsyncTaskLoader<ArrayList<Event>> {


    private SpotifyHandler mSpotifyHandler;

//TODO locationID will be stored as a preference, will need to be retrieved as such
    private String mLocationSkID;
    private ArrayList<Event> cachedData;
    private Map<String, SpotifyArtist> mSpotifyArtists;

//    TODO below field variables need to be established via constructor
    private Boolean includeRelatedArtists;
    private Calendar minDate;
    private Calendar maxDate;


    public EventLoader(Context context, SpotifyHandler spotifyHandler, String skLocation, Calendar minDate, Calendar maxDate, Boolean includeRelatedArtists) {
        super(context);
        mSpotifyHandler = spotifyHandler;
        this.mLocationSkID = skLocation;
        this.minDate = minDate;
        this.maxDate = maxDate;
        this.includeRelatedArtists = includeRelatedArtists;
    }

    @Override
    protected void onStartLoading() {
        if (cachedData == null) {
            forceLoad();
        } else {
            super.deliverResult(cachedData);
        }

    }

//    TODO find way of publishing progress to the UI?? Like progressUpdate() for AsyncTask
    @Override
    public ArrayList<Event> loadInBackground() {

        ArrayList<Event> results = new ArrayList<Event>();

//        TODO fix this to accept params - basically whether to use dates for songkick and related artists for spotify, will need to pass values to Loader constructor
        mSpotifyArtists = mSpotifyHandler.buildArtists(2000, includeRelatedArtists);
        ArrayList<Event> allEvents = new ArrayList<Event>();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String startFormatted = format1.format(minDate.getTime());
        String futureFormatted = format1.format(maxDate.getTime());

        try {
            allEvents = SongKickUtils.getEventsForMetro(mLocationSkID,startFormatted,futureFormatted);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //LOOP THE EVENTS AND SEE IF ANY HITS WITH ARTISTS
        for (Event result : allEvents) {
            for (SongKickArtist a : result.getPerformers()) {
                if (mSpotifyArtists.containsKey(a.getName())) {
                    results.add(result);
                }
            }
        }
        return results;
    }


    public void deliverResult (ArrayList<Event> data) {
        cachedData = data;
        super.deliverResult(data);
    }
}
