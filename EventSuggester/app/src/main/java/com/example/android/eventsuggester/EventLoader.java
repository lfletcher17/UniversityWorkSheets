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
    //THIS WILL ACTUALLY BE STORED AS A PREFERENCE
    private String mLocationSkID;
    private Map<String, SpotifyArtist> mSpotifyArtists;
    //CAN STORE ALL SORTS OF PARAMS HERE I.E. DATE RANGES
    private ArrayList<Event> cachedData;
    private Calendar minDate;
    private Calendar maxDate;


    public EventLoader(Context context, SpotifyHandler spotifyHandler, String skLocation) {
        super(context);
        mSpotifyHandler = spotifyHandler;
        this.mLocationSkID = skLocation;
    }

    public EventLoader(Context context, SpotifyHandler spotifyHandler, String skLocation, Calendar minDate, Calendar maxDate) {
        super(context);
        mSpotifyHandler = spotifyHandler;
        this.mLocationSkID = skLocation;
        this.minDate = minDate;
        this.maxDate = maxDate;
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

        ArrayList<Event> results = new ArrayList<Event>();

        //BASICALLY HAVE A CONDITIONAL HERE TO CALL APPROPRIATE HELPER METHOD BASED OFF OF FIELD VARIABLE VALUES
        if (minDate != null) {
            //DO ALL THE DATE STUFF - DIFFERENT METHODS DEPENDING ON TIMESPAN
            //GET THE ARTISTS AS A MAP OF STRINGS AND SPOTIFY ARTISTS
            mSpotifyArtists = mSpotifyHandler.buildArtists();
            ArrayList<Event> allEvents = new ArrayList<Event>();

            //THIS SCORE WILL BE USED FOR DETERMININING RECOMMENDATIONS ETC.
            double averageScore = SpotifyHandler.getMeanScore(mSpotifyArtists);

            //GET ALL EVENTS FOR SPECIFIED DATES - THESE WILL NEED TO BE PASSED TO LOADER ON CONSTRUCTION
            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            max.add(Calendar.MONTH, 1);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String todayFormatted = format1.format(min.getTime());
            String futureFormatted = format1.format(max.getTime());

            try {
                allEvents = SongKickUtils.getEventsForMetro(mLocationSkID,todayFormatted,futureFormatted);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //LOOP THE EVENTS AND SEE IF ANY HITS WITH ARTISTS
            for (Event result : results) {
                for (SongKickArtist a : result.getPerformers()) {
                    if (mSpotifyArtists.containsKey(a.getName())) {
                        results.add(result);
                    }
                }
            }
        } else {
            //DO THE NON DATE BASED STUFF??
            mSpotifyArtists = mSpotifyHandler.buildArtists();
            double averageScore = SpotifyHandler.getMeanScore(mSpotifyArtists);
            //LOOP THE ARTIST AND GET ANY EVENTS FEATURING THEM
            for (Map.Entry entry : mSpotifyArtists.entrySet()) {

                SpotifyArtist a = (SpotifyArtist) entry.getValue();
                Artist artist = a.getArtist();
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
        }

        //RETURN THE EVENTS
        return results;
    }


    public void deliverResult (ArrayList<Event> data) {
        cachedData = data;
        super.deliverResult(data);
    }
}
