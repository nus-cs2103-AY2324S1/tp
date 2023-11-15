package seedu.address.model.displayable;

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

        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertTrue(Name.isValidName("^")); // only non-alphanumeric characters
        assertTrue(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
    @Test
    public void isAppropriateName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isAppropriateName(null));

        // invalid name
        assertFalse(Name.isAppropriateName("")); // empty string
        assertFalse(Name.isAppropriateName(" ")); // spaces only
        assertFalse(Name.isAppropriateName("^")); // only non-alphanumeric characters
        assertFalse(Name.isAppropriateName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isAppropriateName("peter jack")); // alphabets only
        assertTrue(Name.isAppropriateName("12345")); // numbers only
        assertTrue(Name.isAppropriateName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isAppropriateName("Capital Tan")); // with capital letters
        assertTrue(Name.isAppropriateName("David Roger Jackson Ray Jr 2nd")); // long names
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
