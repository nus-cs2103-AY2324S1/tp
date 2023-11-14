package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ccacommander.storage.JsonAdaptedEnrolment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalEnrolments.ALICE_AURORA;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.exceptions.IllegalValueException;
import seedu.ccacommander.model.enrolment.Hours;
import seedu.ccacommander.model.enrolment.Remark;
import seedu.ccacommander.model.shared.Name;

public class JsonAdaptedEnrolmentTest {
    private static final String MEMBER_NAME = "memberName";
    private static final String EVENT_NAME = "eventName";
    private static final String INVALID_MEMBER_NAME = "@lice";
    private static final String INVALID_EVENT_NAME = "@urora";
    private static final String INVALID_HOURS = "-1";
    private static final String INVALID_REMARK = " ";

    private static final String VALID_MEMBER_NAME = ALICE_AURORA.getMemberName().toString();
    private static final String VALID_EVENT_NAME = ALICE_AURORA.getEventName().toString();
    private static final String VALID_HOURS = ALICE_AURORA.getHours().value.toString();
    private static final String VALID_REMARK = ALICE_AURORA.getRemark().toString();
    @Test
    public void toModelType_validEnrolmentDetails_returnsEnrolment() throws Exception {
        JsonAdaptedEnrolment enrolment = new JsonAdaptedEnrolment(ALICE_AURORA);
        assertEquals(ALICE_AURORA, enrolment.toModelType());
    }

    @Test
    public void toModelType_invalidMemberName_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(INVALID_MEMBER_NAME, VALID_EVENT_NAME, VALID_HOURS, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }

    @Test
    public void toModelType_nullMemberName_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(null, VALID_EVENT_NAME, VALID_HOURS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MEMBER_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }

    @Test
    public void toModelType_invalidEventName_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(VALID_MEMBER_NAME, INVALID_EVENT_NAME, VALID_HOURS, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }

    @Test
    public void toModelType_nullEventName_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(VALID_MEMBER_NAME, null, VALID_HOURS, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EVENT_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }

    @Test
    public void toModelType_invalidHours_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(VALID_MEMBER_NAME, VALID_EVENT_NAME, INVALID_HOURS, VALID_REMARK);
        String expectedMessage = Hours.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }

    @Test
    public void toModelType_nullHours_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(VALID_MEMBER_NAME, VALID_EVENT_NAME, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hours.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(VALID_MEMBER_NAME, VALID_EVENT_NAME, VALID_HOURS, INVALID_REMARK);
        String expectedMessage = Remark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedEnrolment enrolment =
                new JsonAdaptedEnrolment(VALID_MEMBER_NAME, VALID_EVENT_NAME, VALID_HOURS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, enrolment::toModelType);
    }


}
