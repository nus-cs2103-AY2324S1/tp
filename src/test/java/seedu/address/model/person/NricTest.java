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
        // null id
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid id
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only

        // valid id
        assertTrue(Nric.isValidNric("S1234567E"));
        assertTrue(Nric.isValidNric("-")); // one character
        assertTrue(Nric.isValidNric("t0987654f")); // long id
        assertTrue(Nric.isValidNric("S123")); // custom ID 1
        assertTrue(Nric.isValidNric("S2-1213")); // custom ID 2
    }

    @Test
    public void equals() {
        Nric nric = new Nric("Valid Nric");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("Valid Nric")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("Other Valid Nric")));
    }
}
