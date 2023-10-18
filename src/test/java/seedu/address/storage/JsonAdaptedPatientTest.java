package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatient.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GENDER = "G";
    private static final String INVALID_NRIC = "T33340K";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_EMERGENCY_CONTACT = "81";
    private static final String INVALID_CONDITION = "";
    private static final String INVALID_BLOODTYPE = "c";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_NRIC = BENSON.getIc().toString();
    private static final String VALID_EMERGENCY_CONTACT = BENSON.getEmergencyContact().toString();
    private static final String VALID_CONDITION = BENSON.getCondition().toString();
    private static final String VALID_BLOODTYPE = BENSON.getBloodType().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, patient.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        INVALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        null, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidIc_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, INVALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullIc_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, null, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, invalidTags, VALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidCondition_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, INVALID_CONDITION, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = Condition.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullCondition_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, null, VALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Condition.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, INVALID_BLOODTYPE,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = BloodType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, null,
                        VALID_EMERGENCY_CONTACT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidEmergencyContact_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        INVALID_EMERGENCY_CONTACT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmergencyContact_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_CONDITION, VALID_BLOODTYPE,
                        null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

}
