package seedu.address.storage.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtilTest.createMoreThanAllowedString;
import static seedu.address.storage.events.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT5;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;
import seedu.address.storage.address.JsonAdaptedPerson;

public class JsonAdaptedEventTest {
    private static final String TEXT_MORE_THAN_256 = createMoreThanAllowedString();
    private static final String INVALID_NAME = TEXT_MORE_THAN_256;
    private static final String INVALID_DATETIME = "23-09-2023 120:00";
    private static final String INVALID_LOCATION = TEXT_MORE_THAN_256;
    private static final String INVALID_DESCRIPTION = TEXT_MORE_THAN_256;

    private static final String VALID_NAME = EVENT5.getEventName().toString();
    private static final String VALID_START_TIME = EVENT5.getDuration().getTimeStartValue();
    private static final String VALID_END_TIME = EVENT5.getDuration().getTimeEndValue();
    private static final String VALID_LOCATION = EVENT5.getLocation().toString();
    private static final String VALID_DESCRIPTION = EVENT5.getDescription().toString();

    private static final List<JsonAdaptedPerson> VALID_CLIENTS = EVENT5.getClients().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(EVENT5);
        assertEquals(EVENT5, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_START_TIME, VALID_END_TIME, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(null, VALID_START_TIME, VALID_END_TIME, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidTimeStart_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_DATETIME, VALID_END_TIME, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = DateTimeParser.INVALID_DATETIME_FORMAT;
        assertThrows(ParseException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullTimeStart_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, null, VALID_END_TIME, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeStart.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidTimeEnd_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_TIME, INVALID_DATETIME, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = DateTimeParser.INVALID_DATETIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullTimeEnd_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_TIME, null, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeEnd.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_TIME, VALID_END_TIME, INVALID_LOCATION,
                        VALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }


    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_START_TIME, VALID_END_TIME, VALID_LOCATION,
                        INVALID_DESCRIPTION, VALID_CLIENTS);
        String expectedMessage = EventDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
