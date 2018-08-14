package com.example.android.eventsuggester;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsCursorPager;
import kaaes.spotify.webapi.android.models.Pager;
import retrofit.RetrofitError;

public class SpotifyHandler {

    private static final String LONG_TERM = "long_term";
    private static final String MEDIUM_TERM = "medium_term";
    private static final String SHORT_TERM = "short_term";
    private SpotifyApi mSpotifyApi = new SpotifyApi();
    private SpotifyService mSpotify;

    public SpotifyHandler (String spotifyToken) {
        this.mSpotifyApi.setAccessToken(spotifyToken);
        this.mSpotify = mSpotifyApi.getService();
    }

    public Map<Artist, Integer> buildArtists () {

        double totalHits = 0;
        double averageScore = 0;
        Map<String, Artist> artistMap = new HashMap<String, Artist>();
        Map<String, Integer> artistCount = new HashMap<>();

        ArrayList<Artist> artists = new ArrayList<Artist>();
        artists.addAll(getTopArtists(LONG_TERM));
        artists.addAll(getTopArtists(MEDIUM_TERM));
        artists.addAll(getTopArtists(SHORT_TERM));
        artists.addAll(getFollowedArtists());

        for (Artist a : artists) {
            if (!artistCount.containsKey(a.name)) {
                artistMap.put(a.name, a);
                artistCount.put(a.name, 1);
            } else {
                int count = artistCount.get(a.name);
                artistMap.put(a.name, a);
                artistCount.put(a.name, count + 1);
            }
        }

        Map<Artist, Integer> results = new HashMap<Artist, Integer>();

        for (Map.Entry entry : artistCount.entrySet()) {
            results.put(artistMap.get(entry.getKey()), (Integer) entry.getValue());
        }

        return results;

    }

    public static double getMeanScore (Map<Artist, Integer> artists) {

        double count = 0;
        double result = 0;

        for (Map.Entry entry : artists.entrySet()) {
            Artist artist = (Artist) entry.getKey();
            Integer indvCount = (Integer) entry.getValue();
            count = count + indvCount;
        }

        result = count / ((double) artists.entrySet().size());
        return result;
    }

    private ArrayList<Artist> getFollowedArtists() {

        final ArrayList<Artist> followedArtists = new ArrayList<Artist>();
        int followedArtistCount = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SpotifyService.LIMIT, 50);

        try {
            while (followedArtists.size() % 50 == 0 && followedArtists.size()< 1000) {

                ArtistsCursorPager followedArtistPager = mSpotify.getFollowedArtists(map);
                List<Artist> artists = followedArtistPager.artists.items;
                for (Artist a : artists) {
                    followedArtists.add(a);
                }

                if (followedArtistCount == followedArtists.size()) {
                    break;
                }

                followedArtistCount = followedArtists.size();
                map.put("after", followedArtists.get(followedArtists.size()-1).id);

            }
        } catch (RetrofitError error) {
            SpotifyError spotifyError = SpotifyError.fromRetrofitError(error);
            Log.d("ERROR", spotifyError.toString());
        }
        return followedArtists;
    }


    private ArrayList<Artist> getTopArtists(final String term) {

        final ArrayList<Artist> topArtists = new ArrayList<Artist>();
        int offset = 0;
        int topArtistCount = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SpotifyService.LIMIT, 50);
        map.put(SpotifyService.OFFSET, offset);
        map.put(SpotifyService.TIME_RANGE, term);
        try {
            while (topArtists.size() % 50 == 0 && topArtists.size() < 1000) {

                Pager<Artist> topArtistPager = mSpotify.getTopArtists(map);
                List<Artist> artists = topArtistPager.items;
                for (Artist a : artists) {
                    topArtists.add(a);
                }
                if (topArtistCount == topArtists.size()) {
                    break;
                }
                topArtistCount = topArtists.size();
                offset = topArtists.size() + 1;
                map.put(SpotifyService.OFFSET, offset);
            }

        } catch (RetrofitError error) {
            SpotifyError spotifyError = SpotifyError.fromRetrofitError(error);
            Log.d("ERROR", spotifyError.toString());
        }
        return topArtists;
    }



}