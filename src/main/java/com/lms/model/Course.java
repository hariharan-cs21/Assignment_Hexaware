package com.lms.model;

import java.time.LocalDate;

public class Course {
    private int id;
    private String title;
    private double fee;
    private double disount;
    private LocalDate publish_date;
    private Track track;
    public Course(){}
    public Course(int id, String title, double fee, double disount, LocalDate publish_date, Track track) {
        this.id = id;
        this.title = title;
        this.fee = fee;
        this.disount = disount;
        this.publish_date = publish_date;
        this.track = track;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getDisount() {
        return disount;
    }

    public void setDisount(double disount) {
        this.disount = disount;
    }

    public LocalDate getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(LocalDate publish_date) {
        this.publish_date = publish_date;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fee=" + fee +
                ", disount=" + disount +
                ", publish_date='" + publish_date + '\'' +
                ", track=" + track +
                '}';
    }
}
