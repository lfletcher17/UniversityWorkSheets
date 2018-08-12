package com.example.android.eventsuggester;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;

public class SpotifyAuthActivity extends AppCompatActivity implements ConnectionStateCallback {

    private static final String SPOTIFY_CLIENT_ID = "9c7db37d947d41519f7148ad5076f76a";
    private static final String SPOTIFY_REDIRECT_URI = "proto-login://callback";
    private static final int SPOTIFY_REQUEST_CODE = 2018;
    private static final int LOCATION_REQUEST_CODE = 20;
    private String mSpotifyToken;
    private Button mCurrentLocation;
    private Button mManualLocation;
    private Location mAuthorisedLocation;
    private FusedLocationProviderClient mFusedLocationClient;

    public void getCurrentLocation() {
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,new String[]{"ACCESS_COARSE_LOCATION"}, LOCATION_REQUEST_CODE);
        }
        Task task = mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                mAuthorisedLocation = location;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify_auth);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mCurrentLocation = (Button) findViewById(R.id.btn_current_location);
        mCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BUTTONCLICKED", "Current Location");
                getCurrentLocation();
                //NEED TO ADD CODE HERE TO HANDLE SONGKICK API CALL USING LONG AND LAT
            }
        });

        mManualLocation = (Button) findViewById(R.id.btn_manual_location);
        mManualLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SpotifyAuthActivity.this;
                Class destinationActivity = LocationActivity.class;
                Intent locationIntent = new Intent (context, destinationActivity);
                locationIntent.putExtra("spotifyToken", mSpotifyToken);
                startActivity(locationIntent);
            }
        });

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN, SPOTIFY_REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming", "user-follow-read"});
        AuthenticationRequest request = builder.build();
        AuthenticationClient.openLoginActivity(this, SPOTIFY_REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == SPOTIFY_REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            /*
            THIS SHOULD BE THE ACCESS TOKEN WHICH WILL BE USED FOR FURTHER WEB API CALLS TO SPOTIFY
            String accessToken = response.getAccessToken();
            */
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                //FINE, THE APP CAN CONTINUE BUT WE NEED TO STORE THIS ACCESS TOKEN SOMEWHERE
                mSpotifyToken = response.getAccessToken();
                Log.d("SPOTIFYAUTHTOKEN:", mSpotifyToken);
//                Context context = SpotifyAuthActivity.this;
//                Class destinationActivity = LocationActivity.class;
//                Intent locationIntent = new Intent (context, destinationActivity);
//                locationIntent.putExtra("spotifyToken", mSpotifyToken);
//                startActivity(locationIntent);
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
