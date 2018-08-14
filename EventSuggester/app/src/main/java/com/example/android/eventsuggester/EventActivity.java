package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Artist;

public class EventActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String>, EventAdapter.EventAdapterOnClickHandler {

    private static final int EVENT_DATA_LOADERID = 23;
    private static final int ARTIST_DATA_LOADERID = 24;
    private static final String SEARCH_EVENT = "events";
    private static final String SEARCH_ARTISTS = "artists";
    private String mlocationSkID;
    private String mSpotifyToken;
    private Map<Artist, Integer> mFollowedArtistsMap = new HashMap<Artist, Integer>();
    private ArrayList<Event> mEventResults = new ArrayList<Event>();
    private ProgressBar mLoadingIndicator;
    private TextView mEventErrorMessageDisplay;

    private int NUM_LIST_ITEMS = 100;
    private RecyclerView mRecyclerView;
    private EventAdapter mEventAdapter;

    private SpotifyHandler mSpotifyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Intent intent = getIntent();
        mlocationSkID = intent.getStringExtra("skID");
        mSpotifyToken = intent.getStringExtra("spotifyToken");
        mSpotifyHandler = new SpotifyHandler(mSpotifyToken);

        mEventErrorMessageDisplay = (TextView) findViewById(R.id.events_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_events);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mEventAdapter = new EventAdapter(this);
        mRecyclerView.setAdapter(mEventAdapter);

        getSupportLoaderManager().initLoader(ARTIST_DATA_LOADERID, null, this);
        getSupportLoaderManager().initLoader(EVENT_DATA_LOADERID, null, this);

        getArtists();
    }

    private void getArtists() {
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_ARTISTS, String.valueOf("search_artist"));
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> createArtistDataLoader = loaderManager.getLoader(ARTIST_DATA_LOADERID);
        if (createArtistDataLoader == null) {
            loaderManager.initLoader(ARTIST_DATA_LOADERID, queryBundle, this);
        } else {
            loaderManager.restartLoader(ARTIST_DATA_LOADERID, queryBundle, this);
        }

    }

    private void findEvent() {
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_EVENT, String.valueOf("search_event"));
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> createEventDataLoader = loaderManager.getLoader(EVENT_DATA_LOADERID);
        if (createEventDataLoader == null) {
            loaderManager.initLoader(EVENT_DATA_LOADERID, queryBundle, this);
        } else {
            loaderManager.restartLoader(EVENT_DATA_LOADERID, queryBundle, this);
        }
    }

    private void showDataView() {
        mEventErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mEventErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Event selectedEvent) {
        Context context = this;
        Toast.makeText(context, selectedEvent.toString(), Toast.LENGTH_SHORT)
                .show();
//        mLocation = selectedLocation;
//        Context context = LocationActivity.this;
//        Class destinationActivity = EventActivity.class;
//        Intent eventIntent = new Intent (context, destinationActivity);
//        eventIntent.putExtra("location", mLocation);
//        eventIntent.putExtra("spotifyToken", mSpotifyToken);
//        startActivity(eventIntent);
    }

    @Override
    public Loader<String> onCreateLoader(final int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            private Map<Artist, Integer> loaderFollowedArtistsMap = new HashMap<Artist, Integer>();
            private ArrayList<Event> loaderEventResults = new ArrayList<Event>();

            @Override
            protected void onStartLoading() {
                if (!loaderFollowedArtistsMap.isEmpty() && !loaderEventResults.isEmpty()) {
                    Log.d("realises there", "is data");
                    deliverResult("");
                    return;
                }
                if (id == 23) {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
                if (id == 24) {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }
            @Override
            public String loadInBackground() {
//                if ((args.getString(SEARCH_ARTISTS) == null || TextUtils.isEmpty(args.getString(SEARCH_ARTISTS))) &&
//                        (args.getString(SEARCH_EVENT) == null || TextUtils.isEmpty(args.getString(SEARCH_EVENT)))) {
//                    return null;
//                }
                if(id == 23) {
                    try {
                        String result = SongKickUtils.getResponseFromHttpUrl(SongKickUtils.buildEventSearchUrl(mlocationSkID, "The Streets"));
                        try {
                            ArrayList<Event> events = SongKickUtils.getEvent(result);
                            for (int j = 0; j < events.size(); j++) {
                                loaderEventResults.add(events.get(j));
                            }
                            mEventResults.addAll(events);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return null;
                        }
//                        for (Map.Entry entry : mFollowedArtistsMap.entrySet()) {
//                            Artist artist = (Artist) entry.getKey();
//                            Log.d("CALLINGSONGKICK", "EVENTS FOR" + artist.name);
//                            String result = SongKickUtils.getResponseFromHttpUrl(SongKickUtils.buildEventSearchUrl(mlocationSkID, artist.name));
//                            try {
//                                ArrayList<Event> events = SongKickUtils.getEvent(result);
//                                for (int j = 0; j < events.size(); j++) {
//                                    mEventResults.add(events.get(j));
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if (id == 24) {
                    loaderFollowedArtistsMap = mSpotifyHandler.buildArtists();
                    mFollowedArtistsMap = loaderFollowedArtistsMap;
                    double averageScore = SpotifyHandler.getMeanScore(mFollowedArtistsMap);
                    return "";
                }
                return null;
            }

            @Override
            public void deliverResult(String s) {
                mFollowedArtistsMap = loaderFollowedArtistsMap;
                mEventResults = loaderEventResults;
                super.deliverResult(s);
            }

        };





    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (loader.getId() == 23) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            ArrayList<Event> eventResults = new ArrayList<Event>();
            if (mEventResults.isEmpty()) {
                showErrorMessage();
            } else {
                Collections.sort(mEventResults);
                mEventAdapter.setEventData(mEventResults);
                showDataView();
            }
        } else if (loader.getId() == 24) {
            findEvent();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //??

    }

}
