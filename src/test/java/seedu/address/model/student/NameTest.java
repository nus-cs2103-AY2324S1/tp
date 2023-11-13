package seedu.address.model.student;

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
        String hundredCharName = "Ali Ben Ching Dover Elephant Fishballs Ginormous Hugh Indiana Jelly Krispy "
                + "Lambasted Mamamia Nutella";

        // EP: null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid names
        // EP: empty strings
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName("  ")); // spaces only

        // EP : non-alphabetical characters
        assertFalse(Name.isValidName("^")); // only non-alphabetical characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphabetical characters
        assertFalse(Name.isValidName("12345")); // numbers only
        assertFalse(Name.isValidName("peter the 2nd")); // contains numbers

        // EP: more than 100 characters
        assertFalse(Name.isValidName(hundredCharName + "a")); // 101 characters


        // valid names
        // EP: one character alphabet only
        assertTrue(Name.isValidName("A"));

        // EP: Alphabetical characters with capital letters
        assertTrue(Name.isValidName("Capital Tan"));

        // EP: 100 alphabetical characters
        assertTrue(Name.isValidName(hundredCharName));
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
