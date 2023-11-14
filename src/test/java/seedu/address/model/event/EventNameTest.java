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
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidEventName(null));

        // invalid name
        assertFalse(EventName.isValidEventName("")); // empty string
        assertFalse(EventName.isValidEventName(" ")); // spaces only
        assertFalse(EventName.isValidEventName("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
                + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor "
                + "in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur "
                + "sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim "
                + "id est laborum.")); // more than 256 characters

        // valid name
        assertTrue(EventName.isValidEventName("meeting with peter jack")); // alphabets only
        assertTrue(EventName.isValidEventName("^")); // only non-alphanumeric characters
        assertTrue(EventName.isValidEventName("meeting*")); // contains non-alphanumeric characters
        assertTrue(EventName.isValidEventName("12345")); // numbers only
        assertTrue(EventName.isValidEventName("2 meetings with peter the 2nd")); // alphanumeric characters
        assertTrue(EventName.isValidEventName("Capital Tan")); // with capital letters
        assertTrue(EventName.isValidEventName("A really long meeting with David Roger Jackson Ray Jr 2nd"));
        // long names
    }

    @Test
    public void equals() {
        EventName eventName = new EventName("Valid Name");

        // same values -> returns true
        assertTrue(eventName.equals(new EventName("Valid Name")));

        // same object -> returns true
        assertTrue(eventName.equals(eventName));

        // null -> returns false
        assertFalse(eventName.equals(null));

        // different types -> returns false
        assertFalse(eventName.equals(5.0f));

        // different values -> returns false
        assertFalse(eventName.equals(new EventName("Other Valid Name")));
    }
}
