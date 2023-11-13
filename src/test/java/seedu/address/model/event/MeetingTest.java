package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.MEETING_WITHOUT_TIME;
import static seedu.address.testutil.TypicalEvents.TP_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    @Test
    public void isSameEventTest() {
        assertTrue(TP_MEETING.isSameEvent(TP_MEETING));

        Meeting tpMeetingCopy = new MeetingBuilder(TP_MEETING).build();
        assertTrue(TP_MEETING.isSameEvent(tpMeetingCopy));
    }

    @Test
    public void getEventTypeTest() {
        assertEquals("meeting", TP_MEETING.getEventType().toString());
    }

    @Test
    public void getStartDate() {
        assertEquals("2023-10-18", TP_MEETING.getStartDate().toString());
    }

    @Test
    public void getEndDate() {
        assertEquals("2023-10-18", TP_MEETING.getEndDate().toString());
    }

    @Test
    public void getStartTime() {
        assertEquals("0000", TP_MEETING.getStartTime().toString());
        assertEquals("0000", MEETING_WITHOUT_TIME.getStartTime().toString());
    }

    @Test
    public void getEndTime() {
        assertEquals("2359", TP_MEETING.getEndTime().toString());
        assertEquals("0000", MEETING_WITHOUT_TIME.getEndTime().toString());
    }

    @Test
    public void hasStartTime() {
        assertTrue(TP_MEETING.hasStartTime());
        assertFalse(MEETING_WITHOUT_TIME.hasStartTime());
    }

    @Test
    public void hasEndTime() {
        assertTrue(TP_MEETING.hasEndTime());
        assertFalse(MEETING_WITHOUT_TIME.hasEndTime());
    }

    @Test
    public void equalsTest() throws ParseException {
        Meeting expected = new MeetingBuilder(TP_MEETING).withEventDate("2020-10-10").build();
        Meeting actual = new MeetingBuilder(TP_MEETING).withEventDate("2020-10-10").build();

        assertEquals(expected, actual);
    }
    @Test
    public void equalsWrongType() {
        assertNotEquals(1, TP_MEETING);
    }

    @Test
    public void isSameMeetingWrongType() {
        assertFalse(TP_MEETING.isSameEvent(null));
    }

}
