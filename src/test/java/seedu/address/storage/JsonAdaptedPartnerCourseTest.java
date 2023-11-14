package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPartnerCourse.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_PARTNER_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.TYPICAL_UNIVERSITY_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.partnercourse.PartnerUnit;

public class JsonAdaptedPartnerCourseTest {

    @Test
    public void toModelType_validPartnerCourseDetails_returnsPartnerCourse() throws Exception {
        JsonAdaptedPartnerCourse partnerCourse = new JsonAdaptedPartnerCourse(COMP1000);
        assertEquals(COMP1000, partnerCourse.toModelType());
    }

    @Test
    public void toModelType_invalidPartnerCode_throwsIllegalValueException() {
        JsonAdaptedPartnerCourse partnerCourse =
            new JsonAdaptedPartnerCourse(
                TYPICAL_UNIVERSITY_NAME,
                INVALID_PARTNER_COURSE_CODE,
                TYPICAL_PARTNER_COURSE_NAME,
                TYPICAL_PARTNER_COURSE_UNIT,
                TYPICAL_PARTNER_COURSE_DESCRIPTION);
        String expectedMessage = ConstraintMessage.PARTNERCOURSE_CODE.toString();
        assertThrows(IllegalValueException.class, expectedMessage, partnerCourse::toModelType);
    }

    @Test
    public void toModelType_nullPartnerCode_throwsIllegalValueException() {
        JsonAdaptedPartnerCourse partnerCourse =
            new JsonAdaptedPartnerCourse(
                TYPICAL_UNIVERSITY_NAME,
                null,
                TYPICAL_PARTNER_COURSE_NAME,
                TYPICAL_PARTNER_COURSE_UNIT,
                TYPICAL_PARTNER_COURSE_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PartnerCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, partnerCourse::toModelType);
    }

    @Test
    public void toModelType_invalidPartnerName_throwsIllegalValueException() {
        JsonAdaptedPartnerCourse partnerCourse =
            new JsonAdaptedPartnerCourse(
                TYPICAL_UNIVERSITY_NAME,
                TYPICAL_PARTNER_COURSE_CODE,
                INVALID_PARTNER_COURSE_NAME,
                TYPICAL_PARTNER_COURSE_UNIT,
                TYPICAL_PARTNER_COURSE_DESCRIPTION);
        String expectedMessage = ConstraintMessage.PARTNERCOURSE_NAME.toString();
        assertThrows(IllegalValueException.class, expectedMessage, partnerCourse::toModelType);
    }

    @Test
    public void toModelType_nullPartnerName_throwsIllegalValueException() {
        JsonAdaptedPartnerCourse partnerCourse = new JsonAdaptedPartnerCourse(
            TYPICAL_UNIVERSITY_NAME,
            TYPICAL_PARTNER_COURSE_CODE,
            null,
            TYPICAL_PARTNER_COURSE_UNIT,
            TYPICAL_PARTNER_COURSE_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PartnerName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, partnerCourse::toModelType);
    }

    @Test
    public void toModelType_nullPartnerUnit_throwsIllegalValueException() {
        JsonAdaptedPartnerCourse partnerCourse = new JsonAdaptedPartnerCourse(
            TYPICAL_UNIVERSITY_NAME,
            TYPICAL_PARTNER_COURSE_CODE,
            TYPICAL_PARTNER_COURSE_NAME,
            null,
            TYPICAL_PARTNER_COURSE_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PartnerUnit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, partnerCourse::toModelType);
    }

    @Test
    public void toModelType_negativePartnerUnit_throwsIllegalValueException() {
        JsonAdaptedPartnerCourse partnerCourse = new JsonAdaptedPartnerCourse(
            TYPICAL_UNIVERSITY_NAME,
            TYPICAL_PARTNER_COURSE_CODE,
            TYPICAL_PARTNER_COURSE_NAME,
            INVALID_PARTNER_COURSE_UNIT,
            TYPICAL_PARTNER_COURSE_DESCRIPTION);
        String expectedMessage = ConstraintMessage.PARTNERCOURSE_UNIT.toString();
        assertThrows(IllegalValueException.class, expectedMessage, partnerCourse::toModelType);
    }
}
