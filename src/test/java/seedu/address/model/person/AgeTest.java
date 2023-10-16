package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        // null Age
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid ages
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("-1")); // negative numbers
        assertFalse(Age.isValidAge("150")); // absurdly high age
        assertFalse(Age.isValidAge("age")); // non-numeric
        assertFalse(Age.isValidAge("9p1")); // alphabets within digits
        assertFalse(Age.isValidAge("3 4")); // spaces within digits
        assertFalse(Age.isValidAge("34a")); // trailing alphabets

        // valid phone numbers
        assertTrue(Age.isValidAge("0")); // edge case
        assertTrue(Age.isValidAge("6")); // single digit
        assertTrue(Age.isValidAge("91")); // double digits
        assertTrue(Age.isValidAge("114")); // triple digits
        assertTrue(Age.isValidAge("149")); // edge case

    }

    @Test
    public void equals() {
        Age age = new Age("30");

        // same values -> returns true
        assertTrue(age.equals(new Age("30")));

        // same object -> returns true
        assertTrue(age.equals(age));

        // null -> returns false
        assertFalse(age.equals(null));

        // different types -> returns false
        assertFalse(age.equals(5.0f));

        // different values -> returns false
        assertFalse(age.equals(new Age("95")));
    }
}
