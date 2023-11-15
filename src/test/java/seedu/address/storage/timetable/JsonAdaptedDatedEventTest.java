package seedu.address.storage.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedule.NORMAL_DATED_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.timetable.DatedEvent;

public class JsonAdaptedDatedEventTest {
    private static final String INVALID_TIMEBLOCK = "monday";
    private static final String INVALID_DATE = "26-12-2001";
    private static final String VALID_NAME = "CS2100";
    private static final String VALID_TIMEBLOCK = "Monday 1200 1300";
    private static final String VALID_DATE = "2001-12-26 1200 1400";

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDatedEvent datedEvent = new JsonAdaptedDatedEvent(null, VALID_TIMEBLOCK, VALID_DATE, false);
        assertThrows(IllegalValueException.class, datedEvent::toModelType);
    }

    @Test
    public void toModelType_invalidTimeBlock_throwsIllegalValueException() {
        JsonAdaptedDatedEvent datedEvent = new JsonAdaptedDatedEvent(VALID_NAME, INVALID_TIMEBLOCK, VALID_DATE, false);
        String expectedMessage = DatedEvent.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, datedEvent::toModelType);
    }

    @Test
    public void toModelType_nullTimeBlock_throwsIllegalValueException() {
        JsonAdaptedDatedEvent datedEvent = new JsonAdaptedDatedEvent(VALID_NAME, null, VALID_DATE, false);
        String expectedMessage = DatedEvent.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, datedEvent::toModelType);
    }

    @Test
    public void toModelType_invalidDateString_throwsIllegalValueException() {
        JsonAdaptedDatedEvent datedEvent = new JsonAdaptedDatedEvent(VALID_NAME, VALID_TIMEBLOCK, INVALID_DATE, false);
        String expectedMessage = DatedEvent.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, datedEvent::toModelType);
    }

    @Test
    public void toModelType_validDatedEvent_success() throws IllegalValueException {
        JsonAdaptedDatedEvent datedEvent = new JsonAdaptedDatedEvent(NORMAL_DATED_EVENT);
        assertEquals(NORMAL_DATED_EVENT, datedEvent.toModelType());
    }


}
