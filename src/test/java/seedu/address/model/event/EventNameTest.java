package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidEventName_throwsIllegalArgumentException() {
        String invalidEventName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidEventName));
    }

    @Test
    public void isValidEventName() {
        // null event name
        assertThrows(NullPointerException.class, () -> EventName.isValidName(null));

        // invalid event names
        assertFalse(EventName.isValidName("")); // empty string
        assertFalse(EventName.isValidName(" ")); // spaces only

        // valid event names
        assertTrue(EventName.isValidName("event name")); // alphabets only
        assertTrue(EventName.isValidName("12345")); // numbers only
        assertTrue(EventName.isValidName("event name 2")); // alphanumeric characters
        assertTrue(EventName.isValidName("Event Name")); // with capital letters
        assertTrue(EventName.isValidName("Event Name 2")); // long event names
        assertTrue(EventName.isValidName("v1.2")); // alphanumeric with symbols
    }

    @Test
    public void equals() {
        EventName eventName = new EventName("Valid Event Name");

        // same values -> returns true
        assertTrue(eventName.equals(new EventName("Valid Event Name")));

        // same object -> returns true
        assertTrue(eventName.equals(eventName));

        // null -> returns false
        assertFalse(eventName.equals(null));

        // different types -> returns false
        assertFalse(eventName.equals(5.0f));

        // different values -> returns false
        assertFalse(eventName.equals(new EventName("Other Valid Event Name")));
    }

    @Test
    public void toStringTest() {
        EventName eventName = new EventName("Valid Event Name");
        assertTrue(eventName.toString().equals("Valid Event Name"));
    }
}
