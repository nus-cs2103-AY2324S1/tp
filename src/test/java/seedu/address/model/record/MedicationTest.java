package seedu.address.model.record;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class MedicationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medication(null));
    }
    @Test
    public void isValidMedication_success() {
        String validMedication = "Tylenol";
        assertTrue(Medication.isValidMedication(validMedication));
    }
    @Test
    public void isValidMedication_failure() {
        String invalidMedication = "";
        assertFalse(Medication.isValidMedication(invalidMedication));
    }
    @Test
    public void equals() {
        Medication medication = new Medication("Tylenol");

        assertTrue(medication.equals(medication));

        assertTrue(medication.equals(new Medication("Tylenol")));

        assertFalse(medication.equals(null));

        assertFalse(medication.equals(new Condition("Allergic Reaction")));
    }
}
