package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // blank gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only

        // Repeated/Invalid
        assertFalse(Gender.isValidGender("FF")); // repetition of F
        assertFalse(Gender.isValidGender("MM")); // repetition of F
        assertFalse(Gender.isValidGender("T")); // something other than M or F

        // valid gender
        assertTrue(Gender.isValidGender("M")); // underscore in local part
        assertTrue(Gender.isValidGender("F")); // period in local part
    }

    @Test
    public void equals() {
        Gender gender = new Gender("M");

        // same values -> returns true
        assertTrue(gender.equals(new Gender("M")));

        // same object -> returns true
        assertTrue(gender.equals(gender));

        // null -> returns false
        assertFalse(gender.equals(null));

        // different types -> returns false
        assertFalse(gender.equals(5.0f));

        // different values -> returns false
        assertFalse(gender.equals(new Gender("F")));
    }

    @Test
    public void hashCodeTest() {
        Gender gender = new Gender("F");
        assertTrue(new Gender("F").hashCode() == gender.hashCode());
    }
}
