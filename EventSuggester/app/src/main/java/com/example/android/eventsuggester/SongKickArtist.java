package com.example.android.eventsuggester;

public class SongKickArtist {

    private int id;
    private String name;
    private String uri;
    private String billing;

    public SongKickArtist (int id, String name, String uri, String billing) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.billing = billing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getBilling() {
        return billing;
    }

    public void setBilling(String billing) {
        this.billing = billing;
    }
}
