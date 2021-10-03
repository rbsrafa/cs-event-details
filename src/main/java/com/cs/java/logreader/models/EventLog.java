package com.cs.java.logreader.models;

public class EventLog {

    private String id;
    private State state;
    private long timestamp;

    public EventLog() {}

    public EventLog(String id, State state, long timestamp) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "EventLog{" +
                "id=" + id +
                ", state=" + state +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
