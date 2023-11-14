package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        String invalidYear = "10";
        assertThrows(IllegalArgumentException.class, () -> new Year(invalidYear));
    }

    @Test
    public void isValidYear() {
        // null year
        assertThrows(NullPointerException.class, () -> Year.isValidYear(null));

        // blank year
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // spaces only

        // invalid year
        assertFalse(Year.isValidYear("one")); // string
        assertFalse(Year.isValidYear("-1")); // negative number
        assertFalse(Year.isValidYear("1.5")); // decimal
        assertFalse(Year.isValidYear("10")); // more than one digit long
        assertFalse(Year.isValidYear(" 1")); // leading space
        assertFalse(Year.isValidYear("1 ")); // trailing space
        assertFalse(Year.isValidYear("^")); // only non-alphanumeric characters

        // valid year
        assertTrue(Year.isValidYear("1"));
        assertTrue(Year.isValidYear("2"));
    }

    @Test
    public void equals() {
        Year year = new Year("2");

        // same values -> returns true
        assertTrue(year.equals(new Year("2")));

        // same object -> returns true
        assertTrue(year.equals(year));

        // null -> returns false
        assertFalse(year.equals(null));

        // different types -> returns false
        assertFalse(year.equals(5.0f));

        // different values -> returns false
        assertFalse(year.equals(new Year("3")));
    }

    @Test
    public void hashCode_returnsExpectedHashCode() {
        Year year = new Year("2");
        int expectedHashCode = "2".hashCode();
        int actualHashCode = year.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
}
