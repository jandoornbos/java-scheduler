package com.nhlstenden.validators;

import com.nhlstenden.event.Event;

import java.util.ArrayList;
import java.util.List;

public class RoomConflictValidator implements AppointmentConflictValidator
{
    @Override
    public boolean hasConflict(ArrayList<Event> eventsToCheck, Event eventToAdd)
    {
        for (Event event : eventsToCheck) {
            if (event.getRoom().equals(eventToAdd.getRoom())) {
                return new TimeConflictValidator().hasConflict(new ArrayList<>(List.of(event)), eventToAdd);
            }
        }
        return false;
    }

    @Override
    public String getConflictMessage()
    {
        return "The event is in a room where already an event is.";
    }
}
