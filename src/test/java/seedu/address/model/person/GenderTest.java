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
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender("N")); // incorrect gender
        assertFalse(Gender.isValidGender("23")); // number
        assertFalse(Gender.isValidGender("!!")); // special characters

        //valid gender
        assertTrue(Gender.isValidGender("M")); // Male
        assertTrue(Gender.isValidGender("m")); // Male
        assertTrue(Gender.isValidGender("F")); // Female
        assertTrue(Gender.isValidGender("f")); // Female

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
}
