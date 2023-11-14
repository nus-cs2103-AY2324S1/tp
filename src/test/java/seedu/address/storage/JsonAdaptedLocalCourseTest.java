package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedLocalCourse.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_UNIT;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_DESCRIPTION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_UNIT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;

public class JsonAdaptedLocalCourseTest {

    @Test
    public void toModelType_validLocalCourseDetails_returnsLocalCourse() throws Exception {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(CS2030S);
        assertEquals(CS2030S, localCourse.toModelType());
    }

    @Test
    public void toModelType_invalidLocalCode_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse =
                new JsonAdaptedLocalCourse(
                    INVALID_LOCAL_COURSE_CODE,
                    TYPICAL_LOCAL_COURSE_NAME,
                    TYPICAL_LOCAL_COURSE_UNIT,
                    TYPICAL_LOCAL_COURSE_DESCRIPTION);
        String expectedMessage = ConstraintMessage.LOCALCOURSE_CODE.toString();
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_nullLocalCode_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(
            null,
            TYPICAL_LOCAL_COURSE_NAME,
            TYPICAL_LOCAL_COURSE_UNIT,
            TYPICAL_LOCAL_COURSE_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_invalidLocalName_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse =
                new JsonAdaptedLocalCourse(
                    TYPICAL_LOCAL_COURSE_CODE,
                    INVALID_LOCAL_COURSE_NAME,
                    TYPICAL_LOCAL_COURSE_UNIT,
                    TYPICAL_LOCAL_COURSE_DESCRIPTION);
        String expectedMessage = ConstraintMessage.LOCALCOURSE_NAME.toString();
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_nullLocalName_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(
            TYPICAL_LOCAL_COURSE_CODE,
            null,
            TYPICAL_LOCAL_COURSE_UNIT,
            TYPICAL_LOCAL_COURSE_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_nullLocalUnit_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(
            TYPICAL_LOCAL_COURSE_CODE,
            TYPICAL_LOCAL_COURSE_NAME,
            null,
            TYPICAL_LOCAL_COURSE_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalUnit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }

    @Test
    public void toModelType_negativeLocalUnit_throwsIllegalValueException() {
        JsonAdaptedLocalCourse localCourse = new JsonAdaptedLocalCourse(
            TYPICAL_LOCAL_COURSE_CODE,
            TYPICAL_LOCAL_COURSE_NAME,
            INVALID_LOCAL_COURSE_UNIT,
            TYPICAL_LOCAL_COURSE_DESCRIPTION);
        String expectedMessage = ConstraintMessage.LOCALCOURSE_UNIT.toString();
        assertThrows(IllegalValueException.class, expectedMessage, localCourse::toModelType);
    }
}
