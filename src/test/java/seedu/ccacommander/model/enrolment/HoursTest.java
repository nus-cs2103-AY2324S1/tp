package seedu.ccacommander.model.enrolment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class HoursTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Hours(null));
    }

    @Test
    public void constructor_invalidHours_throwsIllegalArgumentException() {
        String invalidHours = "10 hours";
        assertThrows(IllegalArgumentException.class, () -> new Hours(invalidHours));
    }

    @Test
    void isValidHours() {

        // invalid hours
        assertFalse(Hours.isValidHours(null)); // null
        assertFalse(Hours.isValidHours("")); // empty string
        assertFalse(Hours.isValidHours(" ")); // spaces only
        assertFalse(Hours.isValidHours("twenty")); // wrong format
        assertFalse(Hours.isValidHours("2hrs")); // wrong format
        assertFalse(Hours.isValidHours("3.5")); // wrong format
        assertFalse(Hours.isValidHours("-4")); // negative hours
        assertFalse(Hours.isValidHours("-2147483649")); // overflow smallest negative integer
        assertFalse(Hours.isValidHours("2147483648")); // overflow largest positive integer

        // valid hours
        assertTrue(Hours.isValidHours("0")); // zero
        assertTrue(Hours.isValidHours("1")); // single digit
        assertTrue(Hours.isValidHours("10")); // multiple digits
        assertTrue(Hours.isValidHours("03")); // leading 0
        assertTrue(Hours.isValidHours("1000")); // long hours
        assertTrue(Hours.isValidHours("2147483647")); // largest positive integer
    }

    @Test
    public void equals() {
        Hours hours = new Hours("5");

        // same values -> returns true
        assertTrue(hours.equals(new Hours("5")));

        // same object -> returns true
        assertTrue(hours.equals(hours));

        // null -> returns false
        assertFalse(hours.equals(null));

        // different types -> returns false
        assertFalse(hours.equals(5.0f));

        // different values -> returns false
        assertFalse(hours.equals(new Hours("10")));
    }

}
