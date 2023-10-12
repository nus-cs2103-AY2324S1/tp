package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IcTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ic(null));
    }

    @Test
    public void constructor_invalidIc1_throwsIllegalArgumentException() {
        String invalidIc1 = "S458Z";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidIc1));
    }

    @Test
    public void constructor_invalidIc2_throwsIllegalArgumentException() {
        String invalidIc2 = "W1234567Z";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidIc2));
    }

    @Test
    public void isValidIc() {
        // null nric
        assertThrows(NullPointerException.class, () -> Ic.isValidIc(null));

        // invalid nric
        assertFalse(Ic.isValidIc("")); // empty string
        assertFalse(Ic.isValidIc(" ")); // spaces only
        assertFalse(Ic.isValidIc("S333444Z")); // only 6 numbers included
        assertFalse(Ic.isValidIc("G2223331H")); // starting alphabet not S or T

        // valid nric
        assertTrue(Ic.isValidIc("S0345999H")); // starting alphabet S
        assertTrue(Ic.isValidIc("T0345999H")); // starting alphabet T
    }

    @Test
    public void equals() {
        Ic ic = new Ic("T1111222H");

        // same values -> returns true
        assertTrue(ic.equals(new Ic("T1111222H")));

        // same object -> returns true
        assertTrue(ic.equals(ic));

        // null -> returns false
        assertFalse(ic.equals(null));

        // different types -> returns false
        assertFalse(ic.equals(5.0f));

        // different values -> returns false
        assertFalse(ic.equals(new Name("T1111222H")));
    }
}
