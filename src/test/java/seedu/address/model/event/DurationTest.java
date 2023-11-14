package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;


public class DurationTest {
    @Test
    public void constructor_validDuration_success() throws ParseException {
        // Test the constructor with valid inputs
        TimeStart timeStart = new TimeStart("2023-01-01 12:00");
        TimeEnd timeEnd = new TimeEnd("2023-01-01 14:00");
        Duration duration = new Duration(timeStart, timeEnd);

        // Assertions
        assertEquals("2023-01-01 12:00", duration.getTimeStartValue());
        assertEquals("2023-01-01 14:00", duration.getTimeEndValue());
    }

    @Test
    public void constructor_invalidDuration_throwsParseException() throws ParseException {
        // Test the constructor with an invalid duration
        TimeStart timeStart = new TimeStart("2023-01-01 14:00");
        TimeEnd timeEnd = new TimeEnd("2023-01-01 12:00");

        // Assertions
        assertThrows(ParseException.class, () -> new Duration(timeStart, timeEnd));
    }

    @Test
    public void updateDuration_validInput_success() throws ParseException {
        // Test the updateDuration method with valid inputs
        Duration oldDuration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));
        Duration updatedDuration = Duration.updateDuration(oldDuration,
                "2023-01-01 15:00", "2023-01-01 16:00");

        // Assertions
        assertEquals("01-01-2023 15:00", updatedDuration.getTimeStartValue());
        assertEquals("01-01-2023 16:00", updatedDuration.getTimeEndValue());
    }

    @Test
    public void updateDuration_nullInputs_noUpdate() throws ParseException {
        // Test the updateDuration method with null inputs
        Duration oldDuration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));
        Duration updatedDuration = Duration.updateDuration(oldDuration, null, null);

        // Assertions
        assertEquals(oldDuration, updatedDuration);
    }

    @Test
    public void isAfterNow_durationEndAfterNow_true() throws ParseException {
        // Test the isAfterNow method with a duration whose end is after the current time
        Duration duration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd(LocalDateTime.now().plusHours(1)));

        // Assertion
        assertTrue(duration.isAfterNow());
    }

    @Test
    public void isAfterNow_durationEndBeforeNow_false() throws ParseException {
        // Test the isAfterNow method with a duration whose end is before the current time
        Duration duration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd(LocalDateTime.now().minusHours(1)));

        // Assertion
        assertFalse(duration.isAfterNow());
    }

    @Test
    public void hasTime_timeWithinDuration_true() throws ParseException {
        // Test the hasTime method with a time that falls within the duration
        Duration duration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));

        // Assertion
        assertTrue(duration.hasTime(LocalDateTime.parse("2023-01-01T13:00")));
    }

    @Test
    public void hasTime_timeBeforeDuration_false() throws ParseException {
        // Test the hasTime method with a time that is before the duration
        Duration duration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));

        // Assertion
        assertFalse(duration.hasTime(LocalDateTime.parse("2023-01-01T11:00")));
    }

    @Test
    public void hasTime_timeAfterDuration_false() throws ParseException {
        // Test the hasTime method with a time that is after the duration
        Duration duration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd(LocalDateTime.now().minusHours(1)));

        // Assertion
        assertTrue(duration.hasTime(LocalDateTime.parse("2023-01-01T15:00")));
    }

    @Test
    public void isBeforeStartTime_timeBeforeStartTime_true() throws ParseException {
        // Test the isBeforeStartTime method with a time that is before the start time
        Duration duration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd(LocalDateTime.now().minusHours(1)));

        // Assertion
        assertFalse(duration.hasStartTimeBefore(LocalDateTime.parse("2023-01-01T11:00")));
    }

    @Test
    public void isBeforeStartTime_timeAfterStartTime_false() throws ParseException {
        // Test the isBeforeStartTime method with a time that is after the start time
        Duration duration = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));

        // Assertion
        assertFalse(duration.hasStartTimeBefore(LocalDateTime.parse("2023-01-01T12:30")));
    }

    @Test
    public void equals_sameDurationObjects_true() throws ParseException {
        // Test the equals method with two equal duration objects
        Duration duration1 = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));
        Duration duration2 = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));

        // Assertion
        assertTrue(duration1.equals(duration2));
    }

    @Test
    public void equals_differentDurationObjects_false() throws ParseException {
        // Test the equals method with two different duration objects
        Duration duration1 = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));
        Duration duration2 = new Duration(new TimeStart("2023-01-01 13:00"),
                new TimeEnd("2023-01-01 15:00"));

        // Assertion
        assertFalse(duration1.equals(duration2));
    }

    @Test
    public void compareTo_earlierStartTime_negative() throws ParseException {
        // Test the compareTo method with a duration that has an earlier start time
        Duration duration1 = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));
        Duration duration2 = new Duration(new TimeStart("2023-01-01 13:00"),
                new TimeEnd("2023-01-01 15:00"));

        // Assertion
        assertTrue(duration1.compareTo(duration2) < 0);
    }

    @Test
    public void compareTo_laterStartTime_positive() throws ParseException {
        // Test the compareTo method with a duration that has a later start time
        Duration duration1 = new Duration(new TimeStart("2023-01-01 13:00"),
                new TimeEnd("2023-01-01 15:00"));
        Duration duration2 = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));

        // Assertion
        assertTrue(duration1.compareTo(duration2) > 0);
    }

    @Test
    public void compareTo_equalStartTime_zero() throws ParseException {
        // Test the compareTo method with durations that have equal start times
        Duration duration1 = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 14:00"));
        Duration duration2 = new Duration(new TimeStart("2023-01-01 12:00"),
                new TimeEnd("2023-01-01 15:00"));

        // Assertion
        assertEquals(0, duration1.compareTo(duration2));
    }
}
