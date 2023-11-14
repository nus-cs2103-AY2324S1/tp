package seedu.address.model.finance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Duration;


/**
 * Represents the time due of a finance entry in the address book.
 */
public class TimeDue {
    public static final String MESSAGE_CONSTRAINTS = DateTimeParser.INVALID_DATETIME_FORMAT;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public final String value;

    private final LocalDateTime timeDue;
    /**
     * Constructs a {@code TimeDue}.
     *
     * @param time A valid due time.
     */
    public TimeDue(LocalDateTime time) {
        requireNonNull(time);
        timeDue = time;
        value = timeToString(time);
    }

    /**
     * Constructs a {@code TimeDue}.
     *
     * @param timeString A valid start time string.
     */
    public TimeDue(String timeString) throws ParseException {
        requireNonNull(timeString);
        timeDue = stringToTime(timeString);
        value = timeString;
    }


    public LocalDateTime getTime() {
        return timeDue;
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

    public boolean isWithin(Duration duration) {
        return duration.hasTime(timeDue);
    }

    public boolean isBefore(TimeDue time) {
        return timeDue.isBefore(time.getTime());
    }

    @Override
    public String toString() {
        return timeDue.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeDue)) {
            return false;
        }

        TimeDue otherTimeDue = (TimeDue) other;
        return timeDue.equals(otherTimeDue.timeDue);
    }

    @Override
    public int hashCode() {
        return timeDue.hashCode();
    }
}
