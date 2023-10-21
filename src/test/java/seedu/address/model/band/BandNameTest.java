package seedu.address.model.band;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BandNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BandName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new BandName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> BandName.isValidName(null));

        // invalid name
        assertFalse(BandName.isValidName("")); // empty string
        assertFalse(BandName.isValidName(" ")); // spaces only
        assertFalse(BandName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(BandName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(BandName.isValidName("peter jack")); // alphabets only
        assertTrue(BandName.isValidName("12345")); // numbers only
        assertTrue(BandName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(BandName.isValidName("Capital Tan")); // with capital letters
        assertTrue(BandName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        BandName name = new BandName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new BandName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new BandName("Other Valid Name")));
    }
}

