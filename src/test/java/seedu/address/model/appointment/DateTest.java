package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // valid Date
        // EP: follows yyyy-MM-dd format
        assertTrue(Date.isValidDate("2023-10-14"));
        assertTrue(Date.isValidDate("2023-11-14"));
        assertTrue(Date.isValidDate("2023-10-31"));

        // invalid Date: does not follow yyyy-MM-dd format
        // EP: extra characters
        assertFalse(Date.isValidDate("2023-10-14 15:30")); // includes time
        assertFalse(Date.isValidDate("2023-10-14 15:")); // incomplete
        assertFalse(Date.isValidDate("2023-10-14 15:301")); // extra digit

        // EP: too little characters
        assertFalse(Date.isValidDate("15:30")); // only time, no date

        // EP: non-numerical characters
        assertFalse(Date.isValidDate("2023-10-14 15:AM")); // contains non-digit

        // EP: not using dashes
        assertFalse(Date.isValidDate("2023:10:14")); // contains ":"
        assertFalse(Date.isValidDate("2023/10/14")); // contains "/"

        // invalid Date: out of bounds date and month

        // EP: out of bounds for date and month
        assertFalse(Date.isValidDate("2023-13-14")); // month 13
        assertFalse(Date.isValidDate("2023-10-32")); // day 32
        assertFalse(Date.isValidDate("1999-10-14")); // year 1999, in the past
        assertFalse(Date.isValidDate("8000-10-14")); // year 8000, too far in the future, must be within a year


        // February special cases

        // EP: february max 28 days on non-leap year
        assertFalse(Date.isValidDate("2023-02-29")); // non-leap year
        assertTrue(Date.isValidDate("2023-02-28"));

        // EP: february 29th day on leap year
        assertTrue(Date.isValidDate("2024-02-29"));
    }

    @Test
    public void equals() {
        Date date = new Date("2023-10-14");

        // same values -> returns true
        assertTrue(date.equals(new Date("2023-10-14")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new Date("2023-10-15")));
    }
}
