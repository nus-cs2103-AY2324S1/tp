package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.BOYD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.AppointmentBuilder;

public class JsonAdaptedDoctorTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GENDER = "G";
    private static final String INVALID_NRIC = "T33340K";
    private static final String INVALID_TAG = "nurse";


    private static final String VALID_NAME = BOYD.getName().toString();
    private static final String VALID_PHONE = BOYD.getPhone().toString();
    private static final String VALID_EMAIL = BOYD.getEmail().toString();
    private static final String VALID_ADDRESS = BOYD.getAddress().toString();
    private static final String VALID_REMARK = BOYD.getRemark().toString();
    private static final String VALID_GENDER = BOYD.getGender().toString();
    private static final String VALID_NRIC = BOYD.getIc().toString();
    private static final Appointment BOYD_APPOINTMENT =
            new AppointmentBuilder().withPatientIc(BOYD.getIc()).build();
    private static final List<JsonAdaptedTag> VALID_TAGS = BOYD.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAppointment> VALID_APPOINTMENTS = Arrays.asList(BOYD_APPOINTMENT).stream()
            .map(JsonAdaptedAppointment::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validDoctorDetails_returnsDoctor() throws Exception {
        JsonAdaptedDoctor doctor = new JsonAdaptedDoctor(BOYD);
        assertEquals(BOYD, doctor.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        INVALID_GENDER, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        null, VALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidIc_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, INVALID_NRIC, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_nullIc_throwsIllegalValueException() {
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, null, VALID_TAGS, VALID_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, doctor::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedDoctor doctor =
                new JsonAdaptedDoctor(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_REMARK,
                        VALID_GENDER, VALID_NRIC, invalidTags, VALID_APPOINTMENTS);
        assertThrows(IllegalValueException.class, doctor::toModelType);
    }
}
