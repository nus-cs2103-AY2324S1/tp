package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // valid Time
        assertTrue(Time.isValidTime("15:30"));
        assertTrue(Time.isValidTime("12:10"));
        assertTrue(Time.isValidTime("00:00"));

        // invalid Time: does not follow HH:mm format
        // EP: extra characters
        assertFalse(Time.isValidTime("2023-10-14 15:30")); // includes date
        assertFalse(Time.isValidTime("15:301")); // extra digit

        // EP: too little characters
        assertFalse(Time.isValidTime("15:")); // incomplete

        // EP: non-numerical characters
        assertFalse(Time.isValidTime("15:AM")); // contains alphabetical characters
        assertFalse(Time.isValidTime("15:##")); // contains special characters

        // EP: outside of 00:00 to 24:00
        assertFalse(Time.isValidTime("25:00"));
        assertFalse(Time.isValidTime("24:01"));

        // EP: outside of xx:00 to xx:59
        assertFalse(Time.isValidTime("15:60")); // 60 minutes
    }

    @Test
    public void equals() {
        Time time = new Time("15:30");

        // same values -> returns true
        assertTrue(time.equals(new Time("15:30")));

        // same object -> returns true
        assertTrue(time.equals(time));

        // null -> returns false
        assertFalse(time.equals(null));

        // different types -> returns false
        assertFalse(time.equals(5.0f));

        // different values -> returns false
        assertFalse(time.equals(new Time("12:50")));
    }
}
