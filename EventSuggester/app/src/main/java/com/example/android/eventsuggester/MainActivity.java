package com.example.android.eventsuggester;


import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SpotifyPlayer.NotificationCallback, ConnectionStateCallback,
        LoaderManager.LoaderCallbacks<String> {

    private static final String SPOTIFY_CLIENT_ID = "9c7db37d947d41519f7148ad5076f76a";
    private static final String SPOTIFY_REDIRECT_URI = "proto-login://callback";
    private static final int REQUEST_CODE = 2018;
    private Player mPlayer;
    private static final int CREATE_EVENT_DATA_LOADERID = 22;
    private static final String CREATE_EVENT_DATA_LOCATION = "event location";
    private String mLocation = "London";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spotify Auth code
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN, SPOTIFY_REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        getSupportLoaderManager().initLoader(CREATE_EVENT_DATA_LOADERID, null, this);
    }

    private void createEventData () {
        //ALL THIS LOADER CODE BASICALLY ENSURE WE DON'T KEEP MAKING SAME NETWORK CALL AGAIN AND AGAIN
        Bundle queryBundle = new Bundle();
        queryBundle.putString(CREATE_EVENT_DATA_LOCATION, mLocation);
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> createEventDataLoader = loaderManager.getLoader(CREATE_EVENT_DATA_LOADERID);
        if (createEventDataLoader == null) {
            loaderManager.initLoader(CREATE_EVENT_DATA_LOADERID, queryBundle, this);
        } else {
            loaderManager.restartLoader(CREATE_EVENT_DATA_LOADERID, queryBundle, this);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                if (args == null) {
                    return;
                }
                //SET A LOADING INDICATOR HERE!!!
            }
            @Override
            public String loadInBackground() {
                String queryLocation = args.getString(CREATE_EVENT_DATA_LOCATION);
                if (queryLocation == null || TextUtils.isEmpty(queryLocation)) {
                    return null;
                }
                try {
                    String result = SongKickUtils.getResponseFromHttpUrl(SongKickUtils.buildLocationUrl(queryLocation));
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
        //STOP SHOWING LOADER AND DISPLAY RESULTS HERE!!
        if (data != null && !data.equals("")) {
            //show the results
        } else {
            //show some error message?
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //??
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            /*
            THIS SHOULD BE THE ACCESS TOKEN WHICH WILL BE USED FOR FURTHER WEB API CALLS TO SPOTIFY
            String accessToken = response.getAccessToken();
            */
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                //CALL OUR METHOD THAT CREATES THE EVENT DATA
                createEventData();
//                Config playerConfig = new Config(this, response.getAccessToken(), SPOTIFY_CLIENT_ID);
//                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
//                    @Override
//                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
//                        mPlayer = spotifyPlayer;
//                        mPlayer.addConnectionStateCallback(MainActivity.this);
//                        mPlayer.addNotificationCallback(MainActivity.this);
//                    }
//                    @Override
//                    public void onError(Throwable throwable) {
//                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
//                    }
//                });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        Log.d("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");

        // This is the line that plays a song.
        mPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0);
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Error var1) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }
}
