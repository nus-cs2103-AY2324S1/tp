package seedu.address.model.event;

import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTime {

    public static final String MESSAGE_CONSTRAINTS =
            "The time must be in HHmm format, i.e. 2359, and it should not be blank";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    public static final EventTime NULL_EVENT_TIME = new EventTime("0000");
    private LocalTime eventTime;


    private EventTime(String eventTime) {
        this.eventTime = LocalTime.parse(eventTime, TIME_FORMATTER);
    }

    public static boolean isValidTime(String trimmedTime) {
        //to represent the case of optional time.
        if (trimmedTime.isBlank()) {
            return true;
        }

        try {
            LocalTime parsedTime = LocalTime.parse(trimmedTime, TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the event time.
     * @return event time.
     */
    public LocalTime getEventTime() {
        return this.eventTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventTime)) {
            return false;
        }

        EventTime otherTime = (EventTime) other;
        return this.eventTime.equals(otherTime.eventTime);
    }

    /**
     * Factory method for EventTime.
     * @param eventTime the event time in HHmm format.
     * @return an instance of EventTime.
     * @throws ParseException if the given eventTime does not follow the format.
     */
    public static EventTime of(String eventTime) throws ParseException {
        if (eventTime.isBlank()) {
            return NULL_EVENT_TIME;
        }
        if (!isValidTime(eventTime)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        return new EventTime(eventTime);
    }


    /**
     * ToString for the event time.
     * Kept as the same format as the input for json storage.
     */
    @Override
    public String toString() {
        return eventTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    public String forDisplay() {
        return eventTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}