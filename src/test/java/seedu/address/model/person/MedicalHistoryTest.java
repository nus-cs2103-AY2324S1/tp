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

        // valid medical history
        assertTrue(MedicalHistory.isValidMedicalHistory("Diabetes"));
        assertTrue(MedicalHistory.isValidMedicalHistory("-")); // one character
        assertTrue(MedicalHistory.isValidMedicalHistory("hypertension")); // long address
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
}
