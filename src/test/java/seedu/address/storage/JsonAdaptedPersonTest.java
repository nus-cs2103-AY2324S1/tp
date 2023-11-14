package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.testutil.PersonBuilder;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TELEGRAM_HANDLE = " +2319*";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_ASSIGNMENT = "#8982)#(@";
    private static final String INVALID_GROUP = "#898)#(@";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TELEGRAM_HANDLE = BENSON.getTelegramHandle().toString();
    private static final String VALID_ATTENDANCE = BENSON.getAttendance().atdToString();
    private static final String VALID_PARTICIPATION = BENSON.getAttendance().ppToString();
    private static final String VALID_GROUP = BENSON.getGroup().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAssignment> VALID_ASSIGNMENTS = BENSON.getAssignments().stream()
            .map(JsonAdaptedAssignment::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedComment> VALID_COMMENTS = BENSON.getComments().stream()
            .map(JsonAdaptedComment::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS,
                        VALID_COMMENTS, VALID_ASSIGNMENTS, VALID_GROUP);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS, VALID_COMMENTS,
                VALID_ASSIGNMENTS, VALID_GROUP);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS,
                        VALID_COMMENTS, VALID_ASSIGNMENTS, VALID_GROUP);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsNullPointerException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS, VALID_COMMENTS,
                VALID_ASSIGNMENTS, VALID_GROUP);
        assertThrows(NullPointerException.class, person::toModelType);
    }

    @Test
    public void toModelType_emptyPhone_success() throws Exception {
        Person expectedPerson = new PersonBuilder().withName("Nick Jones")
                .withTelegram("nickJones")
                .withEmail("nickJones@gmail.com").withPhone("")
                .withTags("owesMoney", "friends")
                .withAttendance("U,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0")
                .build();
        JsonAdaptedPerson person = new JsonAdaptedPerson(expectedPerson);
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS,
                        VALID_COMMENTS, VALID_ASSIGNMENTS, VALID_GROUP);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsNullPointerException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS, VALID_COMMENTS,
                VALID_ASSIGNMENTS, VALID_GROUP);
        assertThrows(NullPointerException.class, person::toModelType);
    }

    @Test
    public void toModelType_emptyEmail_success() throws Exception {
        Person expectedPerson = new PersonBuilder().withName("Nick Jones")
                .withTelegram("nickJones")
                .withEmail("").withPhone("98765432")
                .withTags("owesMoney", "friends")
                .withAttendance("U,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0")
                .build();
        JsonAdaptedPerson person = new JsonAdaptedPerson(expectedPerson);
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS,
                        VALID_COMMENTS, VALID_ASSIGNMENTS, VALID_GROUP);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS, VALID_COMMENTS,
                VALID_ASSIGNMENTS, VALID_GROUP);
        assertThrows(NullPointerException.class, person::toModelType);
    }

    @Test
    public void toModelType_emptyTelegramHandle_success() throws Exception {
        Person expectedPerson = new PersonBuilder().withName("Nick Jones")
                .withTelegram("")
                .withEmail("tele@gmail.com").withPhone("98765432")
                .withTags("owesMoney", "friends")
                .withAttendance("U,U,U,U,U,U,U,U,U,U,U,U", "0,0,0,0,0,0,0,0,0,0,0,0")
                .build();
        JsonAdaptedPerson person = new JsonAdaptedPerson(expectedPerson);
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, invalidTags,
                        VALID_COMMENTS, VALID_ASSIGNMENTS, VALID_GROUP);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, null, VALID_PARTICIPATION, VALID_TAGS,
                        VALID_COMMENTS, VALID_ASSIGNMENTS, INVALID_GROUP);
        String expectedMessage = Attendance.TUTORIAL_ERROR_MSG;
        System.out.println(expectedMessage);
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullParticipation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, null, VALID_TAGS, VALID_COMMENTS,
                        VALID_ASSIGNMENTS, INVALID_GROUP);
        String expectedMessage = Attendance.PARTICIPATION_ERROR_MSG;
        System.out.println(expectedMessage);
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGroup_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_TAGS,
                        VALID_COMMENTS, VALID_ASSIGNMENTS, INVALID_GROUP);
        String expectedMessage = Group.MESSAGE_CONSTRAINTS;
        System.out.println(expectedMessage);
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
