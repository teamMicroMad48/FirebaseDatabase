package com.MY.project1;

public class Event {
    private String endDate;
    private String eventId;
    private String eventName;
    private String startDate;

    public Event(String endDate, String eventId, String eventName, String startDate) {
        this.endDate = endDate;
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
