package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HousingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Housing(null));
    }

    @Test
    public void constructor_invalidHousing_throwsIllegalArgumentException() {
        String invalidHousing = "invalidHousing";
        assertThrows(IllegalArgumentException.class, () -> new Housing(invalidHousing));
    }

    @Test
    public void isValidHousing() {
        // null housing
        assertThrows(NullPointerException.class, () -> Housing.isValidHousing(null));

        // invalid housing types
        assertFalse(Housing.isValidHousing("invalidHousing")); // not one of the allowed values
        assertFalse(Housing.isValidHousing("")); // empty string
        assertFalse(Housing.isValidHousing(" ")); // spaces only
        assertFalse(Housing.isValidHousing("HDB Condo")); // contains space

        // valid housing types
        assertTrue(Housing.isValidHousing("HDB"));
        assertTrue(Housing.isValidHousing("Condo"));
        assertTrue(Housing.isValidHousing("Landed"));
        assertTrue(Housing.isValidHousing("nil"));
    }

    @Test
    public void equals() {
        Housing housing = new Housing("HDB");

        // same values -> returns true
        assertTrue(housing.equals(new Housing("HDB")));

        // same object -> returns true
        assertTrue(housing.equals(housing));

        // null -> returns false
        assertFalse(housing.equals(null));

        // different types -> returns false
        assertFalse(housing.equals(5.0f));

        // different values -> returns false
        assertFalse(housing.equals(new Housing("Condo")));
    }

}
