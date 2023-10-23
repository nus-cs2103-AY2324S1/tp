package seedu.ccacommander.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ccacommander.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalEvents.AURORA_BOREALIS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.exceptions.IllegalValueException;
import seedu.ccacommander.model.event.EventDate;
import seedu.ccacommander.model.event.Location;
import seedu.ccacommander.model.shared.Name;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = "µ@18Tur";
    private static final String INVALID_DATE = "20/12/12";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_TAG = "#nature";

    private static final String VALID_NAME = AURORA_BOREALIS.getName().toString();
    private static final String VALID_DATE = AURORA_BOREALIS.getDate().toString();
    private static final String VALID_LOCATION = AURORA_BOREALIS.getLocation().value;
    private static final List<JsonAdaptedTag> VALID_TAGS = AURORA_BOREALIS.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(AURORA_BOREALIS);
        assertEquals(AURORA_BOREALIS, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_DATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_DATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, INVALID_DATE, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, null, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_DATE, INVALID_LOCATION, VALID_TAGS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_NAME, VALID_DATE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_DATE, VALID_LOCATION, invalidTags);
        assertThrows(IllegalValueException.class, event::toModelType);
    }


}
