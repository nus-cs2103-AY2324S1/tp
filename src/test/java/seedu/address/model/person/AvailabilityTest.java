package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AvailabilityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Availability(null));
    }

    @Test
    public void constructor_invalidAvailability_throwsIllegalArgumentException() {
        // invalid availability, anything other than 'nil', 'Available', 'NotAvailable'
        String invalidAvailability = "invalidAvailability";
        assertThrows(IllegalArgumentException.class, () -> new Availability(invalidAvailability));
    }

    @Test
    public void isValidAvailability() {
        // null availability
        assertThrows(NullPointerException.class, () -> Availability.isValidAvailability(null));

        // invalid availability, anything other than 'nil', 'Available', 'NotAvailable'
        assertFalse(Availability.isValidAvailability("invalidAvailability"));

        // blank availability
        assertFalse(Availability.isValidAvailability("")); // empty string
        assertFalse(Availability.isValidAvailability(" ")); // spaces only

        // valid availability
        assertTrue(Availability.isValidAvailability("Available"));
        assertTrue(Availability.isValidAvailability("NotAvailable"));
        assertTrue(Availability.isValidAvailability("nil"));
    }

    @Test
    public void equals() {
        Availability availability = new Availability("Available");

        // same values -> returns true
        assertTrue(availability.equals(new Availability("Available")));

        // same object -> returns true
        assertTrue(availability.equals(availability));

        // null -> returns false
        assertFalse(availability.equals(null));

        // different types -> returns false
        assertFalse(availability.equals(5.0f));

        // different values -> returns false
        assertFalse(availability.equals(new Availability("NotAvailable")));
    }

}
