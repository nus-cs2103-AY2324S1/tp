package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingTime(null));
    }

    @Test
    public void constructor_invalidMeetingTime_throwsIllegalArgumentException() {
        String invalidMeetingTime = "32/13/2020 25:00";
        assertThrows(IllegalArgumentException.class, () -> new MeetingTime(invalidMeetingTime));
    }

    @Test
    public void isValidMeetingTime() {
        // null meeting time string
        assertThrows(NullPointerException.class, () -> MeetingTime.isValidMeetingTime(null));

        // invalid meeting time strings
        assertFalse(MeetingTime.isValidMeetingTime("")); // empty string
        assertFalse(MeetingTime.isValidMeetingTime(" ")); // spaces only
        assertFalse(MeetingTime.isValidMeetingTime("meeting")); // non-numeric
        assertFalse(MeetingTime.isValidMeetingTime("23/10/2023")); // only date and no time
        assertFalse(MeetingTime.isValidMeetingTime("2/1/2023")); // date not enough numbers
        assertFalse(MeetingTime.isValidMeetingTime("23-10-2023 10:53pm")); // datetime with wrong format
        assertFalse(MeetingTime.isValidMeetingTime("23/10/2023 10.53")); // datetime with wrong format

        // valid meeting time strings
        assertTrue(MeetingTime.isValidMeetingTime("23/10/2023 15:30"));
        assertTrue(MeetingTime.isValidMeetingTime("31/12/2020 12:00"));
        assertTrue(MeetingTime.isValidMeetingTime("01/01/2021 00:00"));
    }

    @Test
    public void equals() {
        MeetingTime meetingTime = new MeetingTime("23/10/2023 15:30");

        // same values -> returns true
        assertTrue(meetingTime.equals(new MeetingTime("23/10/2023 15:30")));

        // same object -> returns true
        assertTrue(meetingTime.equals(meetingTime));

        // null -> returns false
        assertFalse(meetingTime.equals(null));

        // different types -> returns false
        assertFalse(meetingTime.equals(5.0f));

        // different values -> returns false
        assertFalse(meetingTime.equals(new MeetingTime("12/10/2025 10:20")));
    }

    @Test
    void hashCode_equals() {
        assertEquals(new MeetingTime("12/10/2025 10:20").hashCode(),
                new MeetingTime("12/10/2025 10:20").hashCode());
    }
}
