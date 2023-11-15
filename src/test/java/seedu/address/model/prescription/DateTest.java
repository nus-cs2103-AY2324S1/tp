package seedu.address.model.prescription;

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
    public void isValidDateFormat() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDateFormat(null));

        // invalid date
        assertFalse(Date.isValidDateFormat("")); // empty string
        assertFalse(Date.isValidDateFormat(" ")); // spaces only
        assertFalse(Date.isValidDateFormat("^")); // non-alphanumeric characters
        assertFalse(Date.isValidDateFormat("1/01/2023")); // contains incorrect date format
        assertFalse(Date.isValidDateFormat("01/1/2023")); // contains incorrect date format
        assertFalse(Date.isValidDateFormat("01/01/23")); // contains incorrect date format
        assertFalse(Date.isValidDateFormat("2023/01/01")); // contains incorrect date format

        // valid date
        assertTrue(Date.isValidDateFormat("01/01/2023")); // day and month are single digit
        assertTrue(Date.isValidDateFormat("21/01/2023")); // month is single digit
        assertTrue(Date.isValidDateFormat("01/12/2023")); // day is single digit
        assertTrue(Date.isValidDateFormat("21/12/2023")); // day and month are double digits
    }

    @Test
    public void equals() {
        Date date = new Date("01/01/2023");

        // same values -> returns true
        assertTrue(date.equals(new Date("01/01/2023")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new Date("02/01/2023")));
        assertFalse(date.equals(new Date("01/02/2023")));
        assertFalse(date.equals(new Date("01/01/2024")));
        assertFalse(date.equals(new Date("21/12/2024")));
    }
}
