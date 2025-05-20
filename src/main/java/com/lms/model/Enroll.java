package com.lms.model;

import java.time.LocalDate;

public class Enroll {
    Learner learner;
    Course course;
    LocalDate date_of_enroll;
    String coupone_used;
    double fee_paid;
    public Enroll(){};
    public Enroll(Learner learner, Course course, LocalDate date_of_enroll, String coupone_used, double fee_paid) {
        this.learner = learner;
        this.course = course;
        this.date_of_enroll = date_of_enroll;
        this.coupone_used = coupone_used;
        this.fee_paid = fee_paid;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDate_of_enroll() {
        return date_of_enroll;
    }

    public void setDate_of_enroll(LocalDate date_of_enroll) {
        this.date_of_enroll = date_of_enroll;
    }

    public String getCoupone_used() {
        return coupone_used;
    }

    public void setCoupone_used(String coupone_used) {
        this.coupone_used = coupone_used;
    }

    public double getFee_paid() {
        return fee_paid;
    }

    public void setFee_paid(double fee_paid) {
        this.fee_paid = fee_paid;
    }

    @Override
    public String toString() {
        return "Enroll{" +
                "learner=" + learner +
                ", course=" + course +
                ", date_of_enroll=" + date_of_enroll +
                ", coupone_used='" + coupone_used + '\'' +
                ", fee_paid=" + fee_paid +
                '}';
    }
}
