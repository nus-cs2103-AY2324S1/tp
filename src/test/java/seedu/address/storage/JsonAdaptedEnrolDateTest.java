package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.EnrolDate;

public class JsonAdaptedEnrolDateTest {
    private static final String VALID_DATE = "Jun 2023";
    private static final String INVALID_DATE = "June 2023";

    @Test
    public void toModelType_validEnrolDate_returnsEnrolDate() throws Exception {
        JsonAdaptedEnrolDate date = new JsonAdaptedEnrolDate(VALID_DATE);
        EnrolDate expected = new EnrolDate("Jun 2023");
        assertEquals(expected, date.toModelType());
    }

    @Test
    public void toModelType_invalidEnrolDate_throwsIllegalValueException() {
        JsonAdaptedEnrolDate date = new JsonAdaptedEnrolDate(INVALID_DATE);
        String expectedMessage = EnrolDate.MESSAGE_INVALID_DATE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, date::toModelType);
    }
}
