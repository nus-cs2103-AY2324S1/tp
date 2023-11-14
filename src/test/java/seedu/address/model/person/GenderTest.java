package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String invalidGender = "Male";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("Male"));
        assertFalse(Gender.isValidGender("Female"));
        assertFalse(Gender.isValidGender("1234"));
        assertFalse(Gender.isValidGender("M F"));

        // valid gender
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("F"));
        assertTrue(Gender.isValidGender("m"));
        assertTrue(Gender.isValidGender("f"));
    }

    @Test
    public void equals() {
        Gender gender = new Gender("M");

        // same values -> returns true
        assertTrue(gender.equals(new Gender("M")));
        assertTrue(gender.equals(new Gender("m")));

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
    public void hashCode_returnsExpectedHashCode() {
        Gender gender = new Gender("M");
        int expectedHashCode = "M".hashCode();
        int actualHashCode = gender.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
}
