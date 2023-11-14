package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PropNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PropName(null));
    }

    @Test
    public void constructor_invalidPropName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new PropName(invalidName));
    }

    @Test
    public void isValidPropName() {
        // null name
        assertThrows(NullPointerException.class, () -> PropName.isValidName(null));

        // invalid name
        assertFalse(PropName.isValidName("")); // empty string
        assertFalse(PropName.isValidName(" ")); // spaces only
        assertFalse(PropName.isValidName(" Hello")); // start with space
        assertFalse(PropName.isValidName("Hello a/")); // with slash
        assertFalse(PropName.isValidName("/")); // with slash
        assertFalse(PropName.isValidName("/abc")); // with slash

        // valid name
        assertTrue(PropName.isValidName("^")); // only non-alphanumeric characters
        assertTrue(PropName.isValidName("skyview*")); // contains non-alphanumeric characters
        assertTrue(PropName.isValidName("skyview")); // alphabets only
        assertTrue(PropName.isValidName("12345")); // numbers only
        assertTrue(PropName.isValidName("skyview 2")); // alphanumeric characters
        assertTrue(PropName.isValidName("Skyview")); // with capital letters
        assertTrue(PropName.isValidName("Skyview Horizonview")); // long names
        assertTrue(PropName.isValidName("J'den")); // apostrophes
        assertTrue(PropName.isValidName("With space")); // with space
        assertTrue(PropName.isValidName("!@#$%^&*()-_+=[{}]|:;'',<>.?")); // symbols except /
        assertTrue(PropName.isValidName("-Hello")); // start with dash
    }

    @Test
    public void equals() {
        PropName name = new PropName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new PropName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new PropName("Other Valid Name")));
    }
}
