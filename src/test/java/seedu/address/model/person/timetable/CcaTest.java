package seedu.address.model.person.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class CcaTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cca(null, "monday 1200 1400"));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Cca(invalidName, "monday 1200 1400"));
    }

    @Test
    public void newCca_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Cca.newCca(null));
    }

    @Test
    public void newCca_invalidInput_throwsIllegalArgumentException() {
        String invalidInput = "monday 1200 1400";
        assertThrows(IllegalArgumentException.class, () -> Cca.newCca(invalidInput));
    }

    @Test
    public void newCca_invalidName_throwsIllegalValueException() {
        String invalidInput = "@ monday 1200 1400";
        assertThrows(IllegalValueException.class, () -> Cca.newCca(invalidInput));
    }

    @Test
    public void newCca_validInput_success() throws IllegalValueException {
        String validInput = "badminton monday 1200 1400";
        assertEquals(new Cca("badminton", "monday 1200 1400"), Cca.newCca(validInput));
    }

    @Test
    public void newCca_validInputSpaceBetweenName_success() throws IllegalValueException {
        String validInput = "badminton 2 monday 1200 1400";
        assertEquals(new Cca("badminton 2", "monday 1200 1400"), Cca.newCca(validInput));
    }

    @Test
    public void isCca_success() {
        Cca cca = new Cca("badminton", "monday 1200 1400");
        assertTrue(cca.isCca());
    }

    @Test
    public void getType_success() {
        Cca cca = new Cca("badminton", "monday 1200 1400");
        assertEquals("CCA", cca.getType());
    }

    @Test
    public void isValidCcaName() {
        // null name
        assertThrows(NullPointerException.class, () -> Cca.isValidCcaName(null));

        // invalid name
        assertFalse(Cca.isValidCcaName("")); // empty string
        assertFalse(Cca.isValidCcaName(" ")); // spaces only
        assertFalse(Cca.isValidCcaName("^")); // only non-alphanumeric characters
        assertFalse(Cca.isValidCcaName("basketball*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Cca.isValidCcaName("badminton")); // alphabets only
        assertTrue(Cca.isValidCcaName("12345")); // numbers only
        assertTrue(Cca.isValidCcaName("badminton 1")); // alphanumeric characters
        assertTrue(Cca.isValidCcaName("Badminton 2")); // with capital letters
        assertTrue(Cca.isValidCcaName("Badminton is a good sport")); // long names
    }

    @Test
    public void equals() {
        Cca cca = new Cca("Valid Cca", "monday 1200 1400");

        // same values -> returns true
        assertTrue(cca.equals(new Cca("Valid Cca", "monday 1200 1400")));

        // same object -> returns true
        assertTrue(cca.equals(cca));

        // null -> returns false
        assertFalse(cca.equals(null));

        // different types -> returns false
        assertFalse(cca.equals(5.0f));

        // different values -> returns false
        assertFalse(cca.equals(new Cca("Other Valid Name", "monday 1200 1400")));

        assertFalse(cca.equals(new Cca("Valid Cca", "tuesday 1200 1400")));
    }

    @Test
    public void testHashCode() {
        Cca cca = new Cca("Valid Cca", "monday 1200 1400");
        assertEquals(cca.hashCode(), new Cca("Valid Cca", "monday 1200 1400").hashCode());
    }
}
