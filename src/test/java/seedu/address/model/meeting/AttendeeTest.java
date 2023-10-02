package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class AttendeeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendee(null));
    }

    @Test
    public void constructor_invalidAttendeeName_throwsIllegalArgumentException() {
        String invalidAttendeeName = "";
        assertThrows(IllegalArgumentException.class, () -> new Attendee(invalidAttendeeName));
    }

    @Test
    public void isValidAttendeeName() {
        // null name
        assertThrows(NullPointerException.class, () -> Attendee.isValidAttendee(null));

        // invalid name
        assertFalse(Attendee.isValidAttendee("")); // empty string
        assertFalse(Attendee.isValidAttendee(" ")); // spaces only
        assertFalse(Attendee.isValidAttendee("^")); // only non-alphanumeric characters
        assertFalse(Attendee.isValidAttendee("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Attendee.isValidAttendee("peter jack")); // alphabets only
        assertTrue(Attendee.isValidAttendee("12345")); // numbers only
        assertTrue(Attendee.isValidAttendee("peter the 2nd")); // alphanumeric characters
        assertTrue(Attendee.isValidAttendee("Capital Tan")); // with capital letters
        assertTrue(Attendee.isValidAttendee("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        Attendee attendee = new Attendee("Valid Name");

        // same values -> returns true
        assertTrue(attendee.equals(new Attendee("Valid Name")));

        // same object -> returns true
        assertTrue(attendee.equals(attendee));

        // null -> returns false
        assertFalse(attendee.equals(null));

        // different types -> returns false
        assertFalse(attendee.equals(5.0f));

        // different values -> returns false
        assertFalse(attendee.equals(new Attendee("Other Valid Name")));
    }
}
