package com.nhlstenden.event;

import com.nhlstenden.schedule.Group;
import com.nhlstenden.schedule.Room;
import com.nhlstenden.person.Teacher;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Event
{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final HashSet<Group> groups;
    private final HashSet<Teacher> teachers;
    private Room room;

    public Event(LocalDateTime startTime, LocalDateTime endTime, Room room) throws EventException
    {
        if (endTime.isBefore(startTime))
        {
            throw new EventException("End time cannot be before start time.");
        }

        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.groups = new HashSet<>();
        this.teachers = new HashSet<>();
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public HashSet<Group> getGroups()
    {
        return groups;
    }

    public void addGroup(Group group)
    {
        this.groups.add(group);
    }

    public HashSet<Teacher> getTeachers()
    {
        return teachers;
    }

    public void addTeacher(Teacher teacher)
    {
        this.teachers.add(teacher);
    }

    public LocalDateTime getStartTime()
    {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime()
    {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime)
    {
        this.endTime = endTime;
    }
}
