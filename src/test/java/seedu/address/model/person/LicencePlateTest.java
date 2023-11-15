package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LicencePlateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LicencePlate(null));
    }

    @Test
    public void constructor_invalidLicencePlate_throwsIllegalArgumentException() {
        String invalidLicencePlate = "";
        assertThrows(IllegalArgumentException.class, () -> new LicencePlate(invalidLicencePlate));
    }

    @Test
    public void isValidLicencePlate() {
        // null licence plate
        assertThrows(NullPointerException.class, () -> LicencePlate.isValidLicencePlate(null));

        // invalid licence plate
        assertFalse(LicencePlate.isValidLicencePlate("")); // empty string
        assertFalse(LicencePlate.isValidLicencePlate(" ")); // spaces only
        assertFalse(LicencePlate.isValidLicencePlate("^")); // only non-alphanumeric characters
        assertFalse(LicencePlate.isValidLicencePlate("SSS1234A*")); // contains non-alphanumeric characters
        assertFalse(LicencePlate.isValidLicencePlate("ABC1234A")); // does not start with S
        assertFalse(LicencePlate.isValidLicencePlate("SIA1234A")); // contains forbidden letters
        assertFalse(LicencePlate.isValidLicencePlate("SSS12345A")); // more numbers than allowed

        // valid licence plate
        assertTrue(LicencePlate.isValidLicencePlate("SFZ1A")); // all capital letters, minimum length
        assertTrue(LicencePlate.isValidLicencePlate("sfz1a")); // with small letters
        assertTrue(LicencePlate.isValidLicencePlate("SSS1234S")); // maximum length
    }

    @Test
    public void equals() {
        LicencePlate licencePlate = new LicencePlate("SFZ1A");

        // same values -> returns true
        assertTrue(licencePlate.equals(new LicencePlate("SFZ1A")));
        assertTrue(licencePlate.equals(new LicencePlate("sfz1a")));

        // same object -> returns true
        assertTrue(licencePlate.equals(licencePlate));

        // null -> returns false
        assertFalse(licencePlate.equals(null));

        // different types -> returns false
        assertFalse(licencePlate.equals(5.0f));

        // different values -> returns false
        assertFalse(licencePlate.equals(new LicencePlate("SFZ2A")));
    }
}
