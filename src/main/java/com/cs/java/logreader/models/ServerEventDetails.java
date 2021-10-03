package com.cs.java.logreader.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "server_event_details")
public class ServerEventDetails extends Details {

    private String type;
    private String host;

    public ServerEventDetails() {}

    public ServerEventDetails(String id, long duration, String type, String host) {
        super(id, duration);
        this.type = type;
        this.host = host;
    }

    public ServerEventDetails(String id, long duration, boolean alert, String type, String host) {
        super(id, duration, alert);
        this.type = type;
        this.host = host;
    }

    @Override
    public String toString() {
        return "ServerEventDetails{" +
                "id=" + this.getId() +
                ", duration=" + this.getDuration() +
                ", alert=" + this.isAlert() +
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
