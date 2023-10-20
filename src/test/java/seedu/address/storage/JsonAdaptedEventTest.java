package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventInformation;
import seedu.address.model.event.EventLocation;

public class JsonAdaptedEventTest {
    private static final String VALID_NAME = "Meeting with professor";
    private static final String VALID_START_TIME = "12:00";
    private static final String VALID_END_TIME = "01:00";
    private static final String VALID_LOCATION = "COM 1 Basement";
    private static final String VALID_INFORMATION = "Discuss the project implementation with the professor";

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_START_TIME, VALID_END_TIME, VALID_LOCATION,
                VALID_INFORMATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, null, VALID_END_TIME, VALID_LOCATION,
                VALID_INFORMATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "start");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_START_TIME, null, VALID_LOCATION,
                VALID_INFORMATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "end");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_START_TIME, VALID_END_TIME, null,
                VALID_INFORMATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventLocation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullInformation_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_START_TIME, VALID_END_TIME, VALID_LOCATION,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventInformation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
