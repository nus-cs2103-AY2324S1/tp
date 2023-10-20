package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CONFERENCE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;

public class JsonAdaptedEventTest {
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_EVENT_PERIOD = "2010-10-10 10:00-2010-10-10 12:00";

    private static final String VALID_DESCRIPTION = CONFERENCE.getDescription().getDescription();
    private static final String VALID_EVENT_PERIOD = CONFERENCE.getEventPeriod().getFormattedPeriod();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(CONFERENCE);
        assertEquals(CONFERENCE, event.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_DESCRIPTION, VALID_EVENT_PERIOD);
        String expectedMessage = EventDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_EVENT_PERIOD);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventPeriod_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_DESCRIPTION, INVALID_EVENT_PERIOD);
        String expectedMessage = EventPeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventPeriod_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventPeriod.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

}
