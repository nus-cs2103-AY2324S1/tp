package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Age;
import seedu.address.model.person.MedicalHistory;

public class JsonAdaptedPatientTest {
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_MEDICAL_HISTORY = BENSON.getMedicalHistory().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();
    private static final String INVALID_AGE = "-1";
    private static final String INVALID_MEDICAL_HISTORY = "";
    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient specialist = new JsonAdaptedPatient(ALICE);
        assertEquals(ALICE, specialist.toModelType());
    }
    @Test
    public void toModelType_invalidMedicalHistory_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_TAGS, VALID_AGE, INVALID_MEDICAL_HISTORY);
        String expectedMessage = MedicalHistory.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_nullMedicalHistory_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_TAGS, VALID_AGE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MedicalHistory.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_TAGS, INVALID_AGE, VALID_MEDICAL_HISTORY);
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_nullAge_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_TAGS, null, VALID_MEDICAL_HISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
