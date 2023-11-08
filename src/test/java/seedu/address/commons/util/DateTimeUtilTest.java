package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {
    @Test
    public void test_parseStringCorrectFormat_pass() {
        assertEquals(DateTimeUtil.parseString("12:00"), LocalDateTime.now()
                .withHour(12).withMinute(0).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("02:00"), LocalDateTime.now()
                .withHour(2).withMinute(0).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("02:01"), LocalDateTime.now()
                .withHour(2).withMinute(1).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("02:10"), LocalDateTime.now()
                .withHour(2).withMinute(10).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("00:10"), LocalDateTime.now()
                .withHour(0).withMinute(10).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("05:01:45"), LocalDateTime.now()
                .withHour(5).withMinute(1).withSecond(45).withNano(0));
        assertEquals(DateTimeUtil.parseString("2023-10-12 02:10"),
                LocalDateTime.parse("2023-10-12T02:10:00"));
        assertEquals(DateTimeUtil.parseString("2023-10-12 20:05"),
                LocalDateTime.parse("2023-10-12T20:05:00"));
        assertEquals(DateTimeUtil.parseString("2023-10-12 20:05:30"),
                LocalDateTime.parse("2023-10-12T20:05:30"));
        assertEquals(DateTimeUtil.parseString("2023-10-12"),
                LocalDateTime.parse("2023-10-12T00:00:00"));
    }

    @Test
    public void test_parseStringWrongFormat_fails() {
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("002:00"));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("02:0"));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("12:1"));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("2023-10-12 0:0:0"));
    }

    @Test
    public void test_withinInterval_pass() {
        // start time == check time == end time
        assertTrue(DateTimeUtil.withinTimeInterval(
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00")));
        // start time < check time < end time
        assertTrue(DateTimeUtil.withinTimeInterval(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00")));
        // start time <= check time < end time
        assertTrue(DateTimeUtil.withinTimeInterval(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-01T20:10:00")));
        // start time < check time <= end time
        assertTrue(DateTimeUtil.withinTimeInterval(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00")));
        // check time < start time
        assertFalse(DateTimeUtil.withinTimeInterval(
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-01T20:10:00")));
        // check time > end time
        assertFalse(DateTimeUtil.withinTimeInterval(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00")));
    }

    @Test
    public void test_timeIntervalsOverlap_pass() {
        // interval A and B does not overlap
        assertFalse(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-04T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00")));
        // same intervals
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00")));
        // start A == start B
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-04T20:10:00")));
        // end A == end B
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00")));
        // start A within interval B
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-04T20:10:00")));
        // end A within interval B
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-04T20:10:00")));
        // start B within interval A
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00")));
        // end B within interval A
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-04T20:10:00"),
                LocalDateTime.parse("2023-11-06T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00")));
        // interval A totally covers interval B
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00"),
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00")));
        // interval B totally covers interval A
        assertTrue(DateTimeUtil.timeIntervalsOverlap(
                LocalDateTime.parse("2023-11-02T20:10:00"),
                LocalDateTime.parse("2023-11-03T20:10:00"),
                LocalDateTime.parse("2023-11-01T20:10:00"),
                LocalDateTime.parse("2023-11-05T20:10:00")));
    }
}
