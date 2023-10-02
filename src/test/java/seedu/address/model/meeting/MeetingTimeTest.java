package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class MeetingTimeTest {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingTime(null, null));
        assertThrows(NullPointerException.class, () -> new MeetingTime(LocalDateTime.parse("03.10.2023 1000", FORMAT),
                null));
        assertThrows(NullPointerException.class, () -> new MeetingTime(null,
                LocalDateTime.parse("03.10.2023 1000", FORMAT)));
    }

    @Test
    public void isValidMeetingTime() {
        // null
        assertThrows(NullPointerException.class, () -> MeetingTime.isValidMeetingTime(null, null));

        // invalid
        assertFalse(MeetingTime.isValidMeetingTime(LocalDateTime.parse("03.10.2023 1000", FORMAT),
                LocalDateTime.parse("02.10.2023 1000", FORMAT))); // start is after end

        // valid
        assertTrue(MeetingTime.isValidMeetingTime(LocalDateTime.parse("02.10.2023 1000", FORMAT),
                LocalDateTime.parse("03.10.2023 1000", FORMAT))); // start before end
        assertTrue(MeetingTime.isValidMeetingTime(LocalDateTime.parse("02.10.2023 1000", FORMAT),
                LocalDateTime.parse("02.10.2023 1000", FORMAT))); // start equal end
    }

    @Test
    public void equals() {
        MeetingTime meetingTime = new MeetingTime(LocalDateTime.parse("02.10.2023 1000", FORMAT),
                LocalDateTime.parse("03.10.2023 1000", FORMAT));

        // same values -> returns true
        assertTrue(meetingTime.equals(new MeetingTime(LocalDateTime.parse("02.10.2023 1000", FORMAT),
                LocalDateTime.parse("03.10.2023 1000", FORMAT))));

        // same object -> returns true
        assertTrue(meetingTime.equals(meetingTime));

        // null -> returns false
        assertFalse(meetingTime.equals(null));

        // different types -> returns false
        assertFalse(meetingTime.equals(5.0f));

        // different values -> returns false
        assertFalse(meetingTime.equals(new MeetingTime(LocalDateTime.parse("02.10.2023 1000", FORMAT),
                LocalDateTime.parse("04.10.2023 1000", FORMAT))));
    }
}
