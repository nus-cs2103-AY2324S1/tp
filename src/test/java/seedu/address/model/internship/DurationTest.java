package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DurationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        String invalidDuration = "";
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // null duration -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // invalid durations -> returns false
        assertFalse(Duration.isValidDuration("")); // empty string
        assertFalse(Duration.isValidDuration(" ")); // spaces only
        assertFalse(Duration.isValidDuration("a")); // non number
        assertFalse(Duration.isValidDuration("2.2")); // non-integer number
        assertFalse(Duration.isValidDuration("-2")); // non-positive number

        // valid durations -> returns true
        assertTrue(Duration.isValidDuration("1"));
        assertTrue(Duration.isValidDuration("100"));
        assertTrue(Duration.isValidDuration("999999999"));
    }

    @Test
    public void equals() {
        Duration duration = new Duration("2");

        // same values -> returns true
        assertTrue(duration.equals(new Duration("2")));

        // same object -> returns true
        assertTrue(duration.equals(duration));

        // null -> returns false
        assertFalse(duration.equals(null));

        // different types -> returns false
        assertFalse(duration.equals(5.0f));

        // different values -> returns false
        assertFalse(duration.equals(new Duration("5")));
    }

    @Test
    public void compareTo_differentDurations_correctComparison() {
        Duration d1 = new Duration("3");
        Duration d2 = new Duration("5");

        // d1 is less than d2, so it should return a negative value
        assertTrue(d1.compareTo(d2) < 0);

        // d2 is greater than d1, so it should return a positive value
        assertTrue(d2.compareTo(d1) > 0);
    }
}
