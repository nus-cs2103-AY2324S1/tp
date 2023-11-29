package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.PATIENT_BOB;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.treatment.TreatmentName;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_BIRTHDATE = "32-15-2023";
    private static final String INVALID_GENDER = "H";
    private static final String INVALID_TREATMENT = " ";
    private static final String INVALID_REMARK = " ";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = PATIENT_BOB.getName().toString();
    private static final String VALID_PHONE = PATIENT_BOB.getPhone().toString();
    private static final String VALID_EMAIL = PATIENT_BOB.getEmail().toString();
    private static final String VALID_ADDRESS = PATIENT_BOB.getAddress().toString();
    private static final String VALID_BIRTHDATE = "12-12-1990";
    private static final String VALID_GENDER = "M";
    private static final String VALID_TREATMENT = "Cleaning";
    private static final String VALID_REMARK = "Peanut Allergy";
    private static final String DEFAULT_PATIENT_ID = "1";
    private static final List<JsonAdaptedTag> VALID_TAGS = PATIENT_BOB.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPatientDetails_returnsPerson() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(PATIENT_BOB);
        assertEquals(PATIENT_BOB, patient.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(INVALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(null, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, INVALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, null, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, INVALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, null, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, INVALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, null, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, INVALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, null, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidBirthdate_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, INVALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = Birthdate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullBirthdate_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, null, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthdate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, INVALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = Remark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, null,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidTreatment_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        INVALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = TreatmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullTreatment_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        null, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TreatmentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_BIRTHDATE, VALID_GENDER, VALID_REMARK,
                        VALID_TREATMENT, VALID_ADDRESS, VALID_EMAIL, DEFAULT_PATIENT_ID, invalidTags);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

}
