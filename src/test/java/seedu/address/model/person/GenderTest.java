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
    public void constructor_invalidGender1_throwsIllegalArgumentException() {
        String invalidGender1 = "Male";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender1));
    }

    @Test
    public void constructor_invalidGender2_throwsIllegalArgumentException() {
        String invalidGender2 = "B";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender2));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("Female")); // Female gender spelt out instead of F
        assertFalse(Gender.isValidGender("B")); // gender not M or F

        // valid gender
        assertTrue(Gender.isValidGender("M")); // gender M for male
        assertTrue(Gender.isValidGender("F")); // gender F for female
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

