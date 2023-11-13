package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMember.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.BETTY_MEMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Telegram;

public class JsonAdaptedMemberTest {
    private static final String INVALID_NAME = "R#chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TELEGRAM = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BETTY_MEMBER.getName().toString();
    private static final String VALID_PHONE = BETTY_MEMBER.getPhone().toString();
    private static final String VALID_EMAIL = BETTY_MEMBER.getEmail().toString();
    private static final String VALID_TELEGRAM = BETTY_MEMBER.getTelegram().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BETTY_MEMBER.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTask> VALID_TODO = BETTY_MEMBER.getTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMemberDetails_returnsMember() throws Exception {
        JsonAdaptedMember member = new JsonAdaptedMember(BETTY_MEMBER);
        assertEquals(BETTY_MEMBER, member.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_TODO);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(null, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM,
                VALID_TAGS, VALID_TODO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_TODO);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, null, VALID_EMAIL, VALID_TELEGRAM,
                VALID_TAGS, VALID_TODO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TELEGRAM, VALID_TAGS, VALID_TODO);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, null, VALID_TELEGRAM,
                VALID_TAGS, VALID_TODO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_TELEGRAM, VALID_TAGS, VALID_TODO);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_throwsIllegalValueException() {
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_TAGS, VALID_TODO);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Telegram.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM, invalidTags, VALID_TODO);
        assertThrows(IllegalValueException.class, member::toModelType);
    }

}
