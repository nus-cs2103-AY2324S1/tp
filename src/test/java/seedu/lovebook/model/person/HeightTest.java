package seedu.lovebook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Height(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null lovebook
        assertThrows(NullPointerException.class, () -> Height.isValidAddress(null));

        // invalid addresses
        assertFalse(Height.isValidAddress("")); // empty string
        assertFalse(Height.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Height.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Height.isValidAddress("-")); // one character
        assertTrue(Height.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long lovebook
    }

    @Test
    public void equals() {
        Height height = new Height("Valid Height");

        // same values -> returns true
        assertTrue(height.equals(new Height("Valid Height")));

        // same object -> returns true
        assertTrue(height.equals(height));

        // null -> returns false
        assertFalse(height.equals(null));

        // different types -> returns false
        assertFalse(height.equals(5.0f));

        // different values -> returns false
        assertFalse(height.equals(new Height("Other Valid Height")));
    }
}
