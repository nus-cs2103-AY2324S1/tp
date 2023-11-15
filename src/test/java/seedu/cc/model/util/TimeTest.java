package seedu.cc.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void isValidTime_validTime_true() {
        assertTrue(Time.isValidTime("13:45"));
    }

    @Test
    public void isValidTime_invalidTime_false() {
        assertFalse(Time.isValidTime("1:45")); // Missing leading zero
    }

    @Test
    public void isValidTime_emptyString_false() {
        assertFalse(Time.isValidTime(""));
    }

    @Test
    public void constructor_validTime_validTimeObject() {
        Time validTime = new Time("17:30");
        assertEquals("17:30", validTime.toString());
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        try {
            new Time("24:00"); // Invalid time (24:00 is not a valid time)
            // If no exception is thrown, the test should fail
            assertFalse(true, "Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true, "Expected IllegalArgumentException");
        }
    }

    @Test
    public void toString_validTime_returnsFormattedString() {
        Time validTime = new Time("09:15");
        assertEquals("09:15", validTime.toString());
    }

    @Test
    public void equals_sameTime_true() {
        Time time1 = new Time("14:00");
        Time time2 = new Time("14:00");
        assertTrue(time1.equals(time2));
    }

    @Test
    public void equals_differentTime_false() {
        Time time1 = new Time("12:30");
        Time time2 = new Time("13:45");
        assertFalse(time1.equals(time2));
    }
}
