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
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsCursorPager;
import kaaes.spotify.webapi.android.models.ArtistsPager;
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

    //TODO THIS METHOD NEEDS TO CALL THE DB AND ENSURE THAT BLACKLISTED ARTISTS ARE NEVER RETURNED
    public Map<String, SpotifyArtist> buildArtists (int maxArtists, boolean includeRelatedArtists) {

        Map<String, SpotifyArtist> artistMap = new HashMap<String, SpotifyArtist>();

        ArrayList<Artist> artists = new ArrayList<Artist>();
        artists.addAll(getTopArtists(LONG_TERM, maxArtists - artists.size()));
        artists.addAll(getTopArtists(MEDIUM_TERM, maxArtists - artists.size()));
        artists.addAll(getTopArtists(SHORT_TERM, maxArtists - artists.size()));
        artists.addAll(getFollowedArtists(maxArtists - artists.size()));


        if (includeRelatedArtists) {
            ArrayList<Artist> relatedArtists = new ArrayList<Artist>();
            int count = 0;
            while (relatedArtists.size() + artists.size() < maxArtists && count < artists.size()) {
                Artist artist = artists.get(count);
                Log.d("RELATED", "artists of: " + artists.get(count));
                artists.addAll(getRelatedArtist(artist.id));
                count++;
            }
        }

        int count = 0;
        for (Artist a: artists) {
            if (!artistMap.containsKey(a.name)) {
                artistMap.put(a.name, new SpotifyArtist(a, 1));
            } else {
                SpotifyArtist artist  = artistMap.get(a.name);
                count = artist.getScore();
                artistMap.put(a.name, new SpotifyArtist(a, count +1));
            }
        }

        return artistMap;
    }

    public static double getMeanScore (Map<String, SpotifyArtist> artists) {

        double count = 0;
        double result = 0;

        for (Map.Entry entry : artists.entrySet()) {
            SpotifyArtist artist = (SpotifyArtist) entry.getValue();
            Integer indvCount = artist.getScore();
            count = count + indvCount;
        }

        result = count / ((double) artists.entrySet().size());
        return result;
    }


    private ArrayList<Artist> getFollowedArtists(int maxArtists) {

        final ArrayList<Artist> followedArtists = new ArrayList<Artist>();
        int followedArtistCount = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SpotifyService.LIMIT, 50);

        try {
            while (followedArtists.size() % 50 == 0 && followedArtists.size()< maxArtists) {

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


    private ArrayList<Artist> getTopArtists(final String term, int maxArtists) {

        final ArrayList<Artist> topArtists = new ArrayList<Artist>();
        int offset = 0;
        int topArtistCount = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SpotifyService.LIMIT, 50);
        map.put(SpotifyService.OFFSET, offset);
        map.put(SpotifyService.TIME_RANGE, term);
        try {
            while (topArtists.size() % 50 == 0 && topArtists.size() < maxArtists) {

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

    private ArrayList<Artist> getRelatedArtist(final String id) {

        final ArrayList<Artist> topArtists = new ArrayList<Artist>();
        try {
            Artists relatedArtists = mSpotify.getRelatedArtists(id);
            topArtists.addAll(relatedArtists.artists);
        } catch (RetrofitError e){
            Log.d("", "TOO MANY SPOTIFY REQUESTS!");
        }

        return topArtists;

    }

    public String getArtistID (String name) {
        Artist result = new Artist();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SpotifyService.LIMIT, 1);
        try {
            ArtistsPager topArtistPager = mSpotify.searchArtists(name);
            result = topArtistPager.artists.items.get(0);
        } catch (RetrofitError error) {
            Log.d("error", error.toString());
        }
        return result.id;
    }



}
