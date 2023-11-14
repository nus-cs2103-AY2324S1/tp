package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDescription(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = " ";
        assertThrows(IllegalArgumentException.class, () -> new EventDescription(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> EventDescription.isValidDescription(null));

        // invalid descriptions
        assertFalse(EventDescription.isValidDescription(" ")); // spaces only

        // valid descriptions
        assertTrue(EventDescription.isValidDescription("")); // empty string
        assertTrue(EventDescription.isValidDescription("This is a valid description."));
        assertTrue(EventDescription.isValidDescription("A description with special characters: @#$%^&*()"));
        assertTrue(EventDescription.isValidDescription("Long Event Description with Many Words and Numbers 12345"));
    }

    @Test
    public void equals() {
        EventDescription description = new EventDescription("Valid Description");

        // same values -> returns true
        assertTrue(description.equals(new EventDescription("Valid Description")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new EventDescription("Other Valid Description")));
    }
}
