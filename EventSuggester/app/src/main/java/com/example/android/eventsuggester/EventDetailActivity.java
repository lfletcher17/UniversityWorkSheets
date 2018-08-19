package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
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
    private Button mAddToCalendar;

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
        mAddToCalendar = (Button) findViewById(R.id.btn_add_to_calendar);
        mAddToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCalendar();
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

//    TODO ammend code so that this actually adds event to users calendar and saves songkickID to DB
    //TODO possible... if a user attends an event, the headliner should be stored in db as a favored artist? used first for related artists!!
    //TODO remove duplicates from event activity
    public void addToCalendar () {
        Toast toast = Toast.makeText(this, "addToCalendar", Toast.LENGTH_LONG);
        toast.show();
    }

    //taken from Spotify
    public void openSpotify(String artistID) {
        PackageManager pm = getPackageManager();
        boolean isSpotifyInstalled;
        try {
            pm.getPackageInfo("com.spotify.music", 0);
            isSpotifyInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isSpotifyInstalled = false;
        }
        if (isSpotifyInstalled) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("spotify:artist:" + artistID));
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

    public class GetSpotifyArtistTask extends AsyncTask<SongKickArtist, Void, String> {

        @Override
        protected String doInBackground(SongKickArtist... songKickArtists) {
            SongKickArtist artist = songKickArtists[0];
            String artistID = mSpotifyHandler.getArtistID(artist.getName());
            return artistID;
        }

        @Override
        protected void onPostExecute(String s) {
            openSpotify(s);
        }
    }


    @Override
    public void onClick(SongKickArtist selectedArtist) {

        new GetSpotifyArtistTask().execute(selectedArtist);
    }
}
