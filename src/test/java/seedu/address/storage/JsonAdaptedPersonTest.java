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
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKinName;
import seedu.address.model.person.NextOfKinPhone;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NEXT_OF_KIN_NAME = "R@chel";
    private static final String INVALID_NEXT_OF_KIN_PHONE = "+651234";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_FINANCIAL_PLAN = "#plan";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_NEXT_OF_KIN_NAME = BENSON.getNextOfKinName().toString();
    private static final String VALID_NEXT_OF_KIN_PHONE = BENSON.getNextOfKinPhone().toString();
    private static final JsonAdaptedAppointment VALID_APPOINTMENT = new JsonAdaptedAppointment(
            BENSON.getAppointment().toString());
    private static final List<JsonAdaptedFinancialPlan> VALID_FINANCIAL_PLANS = BENSON.getFinancialPlans().stream()
            .map(JsonAdaptedFinancialPlan::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                        VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                        VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                        VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                        VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidNextOfKinName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                        VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = NextOfKinName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNextOfKinName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_NEXT_OF_KIN_PHONE, VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NextOfKinName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNextOfKinPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NEXT_OF_KIN_NAME, INVALID_NEXT_OF_KIN_PHONE,
                        VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = NextOfKinPhone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNextOfKinPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_NEXT_OF_KIN_NAME, null,
                VALID_FINANCIAL_PLANS, VALID_TAGS, VALID_APPOINTMENT);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NextOfKinPhone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                        VALID_FINANCIAL_PLANS, invalidTags, VALID_APPOINTMENT);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidFinancialPlans_throwsIllegalValueException() {
        List<JsonAdaptedFinancialPlan> invalidFinancialPlans = new ArrayList<>(VALID_FINANCIAL_PLANS);
        invalidFinancialPlans.add(new JsonAdaptedFinancialPlan(INVALID_FINANCIAL_PLAN));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE,
                        invalidFinancialPlans, VALID_TAGS, VALID_APPOINTMENT);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
