package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;

public class SpotifyAuthActivity extends AppCompatActivity implements ConnectionStateCallback {

    private static final String SPOTIFY_CLIENT_ID = "9c7db37d947d41519f7148ad5076f76a";
    private static final String SPOTIFY_REDIRECT_URI = "proto-login://callback";
    private static final int REQUEST_CODE = 2018;
    private String spotifyToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_auth);
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN, SPOTIFY_REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
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
                //FINE, THE APP CAN CONTINUE BUT WE NEED TO STORE THIS ACCESS TOKEN SOMEWHERE
                spotifyToken = response.getAccessToken();
                Context context = SpotifyAuthActivity.this;
                Class destinationActivity = LocationActivity.class;
                Intent locationIntent = new Intent (context, destinationActivity);
                startActivity(locationIntent);
            } else {
                //SOME ERROR HANDLING FOR IF USER ISN'T AUTHENTICATED?
            }
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("LocationActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("LocationActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Error var1) {
        Log.d("LocationActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("LocationActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("LocationActivity", "Received connection message: " + message);
    }
}
