package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Medication;
public class JsonAdaptedMedicationTest {
    private static final String INVALID_MEDICATION = "";
    private static final String INVALID_MEDICATION2 = "Tylenol+";
    private static final Medication VALID_MEDICATION = new Medication("Tylenol");

    @Test
    public void toModelType_validRecordDetails_returnsMedication() throws Exception {
        JsonAdaptedMedication medication = new JsonAdaptedMedication(VALID_MEDICATION);
        assertEquals(VALID_MEDICATION, medication.toModelType());
    }
    @Test
    public void toModelType_invalidMedication_throwsIllegalArgumentException() {
        JsonAdaptedMedication medication = new JsonAdaptedMedication(INVALID_MEDICATION);
        String expectedMessage = Medication.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, medication::toModelType);
    }
    @Test
    public void toModelType_invalidMedication2_throwsIllegalArgumentException() {
        JsonAdaptedMedication medication = new JsonAdaptedMedication(INVALID_MEDICATION2);
        String expectedMessage = Medication.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, medication::toModelType);
    }
    @Test
    public void toModelType_nullMedication_throwsIllegalValueException() {
        JsonAdaptedMedication medication = new JsonAdaptedMedication((String) null);
        String expectedMessage = Medication.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, medication::toModelType);

    }
}
