package com.nhlstenden.validators;

import com.nhlstenden.event.Event;

import java.util.ArrayList;

public interface AppointmentConflictValidator
{
    boolean hasConflict(ArrayList<Event> eventsToCheck, Event eventToAdd);
    String getConflictMessage();
}
