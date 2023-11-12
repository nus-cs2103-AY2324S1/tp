package seedu.address.storage.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INCORRECT_DATE_FORMAT;
import static seedu.address.storage.event.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT_1;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedEventTest {
    private static final String VALID_START_TIME = "2023-09-22 18:00";
    private static final String VALID_END_TIME = "2023-09-22 19:00";

    private static final String VALID_DESCRIPTION = "Interview";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String INVALID_START_TIME = "22-09-2023 18:00";
    private static final String INVALID_END_TIME = "22-09-2023 19:00";

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(EVENT_1);
        assertEquals(EVENT_1, event.toModelType());
    }

    @Test
    public void toModelType_nullPerson_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_DESCRIPTION, VALID_START_TIME, VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, null, VALID_START_TIME, VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "description");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_DESCRIPTION, null, VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "startTime");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_DESCRIPTION, VALID_START_TIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "endTime");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(ALICE.getName().toString(), VALID_DESCRIPTION, INVALID_START_TIME, VALID_END_TIME);
        String expectedMessage = MESSAGE_INCORRECT_DATE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(ALICE.getName().toString(), VALID_DESCRIPTION, VALID_START_TIME, INVALID_END_TIME);
        String expectedMessage = MESSAGE_INCORRECT_DATE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

}
