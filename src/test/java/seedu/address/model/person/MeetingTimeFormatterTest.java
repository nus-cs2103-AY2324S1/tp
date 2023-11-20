package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class MeetingTimeFormatterTest {
    @Test
    public void parse() {
        // null meeting time string
        assertThrows(NullPointerException.class, () -> MeetingTimeFormatter.parse(null));

        // invalid meeting time strings
        assertThrows(DateTimeParseException.class, () -> MeetingTimeFormatter.parse("")); // empty string
        assertThrows(DateTimeParseException.class, () -> MeetingTimeFormatter.parse(" ")); // spaces only
        assertThrows(DateTimeParseException.class, () -> MeetingTimeFormatter.parse("meeting")); // non-numeric
        assertThrows(DateTimeParseException.class, () ->
                MeetingTimeFormatter.parse("23/10/2023")); // only date and no time
        assertThrows(DateTimeParseException.class, () ->
                MeetingTimeFormatter.parse("2/1/2023")); // date not enough numbers
        assertThrows(DateTimeParseException.class, () ->
                MeetingTimeFormatter.parse("23-10-2023 10:53pm")); // datetime with wrong format
        assertThrows(DateTimeParseException.class, () ->
                MeetingTimeFormatter.parse("23/10/2023 10.53")); // datetime with wrong format

        // valid meeting time strings
        LocalDateTime meetingTime1 = LocalDateTime.parse("2023-10-23T15:30:00");
        assertEquals(MeetingTimeFormatter.parse("23/10/2023 15:30"), meetingTime1);
        LocalDateTime meetingTime2 = LocalDateTime.parse("2020-12-31T12:00:00");
        assertEquals(MeetingTimeFormatter.parse("31/12/2020 12:00"), meetingTime2);
        LocalDateTime meetingTime3 = LocalDateTime.parse("2021-01-01T00:00:00");
        assertEquals(MeetingTimeFormatter.parse("01/01/2021 00:00"), meetingTime3);
    }

    @Test
    public void format() {
        // null meeting time
        assertThrows(NullPointerException.class, () -> MeetingTimeFormatter.format(null));

        // valid meeting time
        LocalDateTime meetingTime1 = LocalDateTime.parse("2023-10-23T15:30:00");
        assertEquals(MeetingTimeFormatter.format(meetingTime1), "23/10/2023 15:30");
        LocalDateTime meetingTime2 = LocalDateTime.parse("2020-12-31T12:00:00");
        assertEquals(MeetingTimeFormatter.format(meetingTime2), "31/12/2020 12:00");
        LocalDateTime meetingTime3 = LocalDateTime.parse("2021-01-01T00:00:00");
        assertEquals(MeetingTimeFormatter.format(meetingTime3), "01/01/2021 00:00");
    }

    @Test
    public void isValid() {
        // null meeting time string
        assertThrows(NullPointerException.class, () -> MeetingTimeFormatter.isValid(null));

        // invalid meeting time strings
        assertFalse(MeetingTimeFormatter.isValid("")); // empty string
        assertFalse(MeetingTimeFormatter.isValid(" ")); // spaces only
        assertFalse(MeetingTimeFormatter.isValid("meeting")); // non-numeric
        assertFalse(MeetingTimeFormatter.isValid("23/10/2023")); // only date and no time
        assertFalse(MeetingTimeFormatter.isValid("2/1/2023")); // date not enough numbers
        assertFalse(MeetingTimeFormatter.isValid("23-10-2023 10:53pm")); // datetime with wrong format
        assertFalse(MeetingTimeFormatter.isValid("23/10/2023 10.53")); // datetime with wrong format

        // valid meeting time strings
        assertTrue(MeetingTimeFormatter.isValid("23/10/2023 15:30"));
        assertTrue(MeetingTimeFormatter.isValid("31/12/2020 12:00"));
        assertTrue(MeetingTimeFormatter.isValid("01/01/2021 00:00"));
    }
}
