package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.util.DateTimeParser.INPUT_TIME_FORMATTER;

import java.time.LocalTime;

/**
 * Represents the time of an appointment.
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should follow HH:mm";
    public static final String VALIDATION_REGEX =
            "([01][0-9]|2[0-3]):([0-5][0-9])$";

    public final String value;

    /** To facilitate time comparisons. **/
    public final LocalTime localTime;

    /**
     * Constructs a {@code Time}.a
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
        localTime = LocalTime.parse(time, INPUT_TIME_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTime() {
        return value;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Time)) {
            return false;
        }

        Time otherTime = (Time) other;
        return value.equals(otherTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
