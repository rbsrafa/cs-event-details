package com.cs.java.logreader.models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Details {
    @Id
    private String id;
    private long duration;
    private boolean alert;

    public Details() {}

    public Details(String id, long duration) {
        this.id = id;
        this.duration = duration;
    }

    public Details(String id, long duration, boolean alert) {
        this.id = id;
        this.duration = duration;
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", duration=" + duration +
                ", alert=" + alert +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
