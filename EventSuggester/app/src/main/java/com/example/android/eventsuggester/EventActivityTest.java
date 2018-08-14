package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Artist;


public class EventActivityTest extends AppCompatActivity implements EventAdapter.EventAdapterOnClickHandler {

    private static final int EVENT_DATA_LOADERID = 23;
    private static final String SEARCH_EVENT = "events";
    private String mlocationSkID;
    private String mSpotifyToken;
    private ProgressBar mLoadingIndicator;
    private TextView mEventErrorMessageDisplay;
    private SpotifyHandler mSpotifyHandler;

    private RecyclerView mRecyclerView;
    private EventAdapter mEventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Intent intent = getIntent();
        mlocationSkID = intent.getStringExtra("skID");
        mSpotifyToken = intent.getStringExtra("spotifyToken");
        mSpotifyHandler = new SpotifyHandler(mSpotifyToken);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_events);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mEventAdapter = new EventAdapter(this);
        mRecyclerView.setAdapter(mEventAdapter);

        mEventErrorMessageDisplay = (TextView) findViewById(R.id.events_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        mLoadingIndicator.setVisibility(View.VISIBLE);

        getSupportLoaderManager().initLoader(EVENT_DATA_LOADERID, null, loaderCallbacks);
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
    }


    private LoaderManager.LoaderCallbacks<ArrayList<Event>> loaderCallbacks = new LoaderManager.LoaderCallbacks<ArrayList<Event>>() {
        @Override
        public Loader<ArrayList<Event>> onCreateLoader(int id, Bundle args) {
            Context context = getApplicationContext();
            return new EventLoader(context, mSpotifyHandler, mlocationSkID);
        }

        @Override
        public void onLoadFinished(Loader<ArrayList<Event>> loader, ArrayList<Event> data) {
            if (data == null) {
                showErrorMessage();
            } else {
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                Collections.sort(data);
                mEventAdapter.setEventData(data);
                showDataView();
            }
        }

        @Override
        public void onLoaderReset(Loader<ArrayList<Event>> loader) {
         //
        }
    };




}
