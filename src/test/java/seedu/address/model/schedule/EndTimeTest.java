package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class EndTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EndTime(null));
    }

    @Test
    public void isValidEndTime() {
        // null
        assertThrows(NullPointerException.class, () -> EndTime.isValidEndTime(null));

        // invalid EndTimes
        assertFalse(EndTime.isValidEndTime("")); // empty string
        assertFalse(EndTime.isValidEndTime(" ")); // spaces only
        assertFalse(EndTime.isValidEndTime("12313")); // positive numbers
        assertFalse(EndTime.isValidEndTime("-91")); // negative numbers
        assertFalse(EndTime.isValidEndTime("EndTime")); // alphabets
        assertFalse(EndTime.isValidEndTime("End Time")); // spaces within alphabets
        assertFalse(EndTime.isValidEndTime("9011p041")); // alphabets within digits
        assertFalse(EndTime.isValidEndTime("9312 1534")); // spaces within digits
        assertFalse(EndTime.isValidEndTime("2024-31-12T00:00:00")); // bad month
        assertFalse(EndTime.isValidEndTime("2024-01-32T00:00:00")); // bad day
        assertFalse(EndTime.isValidEndTime("2024-01-01T25:00:00")); // bad hour
        assertFalse(EndTime.isValidEndTime("2024-01-01T00:61:00")); // bad min
        assertFalse(EndTime.isValidEndTime("2024-01-01T00:00:61")); // bad sec
        assertFalse(EndTime.isValidEndTime("2023-02-29T01:00:00")); // non leap year

        // valid EndTimes
        assertTrue(EndTime.isValidEndTime("2024-02-29T01:00:00")); // leap year
        assertTrue(EndTime.isValidEndTime("2024-01-01T00:00:00")); // first second of a year
        assertTrue(EndTime.isValidEndTime("2023-12-31T23:59:59")); // last second of a year
        assertTrue(EndTime.isValidEndTime("2099-01-01T00:00:00")); // Long time in the future
        assertTrue(EndTime.isValidEndTime("1899-01-01T00:00:00")); // Long time in the past
        assertTrue(EndTime.isValidEndTime("2023-10-17T18:15:33")); // random date

    }

    @Test
    public void testEquals() {
        EndTime endTime = new EndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));

        // same values -> returns true
        assertTrue(endTime.equals(new EndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))));

        // same object -> returns true
        assertTrue(endTime.equals(endTime));

        // same StartTime -> returns true
        assertTrue(endTime.equals(new StartTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0))));

        // null -> returns false
        assertFalse(endTime.equals(null));

        // different types -> returns false
        assertFalse(endTime.equals("String"));

        // different values -> returns false
        assertFalse(endTime.equals(new EndTime(LocalDateTime.of(2024, 1, 1, 0, 0, 0))));

        // different StartTime -> returns false
        assertFalse(endTime.equals(new StartTime(LocalDateTime.of(2024, 1, 1, 0, 0, 0))));
    }

    @Test
    public void hashcode() {
        EndTime endTime = new EndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));

        // same values -> returns same hashcode
        assertEquals(endTime.hashCode(), new EndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0)).hashCode());

        // different value -> returns different hashcode
        assertNotEquals(endTime.hashCode(), new EndTime(LocalDateTime.of(2024, 1, 1, 0, 0, 0)).hashCode());
    }

    @Test
    public void toStringMethod() {
        EndTime endTime = new EndTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        String expectedString = "Jan 1 2023 00:00";
        assertEquals(expectedString, endTime.toString());
    }
}
