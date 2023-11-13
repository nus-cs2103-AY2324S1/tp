package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTimeTest {
    @Test
    public void test_invalidEventTime_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> EventTime.fromString("29/05/2024"));
    }

    @Test
    public void test_eventTimeEquals_pass() {
        EventTime instance = EventTime.fromString("2023-11-03 00:30:00");

        // same object --> returns true
        assertTrue(instance.equals(instance));

        // empty object --> returns false
        assertFalse(instance.equals(new Object()));

        // same values --> returns true
        assertEquals(EventTime.fromString("2023-11-02 00:30:00"),
                EventTime.fromString("2023-11-02 00:30:00"));

        // only time provided --> date will be current date
        assertEquals(EventTime.fromString("00:30:00"),
                EventTime.fromString(
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:30:00"));

        // different values --> returns false
        assertNotEquals(EventTime.fromString("2023-11-02 10:00:00"),
                EventTime.fromString("2023-11-02 10:00:01"));

        // different time --> returns false
        assertNotEquals(EventTime.fromString("10:00:00"),
                EventTime.fromString(
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 10:00:01"));
    }

    @Test
    public void test_getTime_pass() {
        assertEquals(EventTime.fromString("2023-11-02 00:30:00").getTime(),
                LocalDateTime.of(2023, 11, 2, 0, 30, 0));
        assertEquals(EventTime.fromString("00:30:00").getTime(),
                        LocalDateTime.now().withHour(0).withMinute(30).withSecond(0).withNano(0));
    }

}
