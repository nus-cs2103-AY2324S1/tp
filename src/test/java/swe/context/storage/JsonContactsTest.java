package swe.context.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}
