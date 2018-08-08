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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity implements ConnectionStateCallback,
        LoaderManager.LoaderCallbacks<String>, LocationAdapter.LocationAdapterOnClickHandler {

    private static final String SPOTIFY_CLIENT_ID = "9c7db37d947d41519f7148ad5076f76a";
    private static final String SPOTIFY_REDIRECT_URI = "proto-login://callback";
    private static final int REQUEST_CODE = 2018;
    private static final int LOCATION_DATA_LOADERID = 22;
    private static final String SEARCH_LOCATION = "event location";
    private String spotifyToken;
    private String mLocation;

    private EditText mLocationSearchBoxEditText;

    private TextView mLocationErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private RecyclerView mRecyclerView;
    private LocationAdapter mLocationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mLocationSearchBoxEditText = (EditText) findViewById(R.id.location_search_box);
        mLocationErrorMessageDisplay = (TextView) findViewById(R.id.location_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_location);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mLocationAdapter = new LocationAdapter(this);
        mRecyclerView.setAdapter(mLocationAdapter);

        //Spotify Auth code
//        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(SPOTIFY_CLIENT_ID, AuthenticationResponse.Type.TOKEN, SPOTIFY_REDIRECT_URI);
//        builder.setScopes(new String[]{"user-read-private", "streaming"});
//        AuthenticationRequest request = builder.build();
//        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        getSupportLoaderManager().initLoader(LOCATION_DATA_LOADERID, null, this);
    }

    private void findLocation() {
        //ALL THIS LOADER CODE BASICALLY ENSURES WE DON'T KEEP MAKING SAME NETWORK CALL AGAIN AND AGAIN
        String locationQuery = mLocationSearchBoxEditText.getText().toString();
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_LOCATION, locationQuery);
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> createEventDataLoader = loaderManager.getLoader(LOCATION_DATA_LOADERID);
        if (createEventDataLoader == null) {
            loaderManager.initLoader(LOCATION_DATA_LOADERID, queryBundle, this).forceLoad();
        } else {
            loaderManager.restartLoader(LOCATION_DATA_LOADERID, queryBundle, this);
        }
    }

    private void showDataView() {
        mLocationErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLocationErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Location selectedLocation) {
        Context context = this;
        Toast.makeText(context, selectedLocation.toString(), Toast.LENGTH_SHORT)
                .show();
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
                String queryLocation = args.getString(SEARCH_LOCATION);
//                Toast.makeText(LocationActivity.this,
//                        "Reaching loadInBackground with arg of: " + queryLocation, Toast.LENGTH_LONG).show();
                if (queryLocation == null || TextUtils.isEmpty(queryLocation)) {
                    return null;
                }
                try {
                    //THIS IS WHERE THE ACTUAL WORK IS TAKING PLACE!
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
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            ArrayList<Location> locationResults = new ArrayList<Location>();
            try {
                locationResults = SongKickUtils.getLocation(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (locationResults.isEmpty()) {
                showErrorMessage();
            } else {
                mLocationAdapter.setLocationData(locationResults);
                showDataView();
            }
        } else {

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
                //FINE, THE APP CAN CONTINUE
                spotifyToken = response.getAccessToken();
            } else {
                //SOME ERROR HANDLING FOR IF USER ISN'T AUTHENTICATED?
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            findLocation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
