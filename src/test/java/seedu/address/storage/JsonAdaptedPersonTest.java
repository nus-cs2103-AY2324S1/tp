package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final Integer INVALID_BALANCE = 1000009;

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final Optional<MonthDay> VALID_BIRTHDAY = BENSON.getBirthday().map(b -> b.birthday);
    private static final Optional<String> VALID_LINKEDIN = BENSON.getLinkedin().map(linkedin -> linkedin.value);
    private static final Optional<String> VALID_SECONDARY_EMAIL = BENSON.getSecondaryEmail().map(email -> email.value);
    private static final Optional<String> VALID_TELEGRAM = BENSON.getTelegram().map(telegram -> telegram.value);
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final Integer VALID_BALANCE = BENSON.getBalance().value;
    private static final Optional<Integer> VALID_ID = BENSON.getId();
    private static final JsonAdaptedAvatar VALID_AVATAR = new JsonAdaptedAvatar(BENSON.getAvatar());


    private static final List<JsonAdaptedNote> VALID_NOTES = BENSON.getNotes().stream()
            .map(JsonAdaptedNote::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR
                );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                VALID_NOTES, VALID_BALANCE, VALID_AVATAR
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR
                );
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR
                );
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR
                );
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, invalidTags, VALID_ID,
                        VALID_NOTES, VALID_BALANCE, VALID_AVATAR);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidBalance_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, INVALID_BALANCE, VALID_AVATAR
                );
        String expectedMessage = Balance.MESSAGE_BALANCE_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBalance_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_LINKEDIN, VALID_SECONDARY_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_ID,
                        VALID_NOTES, null, VALID_AVATAR
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Balance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
