package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ccacommander.storage.JsonAdaptedMember.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalMembers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.exceptions.IllegalValueException;
import seedu.ccacommander.model.member.Address;
import seedu.ccacommander.model.member.Email;
import seedu.ccacommander.model.member.Gender;
import seedu.ccacommander.model.member.Phone;
import seedu.ccacommander.model.shared.Name;

public class JsonAdaptedMemberTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GENDER = "Helicopter";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMemberDetails_returnsMember() throws Exception {
        JsonAdaptedMember member = new JsonAdaptedMember(BENSON);
        assertEquals(BENSON, member.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(INVALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(null, VALID_GENDER, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, INVALID_GENDER, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_GENDER, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_GENDER, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_GENDER, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_GENDER, VALID_PHONE, null,
                VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL,
                null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_GENDER, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, member::toModelType);
    }

}
