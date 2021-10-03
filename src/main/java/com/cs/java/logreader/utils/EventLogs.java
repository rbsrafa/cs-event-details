package com.cs.java.logreader.utils;

import com.cs.java.logreader.models.EventLog;
import com.cs.java.logreader.models.ServerEventLog;

import java.util.ArrayList;
import java.util.List;

public class EventLogs {
    private List<EventLog> eventLogs = new ArrayList<>();
    private List<ServerEventLog> serverEventLogs = new ArrayList<>();

    public EventLogs() {}

    public List<EventLog> getEventLogs() {
        return eventLogs;
    }

    public void setEventLogs(List<EventLog> eventLogs) {
        this.eventLogs = eventLogs;
    }

    public List<ServerEventLog> getServerEventLogs() {
        return serverEventLogs;
    }

    public void setServerEventLogs(List<ServerEventLog> serverEventLogs) {
        this.serverEventLogs = serverEventLogs;
    }
}
