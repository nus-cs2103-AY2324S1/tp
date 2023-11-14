package seedu.address.model.event;

import java.time.LocalDateTime;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Represents the duration of an event in the events book.
 */
public class Duration implements Comparable<Duration> {
    public static final String MESSAGE_CONSTRAINT = DateTimeParser.INVALID_DATETIME_FORMAT;
    private TimeStart timeStart;
    private TimeEnd timeEnd;

    /**
     * Constructs a {@code Duration}.
     *
     * @param timeStart start time of duration.
     * @param timeEnd end time of duration.
     * @throws ParseException if DateTime is of an invalid date-time duration.
     */
    public Duration(TimeStart timeStart, TimeEnd timeEnd) throws ParseException {
        if (!timeEnd.isAfter(timeStart)) {
            throw new ParseException(DateTimeParser.INVALID_DATETIME_DURATION);
        }
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Updates an old duration given a new start/end time. This method is used for editing Durations in events.
     *
     * @param oldDuration old duration instance.
     * @param newTimeStart new time start input.
     * @param newTimeEnd new time end input.
     * @throws ParseException if update input is invalid.
     */
    public static Duration updateDuration(Duration oldDuration, String newTimeStart, String newTimeEnd)
            throws ParseException {
        if (newTimeStart == null && newTimeEnd == null) {
            return oldDuration;
        } else if (newTimeStart == null) {
            return DateTimeParser.parseDateTimeDuration(oldDuration.timeStart.toString(), newTimeEnd);
        } else if (newTimeEnd == null) {
            return DateTimeParser.parseDateTimeDuration(newTimeStart, oldDuration.timeEnd.toString());
        } else {
            return DateTimeParser.parseDateTimeDuration(newTimeStart, newTimeEnd);
        }
    }

    /**
     * Returns the String value of the startTime
     */
    public String getTimeStartValue() {
        return timeStart.getValue();
    }

    /**
    * Returns the String value of the endTime
    */
    public String getTimeEndValue() {
        return timeEnd.getValue();
    }

    /**
     * Checks if {@code timeEnd} of this {@code Duration} is after now
     */
    public boolean isAfterNow() {
        return timeEnd.isAfterNow();
    }

    /**
     * Checks if the duration contains a specified date-time
     */
    public boolean hasTime(LocalDateTime time) {
        return time.isBefore(timeEnd.getTime())
                && time.isAfter(timeStart.getTime());
    }

    /**
     * Checks if duration start time is before given time and after current time
     */
    public boolean hasStartTimeBefore(LocalDateTime time) {
        return timeStart.getTime().isBefore(time)
                && timeEnd.isAfterNow();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Duration)) {
            return false;
        }

        Duration otherDuration = (Duration) other;
        boolean hasSameStart = timeStart.equals(otherDuration.timeStart);
        boolean hasSameEnd = timeEnd.equals(otherDuration.timeEnd);

        return hasSameStart && hasSameEnd;
    }

    /**
     * Compares {@code Duration} objects using the {@code Duration} parameter.
     *
     * @param other the object to be compared.
     */
    @Override
    public int compareTo(Duration other) {
        if (this.timeStart.isBefore(other.timeStart)) {
            return -1;
        } else if (other.timeStart.isBefore(this.timeStart)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return timeStart.toString() + " - " + timeEnd.toString();
    }
}
