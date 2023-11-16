package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.ClientRoles;
import seedu.address.model.client.Document;
import seedu.address.model.commons.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ROLE = "hiring";
    private static final String INVALID_ORGANISATION = " ";
    private static final String INVALID_DOCUMENT = " ";
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_ORGANISATION = BENSON.getOrganisation().toString();
    private static final String VALID_DOCUMENT = BENSON.getDocument().toString();
    private static final List<String> VALID_PROJECTS = new ArrayList<>(BENSON.getProjects());

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE,
                        VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_ROLE, VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE,
                        VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_ROLE, VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient person =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_ROLE,
                        VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_ROLE, VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient person =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_ROLE,
                        VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_ROLE, VALID_PROJECTS, VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidOrganisation_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE, VALID_PROJECTS,
                INVALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullOrganisation_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE, VALID_PROJECTS,
                null, VALID_DOCUMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDocument_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE, VALID_PROJECTS,
                VALID_ORGANISATION, INVALID_DOCUMENT);
        String expectedMessage = Document.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDocument_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE, VALID_PROJECTS,
                VALID_ORGANISATION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Document.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /*@Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, INVALID_ROLE, VALID_RATING);
        String expectedMessage = DeveloperRoles.NO_SUCH_DEVELOPER_ROLE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedClient person = new JsonAdaptedClient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_PROJECTS,
                VALID_ORGANISATION, VALID_DOCUMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClientRoles.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
