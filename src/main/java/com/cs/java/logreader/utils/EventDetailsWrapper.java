package com.cs.java.logreader.utils;

import com.cs.java.logreader.models.EventDetails;
import com.cs.java.logreader.models.ServerEventDetails;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsWrapper {
    private List<EventDetails> eventDetails = new ArrayList<>();
    private List<ServerEventDetails> serverEventDetails = new ArrayList<>();

    public EventDetailsWrapper() {}

    public List<EventDetails> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(List<EventDetails> eventsDetails) {
        this.eventDetails = eventsDetails;
    }

    public List<ServerEventDetails> getServerEventDetails() {
        return serverEventDetails;
    }

    public void setServerEventDetails(List<ServerEventDetails> serverEventDetails) {
        this.serverEventDetails = serverEventDetails;
    }
}
