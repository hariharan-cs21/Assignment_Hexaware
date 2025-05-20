package com.lms.model;

public class Track {
    private int id;
    private String name;
    public Track() {}
    public Track(int trackId, String name) {
        this.id = trackId;
        this.name = name;
    }

    public int getTrackId() {
        return id;
    }

    public void setTrackId(int trackId) {
        this.id = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
