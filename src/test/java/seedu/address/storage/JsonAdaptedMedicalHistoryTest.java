package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JsonAdaptedMedicalHistoryTest {

    @Test
    public void getMedicalHistoryName() {
        JsonAdaptedMedicalHistory medicalHistory = new JsonAdaptedMedicalHistory("Diabetes");
        assertTrue(medicalHistory.getMedicalHistoryName() == "Diabetes");

        assertFalse(medicalHistory.getMedicalHistoryName() == "Hypertension");
    }

}
