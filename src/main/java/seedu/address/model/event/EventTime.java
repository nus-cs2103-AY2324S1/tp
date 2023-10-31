package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.address.commons.util.DateTimeUtil;

/**
 * The class for holding the "start" and "end" part for an Event
 */
public class EventTime {
    public static final String MESSAGE_INVALID_DATETIME_FORMAT = "Invalid date-time format! ";
    public static final String MESSAGE_NON_EMPTY = "Event time can not be empty!";

    private final LocalDateTime time;

    private EventTime() {
        this.time = null;
    }
    private EventTime(String time) throws DateTimeParseException {
        this.time = DateTimeUtil.parseString(time);
    }

    /**
     * Construct an {@code EventTime} object from {@code String}
     * @param timeStr The time in {@code String}
     * @return The {@code EventTime} object
     */
    public static EventTime fromString(String timeStr) throws DateTimeParseException {
        return timeStr.isEmpty() ? new EventTime() : new EventTime(timeStr);
    }

    /**
     * Get the String representation of this {@code EventTime} object
     * @return The String representation of this {@code EventTime} object, in the format {@code yyyy-MM-dd HH:mm:ss}
     */
    @Override
    public String toString() {
        return this.time != null ? DateTimeUtil.toFormattedString(this.time) : "";
    }
}
