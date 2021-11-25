package com.nhlstenden.event;

import java.time.LocalDateTime;

public class ExternalEvent extends Event
{
    private String location;

    public ExternalEvent(LocalDateTime startTime, LocalDateTime endTime, String location) throws EventException
    {
        super(startTime, endTime,null);
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
