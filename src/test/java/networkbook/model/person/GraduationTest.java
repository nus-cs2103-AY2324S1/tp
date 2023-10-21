package networkbook.model.person;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GraduationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Graduation(null));
    }

    @Test
    public void isValidGraduation() {
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
        assertFalse(Graduation.isValidGraduation("AY2423-S1")); // years in wrong order
        assertFalse(Graduation.isValidGraduation("AY2423-S3")); // invalid year and sem


        // valid graduation strings
        assertTrue(Graduation.isValidGraduation("AY2324-S1")); // follows format
        assertTrue(Graduation.isValidGraduation("AY6970-S1")); // follows format
        assertTrue(Graduation.isValidGraduation("AY9900-S1")); // follows format
        assertTrue(Graduation.isValidGraduation("AY0001-S1")); // follows format
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


    @Test
    public void isValidGraduationYear() {
        // invalid
        assertFalse(Graduation.isValidGraduationYear(2001, 2000)); // reversed
        assertFalse(Graduation.isValidGraduationYear(2020, 2020)); // difference == 0
        assertFalse(Graduation.isValidGraduationYear(2025, 2030)); // difference > 1

        // valid
        assertTrue(Graduation.isValidGraduationYear(1999, 2000));
        assertTrue(Graduation.isValidGraduationYear(1997, 1998));
    }

    @Test
    public void isValidGraduationSemester() {
        // null
        assertThrows(NullPointerException.class, () -> Graduation.isValidGraduationSemester(null));

        // invalid
        assertFalse(Graduation.isValidGraduationSemester(Graduation.Semester.INVALID));

        // valid
        assertTrue(Graduation.isValidGraduationSemester(Graduation.Semester.S1));
        assertTrue(Graduation.isValidGraduationSemester(Graduation.Semester.S2));
    }

    @Test
    public void parseSemester_validGraduationString_validSemesterEnum() {
        assertEquals(Graduation.Semester.S1, Graduation.parseSemester("AY2021-S1"));
        assertEquals(Graduation.Semester.S2, Graduation.parseSemester("AY2021-S2"));
    }

    @Test
    public void parseSemester_invalidGraduationString_invalidGraduationSemester() {
        assertEquals(Graduation.Semester.INVALID, Graduation.parseSemester("AY2021-S3")); // invalid sem
        assertEquals(Graduation.Semester.INVALID, Graduation.parseSemester("AY2021-S0")); // invalid sem
        assertEquals(Graduation.Semester.INVALID, Graduation.parseSemester("sem 1")); // invalid format
        assertEquals(Graduation.Semester.INVALID, Graduation.parseSemester("")); // empty
    }

    @Test
    public void compareTo() {
        // earlier AY
        assertEquals(-1, new Graduation("AY7071-S1").compareTo(new Graduation("AY2324-S1")));

        // later AY
        assertEquals(1, new Graduation("AY2425-S1").compareTo(new Graduation("AY2324-S1")));

        // same AY earlier sem
        assertEquals(-1, new Graduation("AY2324-S1").compareTo(new Graduation("AY2324-S2")));

        // same AY later sem
        assertEquals(1, new Graduation("AY2324-S2").compareTo(new Graduation("AY2324-S1")));

        // same AY same sem
        assertEquals(0, new Graduation("AY2324-S1").compareTo(new Graduation("AY2324-S1")));
    }
}
