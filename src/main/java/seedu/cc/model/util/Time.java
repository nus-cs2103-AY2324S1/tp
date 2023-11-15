package seedu.cc.model.util;

import static seedu.cc.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class for time.
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS = "Times should be in the format HH:MM (24-hour-format).";
    private final LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String testTime) {
        try {
            LocalTime.parse(testTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the time represented by this object is in the past relative to the current system time today.
     *
     * @return true if this time is before the current system time.
     */
    public boolean isPastTime() {
        return time.isBefore(LocalTime.now());
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
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
        return time.equals(otherTime.time);
    }
}
