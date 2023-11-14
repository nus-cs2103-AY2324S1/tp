package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // Valid names
        assertTrue(Name.isValidName("John")); // Single word name
        assertTrue(Name.isValidName("John Doe")); // Standard first and last name
        assertTrue(Name.isValidName("Anne Marie")); // Double first name

        // Test cases for invalid names
        assertFalse(Name.isValidName("")); // Empty string
        assertFalse(Name.isValidName(" ")); // Spaces only
        assertFalse(Name.isValidName("123")); // Digits only
        assertFalse(Name.isValidName("John3")); // Name with digits
        assertFalse(Name.isValidName(" John")); // Leading space
        assertFalse(Name.isValidName("John ")); // Trailing space
        assertFalse(Name.isValidName("John@Doe")); // Invalid character
        assertFalse(Name.isValidName("John_Doe")); // Underscore
        assertFalse(Name.isValidName("John.Doe")); // Period character
        assertFalse(Name.isValidName("-John")); // Leading hyphen
        assertFalse(Name.isValidName("John-")); // Trailing hyphen
        assertFalse(Name.isValidName("John--Doe")); // Double hyphen
        assertFalse(Name.isValidName("Anne  Marie")); // Double first name
        assertFalse(Name.isValidName("Ana María")); // Name with accented characters
        assertFalse(Name.isValidName("O'Conner")); // Name with apostrophe
        assertFalse(Name.isValidName("D'Artagnan")); // Name with apostrophe
        assertFalse(Name.isValidName("João")); // Name with non-English characters
        assertFalse(Name.isValidName("François")); // Name with cedilla

        // Exceptions
        assertThrows(NullPointerException.class, () -> Name.isValidName(null)); // Null name
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));
    }
}
