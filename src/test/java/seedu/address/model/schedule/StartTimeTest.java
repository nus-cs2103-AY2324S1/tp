package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class StartTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StartTime(null));
    }

    @Test
    void isValidStartTime() {
        // null
        assertThrows(NullPointerException.class, () -> StartTime.isValidStartTime(null));

        // invalid StartTimes
        assertFalse(StartTime.isValidStartTime("")); // empty string
        assertFalse(StartTime.isValidStartTime(" ")); // spaces only
        assertFalse(StartTime.isValidStartTime("12313")); // positive numbers
        assertFalse(StartTime.isValidStartTime("-91")); // negative numbers
        assertFalse(StartTime.isValidStartTime("StartTime")); // alphabets
        assertFalse(StartTime.isValidStartTime("Start Time")); // spaces within alphabets
        assertFalse(StartTime.isValidStartTime("9011p041")); // alphabets within digits
        assertFalse(StartTime.isValidStartTime("9312 1534")); // spaces within digits
        assertFalse(StartTime.isValidStartTime("2024-31-12T00:00:00")); // bad month
        assertFalse(StartTime.isValidStartTime("2024-01-32T00:00:00")); // bad day
        assertFalse(StartTime.isValidStartTime("2024-01-01T25:00:00")); // bad hour
        assertFalse(StartTime.isValidStartTime("2024-01-01T00:61:00")); // bad min
        assertFalse(StartTime.isValidStartTime("2024-01-01T00:00:61")); // bad sec
        assertFalse(StartTime.isValidStartTime("2023-02-29T01:00:00")); // non leap year

        // valid StartTimes
        assertTrue(StartTime.isValidStartTime("2024-02-29T01:00:00")); // leap year
        assertTrue(StartTime.isValidStartTime("2024-01-01T00:00:00")); // first second of a year
        assertTrue(StartTime.isValidStartTime("2023-12-31T23:59:59")); // last second of a year
        assertTrue(StartTime.isValidStartTime("2099-01-01T00:00:00")); // Long time in the future
        assertTrue(StartTime.isValidStartTime("1899-01-01T00:00:00")); // Long time in the past
        assertTrue(StartTime.isValidStartTime("2023-10-17T18:15:33")); // random date

    }

    @Test
    void testEquals() {
        StartTime startTime = new StartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));

        // same values -> returns true
        assertTrue(startTime.equals(new StartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))));

        // same object -> returns true
        assertTrue(startTime.equals(startTime));

        // same EndTime -> returns true
        assertTrue(startTime.equals(new EndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))));

        // null -> returns false
        assertFalse(startTime.equals(null));

        // different types -> returns false
        assertFalse(startTime.equals("String"));

        // different values -> returns false
        assertFalse(startTime.equals(new StartTime(LocalDateTime.of(2024, 1, 1, 0, 0, 0))));

        // different EndTime -> returns true
        assertFalse(startTime.equals(new EndTime(LocalDateTime.of(2024, 1, 1, 0, 0, 0))));
    }

    @Test
    public void hashcode() {
        StartTime startTime = new StartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));

        // same values -> returns same hashcode
        assertEquals(startTime.hashCode(), new StartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0)).hashCode());

        // different value -> returns different hashcode
        assertNotEquals(startTime.hashCode(), new StartTime(LocalDateTime.of(2024, 1, 1, 0, 0, 0)).hashCode());
    }

    @Test
    public void toStringMethod() {
        StartTime startTime = new StartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        String expectedString = "Jan 1 2023 00:00";
        assertEquals(expectedString, startTime.toString());
    }
}
