package com.nhlstenden.validators;

import com.nhlstenden.event.Event;

import java.util.ArrayList;

public class TimeConflictValidator implements AppointmentConflictValidator
{
    @Override
    public boolean hasConflict(ArrayList<Event> eventsToCheck, Event eventToAdd)
    {
        Event secondEvent = eventsToCheck.get(0);

        // https://stackoverflow.com/questions/17106670/how-to-check-a-timeperiod-is-overlapping-another-time-period-in-java
        return !eventToAdd.getStartTime().isAfter(secondEvent.getEndTime()) && !secondEvent.getStartTime().isAfter(eventToAdd.getEndTime());
    }

    @Override
    public String getConflictMessage()
    {
        return "This event is overlapping.";
    }
}
