package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Represents the start time of a duration in the events book.
 */
public class TimeStart {

    public static final String MESSAGE_CONSTRAINTS = DateTimeParser.INVALID_DATETIME_FORMAT;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final LocalDateTime timeStart;

    private final String value;

    /**
     * Constructs a {@code TimeStart}.
     *
     * @param time A valid start time.
     */
    public TimeStart(LocalDateTime time) {
        requireNonNull(time);
        timeStart = time;
        value = timeToString(time);
    }

    /**
     * Constructs a {@code TimeStart}.
     *
     * @param timeString A valid start time string.
     */
    public TimeStart(String timeString) throws ParseException {
        requireNonNull(timeString);
        timeStart = stringToTime(timeString);
        value = timeString;
    }


    public LocalDateTime getTime() {
        return timeStart;
    }

    public String getValue() {
        return value;
    }

    public String timeToString(LocalDateTime time) {
        return time.format(DATE_TIME_FORMATTER);
    }

    public LocalDateTime stringToTime(String timeString) throws ParseException {
        return DateTimeParser.parseDateTimeInstance(timeString);
    }

    public boolean isBefore(TimeStart time) {
        return timeStart.isBefore(time.getTime());
    }

    @Override
    public String toString() {
        return timeStart.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeStart)) {
            return false;
        }

        TimeStart otherTimeStart = (TimeStart) other;
        return timeStart.equals(otherTimeStart.timeStart);
    }

    @Override
    public int hashCode() {
        return timeStart.hashCode();
    }
}
