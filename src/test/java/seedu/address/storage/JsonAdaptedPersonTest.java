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
import seedu.address.model.person.LicencePlate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.policy.Company;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "Rachel!";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_NRIC = "1234567A";
    private static final String INVALID_LICENCE_PLATE = "ABC123D";
    private static final String INVALID_COMPANY = "COMPANY/";
    private static final String INVALID_POLICY_NUMBER = "#AIA";
    private static final String INVALID_POLICY_DATE = "19-19-2000";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());
    private static final String VALID_NRIC = "567A";
    private static final String VALID_LICENCE_PLATE = "SBC123D";
    private static final String VALID_COMPANY = "NTUC";
    private static final String VALID_POLICY_NUMBER = "AIA1234";
    private static final String VALID_POLICY_DATE = "01-01-2023";
    private static final String VALID_REMARK = BENSON.getRemark().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(
                INVALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
            );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            null,
            VALID_PHONE,
            VALID_EMAIL,
            VALID_ADDRESS,
            VALID_TAGS,
            VALID_NRIC,
            VALID_LICENCE_PLATE,
            VALID_REMARK,
            VALID_COMPANY,
            VALID_POLICY_NUMBER,
            VALID_POLICY_DATE,
            VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(
                VALID_NAME,
                INVALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
            );
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_NAME,
            null,
            VALID_EMAIL,
            VALID_ADDRESS,
            VALID_TAGS,
            VALID_NRIC,
            VALID_LICENCE_PLATE,
            VALID_REMARK,
            VALID_COMPANY,
            VALID_POLICY_NUMBER,
            VALID_POLICY_DATE,
            VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                INVALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
            );
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_NAME,
            VALID_PHONE,
            null,
            VALID_ADDRESS,
            VALID_TAGS,
            VALID_NRIC,
            VALID_LICENCE_PLATE,
            VALID_REMARK,
            VALID_COMPANY,
            VALID_POLICY_NUMBER,
            VALID_POLICY_DATE,
            VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                INVALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
            );
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_NAME,
            VALID_PHONE,
            VALID_EMAIL,
            null,
            VALID_TAGS,
            VALID_NRIC,
            VALID_LICENCE_PLATE,
            VALID_REMARK,
            VALID_COMPANY,
            VALID_POLICY_NUMBER,
            VALID_POLICY_DATE,
            VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                invalidTags,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
            );
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_TAGS,
                        INVALID_NRIC,
                        VALID_LICENCE_PLATE,
                        VALID_REMARK,
                        VALID_COMPANY,
                        VALID_POLICY_NUMBER,
                        VALID_POLICY_DATE,
                        VALID_POLICY_DATE
                );
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                null,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidLicencePlate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_TAGS,
                        VALID_NRIC,
                        INVALID_LICENCE_PLATE,
                        VALID_REMARK,
                        VALID_COMPANY,
                        VALID_POLICY_NUMBER,
                        VALID_POLICY_DATE,
                        VALID_POLICY_DATE
                );
        String expectedMessage = LicencePlate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLicencePlate_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                null,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LicencePlate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                null,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_TAGS,
                        VALID_NRIC,
                        VALID_LICENCE_PLATE,
                        VALID_REMARK,
                        INVALID_COMPANY,
                        VALID_POLICY_NUMBER,
                        VALID_POLICY_DATE,
                        VALID_POLICY_DATE
                );
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                null,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPolicyNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_TAGS,
                        VALID_NRIC,
                        VALID_LICENCE_PLATE,
                        VALID_REMARK,
                        VALID_COMPANY,
                        INVALID_POLICY_NUMBER,
                        VALID_POLICY_DATE,
                        VALID_POLICY_DATE
                );
        String expectedMessage = PolicyNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPolicyNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                null,
                VALID_POLICY_DATE,
                VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PolicyNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPolicyIssueDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_TAGS,
                        VALID_NRIC,
                        VALID_LICENCE_PLATE,
                        VALID_REMARK,
                        VALID_COMPANY,
                        VALID_POLICY_NUMBER,
                        INVALID_POLICY_DATE,
                        VALID_POLICY_DATE
                );
        String expectedMessage = PolicyDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPolicyIssueDate_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                null,
                VALID_POLICY_DATE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PolicyDate.class.getSimpleName() + " (Issue Date)");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPolicyExpiryDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_TAGS,
                        VALID_NRIC,
                        VALID_LICENCE_PLATE,
                        VALID_REMARK,
                        VALID_COMPANY,
                        VALID_POLICY_NUMBER,
                        VALID_POLICY_DATE,
                        INVALID_POLICY_DATE
                );
        String expectedMessage = PolicyDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPolicyExpiryDate_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_ADDRESS,
                VALID_TAGS,
                VALID_NRIC,
                VALID_LICENCE_PLATE,
                VALID_REMARK,
                VALID_COMPANY,
                VALID_POLICY_NUMBER,
                VALID_POLICY_DATE,
                null
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PolicyDate.class.getSimpleName() + " (Expiry Date)");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
