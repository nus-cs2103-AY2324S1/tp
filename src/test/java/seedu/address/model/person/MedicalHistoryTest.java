package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_ANEMIA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_OSTEOPOROSIS;
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
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidMedicalHistory));
    }

    @Test
    public void isValidMedicalHistory() {
        // null specialty
        assertThrows(NullPointerException.class, () -> MedicalHistory.isValidMedicalHistory(null));

        // invalid specialties
        assertFalse(MedicalHistory.isValidMedicalHistory("")); // empty string
        assertFalse(MedicalHistory.isValidMedicalHistory(" ")); // spaces only
        // valid medical history
        assertTrue(MedicalHistory.isValidMedicalHistory(VALID_MEDICAL_HISTORY_OSTEOPOROSIS));
    }

    @Test
    public void equals() {
        MedicalHistory medicalHistory = new MedicalHistory(VALID_MEDICAL_HISTORY_OSTEOPOROSIS);

        // same values -> returns true
        assertTrue(medicalHistory.equals(new MedicalHistory(VALID_MEDICAL_HISTORY_OSTEOPOROSIS)));

        // same object -> returns true
        assertTrue(medicalHistory.equals(medicalHistory));

        // null -> returns false
        assertFalse(medicalHistory.equals(null));

        // different types -> returns false
        assertFalse(medicalHistory.equals(VALID_MEDICAL_HISTORY_OSTEOPOROSIS));

        // different values -> returns false
        assertFalse(medicalHistory.equals(new MedicalHistory(VALID_MEDICAL_HISTORY_ANEMIA)));
    }
}
