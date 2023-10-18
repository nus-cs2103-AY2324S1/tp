package seedu.address.model.event;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents the time of the event.
 */
public class EventTime {

    public static final String MESSAGE_CONSTRAINTS =
            "The time must be in HHmm format, i.e. 2359, and it should not be blank";

    public static final String TIME_REGEX = "([01][0-9]|2[0-3])[0-5][0-9]";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    public static final EventTime NULL_EVENT_TIME;

    static {
        try {
            NULL_EVENT_TIME = new EventTime("0000");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalTime eventTime;

    /**
     * Constructs an EventTime object.
     * @param eventTime the event time in HHmm format.
     * @throws ParseException if the given eventTime does not follow the format.
     */
    public EventTime(String eventTime) throws ParseException {
        try {
            this.eventTime = LocalTime.parse(eventTime, TIME_FORMATTER);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid time.
     * Because time is optional, a blank string is considered valid.
     */
    public static boolean isValidTime(String trimmedTime) {
        //to represent the case of optional time.
        if (trimmedTime.isBlank()) {
            return true;
        } else if (!trimmedTime.matches(TIME_REGEX)) {
            return false;
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

    /**
     * For display in the UI.
     */
    public String forDisplay() {
        return eventTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
