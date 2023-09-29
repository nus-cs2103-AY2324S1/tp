package seedu.address.calendar.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private static final DateTimeFormatter DATE_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String startDateTime, String endDateTime) {
        this.start = LocalDateTime.parse(startDateTime, DATE_TIME_STRING_FORMATTER);
        this.end = LocalDateTime.parse(endDateTime, DATE_TIME_STRING_FORMATTER);
    }

    public Event(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
}
