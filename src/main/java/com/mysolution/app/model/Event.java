package com.mysolution.app.model;

public class Event {

    public Event(String id, String type, String host, long duration, boolean isAlert) {
        this.id = id;
        this.type = type;
        this.host = host;
        this.duration = duration;
        this.isAlert = isAlert;
    }

    private String id;

    private String type;

    private String host;

    private long duration;

    private boolean isAlert;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isAlert() {
        return isAlert;
    }

    public void setAlert(boolean alert) {
        isAlert = alert;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", duration=" + duration +
                ", isAlert=" + isAlert +
                '}';
    }
}
