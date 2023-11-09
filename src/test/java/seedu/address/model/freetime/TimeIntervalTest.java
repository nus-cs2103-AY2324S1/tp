package seedu.address.model.freetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.availability.TimeInterval;

public class TimeIntervalTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeInterval(null, null));
    }

    @Test
    public void constructor_invalidFreeTime_throwsIllegalArgumentException() {
        LocalTime curr = LocalTime.now();
        assertThrows(IllegalArgumentException.class, () -> new TimeInterval(curr, curr));
    }

    @Test
    public void isValidFreeTime() {
        // null from and to
        assertThrows(NullPointerException.class, () -> TimeInterval.isValidTimeInterval(null, null));
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        LocalTime closeFrom = LocalTime.parse("12:21");

        // invalid free time
        assertFalse(TimeInterval.isValidTimeInterval(from, from)); // same from and to
        assertFalse(TimeInterval.isValidTimeInterval(to, from)); // to before from

        // valid free time
        assertTrue(TimeInterval.isValidTimeInterval(from, to)); // to after from
        assertTrue(TimeInterval.isValidTimeInterval(from, closeFrom)); // very short
    }

    @Test
    public void equals() {
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        LocalTime closeFrom = LocalTime.parse("12:21");
        TimeInterval freeTime = new TimeInterval(from, to);

        // same values -> returns true
        assertEquals(freeTime, new TimeInterval(LocalTime.parse("12:20"), LocalTime.parse("23:44")));

        // same object -> returns true
        assertEquals(freeTime, freeTime);

        // null -> returns false
        assertNotEquals(null, freeTime);

        // different values -> returns false
        assertNotEquals(freeTime, new TimeInterval(from, closeFrom));

        // different types -> returns false
        assertNotEquals(freeTime, 5);
    }

    @Test
    public void isInBetween() {
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        LocalTime closeFrom = LocalTime.parse("12:21");
        TimeInterval freeTime = new TimeInterval(from, to);
        TimeInterval freeTime2 = new TimeInterval(from, closeFrom);
        TimeInterval freeTime3 = new TimeInterval(closeFrom, to);

        // same values -> returns true
        assertTrue(freeTime.isInBetween(freeTime));

        // same object -> returns true
        assertTrue(freeTime.isInBetween(freeTime));

        // null -> returns false
        assertFalse(freeTime.isInBetween(null));

        // time is in between -> returns true
        assertTrue(freeTime.isInBetween(freeTime2));

        // time is not in between -> returns false
        assertFalse(freeTime2.isInBetween(freeTime3));
    }

    @Test
    public void getFrom_getTo() {
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        TimeInterval freeTime = new TimeInterval(from, to);
        assertEquals(freeTime.getFrom(), "12:20");
        assertEquals(freeTime.getTo(), "23:44");
    }
}
