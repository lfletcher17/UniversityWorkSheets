package com.example.android.eventsuggester;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public final class SongKickUtils {

    private static final String TAG = SongKickUtils.class.getSimpleName();
    private static final String SONGKICK_URL = "https://api.songkick.com/api/3.0/search";
    private static final String SONGKICK_API_KEY = "F51wCDEQKz2g8ZXz";

    public static URL buildLocationUrl(String locationQuery) {
        String urlString = SONGKICK_URL + "/locations.json?query=" + locationQuery +"&apikey=" + SONGKICK_API_KEY;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static int getLocationID (String locationQueryResult) throws JSONException {
        JSONObject resultJSON = new JSONObject(locationQueryResult);
        JSONObject location = resultJSON.getJSONObject("location");
        JSONObject city = location.getJSONObject("city");
        return city.getInt("id");
    }
}
