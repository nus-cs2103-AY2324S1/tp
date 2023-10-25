package seedu.application.model.job;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "Nov 12 2023";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    void testisValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadlines
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("Today")); // Invalid DateTime

        // valid deadlines
        assertTrue(Deadline.isValidDeadline("Dec 31 2030 1200"));
    }

    @Test
    public void testCompareTo() {
        // Create Deadline objects
        Deadline earlierDeadline = new Deadline("Dec 31 2023 1200");
        Deadline laterDeadline = new Deadline("Dec 31 2030 1200");
        Deadline emptyDeadline = new Deadline(Deadline.TO_ADD_DEADLINE);

        // when both deadlines are empty
        assertEquals(0, emptyDeadline.compareTo(emptyDeadline));
        assertEquals(0, emptyDeadline.compareTo(new Deadline(Deadline.TO_ADD_DEADLINE)));

        // when one deadline is empty
        assertEquals(1, earlierDeadline.compareTo(emptyDeadline));
        assertEquals(-1, emptyDeadline.compareTo(laterDeadline));

        // when both deadlines have valid dates
        assertEquals(-1, earlierDeadline.compareTo(laterDeadline));
        assertEquals(1, laterDeadline.compareTo(earlierDeadline));
        assertEquals(0, earlierDeadline.compareTo(new Deadline("Dec 31 2023 1200")));
    }

    @Test
    void equals() {
        Deadline deadline = new Deadline("Dec 31 2030 1200");

        // same values -> returns true
        assertTrue(deadline.equals(new Deadline("Dec 31 2030 1200")));

        // same object -> returns true
        assertTrue(deadline.equals(deadline));

        // null -> returns false
        assertFalse(deadline.equals(null));

        // different types -> returns false
        assertFalse(deadline.equals(5.0f));

        // different values -> returns false
        assertFalse(deadline.equals(new Deadline("Dec 31 2040 1200")));
    }
}
