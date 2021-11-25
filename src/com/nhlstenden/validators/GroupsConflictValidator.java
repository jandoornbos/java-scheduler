package com.nhlstenden.validators;

import com.nhlstenden.event.Event;
import com.nhlstenden.schedule.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupsConflictValidator implements AppointmentConflictValidator
{
    @Override
    public boolean hasConflict(ArrayList<Event> eventsToCheck, Event eventToAdd)
    {
        for (Event event : eventsToCheck) {
            for (Group group : eventToAdd.getGroups()) {
                if (event.getGroups().contains(group)) {
                    return new TimeConflictValidator().hasConflict(new ArrayList<>(List.of(event)), eventToAdd);
                }
            }
        }

        return false;
    }

    @Override
    public String getConflictMessage()
    {
        return "The assigned group to the event already has another event.";
    }
}
