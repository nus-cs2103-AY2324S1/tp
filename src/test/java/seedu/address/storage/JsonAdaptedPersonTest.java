package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.model.person.interaction.Interaction.Outcome.MESSAGE_CONSTRAINTS;
import static seedu.address.storage.JsonAdaptedInteraction.INVALID_DATE_FIELD_MESSAGE;
import static seedu.address.storage.JsonAdaptedPerson.PERSON_MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Profession;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.lead.Lead;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+65123498249780459780457905427952";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_LEAD = "medium";
    private static final String INVALID_TELEGRAM = "~rachel";
    private static final String INVALID_PROFESSION = "d@ctor";
    private static final String INVALID_INCOME = "lots of money";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_LEAD = BENSON.getLead().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();
    private static final String VALID_PROFESSION = BENSON.getProfession().toString();
    private static final String VALID_INCOME = BENSON.getIncome().toString();
    private static final String VALID_DETAILS = BENSON.getDetails().toString();
    private static final List<JsonAdaptedInteraction> VALID_INTERACTIONS = BENSON.getInteractions().stream()
            .map(JsonAdaptedInteraction::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = String.format(PERSON_MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidLead_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, INVALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = Lead.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLead_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, null,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        Person modelPerson = person.toModelType();
        assertEquals(BENSON, modelPerson);
        assertNull(modelPerson.getLead());
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        INVALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        null, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        Person modelPerson = person.toModelType();
        assertEquals(BENSON, modelPerson);
        assertNull(modelPerson.getTelegram());
    }

    @Test
    public void toModelType_invalidProfession_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, INVALID_PROFESSION, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = Profession.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullProfession_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, null, VALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        Person modelPerson = person.toModelType();
        assertEquals(BENSON, modelPerson);
        assertNull(modelPerson.getProfession());
    }

    @Test
    public void toModelType_invalidIncome_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, INVALID_INCOME, VALID_DETAILS, VALID_INTERACTIONS);
        String expectedMessage = Income.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullIncome_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, null, VALID_DETAILS, VALID_INTERACTIONS);
        Person modelPerson = person.toModelType();
        assertEquals(BENSON, modelPerson);
        assertNull(modelPerson.getIncome());
    }

    @Test
    public void toModelType_nullDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, null, VALID_INTERACTIONS);
        Person modelPerson = person.toModelType();
        assertEquals(BENSON, modelPerson);
        assertNull(modelPerson.getDetails());
    }

    @Test
    public void toModelType_emptyInteractionNote_returnsPerson() throws Exception {
        List<JsonAdaptedInteraction> validInteractions = new ArrayList<>();
        validInteractions.add(new JsonAdaptedInteraction("", "INTERESTED", "27-Oct-2023"));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, validInteractions);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_emptyInteractionOutcome_throwsIllegalValueException() throws Exception {
        List<JsonAdaptedInteraction> validInteractions = new ArrayList<>(VALID_INTERACTIONS);
        validInteractions.add(new JsonAdaptedInteraction("Valid Note", "", "27-Oct-2023"));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, validInteractions);
        assertThrows(IllegalValueException.class, MESSAGE_CONSTRAINTS, person::toModelType);
    }

    @Test
    public void toModelType_invalidInteractionOutcome_throwsIllegalValueException() {
        List<JsonAdaptedInteraction> invalidInteractions = new ArrayList<>(VALID_INTERACTIONS);
        invalidInteractions.add(new JsonAdaptedInteraction("Valid Note", " ", "27-Oct-2023"));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, invalidInteractions);
        assertThrows(IllegalValueException.class, MESSAGE_CONSTRAINTS, person::toModelType);
    }

    @Test
    public void toModelType_nullInteractions_returnsPerson() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, null);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidInteractionDate_throwsIllegalValueException() {
        List<JsonAdaptedInteraction> invalidInteractions = new ArrayList<>(VALID_INTERACTIONS);
        invalidInteractions.add(new JsonAdaptedInteraction(" ", "INTERESTED", " "));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_LEAD,
                        VALID_TELEGRAM, VALID_PROFESSION, VALID_INCOME, VALID_DETAILS, invalidInteractions);
        assertThrows(IllegalValueException.class, INVALID_DATE_FIELD_MESSAGE, person::toModelType);
    }
}
