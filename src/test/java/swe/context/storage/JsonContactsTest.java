package swe.context.storage;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import swe.context.commons.exceptions.IllegalValueException;
import swe.context.model.Contacts;
import swe.context.model.ReadOnlyContacts;
import swe.context.testutil.TestData;

public class JsonContactsTest {
    @Test
    public void toModelType_typicalContacts_equals() throws Exception {
        ReadOnlyContacts expectedContacts = TestData.Valid.Contact.getTypicalContacts();

        JsonContacts jsonContacts = new JsonContacts(expectedContacts);
        ReadOnlyContacts actualContacts = jsonContacts.toModelType();

        assertEquals(expectedContacts, actualContacts);
    }

    @Test
    public void toModelType_emptyContactsList_success() throws Exception {
        ReadOnlyContacts expectedContacts = new Contacts();

        JsonContacts jsonContacts = new JsonContacts(expectedContacts);
        ReadOnlyContacts actualContacts = jsonContacts.toModelType();

        assertEquals(expectedContacts, actualContacts);
    }

    @Test
    public void toModelType_invalidContactsData_throwsIllegalValueException() {
        JsonContacts jsonContacts = new JsonContacts(
            Arrays.asList(new JsonContact(
                "Invalid Name", "123456", null, "Note", null, null))
        );

        assertThrows(IllegalValueException.class, jsonContacts::toModelType);
    }

    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() {
        JsonContact duplicateContact = new JsonContact(
            "Alice", "123456", "alice@example.com", "Note", null, null
        );
        JsonContacts jsonContacts = new JsonContacts(
            Arrays.asList(duplicateContact, duplicateContact)
        );

        assertThrows(IllegalValueException.class, jsonContacts::toModelType);
    }
}
