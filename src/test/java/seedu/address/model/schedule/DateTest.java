package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void isValidDateString() {
        // null
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid Dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("12313")); // positive numbers
        assertFalse(Date.isValidDate("-91")); // negative numbers
        assertFalse(Date.isValidDate("StartTime")); // alphabets
        assertFalse(Date.isValidDate("Start Time")); // spaces within alphabets
        assertFalse(Date.isValidDate("9011p041")); // alphabets within digits
        assertFalse(Date.isValidDate("9312 1534")); // spaces within digits
        assertFalse(Date.isValidDate("2024-31-12")); // bad month
        assertFalse(Date.isValidDate("2024-01-32")); // bad day
        assertFalse(Date.isValidDate("2023-02-29")); // non leap year

        // valid Dates
        assertTrue(Date.isValidDate("2024-02-29")); // leap year
        assertTrue(Date.isValidDate("2024-01-01")); // first day of a year
        assertTrue(Date.isValidDate("2023-12-31")); // last day of a year
        assertTrue(Date.isValidDate("2099-01-01")); // Long date in the future
        assertTrue(Date.isValidDate("1899-01-01")); // Long date in the past
        assertTrue(Date.isValidDate("2023-10-17")); // random date
    }

    @Test
    void testEquals() {
        DateStub date = new DateStub(LocalDate.of(2023, 1, 1));

        // same values -> returns true
        assertTrue(date.equals(new DateStub(LocalDate.of(2023, 1, 1))));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals("String"));

        // different values -> returns false
        assertFalse(date.equals(new DateStub(LocalDate.of(2024, 1, 1))));
    }

    @Test
    void testToString() {
        Date date = new Date(LocalDate.of(2023, 1, 1));
        String expectedString = "Jan 1 2023";
        assertEquals(expectedString, date.toString());
    }

    @Test
    void compareDays() {
        DateStub date1 = new DateStub(LocalDate.of(2023, 1, 1));
        DateStub date2 = new DateStub(LocalDate.of(2023, 1, 2));
        DateStub date3 = new DateStub(LocalDate.of(2023, 1, 3));

        assertTrue(date2.compareTo(date2) == 0); // testing comparison with itself
        assertTrue(date2.compareTo(date1) > 0); // testing comparison with a day before
        assertTrue(date2.compareTo(date3) < 0); // testing comparison with a day after

    }

    // Stub used to test methods in Date
    private class DateStub extends Date {
        protected DateStub(LocalDate date) {
            super(date);
        }
    }
}

