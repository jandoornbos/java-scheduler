package com.nhlstenden.schedule;

import com.nhlstenden.event.Event;
import com.nhlstenden.event.EventException;
import com.nhlstenden.person.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScheduleTest
{
    private Schedule schedule;

    private Room e1;
    private Room e2;

    private Teacher teacherJan;

    private Group groupInf1A;

    @BeforeEach
    void beforeEach()
    {
        this.schedule = new Schedule();

        this.e1 = new Room("E1");
        this.e2 = new Room("E2");

        this.teacherJan = new Teacher("Jan", "Java", 1);

        this.groupInf1A = new Group("INF1A");
    }

    @Test
    void addEvent_InDifferentRoomsAtSameTime_ShouldNotThrow() throws EventException
    {
        // 10:00 - 12:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        // 10:00 - 12:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e2
        );

        assertDoesNotThrow(() -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InSameRoomAtSameTime_ShouldThrow() throws EventException
    {
        // 10:00 - 12:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        // 10:00 - 12:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        assertThrows(SchedulingException.class, () -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InDifferentRoomSameTeachers_ShouldThrow() throws EventException
    {
        // 10:00 - 12:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        // 10:00 - 12:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e2
        );

        eventR1.addTeacher(this.teacherJan);
        eventR2.addTeacher(this.teacherJan);

        assertThrows(SchedulingException.class, () -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InDifferentRoomSameGroups_ShouldThrow() throws EventException
    {
        // 10:00 - 12:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        // 10:00 - 12:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e2
        );

        eventR1.addGroup(this.groupInf1A);
        eventR2.addGroup(this.groupInf1A);

        assertThrows(SchedulingException.class, () -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InSameRoomNoOverlappingTimes_ShouldNotThrow() throws EventException
    {
        // 10:00 - 12:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        // 13:00 - 14:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 13, 0),
                LocalDateTime.of(2021, 11, 21, 14, 0),
                this.e1
        );

        assertDoesNotThrow(() -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InSameRoomStartBeforeAndEndsAfter_ShouldThrow() throws EventException
    {
        // 10:00 - 12:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        // 9:00 - 13:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 9, 0),
                LocalDateTime.of(2021, 11, 21, 13, 0),
                this.e1
        );

        assertThrows(SchedulingException.class, () -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InSameRoomStartsAfterAndEndsBeforeOtherEvent_ShouldThrow() throws EventException
    {
        // 10:00 - 14:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 14, 0),
                this.e1
        );

        // 11:00 - 13:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 11, 0),
                LocalDateTime.of(2021, 11, 21, 13, 0),
                this.e1
        );

        assertThrows(SchedulingException.class, () -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InSameRoomStartsAfterAndEndsAfter_ShouldThrow() throws EventException
    {
        // 10:00 - 14:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 14, 0),
                this.e1
        );

        // 11:00 - 15:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 11, 0),
                LocalDateTime.of(2021, 11, 21, 15, 0),
                this.e1
        );

        assertThrows(SchedulingException.class, () -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InSameRoomStartsBeforeAndEndsBefore_ShouldThrow() throws EventException
    {
        // 10:00 - 14:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 14, 0),
                this.e1
        );

        // 9:00 - 13:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 9, 0),
                LocalDateTime.of(2021, 11, 21, 13, 0),
                this.e1
        );

        assertThrows(SchedulingException.class, () -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }

    @Test
    void addEvent_InDifferentRoomDifferentTeachers_ShouldNotThrow() throws EventException
    {
        Teacher teacherMartijn = new Teacher("Martijn", "Java", 2);

        // 10:00 - 12:00
        Event eventR1 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e1
        );

        // 10:00 - 12:00
        Event eventR2 = new Event(
                LocalDateTime.of(2021, 11, 21, 10, 0),
                LocalDateTime.of(2021, 11, 21, 12, 0),
                this.e2
        );

        eventR1.addTeacher(this.teacherJan);
        eventR2.addTeacher(teacherMartijn);

        assertDoesNotThrow(() -> {
            this.schedule.addEvent(eventR1);
            this.schedule.addEvent(eventR2);
        });
    }
}