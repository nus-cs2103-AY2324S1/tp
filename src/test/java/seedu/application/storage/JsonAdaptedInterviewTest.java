package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.storage.JsonAdaptedInterview.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalInterviews.CHEF_INTERVIEW;

import org.junit.jupiter.api.Test;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;
class JsonAdaptedInterviewTest {

    private static final String VALID_INTERVIEW_TYPE = "case";
    private static final String VALID_INTERVIEW_DATETIME = "Jan 30 2040 1200";
    private static final String VALID_INTERVIEW_ADDRESS = "School";

    private static final String INVALID_INTERVIEW_TYPE = "cinema";
    private static final String INVALID_INTERVIEW_DATETIME = "Jan 30 2001";
    private static final String INVALID_INTERVIEW_ADDRESS = " ";

    @Test
    public void toModelType_validInterviewDetails_returnsInterview() throws Exception {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(CHEF_INTERVIEW);
        assertEquals(CHEF_INTERVIEW, interview.toModelType());
    }
    @Test
    public void toModelType_invalidInterviewType_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(INVALID_INTERVIEW_TYPE, VALID_INTERVIEW_DATETIME, VALID_INTERVIEW_ADDRESS);
        String expectedMessage = InterviewType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullInterviewType_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(null, VALID_INTERVIEW_DATETIME, VALID_INTERVIEW_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidInterviewDateTime_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_INTERVIEW_TYPE, INVALID_INTERVIEW_DATETIME, VALID_INTERVIEW_ADDRESS);
        String expectedMessage = InterviewDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullInterviewDateTime_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_INTERVIEW_TYPE, null, VALID_INTERVIEW_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidInterviewAddress_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_INTERVIEW_TYPE, VALID_INTERVIEW_DATETIME, INVALID_INTERVIEW_ADDRESS);
        String expectedMessage = InterviewAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullInterviewAddress_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_INTERVIEW_TYPE, VALID_INTERVIEW_DATETIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

}
