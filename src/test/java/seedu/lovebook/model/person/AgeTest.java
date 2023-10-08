package seedu.lovebook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age number
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid age numbers
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("age")); // non-numeric
        assertFalse(Age.isValidAge("9011p041")); // alphabets within digits
        assertFalse(Age.isValidAge("9312 1534")); // spaces within digits
        assertFalse(Age.isValidAge("-23")); // Negative Age
        assertFalse(Age.isValidAge("0")); // Zero Age

        // valid age numbers
        assertTrue(Age.isValidAge("91")); // exactly 3 numbers
        assertTrue(Age.isValidAge("23"));
        assertTrue(Age.isValidAge("124")); // long age numbers
    }

    @Test
    public void equals() {
        Age age = new Age("15");

        // same values -> returns true
        assertTrue(age.equals(new Age("15")));

        // same object -> returns true
        assertTrue(age.equals(age));

        // null -> returns false
        assertFalse(age.equals(null));

        // different types -> returns false
        assertFalse(age.equals(5.0f));

        // different values -> returns false
        assertFalse(age.equals(new Age("14")));
    }
}
