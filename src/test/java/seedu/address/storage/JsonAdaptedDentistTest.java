package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDentist.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDentists.DENTIST_BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.dentist.Specialization;
import seedu.address.model.person.dentist.Yoe;

public class JsonAdaptedDentistTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = " "; // Cannot start with whitespace FROM JsonAdaptedDentist
    private static final String INVALID_SPECIALIZATION = "Orthodontics & Endodontics";
    private static final String INVALID_YOE = "651234";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = DENTIST_BENSON.getName().toString();
    private static final String VALID_PHONE = DENTIST_BENSON.getPhone().toString();
    private static final String VALID_EMAIL = DENTIST_BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = DENTIST_BENSON.getAddress().toString();
    private static final String VALID_SPECIALIZATION = DENTIST_BENSON.getSpecialization().toString();
    private static final String VALID_YOE = DENTIST_BENSON.getYoe().toString();

    private static final String DEFAULT_DENTIST_ID = "1";
    private static final List<JsonAdaptedTag> VALID_TAGS = DENTIST_BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validDentistDetails_returnsDentist() throws Exception {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(DENTIST_BENSON);
        assertEquals(DENTIST_BENSON, dentist.toModelType());
    }

    @Test
    public void toModelType_nullDentistId_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, null, VALID_TAGS);
        String expectedMessage = "Dentist ID value does not exist!";
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_invalidSpecialization_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                INVALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = Specialization.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_nullSpecialization_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Specialization.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_invalidYearsOfExperience_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, INVALID_YOE, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = Yoe.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_nullYearsOfExperience_throwsIllegalValueException() {
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, null, DEFAULT_DENTIST_ID, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Yoe.FULL_CLASS_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, dentist::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedDentist dentist = new JsonAdaptedDentist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_SPECIALIZATION, VALID_YOE, DEFAULT_DENTIST_ID, invalidTags);
        assertThrows(IllegalValueException.class, dentist::toModelType);
    }

}
