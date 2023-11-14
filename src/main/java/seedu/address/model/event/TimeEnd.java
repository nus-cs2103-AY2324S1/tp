package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents the end time of an event in the events book.
 * Guarantees: immutable;
 */
public class TimeEnd {

    public static final String MESSAGE_CONSTRAINTS = DateTimeParser.INVALID_DATETIME_FORMAT;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final LocalDateTime timeEnd;

    private final String value;

    /**
     * Constructs a {@code TimeEnd}.
     *
     * @param time A valid end time.
     */
    public TimeEnd(LocalDateTime time) {
        requireNonNull(time);
        timeEnd = time;
        value = timeToString(time);
    }

    /**
     * Constructs a {@code TimeEnd}.
     *
     * @param timeString A valid end time string.
     */
    public TimeEnd(String timeString) throws ParseException {
        requireNonNull(timeString);
        timeEnd = stringToTime(timeString);
        value = timeString;
    }

    /**
     * Returns end time in LocalDateTime type.
     */
    public LocalDateTime getTime() {
        return timeEnd;
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

    public boolean isAfter(TimeStart timeStart) {
        return timeEnd.isAfter(timeStart.getTime());
    }

    public boolean isAfterNow() {
        return timeEnd.isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return timeEnd.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeEnd)) {
            return false;
        }

        TimeEnd otherTimeEnd = (TimeEnd) other;
        return timeEnd.equals(otherTimeEnd.timeEnd);
    }

    @Override
    public int hashCode() {
        return timeEnd.hashCode();
    }
}
