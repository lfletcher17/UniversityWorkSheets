package com.example.android.eventsuggester;

public class TestClass {

    private static String exampleArtist = "The Streets";

    public static void main (String[] args) {
        String[] splitted = exampleArtist.split("\\s+");
        String artistFor = "";
        for (int i = 0; i < splitted.length; i++) {
            if(i > 0) {
                artistFor = artistFor + "+";
            }
            artistFor = artistFor + splitted[i];
        }
        System.out.println(artistFor);
    }
}
