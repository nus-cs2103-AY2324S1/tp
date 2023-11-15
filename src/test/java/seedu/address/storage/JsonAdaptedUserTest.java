package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedUser.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalUsers.JOHN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.storage.timetable.JsonAdaptedDatedEvent;
import seedu.address.storage.timetable.JsonAdaptedSchedule;

public class JsonAdaptedUserTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_BIRTHDAY = "2020-02-30";
    private static final String INVALID_TAG = "#friend";


    private static final String VALID_NAME = JOHN.getName().toString();
    private static final String VALID_PHONE = JOHN.getPhone().toString();
    private static final String VALID_EMAIL = JOHN.getEmail().toString();
    private static final String VALID_ADDRESS = JOHN.getAddress().toString();
    private static final String VALID_BIRTHDAY = JOHN.getBirthday().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = JOHN.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedDatedEvent> VALID_DATEDEVENTS = JOHN.getDatedEvents().stream()
            .map(JsonAdaptedDatedEvent::new)
            .collect(Collectors.toList());

    private static final JsonAdaptedSchedule VALID_SCHEDULE = new JsonAdaptedSchedule(JOHN.getSchedule());

    @Test
    public void toModelType_validUserDetails_returnsUser() throws Exception {
        JsonAdaptedUser user = new JsonAdaptedUser(JOHN);
        assertEquals(JOHN, user.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(null, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, null, VALID_EMAIL,
                        VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS, VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS,
                        VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_BIRTHDAY,
                VALID_TAGS, VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_BIRTHDAY, VALID_TAGS,
                        VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_BIRTHDAY, VALID_TAGS, VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidBirthday_throwsIllegalValueException() {
        JsonAdaptedUser user =
                new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_BIRTHDAY, VALID_TAGS,
                        VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = "Birthday should be in the format of YYYY-MM-DD "
                + "and should be a valid date.";
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullBirthday_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null ,
                VALID_TAGS, VALID_SCHEDULE, VALID_DATEDEVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Birthday");
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_nullSchedule_throwsIllegalValueException() {
        JsonAdaptedUser user = new JsonAdaptedUser(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_TAGS, null, VALID_DATEDEVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Schedule");
        assertThrows(IllegalValueException.class, expectedMessage, user::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_BIRTHDAY,
                        VALID_SCHEDULE, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
