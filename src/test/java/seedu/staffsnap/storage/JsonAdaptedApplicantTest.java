package seedu.staffsnap.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.commons.exceptions.IllegalValueException;
import seedu.staffsnap.model.applicant.Department;
import seedu.staffsnap.model.applicant.JobTitle;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;

public class JsonAdaptedApplicantTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_JOB_TITLE = " ";
    private static final String INVALID_DEPARTMENT = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_DEPARTMENT = BENSON.getDepartment().toString();
    private static final String VALID_JOB_TITLE = BENSON.getJobTitle().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(BENSON);
        assertEquals(BENSON, applicant.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(INVALID_NAME, VALID_PHONE, VALID_DEPARTMENT, VALID_JOB_TITLE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(
                null, VALID_PHONE, VALID_DEPARTMENT, VALID_JOB_TITLE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, INVALID_PHONE, VALID_DEPARTMENT, VALID_JOB_TITLE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(
                VALID_NAME, null, VALID_DEPARTMENT, VALID_JOB_TITLE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidDepartment_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, INVALID_DEPARTMENT, VALID_JOB_TITLE, VALID_TAGS);
        String expectedMessage = Department.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullDepartment_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(
                VALID_NAME, VALID_PHONE, null, VALID_JOB_TITLE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Department.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidJobTitle_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_DEPARTMENT, INVALID_JOB_TITLE, VALID_TAGS);
        String expectedMessage = JobTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullJobTitle_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE,
                VALID_DEPARTMENT, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_DEPARTMENT, VALID_JOB_TITLE, invalidTags);
        assertThrows(IllegalValueException.class, applicant::toModelType);
    }

}
