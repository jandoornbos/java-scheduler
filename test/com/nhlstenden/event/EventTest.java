package com.nhlstenden.event;

import com.nhlstenden.schedule.Room;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest
{
    @Test
    void constructor_InvalidTimeSpan_ShouldThrow()
    {
        assertThrows(EventException.class, () -> {
            new Event(
                    LocalDateTime.of(2021, 11, 25, 12, 0),
                    LocalDateTime.of(2021, 11, 25, 11, 0),
                    new Room("E01")
            );
        });
    }

    @Test
    void constructor_ValidTimespan_ShouldNotThrow()
    {
        assertDoesNotThrow(() -> {
            new Event(
                    LocalDateTime.of(2021, 11, 25, 12, 0),
                    LocalDateTime.of(2021, 11, 25, 13, 0),
                    new Room("E01")
            );
        });
    }
}