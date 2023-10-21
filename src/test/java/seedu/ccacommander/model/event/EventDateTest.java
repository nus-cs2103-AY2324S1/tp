package seedu.ccacommander.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidEventDate_throwsIllegalArgumentException() {
        String invalidEventDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EventDate(invalidEventDate));
    }

    @Test
    public void isValidDate() {
        // null eventDate
        assertThrows(NullPointerException.class, () -> EventDate.isValidDate(null));

        // invalid eventDates
        assertFalse(EventDate.isValidDate("")); // empty string
        assertFalse(EventDate.isValidDate(" ")); // spaces only
        assertFalse(EventDate.isValidDate("2005 12 12")); // wrong format
        assertFalse(EventDate.isValidDate("12th December 2005")); // wrong format
        assertFalse(EventDate.isValidDate("2005-1-1")); // wrong format

        // valid eventDates
        assertTrue(EventDate.isValidDate("2005-12-12"));
        assertTrue(EventDate.isValidDate("2023-10-31"));
        assertTrue(EventDate.isValidDate("2024-01-01")); // leading 0
        assertTrue(EventDate.isValidDate("5555-12-31")); // far date
    }

    @Test
    public void equals() {
        EventDate eventDate = new EventDate("2023-10-31");

        // same values -> returns true
        assertTrue(eventDate.equals(new EventDate("2023-10-31")));

        // same object -> returns true
        assertTrue(eventDate.equals(eventDate));

        // null -> returns false
        assertFalse(eventDate.equals(null));

        // different types -> returns false
        assertFalse(eventDate.equals(5.0f));

        // different values -> returns false
        assertFalse(eventDate.equals(new EventDate("2024-01-05")));
    }
}
