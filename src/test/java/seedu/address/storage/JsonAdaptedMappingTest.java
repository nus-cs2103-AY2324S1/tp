package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMapping.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS2040S_TO_NTU_S0402SC;
import static seedu.address.testutil.TypicalObjects.INVALID_LOCAL_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_MAPPING_MISC_INFORMATION;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_CODE;
import static seedu.address.testutil.TypicalObjects.TYPICAL_UNIVERSITY_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.model.localcourse.LocalCode;

//@@author lamchenghou

public class JsonAdaptedMappingTest {

    @Test
    public void toModelType_validLMappingDetails_returnsMapping() throws Exception {
        JsonAdaptedMapping mapping = new JsonAdaptedMapping(CS2040S_TO_NTU_S0402SC);
        assertEquals(CS2040S_TO_NTU_S0402SC, mapping.toModelType());
    }

    @Test
    public void toModelType_invalidLocalCode_throwsIllegalValueException() {
        JsonAdaptedMapping mapping =
                new JsonAdaptedMapping(
                        INVALID_LOCAL_COURSE_CODE,
                        TYPICAL_UNIVERSITY_NAME,
                        TYPICAL_PARTNER_COURSE_CODE,
                        TYPICAL_MAPPING_MISC_INFORMATION);
        String expectedMessage = ConstraintMessage.LOCALCOURSE_CODE.toString();
        assertThrows(IllegalValueException.class, expectedMessage, mapping::toModelType);
    }

    @Test
    public void toModelType_nullLocalCode_throwsIllegalValueException() {
        JsonAdaptedMapping mapping = new JsonAdaptedMapping(
                null,
                TYPICAL_UNIVERSITY_NAME,
                TYPICAL_PARTNER_COURSE_CODE,
                TYPICAL_MAPPING_MISC_INFORMATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, mapping::toModelType);
    }
}
