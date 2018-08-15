package com.example.android.eventsuggester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity {

    private String mSpotifyToken;
    private Event mEvent;
    private SpotifyHandler mSpotifyHandler;
    private TextView mEventDesc;
    private ListView mPerformers;
    private ArrayAdapter<String> supportActAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);
        Intent intent = getIntent();
        mSpotifyToken = intent.getStringExtra("spotifyToken");

        mEvent = intent.getParcelableExtra("selectedEvent");
        mEventDesc = (TextView) findViewById(R.id.event_description);
        mEventDesc.setText(mEvent.getEventName());

        mPerformers = (ListView) findViewById(R.id.event_performers);


        ArrayList<String> supportActs = new ArrayList<String>();

        for (SongKickArtist s : mEvent.getPerformers()) {
            if (!s.getBilling().equals("headline")) {
                Log.d("support act found", s.getName());
                supportActs.add("Support: " + s.getName());
            }
        }

        supportActAdapter = new ArrayAdapter<String>(this, R.layout.event_performer_list_item, supportActs);

        // Set The Adapter
        mPerformers.setAdapter(supportActAdapter);


    }


}
