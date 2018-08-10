package com.example.android.eventsuggester;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public final class SongKickUtils {

    private static final String TAG = SongKickUtils.class.getSimpleName();
    private static final String SONGKICK_URL = "https://api.songkick.com/api/3.0";
    private static final String SONGKICK_API_KEY = "F51wCDEQKz2g8ZXz";

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

    public static String removeSpaces (String input) {
        String[] splitted = input.split("\\s+");
        String artistFor = "";
        for (int i = 0; i < splitted.length; i++) {
            if(i > 0) {
                artistFor = artistFor + "+";
            }
            artistFor = artistFor + splitted[i];
        }
        System.out.println(artistFor);
        return artistFor;
    }

    public static URL buildLocationUrl(String locationQuery) {
        String urlString = SONGKICK_URL + "/search/locations.json?query=" + locationQuery +"&apikey=" + SONGKICK_API_KEY;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static ArrayList<Location> getLocation(String locationQueryResult) throws JSONException {
        JSONObject rawJSON = new JSONObject(locationQueryResult);
        Log.d("RAWJSON", rawJSON.toString());
        JSONObject resultsPage = rawJSON.getJSONObject("resultsPage");
        Log.d("RESULTSPAGE", resultsPage.toString());
        JSONObject results = resultsPage.getJSONObject("results");
        Log.d("RESULTS", results.toString());
        JSONArray locations = results.getJSONArray("location");
        Log.d("LOCATIONS", results.toString());

        ArrayList<Location> list = new ArrayList<Location>();

        for(int i=0; i<locations.length(); i++){
            JSONObject obj = locations.getJSONObject(i);
            Log.d("LOOPOBJ" + (i + 1), obj.toString());
            JSONObject metro = obj.getJSONObject("metroArea");
            Log.d("METROAREA", metro.toString());
            int metroID = metro.getInt("id");
            String city = metro.getString("displayName");
            JSONObject countryObj = metro.getJSONObject("country");
            String country = countryObj.getString("displayName");
            String lng = metro.getString("lng");
            String lat = metro.getString("lat");
            if (metro.has("state")) {
                JSONObject stateObj = metro.getJSONObject("state");
                String state = stateObj.getString("displayName");
                Location location = new Location(metroID, city, state, country, lng, lat);
                Log.d("LISTED LOCATION", location.toString());
                list.add(location);
            } else {
                Location location = new Location(metroID, city, country, lng, lat);
                Log.d("LISTED LOCATION", location.toString());
                list.add(location);
            }
        }
        return list;
    }

    public static URL buildEventSearchUrl(String metroID, String artist) {
        String urlString = SONGKICK_URL + "/events.json?" + "location=sk:" + metroID + "&artist_name=" + removeSpaces(artist) +"&apikey=" + SONGKICK_API_KEY;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static ArrayList<Event> getEvent(String eventQueryResult) throws JSONException {
        JSONObject rawJSON = new JSONObject(eventQueryResult);
        Log.d("RAWJSON", rawJSON.toString());
        JSONObject resultsPage = rawJSON.getJSONObject("resultsPage");
        Log.d("RESULTSPAGE", resultsPage.toString());
        JSONObject results = resultsPage.getJSONObject("results");
        Log.d("RESULTS", results.toString());
        JSONArray events = results.getJSONArray("event");
        Log.d("EVENTS", events.toString());

        ArrayList<Event> list = new ArrayList<Event>();

        //EVENT OBJECT REQUIRES THE FOLLOWING FIELD VARS
        //private int eventID;
        //private String eventName;
        //private String venue;
        //private String venue;
        //private String date;
        //private String uri;
        //private ArrayList<SongKickArtist> artists;
        for(int i=0; i<events.length(); i++){
            JSONObject obj = events.getJSONObject(i);
            Log.d("LOOPOBJ" + (i + 1), obj.toString());
            int eventID = obj.getInt("id");
            Log.d("ID", String.valueOf(eventID));
            String eventName = obj.getString("displayName");
            Log.d("EVENTNAME", eventName);
            JSONObject venue = obj.getJSONObject("venue");
            String venueName = venue.getString("displayName");
            Log.d("VENUE", venueName);
            JSONObject start = obj.getJSONObject("start");
            String date = start.getString("date");
            Log.d("DATE", date);
            String uri = obj.getString("uri");
            JSONArray performers = obj.getJSONArray("performance");
            Log.d("PERFORMERS", performers.toString());
            for (int j = 0; j < performers.length(); j++) {

            }

//            int metroID = metro.getInt("id");
//            String city = metro.getString("displayName");
//            JSONObject countryObj = metro.getJSONObject("country");
//            String country = countryObj.getString("displayName");
//            String lng = metro.getString("lng");
//            String lat = metro.getString("lat");
//            if (metro.has("state")) {
//                JSONObject stateObj = metro.getJSONObject("state");
//                String state = stateObj.getString("displayName");
//                Location location = new Location(metroID, city, state, country, lng, lat);
//                Log.d("LISTED LOCATION", location.toString());
//                list.add(location);
//            } else {
//                Location location = new Location(metroID, city, country, lng, lat);
//                Log.d("LISTED LOCATION", location.toString());
//                list.add(location);
//            }
        }
        return list;
    }
}
