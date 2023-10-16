package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Meeting meeting = new MeetingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> meeting.getAttendees().remove(0));
    }

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(MEETING1.isSameMeeting(MEETING1));

        // null -> returns false
        assertFalse(MEETING1.isSameMeeting(null));

        // same title, all other attributes different -> returns false
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withLocation("Not a zoom call url")
                .withStart("23.09.2023 1000").withEnd("23.09.2023 1000").withAttendees().build();
        assertFalse(MEETING1.isSameMeeting(editedMeeting1));

        // different title, all other attributes same -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("Different Meeting Title").build();
        assertFalse(MEETING1.isSameMeeting(editedMeeting1));

        // title differs in case, all other attributes same -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("CS2103T MEETING").build();
        assertFalse(MEETING1.isSameMeeting(editedMeeting1));

        // tittle has trailing spaces, all other attributes same -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("CS2103T MEETING ").build();
        assertFalse(MEETING1.isSameMeeting(editedMeeting1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting meeting1Copy = new MeetingBuilder(MEETING1).build();
        assertTrue(MEETING1.equals(meeting1Copy));

        // same object -> returns true
        assertTrue(MEETING1.equals(MEETING1));

        // null -> returns false
        assertFalse(MEETING1.equals(null));

        // different type -> returns false
        assertFalse(MEETING1.equals(5));

        // different name -> returns false
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("CS2103T MEETING").build();
        assertFalse(MEETING1.equals(editedMeeting1));

        // different phone -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withLocation("Room 2").build();
        assertFalse(MEETING1.equals(editedMeeting1));

        // different email -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withStart("20.09.2023 1100").build();
        assertFalse(MEETING1.equals(editedMeeting1));

        // different attendees -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withAttendees("Alice Pauline").build();
        assertFalse(MEETING1.equals(editedMeeting1));

        // different tags -> returns false
        editedMeeting1 = new MeetingBuilder(MEETING1).withTags("other").build();
        assertFalse(MEETING1.equals(editedMeeting1));
    }

    @Test
    public void toStringMethod() {
        String expected = Meeting.class.getCanonicalName() + "{title=" + MEETING1.getTitle() + ", location="
                + MEETING1.getLocation() + ", start=" + MEETING1.getStart() + ", end=" + MEETING1.getEnd()
                + ", attendees=" + MEETING1.getAttendees() + ", tags=" + MEETING1.getTags() + "}";
        assertEquals(expected, MEETING1.toString());
    }
}
