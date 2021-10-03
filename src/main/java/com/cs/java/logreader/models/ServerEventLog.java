package com.cs.java.logreader.models;

public class ServerEventLog extends EventLog {

    private String type;
    private String host;

    public ServerEventLog() {}

    public ServerEventLog(String id, State state, long timestamp, String type, String host) {
        super(id, state, timestamp);
        this.type = type;
        this.host = host;
    }

    @Override
    public String toString() {
        return "ServerEventLog{" +
                "id=" + this.getId() +
                ", state=" + this.getState() +
                ", timestamp=" + this.getTimestamp() +
                ", type=" + type +
                ", host=" + host +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
