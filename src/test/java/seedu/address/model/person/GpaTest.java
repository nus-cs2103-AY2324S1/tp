package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GpaTest {

    @Test
    public void isValidGpa() {
        // invalid GPAs
        assertFalse(Gpa.isValidGpa(-1.0));
        assertFalse(Gpa.isValidGpa(5.1));

        // valid GPAs
        assertTrue(Gpa.isValidGpa(0.0));
        assertTrue(Gpa.isValidGpa(4.0));
        assertTrue(Gpa.isValidGpa(5.0));
    }

    @Test
    public void compareTo() {
        Gpa gpa1 = new Gpa(3.2);
        Gpa gpa2 = new Gpa(3.2);
        Gpa gpa3 = new Gpa(3.3);
        Gpa gpa4 = new Gpa(3.1);

        // gpa1 and gpa2 have the same values, so should return 0
        assertEquals(0, gpa1.compareTo(gpa2));

        // gpa2 is equal to gpa1, so should return 0
        assertEquals(0, gpa2.compareTo(gpa1));

        // gpa1 is less than gpa3, so should return a negative value
        assertTrue(gpa1.compareTo(gpa3) < 0);

        // gpa1 is greater than gpa4, so should return a positive value
        assertTrue(gpa1.compareTo(gpa4) > 0);

    }
    @Test
    public void equals() {
        Gpa gpa = new Gpa(3.2);

        // same values -> returns true
        assertTrue(gpa.equals(new Gpa(3.2)));

        // same object -> returns true
        assertTrue(gpa.equals(gpa));

        // null -> returns false
        assertFalse(gpa.equals(null));

        // different types -> returns false
        assertFalse(gpa.equals("Test"));

        // different values -> returns false
        assertFalse(gpa.equals(new Gpa(3.3)));
    }
}
