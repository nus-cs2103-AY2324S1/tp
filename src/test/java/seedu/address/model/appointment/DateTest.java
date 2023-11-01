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
        assertTrue(Date.isValidDate("2023-10-14"));
        assertTrue(Date.isValidDate("2023-11-14"));
        assertTrue(Date.isValidDate("2023-10-31"));

        // invalid Date
        assertFalse(Date.isValidDate("2023-10-14 15:30")); // includes time
        assertFalse(Date.isValidDate("15:30")); // only time, no date
        assertFalse(Date.isValidDate("2023-10-14 15:")); // incomplete
        assertFalse(Date.isValidDate("2023-10-14 15:301")); // extra digit
        assertFalse(Date.isValidDate("2023-10-14 15:AM")); // contains non-digit
        assertFalse(Date.isValidDate("2023-13-14")); // month 13
        assertFalse(Date.isValidDate("2023-10-32")); // day 32
        assertFalse(Date.isValidDate("1999-10-14")); // year 1999
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
