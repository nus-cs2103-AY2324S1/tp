package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentDescription(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentDescription(invalidDescription));
    }

    @Test
    public void isValidAppointmentDescription() {
        // null name
        assertThrows(NullPointerException.class, () -> AppointmentDescription.isValidAppointmentDescription(null));

        // invalid description
        assertFalse(AppointmentDescription.isValidAppointmentDescription("")); // empty string
        assertFalse(AppointmentDescription.isValidAppointmentDescription("^")); // only non-alphanumeric characters
        assertFalse(AppointmentDescription.isValidAppointmentDescription("t*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(AppointmentDescription.isValidAppointmentDescription("peter jack")); // alphabets only
        assertTrue(AppointmentDescription.isValidAppointmentDescription("12345")); // numbers only
        assertTrue(AppointmentDescription.isValidAppointmentDescription("peter the 2nd")); // alphanumeric characters
        assertTrue(AppointmentDescription.isValidAppointmentDescription("Capital Tan")); // with capital letters
        assertTrue(AppointmentDescription.isValidAppointmentDescription("David Roger Jackson Ray 2nd")); // long names
    }

    @Test
    public void equals() {
        AppointmentDescription description = new AppointmentDescription("Valid Name");

        // same values -> returns true
        assertTrue(description.equals(new AppointmentDescription("Valid Name")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new AppointmentDescription("Other Valid Name")));
    }
}
