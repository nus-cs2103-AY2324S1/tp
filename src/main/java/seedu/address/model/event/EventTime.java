package seedu.address.model.event;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents the time of the event
 */
public class EventTime {

    public static final String MESSAGE_CONSTRAINTS =
            "The time must be in Hmm format, i.e. 2359, and it should not be blank";

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    private Optional<LocalTime> eventTime;

    /**
     * Constructor for EventTime
     * @param eventTime time of the event
     */
    public EventTime(String eventTime) {
        this.eventTime = Optional.of(LocalTime.parse(eventTime, TIME_FORMATTER));
    }

    /**
     * Check if the time is valid
     * @param trimmedTime time to be checked
     * @return true if the time is valid, false otherwise
     */
    public static boolean isValidTime(String trimmedTime) {
        try {
            LocalTime parsedTime = LocalTime.parse(trimmedTime, TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
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

        if (this.eventTime.isPresent() && otherTime.eventTime.isPresent()) {
            return true;
        }
        return this.eventTime.equals(otherTime.eventTime);
    }

    @Override
    public String toString() {
        return this.eventTime.map(localTime -> localTime.format(TIME_FORMATTER)).orElse("");
    }
}
