package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRecord.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.FEVER0;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Condition;
import seedu.address.model.record.Medication;
import seedu.address.model.shared.DateTime;

public class JsonAdaptedRecordTest {

    private static final String VALID_DATETIME = FEVER0.getDateTime().toString();

    private static final List<JsonAdaptedCondition> VALID_CONDITIONS = FEVER0.getConditions()
            .stream()
            .map(JsonAdaptedCondition::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedMedication> VALID_MEDICATIONS = FEVER0.getMedications()
            .stream()
            .map(JsonAdaptedMedication::new)
            .collect(Collectors.toList());
    @Test
    public void toModelType_validRecordDetails_returnsRecord() throws Exception {
        JsonAdaptedRecord record = new JsonAdaptedRecord(FEVER0);
        assertEquals(FEVER0, record.toModelType());
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(null, VALID_CONDITIONS, VALID_MEDICATIONS, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }

    @Test
    public void toModelType_nullCondition_throwsIllegalValueException() {
        List<JsonAdaptedCondition> invalidCondition = new ArrayList<>(VALID_CONDITIONS);
        invalidCondition.add(null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Condition.class.getSimpleName());
        JsonAdaptedRecord record = new JsonAdaptedRecord(VALID_DATETIME,
            invalidCondition, VALID_MEDICATIONS, null, null);
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }

    @Test
    public void toModelType_nullMedication_throwsIllegalValueException() {
        List<JsonAdaptedMedication> invalidMedications = new ArrayList<>(VALID_MEDICATIONS);
        invalidMedications.add(null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Medication.class.getSimpleName());
        JsonAdaptedRecord record = new JsonAdaptedRecord(VALID_DATETIME,
            VALID_CONDITIONS, invalidMedications, null, null);
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }
}
