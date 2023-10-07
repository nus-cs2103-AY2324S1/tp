package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid phone numbers
        assertFalse(Location.isValidLocation("")); // empty string
        assertFalse(Location.isValidLocation(" ")); // spaces only
        assertTrue(Location.isValidLocation("91")); // less than 3 numbers
        assertTrue(Location.isValidLocation("phone")); // non-numeric
        assertTrue(Location.isValidLocation("9011p041")); // alphabets within digits
        assertTrue(Location.isValidLocation("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Location.isValidLocation("911")); // exactly 3 numbers
        assertTrue(Location.isValidLocation("93121534"));
        assertTrue(Location.isValidLocation("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        Location location = new Location("999");

        // same values -> returns true
        assertTrue(location.equals(new Location("999")));

        // same object -> returns true
        assertTrue(location.equals(location));

        // null -> returns false
        assertFalse(location.equals(null));

        // different types -> returns false
        assertFalse(location.equals(5.0f));

        // different values -> returns false
        assertFalse(location.equals(new Location("995")));
    }
}
