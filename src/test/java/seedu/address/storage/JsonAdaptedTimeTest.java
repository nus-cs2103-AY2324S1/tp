package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTime.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Time;
import seedu.address.model.TimeInterval;

public class JsonAdaptedTimeTest {
    @Test
    public void toModelType_validTimeDetails_returnsTime() throws Exception {
        JsonAdaptedTime monday = new JsonAdaptedTime("mon 1200", "mon 1300");
        assertEquals(ParserUtil.parseEachInterval("mon 1200 - mon 1300"), monday.toModelType());
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedTime invalidStartTime = new JsonAdaptedTime(null, "mon 1300");
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "start");
        assertThrows(IllegalValueException.class, expectedMessage, invalidStartTime::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedTime invalidStartTime = new JsonAdaptedTime("saturday 1400", "mon 1300");
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, invalidStartTime::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedTime invalidEndTime = new JsonAdaptedTime("mon 1300", null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "end");
        assertThrows(IllegalValueException.class, expectedMessage, invalidEndTime::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedTime invalidEndTime = new JsonAdaptedTime("mon 1300", "monday 1400");
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, invalidEndTime::toModelType);
    }

    @Test
    public void toModelType_startTimeAfterEndTime_throwsIllegalValueException() {
        JsonAdaptedTime endTimeBeforeStartTime = new JsonAdaptedTime("sat 1300", "mon 1400");
        String expectedMessage = TimeInterval.MESSAGE_CONSTRAINTS_LOGIC;
        assertThrows(ParseException.class, expectedMessage, endTimeBeforeStartTime::toModelType);
    }

    @Test
    public void toModelType_startTimeEqualsEndTime_throwsIllegalValueException() {
        JsonAdaptedTime endTimeBeforeStartTime = new JsonAdaptedTime("sat 1300", "sat 1300");
        String expectedMessage = TimeInterval.MESSAGE_CONSTRAINTS_LOGIC;
        assertThrows(ParseException.class, expectedMessage, endTimeBeforeStartTime::toModelType);
    }
}
