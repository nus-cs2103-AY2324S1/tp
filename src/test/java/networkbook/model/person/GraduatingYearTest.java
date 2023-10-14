package networkbook.model.person;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GraduatingYearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GraduatingYear(null));
    }

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        String invalidYear = "";
        assertThrows(IllegalArgumentException.class, () -> new GraduatingYear(invalidYear));
    }

    @Test
    public void isValidYear() {
        // null graduation year
        assertThrows(NullPointerException.class, () -> GraduatingYear.isValidGraduatingYear(null));

        // invalid graduation years
        assertFalse(GraduatingYear.isValidGraduatingYear("")); // empty string
        assertFalse(GraduatingYear.isValidGraduatingYear(" ")); // spaces only
        assertFalse(GraduatingYear.isValidGraduatingYear("913")); // less than 4 numbers
        assertFalse(GraduatingYear.isValidGraduatingYear("phone")); // non-numeric
        assertFalse(GraduatingYear.isValidGraduatingYear("p041")); // alphabets within digits
        assertFalse(GraduatingYear.isValidGraduatingYear("15 34")); // spaces within digits

        // valid graduation years
        assertTrue(GraduatingYear.isValidGraduatingYear("1911")); // exactly 4 numbers
        assertTrue(GraduatingYear.isValidGraduatingYear("2026")); // exactly 4 numbers
    }

    @Test
    public void equals() {
        GraduatingYear graduatingYear = new GraduatingYear("2023");

        // same values -> returns true
        assertTrue(graduatingYear.equals(new GraduatingYear("2023")));

        // same object -> returns true
        assertTrue(graduatingYear.equals(graduatingYear));

        // null -> returns false
        assertFalse(graduatingYear.equals(null));

        // different types -> returns false
        assertFalse(graduatingYear.equals(5.0f));

        // different values -> returns false
        assertFalse(graduatingYear.equals(new GraduatingYear("2022")));
    }
}
