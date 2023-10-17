package networkbook.model.person;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GraduationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Graduation(null));
    }

    @Test
    public void isValidYear() {
        // null graduation string
        assertThrows(NullPointerException.class, () -> Graduation.isValidGraduation(null));

        // invalid graduation strings
        assertFalse(Graduation.isValidGraduation("")); // empty string
        assertFalse(Graduation.isValidGraduation(" ")); // spaces only
        assertFalse(Graduation.isValidGraduation("2023 sem 1")); // not following format
        assertFalse(Graduation.isValidGraduation("AY2324-z1")); // wrong letters
        assertFalse(Graduation.isValidGraduation("AY2324")); // missing semester
        assertFalse(Graduation.isValidGraduation("AY910-S1")); // AY not 4 digits
        assertFalse(Graduation.isValidGraduation("AY910-S01")); // semester not 1 digit
        assertFalse(Graduation.isValidGraduation("AY2324-S0")); // semester out of range - low
        assertFalse(Graduation.isValidGraduation("AY2324-S3")); // semester out of range - high
        assertFalse(Graduation.isValidGraduation("AY0110-S1")); // years not consecutive
        assertFalse(Graduation.isValidGraduation("AY6970-S1")); // years not consecutive
        assertFalse(Graduation.isValidGraduation("AY2423-S1")); // years in wrong order


        // valid graduation strings
        assertTrue(Graduation.isValidGraduation("AY2324-S1")); // follows format
        assertTrue(Graduation.isValidGraduation("aY7071-s2")); // follows format, case insensitive
        assertTrue(Graduation.isValidGraduation(" AY6869-S1 ")); // follows format, leading/trailing whitespace
    }

    @Test
    public void equals() {
        Graduation graduation = new Graduation("AY2324-S1");

        // same values -> returns true
        assertTrue(graduation.equals(new Graduation("AY2324-S1")));

        // same object -> returns true
        assertTrue(graduation.equals(graduation));

        // null -> returns false
        assertFalse(graduation.equals(null));

        // different types -> returns false
        assertFalse(graduation.equals(5.0f));

        // different values -> returns false
        assertFalse(graduation.equals(new Graduation("AY2122-S1")));
        assertFalse(graduation.equals(new Graduation("AY2324-S2")));
    }
}
