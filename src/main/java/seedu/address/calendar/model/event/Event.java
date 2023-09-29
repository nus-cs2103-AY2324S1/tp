package seedu.address.calendar.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the calendar
 */
public class Event {
    private static final DateTimeFormatter DATE_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an event
     * @param startDateTimeString start date and time string of event in format "yyyy-MM-dd HH:mm"
     * @param endDateTimeString end date and time string of event in format "yyyy-MM-dd HH:mm"
     */
    public Event(String startDateTimeString, String endDateTimeString) {
        this.start = LocalDateTime.parse(startDateTimeString, DATE_TIME_STRING_FORMATTER);
        this.end = LocalDateTime.parse(endDateTimeString, DATE_TIME_STRING_FORMATTER);
    }

    /**
     * Constructs an event
     * @param start start date and time stored in a LocalDateTime object
     * @param end end date and time stored in a LocalDateTime object
     */
    public Event(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
}
