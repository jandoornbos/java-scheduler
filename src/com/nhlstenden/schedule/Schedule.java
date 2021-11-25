package com.nhlstenden.schedule;

import com.nhlstenden.event.Event;
import com.nhlstenden.validators.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Schedule
{
    private final ArrayList<Event> events;

    public Schedule()
    {
        this.events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents()
    {
        return this.events;
    }

    public void addEvent(Event event) throws SchedulingException
    {
        ArrayList<AppointmentConflictValidator> validators = new ArrayList<>(Arrays.asList(
                new RoomConflictValidator(),
                new TeacherConflictValidator(),
                new GroupsConflictValidator()
        ));

        for (AppointmentConflictValidator validator : validators)
        {
            if (validator.hasConflict(this.events, event))
            {
                throw new SchedulingException("Conflict with existing event: " + validator.getConflictMessage());
            }
        }

        this.events.add(event);
    }
}



