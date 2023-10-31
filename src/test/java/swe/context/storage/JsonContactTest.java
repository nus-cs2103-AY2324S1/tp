package swe.context.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static swe.context.testutil.Assert.assertThrows;
import static swe.context.testutil.TestData.Valid.Contact.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import swe.context.commons.exceptions.IllegalValueException;
import swe.context.logic.Messages;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.testutil.TestData;

public class JsonContactTest {

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final List<JsonTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAlternateContact> VALID_ALTERNATECONTACTS = BENSON.getAlternates().stream()
            .map(JsonAlternateContact::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonContact contact = new JsonContact(BENSON);
        assertEquals(BENSON, contact.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonContact contact =
                new JsonContact(
                        TestData.Invalid.NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_NOTE,
                        VALID_TAGS,
                        VALID_ALTERNATECONTACTS
                );
        String expectedMessage = Messages.NAME_INVALID;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(
                null,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_NOTE,
                VALID_TAGS,
                VALID_ALTERNATECONTACTS
        );
        String expectedMessage = Messages.fieldMissing(Name.class.getSimpleName());;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonContact contact =
                new JsonContact(
                        VALID_NAME,
                        TestData.Invalid.PHONE,
                        VALID_EMAIL,
                        VALID_NOTE,
                        VALID_TAGS,
                        VALID_ALTERNATECONTACTS
                );
        String expectedMessage = Messages.PHONE_INVALID;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(
                VALID_NAME,
                null,
                VALID_EMAIL,
                VALID_NOTE,
                VALID_TAGS,
                VALID_ALTERNATECONTACTS
        );
        String expectedMessage = Messages.fieldMissing(Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonContact contact =
                new JsonContact(
                        VALID_NAME,
                        VALID_PHONE,
                        TestData.Invalid.EMAIL,
                        VALID_NOTE,
                        VALID_TAGS,
                        VALID_ALTERNATECONTACTS
                );
        String expectedMessage = Messages.EMAIL_INVALID;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(
                VALID_NAME,
                VALID_PHONE,
                null,
                VALID_NOTE,
                VALID_TAGS,
                VALID_ALTERNATECONTACTS
        );
        String expectedMessage = Messages.fieldMissing(Email.class.getSimpleName());;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonContact contact = new JsonContact(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                null,
                VALID_TAGS,
                VALID_ALTERNATECONTACTS
        );
        String expectedMessage = Messages.fieldMissing(Note.class.getSimpleName());;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonTag(TestData.Invalid.Tag.UNDERSCORE_DASH));
        JsonContact contact = new JsonContact(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_NOTE,
                invalidTags,
                VALID_ALTERNATECONTACTS
        );
        assertThrows(IllegalValueException.class, contact::toModelType);
    }

    @Test
    public void toModelType_invalidAlternateContacts_throwsIllegalValueException() {
        List<JsonAlternateContact> invalidAlternateContacts = new ArrayList<>(VALID_ALTERNATECONTACTS);
        invalidAlternateContacts.add(new JsonAlternateContact(TestData.Invalid.AlternateContact.WHITESPACE));
        JsonContact contact = new JsonContact(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_NOTE,
                VALID_TAGS,
                invalidAlternateContacts
        );
        assertThrows(IllegalValueException.class, contact::toModelType);
    }

}
