package seedu.address.model.company;

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
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("google*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("google")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("3M")); // alphanumeric characters
        assertTrue(Name.isValidName("Lenevo Group")); // with capital letters
        assertTrue(Name.isValidName("Source Code Software Company")); // long names
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

        // different case -> returns true
        assertTrue(name.equals(new Name("VALID NAME")));

        // different case -> returns true
        assertTrue(name.equals(new Name("valid name")));

        name = new Name("Valid Name 123");

        // Alphanumerics preserved -> returns true
        assertTrue(name.equals(new Name("Valid Name 123")));

        name = new Name("Valid Name 2");

        //Does not strip numbers
        assertFalse(name.equals(new Name("Valid Name ")));

        assertFalse(name.equals(new Name("Valid Name")));

    }
}
