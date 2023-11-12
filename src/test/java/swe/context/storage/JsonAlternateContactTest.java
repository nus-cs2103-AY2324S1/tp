package swe.context.storage;

import org.junit.jupiter.api.Test;

import swe.context.commons.exceptions.IllegalValueException;
import swe.context.model.alternate.AlternateContact;
import swe.context.testutil.TestData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonAlternateContactTest {

    // Test successful conversion from AlternateContact to JsonAlternateContact
    @Test
    public void jsonAlternateContact_conversionFromModel_success() {
        AlternateContact original = new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC);
        JsonAlternateContact jsonAlternateContact = new JsonAlternateContact(original);
        assertEquals(original.toString(), jsonAlternateContact.getValue());
    }

    // Test successful conversion from JsonAlternateContact to AlternateContact
    @Test
    public void toModelType_validAlternateContact_success() throws IllegalValueException {
        JsonAlternateContact jsonAlternateContact =
                new JsonAlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC);
        AlternateContact alternateContact = jsonAlternateContact.toModelType();
        assertEquals(TestData.Valid.AlternateContact.ALPHANUMERIC, alternateContact.toString());
    }

    // Test conversion failure when JsonAlternateContact contains invalid data
    @Test
    public void toModelType_invalidAlternateContact_throwsIllegalValueException() {
        JsonAlternateContact jsonAlternateContact = new JsonAlternateContact(TestData.Invalid.ALTERNATE_CONTACT);
        assertThrows(IllegalValueException.class, jsonAlternateContact::toModelType);
    }
}
