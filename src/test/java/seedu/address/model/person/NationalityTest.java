package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NationalityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nationality(null));
    }

    @Test
    public void constructor_invalidNationality_throwsIllegalArgumentException() {
        String invalidNationality = "invalid";
        assertThrows(IllegalArgumentException.class, () -> new Nationality(invalidNationality));
    }

    @Test
    public void isValidNationality() {
        // null nationality
        assertThrows(NullPointerException.class, () -> Nationality.isValidNationality(null));

        // invalid nationality
        assertFalse(Nationality.isValidNationality("")); // empty string
        assertFalse(Nationality.isValidNationality(" ")); // empty string
        assertFalse(Nationality.isValidNationality("random")); // random string

        // valid nationalities
        assertTrue(Nationality.isValidNationality("local"));
        assertTrue(Nationality.isValidNationality("Local"));
        assertTrue(Nationality.isValidNationality("foreigner"));
        assertTrue(Nationality.isValidNationality("Foreigner"));
    }

    @Test
    public void equals() {
        Nationality nationality = new Nationality("local");

        // same values -> returns true
        assertTrue(nationality.equals(new Nationality("local")));
        assertTrue(nationality.equals(new Nationality("Local")));

        // same object -> returns true
        assertTrue(nationality.equals(nationality));

        // null -> returns false
        assertFalse(nationality.equals(null));

        // different types -> returns false
        assertFalse(nationality.equals(5.0f));

        // different values -> returns false
        assertFalse(nationality.equals(new Nationality("foreigner")));
    }

    @Test
    public void hashCode_returnsExpectedHashCode() {
        Nationality nationality = new Nationality("local");
        int expectedHashCode = "local".hashCode();
        int actualHashCode = nationality.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void toString_returnsValue() {
        Nationality nationality = new Nationality("local");
        assertEquals("local", nationality.toString());
    }
}
