package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;

public class EventDetailActivity extends AppCompatActivity implements EventPerformerAdapter.EventPerformerAdapterOnClickHandler {

    private String mSpotifyToken;
    private SpotifyHandler mSpotifyHandler;
    private Event mEvent;
    private TextView mEventDesc;
    private TextView mVenueDesc;
    private TextView mDateDesc;

    private RecyclerView mRecyclerView;
    private EventPerformerAdapter mPerformerAdapter;

    private Button mTicketInfo;

    private ArrayList<SongKickArtist> mPerformersList = new ArrayList<SongKickArtist>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Intent intent = getIntent();
        mSpotifyToken = intent.getStringExtra("spotifyToken");
        mSpotifyHandler = new SpotifyHandler(mSpotifyToken);

        mEvent = intent.getParcelableExtra("selectedEvent");
        mEventDesc = (TextView) findViewById(R.id.event_description);
        mEventDesc.setText(mEvent.getEventName());
        mVenueDesc = (TextView) findViewById(R.id.venue_description);
        mVenueDesc.setText(mEvent.getVenue());
        mDateDesc = (TextView) findViewById(R.id.date_description);
        mDateDesc.setText(mEvent.formattedDate());

        mPerformersList = mEvent.getPerformers();

        mTicketInfo = (Button) findViewById(R.id.btn_ticket_info);
        mTicketInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSongKickPage();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_eventDetailsPerformers);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mPerformerAdapter = new EventPerformerAdapter(this);
        mPerformerAdapter.setEventData(mPerformersList);
        mRecyclerView.setAdapter(mPerformerAdapter);

    }

    public void openSongKickPage() {
        String uri = mEvent.getUri();
        Uri webpage = Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //taken from Spotify
    public void openSpotify(SongKickArtist selectedArtist) {
        PackageManager pm = getPackageManager();
        boolean isSpotifyInstalled;
        try {
            pm.getPackageInfo("com.spotify.music", 0);
            isSpotifyInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isSpotifyInstalled = false;
        }
        if (isSpotifyInstalled) {
            Artist artist = mSpotifyHandler.getArtist(selectedArtist.getName());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("spotify:artist:" + artist.id));
            intent.putExtra(Intent.EXTRA_REFERRER,
                    Uri.parse("android-app://" + this.getPackageName()));
            startActivity(intent);
        } else {
            final String appPackageName = "com.spotify.music";
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException ignored) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }
    }


//todo THIS NEEDS TO BE DONE VIA A LOADER
    @Override
    public void onClick(SongKickArtist selectedArtist) {
        openSpotify(selectedArtist);
    }
}
