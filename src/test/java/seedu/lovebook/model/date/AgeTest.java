package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age number
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid age numbers [Applying Equivalence Partitioning]
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("age")); // non-numeric
        assertFalse(Age.isValidAge("9011p041")); // alphabets within digits
        assertFalse(Age.isValidAge("9312 1534")); // spaces within digits
        assertFalse(Age.isValidAge("-23")); // Negative Age
        assertFalse(Age.isValidAge("0")); // Zero Age
        assertFalse(Age.isValidAge("17")); // Age below 18
        assertFalse(Age.isValidAge("151")); // Age above 150

        // valid age numbers
        assertTrue(Age.isValidAge("91"));
        assertTrue(Age.isValidAge("23"));
        assertTrue(Age.isValidAge("124"));

        // EP Boundary Values
        assertTrue(Age.isValidAge("18")); // Age 18
        assertTrue(Age.isValidAge("150")); // Age 150
    }

    @Test
    public void equals() {
        Age age = new Age("18");

        // same values -> returns true
        assertTrue(age.equals(new Age("18")));

        // same object -> returns true
        assertTrue(age.equals(age));

        // null -> returns false
        assertFalse(age.equals(null));

        // different types -> returns false
        assertFalse(age.equals(5.0f));

        // different values -> returns false
        assertFalse(age.equals(new Age("19")));
    }

    @Test
    public void compareTo() {
        Age age = new Age("25");

        // same values -> returns 0
        assertTrue(age.compareTo(new Age("25")) == 0);

        // same object -> returns 0
        assertTrue(age.compareTo(age) == 0);

        // less than values -> returns 1
        assertTrue(age.compareTo(new Age("23")) == 1);

        // greater values -> returns -1
        assertTrue(age.compareTo(new Age("39")) == -1);
    }

    @Test
    public void hashCodeTest() {
        Age age = new Age("25");
        assertTrue(age.hashCode() == new Age("25").hashCode());
    }
}
