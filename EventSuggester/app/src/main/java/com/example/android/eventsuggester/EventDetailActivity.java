package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity implements EventPerformerAdapter.EventPerformerAdapterOnClickHandler {

    private String mSpotifyToken;
    private Event mEvent;
    private SpotifyHandler mSpotifyHandler;
    private TextView mEventDesc;
    private TextView mVenueDesc;
    private TextView mDateDesc;

    private RecyclerView mRecyclerView;
    private EventPerformerAdapter mPerformerAdapter;

    private ArrayList<SongKickArtist> mPerformersList = new ArrayList<SongKickArtist>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Intent intent = getIntent();
        mSpotifyToken = intent.getStringExtra("spotifyToken");

        mEvent = intent.getParcelableExtra("selectedEvent");
        mEventDesc = (TextView) findViewById(R.id.event_description);
        mEventDesc.setText(mEvent.getEventName());
        mVenueDesc = (TextView) findViewById(R.id.venue_description);
        mVenueDesc.setText(mEvent.getVenue());
        mDateDesc = (TextView) findViewById(R.id.date_description);
        mDateDesc.setText(mEvent.formattedDate());

        mPerformersList = mEvent.getPerformers();


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_eventDetailsPerformers);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mPerformerAdapter = new EventPerformerAdapter(this);
        mPerformerAdapter.setEventData(mPerformersList);
        mRecyclerView.setAdapter(mPerformerAdapter);


    }


    @Override
    public void onClick(SongKickArtist selectedArtist) {
        Context context = this;
        Toast.makeText(context, selectedArtist.getName(), Toast.LENGTH_SHORT)
                .show();
    }
}
