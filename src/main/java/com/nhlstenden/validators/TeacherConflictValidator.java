package com.nhlstenden.validators;

import com.nhlstenden.event.Event;
import com.nhlstenden.person.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherConflictValidator implements AppointmentConflictValidator
{
    @Override
    public boolean hasConflict(ArrayList<Event> eventsToCheck, Event eventToAdd)
    {
        for (Event event : eventsToCheck) {
            for (Teacher teacher : event.getTeachers()) {
                if (eventToAdd.getTeachers().contains(teacher)) {
                    return new TimeConflictValidator().hasConflict(new ArrayList<>(List.of(event)), eventToAdd);
                }
            }
        }

        return false;
    }

    @Override
    public String getConflictMessage()
    {
        return "This teacher already has another event.";
    }
}
