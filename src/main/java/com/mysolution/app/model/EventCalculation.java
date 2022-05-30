package com.mysolution.app.model;

public class EventCalculation {
    public EventCalculation(long duration, boolean isAlert) {
        this.duration = duration;
        this.isAlert = isAlert;
    }

    private long duration;

    private boolean isAlert;

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
}
