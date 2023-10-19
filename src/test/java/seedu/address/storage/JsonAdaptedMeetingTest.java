package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Title;
import seedu.address.testutil.MeetingBuilder;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_TITLE = " ";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_START = "99.99.9999 9999";
    private static final String INVALID_END = "88.88.8888 8888";
    private static final String INVALID_ATTENDEE = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TITLE = MEETING1.getTitle().toString();
    private static final String VALID_LOCATION = MEETING1.getLocation().toString();
    private static final String VALID_START = MEETING1.getStart().toString();
    private static final String VALID_END = MEETING1.getEnd().toString();

    private static final Meeting EDITED_MEETING_1 = new MeetingBuilder(MEETING1).withAttendees("Alice Pauline").build();
    private static final List<JsonAdaptedAttendee> VALID_ATTENDEE = EDITED_MEETING_1.getAttendees().stream()
            .map(JsonAdaptedAttendee::new).collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = MEETING1.getTags().stream().map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(EDITED_MEETING_1);
        assertEquals(EDITED_MEETING_1, meeting.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(INVALID_TITLE, VALID_LOCATION, VALID_START, VALID_END,
                VALID_ATTENDEE, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(null, VALID_LOCATION, VALID_START, VALID_END,
                VALID_ATTENDEE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, INVALID_LOCATION, VALID_START, VALID_END,
                VALID_ATTENDEE, VALID_TAGS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, null, VALID_START, VALID_END, VALID_ATTENDEE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidStart_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, VALID_LOCATION, INVALID_START, VALID_END,
                VALID_ATTENDEE, VALID_TAGS);
        String expectedMessage = MeetingTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullStart_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, VALID_LOCATION, null, VALID_END,
                VALID_ATTENDEE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidEnd_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, VALID_LOCATION, VALID_START, INVALID_END,
                VALID_ATTENDEE, VALID_TAGS);
        String expectedMessage = MeetingTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullEnd_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, VALID_LOCATION, null, VALID_END,
                VALID_ATTENDEE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidAttendees_throwsIllegalValueException() {
        List<JsonAdaptedAttendee> invalidAttendees = new ArrayList<>(VALID_ATTENDEE);
        invalidAttendees.add(new JsonAdaptedAttendee(INVALID_ATTENDEE));
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, VALID_LOCATION, VALID_START, VALID_END,
                invalidAttendees, VALID_TAGS);
        assertThrows(IllegalValueException.class, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_TITLE, VALID_LOCATION, VALID_START, VALID_END,
                VALID_ATTENDEE, invalidTags);
        assertThrows(IllegalValueException.class, meeting::toModelType);
    }

}
