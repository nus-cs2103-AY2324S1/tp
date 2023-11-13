package seedu.address.model.freetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.availability.FreeTime;
import seedu.address.model.availability.TimeInterval;


public class FreeTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FreeTime(null, null));
    }

    @Test
    public void constructor_invalidFreeTime_throwsIllegalArgumentException() {
        LocalTime curr = LocalTime.now();
        assertThrows(IllegalArgumentException.class, () -> new FreeTime(curr, curr));
    }

    @Test
    public void isValidFreeTime() {
        // null from and to
        assertThrows(NullPointerException.class, () -> FreeTime.isValidFreeTime(null));
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        ArrayList<TimeInterval> timeIntervals = new ArrayList<>();
        for (int i = 0; i < FreeTime.NUM_DAYS; i++) {
            timeIntervals.add(new TimeInterval(from, to));
        }

        // valid free time
        assertTrue(FreeTime.isValidFreeTime(timeIntervals)); // same from and to
        timeIntervals.add(new TimeInterval(from, to));

        // more than 5 intervals, invalid
        assertFalse(FreeTime.isValidFreeTime(timeIntervals)); // to after from
    }

    @Test
    public void equals() {
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        LocalTime closeFrom = LocalTime.parse("12:21");
        FreeTime freeTime = new FreeTime(from, to);
        FreeTime otherFreeTime = new FreeTime(LocalTime.parse("12:20"), LocalTime.parse("23:44"));

        // same values -> returns true
        assertEquals(freeTime, otherFreeTime);

        // same object -> returns true
        assertEquals(freeTime, freeTime);

        // null -> returns false
        assertNotEquals(null, freeTime);

        // different values -> returns false
        assertNotEquals(freeTime, new FreeTime(from, closeFrom));

        // set one day to be null -> returns true
        freeTime = freeTime.updateAvailabilityForDay(1, null);
        otherFreeTime = otherFreeTime.updateAvailabilityForDay(1, null);
        assertEquals(freeTime, otherFreeTime);

        // different type
        assertNotEquals(freeTime, 1);
    }

    @Test
    public void updateAvailabilityForDay() {
        LocalTime from = LocalTime.parse("12:20");
        LocalTime to = LocalTime.parse("23:44");
        LocalTime closeFrom = LocalTime.parse("12:21");
        FreeTime freeTime = new FreeTime(from, to);
        TimeInterval upDatedTimeInterval = new TimeInterval(closeFrom, to);
        FreeTime updatedFreeTime = freeTime.updateAvailabilityForDay(1, upDatedTimeInterval);

        // Check original freeTime is unchanged
        assertEquals(freeTime, freeTime);

        // Check updatedFreeTime is edited
        assertNotEquals(freeTime, updatedFreeTime);

        // Check that the time interval for the first day is updated
        assertNotEquals(freeTime.getDay(1), upDatedTimeInterval);
        assertEquals(updatedFreeTime.getDay(1), upDatedTimeInterval);

        // Check other days are not modified
        for (int i = 2; i < 6; i++) {
            assertEquals(freeTime.getDay(i), updatedFreeTime.getDay(i));
        }
    }
}
