package seedu.address.model.shared;

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
    public void constructor_invalidnric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null nric number
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nric numbers
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("S91T")); // less than 7 numbers
        assertFalse(Nric.isValidNric("S12345671A")); // more than 7 numbers
        assertFalse(Nric.isValidNric("S1234567")); // less than 2 alphabets
        assertFalse(Nric.isValidNric("SA1234567A")); // more than 2 alphabet
        assertFalse(Nric.isValidNric("SA1234567")); // alphabets in wrong position
        assertFalse(Nric.isValidNric("T9312 153A")); // spaces within nric

        // valid nric
        assertTrue(Nric.isValidNric("S1234567A"));
        assertTrue(Nric.isValidNric("t9312153a")); // lower case
    }

    @Test
    public void equals() {
        Nric nric = new Nric("S1234567A");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("S1234567A")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("T1234567A")));
    }
}
