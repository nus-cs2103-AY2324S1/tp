package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.BENSON_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.fields.InterviewTime;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;

public class JsonAdaptedApplicantTest {
    private static final String INVALID_NAME = "R#chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_NAME = BENSON_APPLICANT.getName().toString();
    private static final String VALID_PHONE = BENSON_APPLICANT.getPhone().toString();

    private static final String INVALID_DATE = "12/12";

    private static final String VALID_DATE = "12/12/2023 2359";

    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(BENSON_APPLICANT);
        assertEquals(BENSON_APPLICANT, applicant.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(INVALID_NAME, VALID_PHONE, VALID_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(null, VALID_PHONE, VALID_DATE);
        String expectedMessage = String.format(JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, INVALID_PHONE, VALID_DATE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME, null, VALID_DATE);
        String expectedMessage = String.format(JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT,
                Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, INVALID_DATE);
        String expectedMessage = InterviewTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }
}
