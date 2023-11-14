package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidMajor_throwsIllegalArgumentException() {
        String invalidMajor = "Computer Games";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidMajor));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Major.isValidMajor(null));

        // blank major
        assertFalse(Major.isValidMajor("")); // empty string
        assertFalse(Major.isValidMajor(" ")); //  spaces only

        // invalid major
        assertFalse(Major.isValidMajor("Computer Games"));
        assertFalse(Major.isValidMajor("Finance"));
        assertFalse(Major.isValidMajor("ComputerScience")); // no space
        assertFalse(Major.isValidMajor(" Computer Science")); // leading space
        assertFalse(Major.isValidMajor("Computer Science ")); // trailing space

        // valid major
        assertTrue(Major.isValidMajor("computer science")); // lowercase
        assertTrue(Major.isValidMajor("Computer Science"));
        assertTrue(Major.isValidMajor("Information Systems"));
    }

    @Test
    public void equals() {
        Major major = new Major("Computer Science");

        // same values -> returns true
        assertTrue(major.equals(new Major("Computer Science")));

        // same object -> returns true
        assertTrue(major.equals(major));

        // null -> returns false
        assertFalse(major.equals(null));

        // different types -> returns false
        assertFalse(major.equals(5.0f));

        // different values -> returns false
        assertFalse(major.equals(new Name("Information Systems")));
    }

    @Test
    public void hashCode_returnsExpectedHashCode() {
        Major major = new Major("Computer Science");
        int expectedHashCode = "Computer Science".hashCode();
        int actualHashCode = major.hashCode();
        assertEquals(expectedHashCode, actualHashCode);
    }
}
