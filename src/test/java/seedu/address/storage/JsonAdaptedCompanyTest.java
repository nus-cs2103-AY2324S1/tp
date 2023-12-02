package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.AMAZON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Remark;
import seedu.address.model.company.Role;

public class JsonAdaptedCompanyTest {
    private static final String INVALID_COMPANY_NAME = "G@ogle";
    private static final String INVALID_PHONE = "+6512&34";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PRIORITY = "HIGHH";
    private static final String INVALID_REMARK = " ";

    private static final String VALID_COMPANY_NAME = AMAZON.getName().toString();
    private static final String VALID_PHONE = AMAZON.getPhone().toString();
    private static final String VALID_EMAIL = AMAZON.getEmail().toString();
    private static final String VALID_ROLE = AMAZON.getRole().toString();
    private static final String VALID_DEADLINE = AMAZON.getDeadline().toString();
    private static final String VALID_STATUS = AMAZON.getStatus().toString();
    private static final String VALID_RECRUITER_NAME = AMAZON.getRecruiterName().toString();
    private static final String VALID_PRIORITY = AMAZON.getPriority().toString();
    private static final String VALID_REMARK = AMAZON.getRemark().toString();

    @Test
    public void toModelType_validCompanyDetails_returnsCompany() throws Exception {
        JsonAdaptedCompany company = new JsonAdaptedCompany(AMAZON);
        assertEquals(AMAZON, company.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(INVALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE, VALID_DEADLINE,
                        VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS_INVALID_REGEX;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(null, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ROLE,
                        VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS_VALID_REGEX;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                        VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, INVALID_PRIORITY, VALID_REMARK);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS_VALID_REGEX;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, null, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ROLE,
                        VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS_VALID_REGEX;
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, null,
                VALID_ROLE, VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                null, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, null, VALID_RECRUITER_NAME, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ApplicationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }


    @Test
    public void toModelType_nullRecruiterName_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, null, VALID_PRIORITY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RecruiterName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedCompany company = new JsonAdaptedCompany(VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                VALID_DEADLINE, VALID_STATUS, VALID_RECRUITER_NAME, VALID_PRIORITY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

}
