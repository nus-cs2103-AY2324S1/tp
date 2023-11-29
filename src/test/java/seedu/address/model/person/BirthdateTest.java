package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthdate(null));
    }

    @Test
    public void constructor_invalidBirthdate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthdate(invalidDate));
    }

    @Test
    public void isValidBirthdate() {
        // null birthdate
        assertThrows(NullPointerException.class, () -> Birthdate.isValidDate(null));


        // valid birthdate
        assertTrue(Birthdate.isValidDate("01-01-1990")); // past date
        assertTrue(Birthdate.isValidDate("03-01-2021")); // recent date


        // invalid birthdate
        assertFalse(Birthdate.isValidDate("")); // empty string
        assertFalse(Birthdate.isValidDate(" ")); // spaces only
        assertFalse(Birthdate.isValidDate("^")); // only non-alphanumeric characters
        assertFalse(Birthdate.isValidDate("peter*")); // contains non-alphanumeric characters
        assertFalse(Birthdate.isValidDate("12345")); // numbers only
        assertFalse(Birthdate.isValidDate("peter the 2nd")); // alphanumeric characters
        assertFalse(Birthdate.isValidDate("David Roger Jackson Ray Jr 2nd")); // long names
        assertFalse(Birthdate.isValidDate("01-01-3000")); // far into the future
        assertFalse(Birthdate.isValidDate("01-13-2019")); // invalid month


    }

    @Test
    public void equals() {
        Birthdate birthdate = new Birthdate("01-01-1990");

        // same values -> returns true
        assertTrue(birthdate.equals(new Birthdate("01-01-1990")));

        // same object -> returns true
        assertTrue(birthdate.equals(birthdate));

        // null -> returns false
        assertFalse(birthdate.equals(null));

        // different types -> returns false
        assertFalse(birthdate.equals(5.0f));

        // different values -> returns false
        assertFalse(birthdate.equals(new Birthdate("01-01-1991")));
    }
}
