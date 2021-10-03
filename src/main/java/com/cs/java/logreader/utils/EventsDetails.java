package com.cs.java.logreader.utils;

import com.cs.java.logreader.models.EventDetails;
import com.cs.java.logreader.models.ServerEventDetails;

import java.util.ArrayList;
import java.util.List;

public class EventsDetails {
    private List<EventDetails> eventsDetails = new ArrayList<>();
    private List<ServerEventDetails> serverEventDetails = new ArrayList<>();

    public EventsDetails() {}

    public List<EventDetails> getEventDetails() {
        return eventsDetails;
    }

    public void setEventDetails(List<EventDetails> eventsDetails) {
        this.eventsDetails = eventsDetails;
    }

    public List<ServerEventDetails> getServerEventDetails() {
        return serverEventDetails;
    }

    public void setServerEventDetails(List<ServerEventDetails> serverEventDetails) {
        this.serverEventDetails = serverEventDetails;
    }
}
