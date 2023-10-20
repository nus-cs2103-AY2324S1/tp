package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.Valid.Contact.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Note;
import seedu.address.model.contact.Phone;
import seedu.address.testutil.TestData;

public class JsonContactTest {

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final List<JsonTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonContact contact = new JsonContact(BENSON);
        assertEquals(BENSON, contact.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonContact contact =
                new JsonContact(TestData.Invalid.NAME, VALID_PHONE, VALID_EMAIL, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Messages.MESSAGE_NAME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(null, VALID_PHONE, VALID_EMAIL, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(Messages.MESSAGE_FIELD_MISSING, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonContact contact =
                new JsonContact(VALID_NAME, TestData.Invalid.PHONE, VALID_EMAIL, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Messages.MESSAGE_PHONE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(VALID_NAME, null, VALID_EMAIL, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(Messages.MESSAGE_FIELD_MISSING, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonContact contact =
                new JsonContact(VALID_NAME, VALID_PHONE, TestData.Invalid.EMAIL, VALID_NOTE, VALID_TAGS);
        String expectedMessage = Messages.EMAIL_INVALID;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(VALID_NAME, VALID_PHONE, null, VALID_NOTE, VALID_TAGS);
        String expectedMessage = String.format(Messages.MESSAGE_FIELD_MISSING, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(Messages.MESSAGE_FIELD_MISSING, Note.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonTag(TestData.Invalid.Tag.UNDERSCORE_DASH));
        JsonContact contact =
                new JsonContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NOTE, invalidTags);
        assertThrows(IllegalValueException.class, contact::toModelType);
    }

}
