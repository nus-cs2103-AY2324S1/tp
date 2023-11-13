package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // blank deadline
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only

        // Invalid deadline values that violate the pattern
        assertFalse(Deadline.isValidDeadline("25/03/2024"));
        assertFalse(Deadline.isValidDeadline("12-04-2024"));

        // valid deadline
        assertTrue(Deadline.isValidDeadline("25 Mar 2024"));
        assertTrue(Deadline.isValidDeadline("12 Apr 2024"));

        // Edge cases for dates
        assertFalse(Deadline.isValidDeadline("31 Feb 2024"));
        assertFalse(Deadline.isValidDeadline("31 Apr 2024"));
        assertTrue(Deadline.isValidDeadline("29 Feb 2024"));
        assertFalse(Deadline.isValidDeadline("29 Feb 1900"));
        assertTrue(Deadline.isValidDeadline("29 Feb 2000"));
    }

    @Test
    public void equals() {
        Deadline deadline = new Deadline("17 Jan 2024");

        // same values -> returns true
        assertTrue(deadline.equals(new Deadline("17 Jan 2024")));

        // same object -> returns true
        assertTrue(deadline.equals(deadline));

        // null -> returns false
        assertFalse(deadline.equals(null));

        // different types -> returns false
        assertFalse(deadline.equals(5.0f));

        // different values -> returns false
        assertFalse(deadline.equals(new Deadline("19 Feb 2024")));
    }
}
