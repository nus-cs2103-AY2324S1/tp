package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // blank gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender("  ")); // spaces only

        // invalid gender string representation
        assertFalse(Gender.isValidGender("Male"));
        assertFalse(Gender.isValidGender("Female"));
        assertFalse(Gender.isValidGender("male"));
        assertFalse(Gender.isValidGender("female"));
        assertFalse(Gender.isValidGender("m"));
        assertFalse(Gender.isValidGender("f"));

        // valid gender string representation
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("F"));
    }

    @Test
    public void equals() {
        Gender male = new Gender("M");

        // same values -> return true
        assertTrue(male.equals(new Gender("M")));

        // same object -> return true
        assertTrue(male.equals(male));

        // null -> return false
        assertFalse(male.equals(null));

        // different types -> return false
        assertFalse(male.equals(5.0f));

        // different values -> return false
        assertFalse(male.equals(new Gender ("F")));
    }
}
