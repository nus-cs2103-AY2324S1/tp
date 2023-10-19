package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an abstract time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeString(String)}
 */
public abstract class Time {
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATETIME_OUTPUT_FORMAT = "MMM d yyyy HH:mm";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

    public final LocalDateTime value;

    /**
     * Constructs a {@code EndTime}.
     *
     * @param time A valid end time.
     */
    protected Time(LocalDateTime time) {
        requireNonNull(time);

        this.value = time;
    }

    /**
     * Returns true if a given string is a valid end time.
     */
    public static boolean isValidTimeString(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalDateTime.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDateTime getTime() {
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

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT));
    }
}
