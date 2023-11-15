package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null nric
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nric
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("^")); // only non-alphanumeric characters
        assertFalse(Nric.isValidNric("123A*")); // contains non-alphanumeric characters
        assertFalse(Nric.isValidNric("1234A")); // incorrect length
        assertFalse(Nric.isValidNric("1234")); // no letters
        assertFalse(Nric.isValidNric("abcd")); // no numbers

        // valid nric
        assertTrue(Nric.isValidNric("123A")); // capital letter
        assertTrue(Nric.isValidNric("123a")); // small letter
    }

    @Test
    public void equals() {
        Nric nric = new Nric("234B");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("234B")));
        assertTrue(nric.equals(new Nric("234b")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("234C")));
    }
}
