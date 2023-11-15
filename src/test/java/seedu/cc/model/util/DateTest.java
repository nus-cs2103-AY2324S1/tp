package seedu.cc.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void isValidDate_validDate_true() {
        assertTrue(Date.isValidDate("2023-11-01"));
    }

    @Test
    public void isValidDate_invalidDate_false() {
        assertFalse(Date.isValidDate("2023-11-01T12:34:56")); // Invalid format
    }

    @Test
    public void isValidDate_invalidDateFormat_false() {
        assertFalse(Date.isValidDate("01-11-2023")); // Invalid date format
    }

    @Test
    public void isValidDate_emptyString_false() {
        assertFalse(Date.isValidDate(""));
    }

    @Test
    public void constructor_validDate_validDateObject() {
        Date validDate = new Date("2023-11-01");
        assertTrue(validDate.isValidDate("2023-11-01"));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        try {
            new Date("2023-11-01T12:34:56"); // Invalid format
            // If no exception is thrown, the test should fail
            assertFalse(true, "Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true, "Expected IllegalArgumentException");
        }
    }

    @Test
    public void toString_validDate_returnsFormattedString() {
        Date validDate = new Date("2023-11-01");
        assertEquals("2023-11-01", validDate.toString());
    }

    @Test
    public void equals_sameDate_true() {
        Date date1 = new Date("2023-11-01");
        Date date2 = new Date("2023-11-01");
        assertTrue(date1.equals(date2));
    }

    @Test
    public void equals_differentDate_false() {
        Date date1 = new Date("2023-11-01");
        Date date2 = new Date("2023-11-02");
        assertFalse(date1.equals(date2));
    }
}
