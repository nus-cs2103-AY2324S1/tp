package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DosageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Dosage(null));
    }

    @Test
    public void constructor_invalidDosage_throwsIllegalArgumentException() {
        String invalidDosage = "";
        assertThrows(IllegalArgumentException.class, () -> new Dosage(invalidDosage));
    }

    @Test
    public void isValidDosage() {
        // null dosage
        assertThrows(NullPointerException.class, () -> Dosage.isValidDosage(null));

        // invalid dosage
        assertFalse(Dosage.isValidDosage("")); // empty string
        assertFalse(Dosage.isValidDosage(" ")); // spaces only
        assertFalse(Dosage.isValidDosage("^")); // non-alphanumeric characters
        assertFalse(Dosage.isValidDosage("a")); // alphabets

        // valid dosage
        assertTrue(Dosage.isValidDosage("1")); // single digit
        assertTrue(Dosage.isValidDosage("12")); // double digit
        assertTrue(Dosage.isValidDosage("02")); // trailing zero
    }

    @Test
    public void equals() {
        Dosage dosage = new Dosage("1");

        // same values -> returns true
        assertTrue(dosage.equals(new Dosage("1")));

        // same object -> returns true
        assertTrue(dosage.equals(dosage));

        // null -> returns false
        assertFalse(dosage.equals(null));

        // different types -> returns false
        assertFalse(dosage.equals(5.0f));

        // different values -> returns false
        assertFalse(dosage.equals(new Dosage("2")));
    }
}
