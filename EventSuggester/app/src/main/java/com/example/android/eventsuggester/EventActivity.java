package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
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

public class EventActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String>, EventAdapter.EventAdapterOnClickHandler {

    //THERE IS A WAY TO USE THE LOADER FOR DIFFERENT PURPOSES!!! CREATE ANOTHER LOADER ID
    private static final int EVENT_DATA_LOADERID = 23;
    private static final int ARTIST_DATA_LOADERID = 24;
    private static final String SEARCH_EVENT = "events";
    private static final String SEARCH_ARTISTS = "events";
    private Location mLocation;
    private String mSpotifyToken;
    private ArrayList<String> exampleArtists = new ArrayList<String>();
    private ArrayList<Event> mEventResults = new ArrayList<Event>();
    private ProgressBar mLoadingIndicator;
    private TextView mEventErrorMessageDisplay;
    private RecyclerView mRecyclerView;
    private EventAdapter mEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Intent intent = getIntent();
        mLocation = (Location) intent.getParcelableExtra("location");
        mSpotifyToken = intent.getStringExtra("spotifyToken");

        mEventErrorMessageDisplay = (TextView) findViewById(R.id.events_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_events);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mEventAdapter = new EventAdapter(this);
        mRecyclerView.setAdapter(mEventAdapter);
        getSupportLoaderManager().initLoader(EVENT_DATA_LOADERID, null, this);
        getArtists(0);
    }

    private void getArtists(int pageNumber) {
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_ARTISTS, String.valueOf(pageNumber));
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> createArtistDataLoader = loaderManager.getLoader(ARTIST_DATA_LOADERID);
        if (createArtistDataLoader == null) {
            loaderManager.initLoader(ARTIST_DATA_LOADERID, queryBundle, this).forceLoad();
        } else {
            loaderManager.restartLoader(ARTIST_DATA_LOADERID, queryBundle, this).forceLoad();
        }

    }

    private void findEvent() {
        Bundle queryBundle = new Bundle();
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> createEventDataLoader = loaderManager.getLoader(EVENT_DATA_LOADERID);
        if (createEventDataLoader == null) {
            loaderManager.initLoader(EVENT_DATA_LOADERID, queryBundle, this).forceLoad();
        } else {
            loaderManager.restartLoader(EVENT_DATA_LOADERID, queryBundle, this).forceLoad();
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
            @Override
            protected void onStartLoading() {
                if (id == 23) {
                    if (args == null) {
                        return;
                    }
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
                if(id == 23) {
                    try {
                        for (int i = 0; i < exampleArtists.size(); i++) {
                            Log.d("CURRENT ARTIST", exampleArtists.get(i));
                            String result = SongKickUtils.getResponseFromHttpUrl(SongKickUtils.buildEventSearchUrl(String.valueOf(mLocation.getId()), exampleArtists.get(i)));
                            try {
                                ArrayList<Event> events = SongKickUtils.getEvent(result);
                                for (int j = 0; j < events.size(); j++) {
                                    mEventResults.add(events.get(j));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if (id == 24) {
                    //HERE IS WHERE WE MAKE THE CALL TO SPOTIFY TO OBTAIN OUR LIST OF ARTISTS AS A JSON STRING
                    return "";
                }
                return null;
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
            //THIS IS WHERE WE TAKE THE JSON STRING OF ARTISTS AND ADD THEM TO OUR ARRAYLIST
            //IF NOT END OF RESULTS CALL findArtists() again
            Log.d("TRIED TO GET ARTISTS", "");
            exampleArtists.add("The Streets");
            exampleArtists.add("Arctic Monkeys");
            exampleArtists.add("Mariobou State");
            findEvent();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //??
    }
}
