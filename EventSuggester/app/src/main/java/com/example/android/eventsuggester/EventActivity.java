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

public class EventActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String>, EventAdapter.EventAdapterOnClickHandler {

    private static final int EVENT_DATA_LOADERID = 23;
    private static final String SEARCH_EVENT = "events";
    private Location mLocation;
    private String mSpotifyToken;
    private String exampleArtist = "The Streets";
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
        findEvent();
    }

    private void findEvent() {
        //THIS NEEDS TO BE UPDATED TO DEAL WITH ALL SPOTIFY ARTISTS!!!
        String eventQuery = exampleArtist;
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_EVENT, eventQuery);
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> createEventDataLoader = loaderManager.getLoader(EVENT_DATA_LOADERID);
        if (createEventDataLoader == null) {
            loaderManager.initLoader(EVENT_DATA_LOADERID, queryBundle, this).forceLoad();
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
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                if (args == null) {
                    return;
                }
                mLoadingIndicator.setVisibility(View.VISIBLE);
                forceLoad();
            }
            @Override
            public String loadInBackground() {
                String queryEvent = args.getString(SEARCH_EVENT);
                if (queryEvent == null || TextUtils.isEmpty(queryEvent)) {
                    return null;
                }
                try {
                    //THIS IS WHERE THE ACTUAL WORK IS TAKING PLACE! - THIS WILL NEED TO BE AMMENDED TO ACCEPT AN ARRAYLIST OF EVENTS
                    String result = SongKickUtils.getResponseFromHttpUrl(SongKickUtils.buildEventSearchUrl(String.valueOf(mLocation.getId()), queryEvent));
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null && !data.equals("")) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            ArrayList<Event> eventResults = new ArrayList<Event>();
            try {
                eventResults = SongKickUtils.getEvent(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (eventResults.isEmpty()) {
                showErrorMessage();
            } else {
                mEventAdapter.setEventData(eventResults);
                showDataView();
            }
        } else {

        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //??
    }
}
