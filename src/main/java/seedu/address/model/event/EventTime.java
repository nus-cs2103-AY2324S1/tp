package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.address.commons.util.DateTimeUtil;

/**
 * The class for holding the "start" and "end" part for an Event
 */
public class EventTime {
    public static final String MESSAGE_INVALID_DATETIME_FORMAT = "Invalid date-time format! ";
    public static final String MESSAGE_NON_EMPTY = "Time can not be empty!";

    private final LocalDateTime time;

    private EventTime(String time) throws DateTimeParseException {
        this.time = DateTimeUtil.parseString(time);
    }

    /**
     * Construct an {@code EventTime} object from {@code String}
     * @param timeStr The time in {@code String}
     * @return The {@code EventTime} object
     */
    public static EventTime fromString(String timeStr) throws DateTimeParseException {
        return new EventTime(timeStr);
    }

    /**
     * Return the date-time as {@code LocalDateTime}
     * @return The date-time as {@code LocalDateTime}
     */
    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Get the String representation of this {@code EventTime} object
     * @return The String representation of this {@code EventTime} object, in the format {@code yyyy-MM-dd HH:mm:ss}
     */
    @Override
    public String toString() {
        return this.time != null ? DateTimeUtil.toFormattedString(this.time) : "";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventTime)) {
            return false;
        }

        EventTime otherTime = (EventTime) other;
        return time.equals(otherTime.time);
    }

    /**
     * Return {@code true} if this {@code EventTime} is after {@code other}.
     * Always returns false when this {@code EventTime} or {@code other} contains null time
     * @param other The other {@code EventTime}
     */
    public boolean isAfter(EventTime other) {
        return this.time != null && other.time != null && this.time.isAfter(other.time);
    }
}
