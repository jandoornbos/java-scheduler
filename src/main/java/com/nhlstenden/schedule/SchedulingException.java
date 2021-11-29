package com.nhlstenden.schedule;

public class SchedulingException extends Exception
{
    public SchedulingException(String reason)
    {
        super("There was an exception while scheduling the event:" + reason);
    }
}
