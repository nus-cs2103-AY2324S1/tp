package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NameTest {
    @Test
    public void isValidName() {
        // invalid name
        assertFalse(Name.isValid("")); // empty string
        assertFalse(Name.isValid(" ")); // spaces only
        assertFalse(Name.isValid("^")); // only non-alphanumeric characters
        assertFalse(Name.isValid("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValid("peter jack")); // alphabets only
        assertTrue(Name.isValid("12345")); // numbers only
        assertTrue(Name.isValid("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValid("Capital Tan")); // with capital letters
        assertTrue(Name.isValid("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
