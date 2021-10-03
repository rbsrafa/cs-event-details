package com.cs.java.logreader.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "event_details")
public class EventDetails extends Details {

    public EventDetails() {}

    public EventDetails(String id, long duration) {
        super(id, duration);
    }

    public EventDetails(String id, long duration, boolean alert) {
        super(id, duration, alert);
    }

    @Override
    public String toString() {
        return "EventDetails{" +
                "id=" + this.getId() +
                ", duration=" + this.getDuration() +
                ", alert=" + this.isAlert() +
                '}';
    }

}
