package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Schedule's start time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_CONSTRAINTS =
            "StartTime should only contain a valid date and time in the format 2023-09-15T09:00:00,"
                    + " and it should not be blank";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
    private final LocalDateTime time;

    /**
     * Constructs a {@code StartTime}.
     *
     * @param time A valid start time.
     */
    public StartTime(LocalDateTime time) {
        requireNonNull(time);

        this.time = time;
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidStartTime(String test) {
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartTime)) {
            return false;
        }

        StartTime otherStartTime = (StartTime) other;
        return time.equals(otherStartTime.time);
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
