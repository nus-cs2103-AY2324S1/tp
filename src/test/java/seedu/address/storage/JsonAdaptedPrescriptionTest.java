package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPrescription.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPrescriptions.ASPIRIN;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Stock;

public class JsonAdaptedPrescriptionTest {
    private static final String INVALID_NAME = "@spr!n";
    private static final String INVALID_DOSAGE = "ABCD";
    private static final String INVALID_FREQUENCY = " ";
    private static final String INVALID_START_DATE = "1/1/2023";
    private static final String INVALID_END_DATE = "01/2/2024";
    private static final String INVALID_EXPIRY_DATE = "2024/01/23";
    private static final String INVALID_STOCK = "EFGH";
    private static final String INVALID_NOTE = " ";

    private static final String VALID_NAME = ASPIRIN.getName().toString();
    private static final String VALID_DOSAGE = ASPIRIN.getDosage().get().toString();
    private static final String VALID_FREQUENCY = ASPIRIN.getFrequency().get().toString();
    private static final String VALID_START_DATE = ASPIRIN.getStartDate().toString();
    private static final String VALID_END_DATE = ASPIRIN.getEndDate().get().toString();
    private static final String VALID_EXPIRY_DATE = ASPIRIN.getExpiryDate().get().toString();
    private static final String VALID_STOCK = ASPIRIN.getTotalStock().get().toString();
    private static final String VALID_CONSUMPTION = ASPIRIN.getConsumptionCount().toString();
    private static final String VALID_NOTE = ASPIRIN.getNote().get().toString();

    @Test
    public void toModelType_validPrescriptionDetails_returnsPrescription() throws Exception {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(ASPIRIN);
        assertEquals(ASPIRIN, prescription.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            INVALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            null,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidDosage_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            INVALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = Dosage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidFrequency_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            INVALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = Frequency.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            INVALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            INVALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            INVALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidStock_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            INVALID_STOCK,
            VALID_CONSUMPTION,
            false,
            VALID_NOTE
        );
        String expectedMessage = Stock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidNote_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_CONSUMPTION,
            false,
            INVALID_NOTE
        );
        String expectedMessage = Note.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }
}
