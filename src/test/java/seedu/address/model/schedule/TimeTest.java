package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    void isValidTimeString() {
        // null
        assertThrows(NullPointerException.class, () -> Time.isValidTimeString(null));

        // invalid StartTimes
        assertFalse(Time.isValidTimeString("")); // empty string
        assertFalse(Time.isValidTimeString(" ")); // spaces only
        assertFalse(Time.isValidTimeString("12313")); // positive numbers
        assertFalse(Time.isValidTimeString("-91")); // negative numbers
        assertFalse(Time.isValidTimeString("StartTime")); // alphabets
        assertFalse(Time.isValidTimeString("Start Time")); // spaces within alphabets
        assertFalse(Time.isValidTimeString("9011p041")); // alphabets within digits
        assertFalse(Time.isValidTimeString("9312 1534")); // spaces within digits
        assertFalse(Time.isValidTimeString("2024-31-12T00:00")); // bad month
        assertFalse(Time.isValidTimeString("2024-01-32T00:00")); // bad day
        assertFalse(Time.isValidTimeString("2024-01-01T25:00")); // bad hour
        assertFalse(Time.isValidTimeString("2024-01-01T00:61")); // bad min
        assertFalse(Time.isValidTimeString("2023-02-29T01:00")); // non leap year

        // valid StartTimes
        assertTrue(Time.isValidTimeString("2024-02-29T01:00")); // leap year
        assertTrue(Time.isValidTimeString("2024-01-01T00:00")); // first second of a year
        assertTrue(Time.isValidTimeString("2023-12-31T23:59")); // last second of a year
        assertTrue(Time.isValidTimeString("2099-01-01T00:00")); // Long time in the future
        assertTrue(Time.isValidTimeString("1899-01-01T00:00")); // Long time in the past
        assertTrue(Time.isValidTimeString("2023-10-17T18:15")); // random date
    }

    @Test
    void testEquals() {
        TimeStub time = new TimeStub(LocalDateTime.of(2023, 1, 1, 0, 0, 0));

        // same values -> returns true
        assertTrue(time.equals(new TimeStub(LocalDateTime.of(2023, 1, 1, 0, 0, 0))));

        // same object -> returns true
        assertTrue(time.equals(time));

        // null -> returns false
        assertFalse(time.equals(null));

        // different types -> returns false
        assertFalse(time.equals("String"));

        // different values -> returns false
        assertFalse(time.equals(new TimeStub(LocalDateTime.of(2024, 1, 1, 0, 0, 0))));
    }

    @Test
    void testToString() {
        StartTime startTime = new StartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        String expectedString = "Jan 1 2023 00:00";
        assertEquals(expectedString, startTime.toString());
    }

    @Test
    void compareTo() {
        TimeStub time1 = new TimeStub(LocalDateTime.of(2023, 1, 1, 11, 59));
        TimeStub time2 = new TimeStub(LocalDateTime.of(2023, 1, 1, 12, 0));
        TimeStub time3 = new TimeStub(LocalDateTime.of(2023, 1, 1, 12, 59));


        assertTrue(time2.compareTo(time2) == 0); // testing comparison with itself
        assertTrue(time2.compareTo(time1) > 0); // testing comparison with a time before
        assertTrue(time2.compareTo(time3) < 0); // testing comparison with a time after
    }

    @Test
    void compareDays() {
        TimeStub time1 = new TimeStub(LocalDateTime.of(2023, 1, 1, 12, 0));
        TimeStub time2 = new TimeStub(LocalDateTime.of(2023, 1, 2, 12, 0));
        TimeStub time3 = new TimeStub(LocalDateTime.of(2023, 1, 3, 12, 0));

        assertTrue(time2.compareDays(time2) == 0); // testing comparison with itself
        assertTrue(time2.compareDays(time1) > 0); // testing comparison with a day before
        assertTrue(time2.compareDays(time3) < 0); // testing comparison with a day after

    }

    // Stub used to test methods in Time
    private class TimeStub extends Time {
        protected TimeStub(LocalDateTime time) {
            super(time);
        }
    }
}

