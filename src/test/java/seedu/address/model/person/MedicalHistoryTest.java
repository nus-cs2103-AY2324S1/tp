package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicalHistoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalHistory(null));
    }

    @Test
    public void constructor_invalidMedicalHistory_throwsIllegalArgumentException() {
        String invalidMedicalHistory = "";
        assertThrows(IllegalArgumentException.class, () -> new MedicalHistory(invalidMedicalHistory));
    }

    @Test
    public void isValidMedicalHistory() {
        // null medical history
        assertThrows(NullPointerException.class, () -> MedicalHistory.isValidMedicalHistory(null));

        // invalid medical history
        assertFalse(MedicalHistory.isValidMedicalHistory("")); // empty string
        assertFalse(MedicalHistory.isValidMedicalHistory(" ")); // spaces only
        // more than 50 characters long, invalid long medical history
        assertFalse(MedicalHistory.isValidMedicalHistory("PneumonoultramicroscopicsilicovolcanoconiosisMoreChars"));

        // valid medical history
        assertTrue(MedicalHistory.isValidMedicalHistory("Diabetes"));
        assertTrue(MedicalHistory.isValidMedicalHistory("-")); // one character symbol to represent nil
        assertTrue(MedicalHistory.isValidMedicalHistory("covid-19")); // Mix of alphabets and numbers and symbols
        assertTrue(MedicalHistory.isValidMedicalHistory("121")); // Custom Indexed Medical History
        assertTrue(MedicalHistory.isValidMedicalHistory("Diabetes Type 2")); // with spaces

        // valid long medical history
        assertTrue(MedicalHistory.isValidMedicalHistory("Pneumonoultramicroscopicsilicovolcanoconiosis"));
        // valid long medical history with spaces
        assertTrue(MedicalHistory.isValidMedicalHistory("Diabetes Type 2 and High Blood Pressure"));
    }

    @Test
    public void equals() {
        MedicalHistory medicalHistory = new MedicalHistory("Valid Medical History");

        // same values -> returns true
        assertTrue(medicalHistory.equals(new MedicalHistory("Valid Medical History")));

        // same object -> returns true
        assertTrue(medicalHistory.equals(medicalHistory));

        // null -> returns false
        assertFalse(medicalHistory.equals(null));

        // different types -> returns false
        assertFalse(medicalHistory.equals(5.0f));

        // different values -> returns false
        assertFalse(medicalHistory.equals(new MedicalHistory("Other Valid Medical History")));
    }

    @Test
    public int hashCode() {
        String medicalHistoryValue = "Valid Medical History";
        MedicalHistory medicalHistory = new MedicalHistory(medicalHistoryValue);

        // same value
        assertTrue(medicalHistory.hashCode() == medicalHistoryValue.hashCode());

        // different value
        assertFalse(medicalHistory.hashCode() != medicalHistoryValue.hashCode());

        return 0;
    }
}
