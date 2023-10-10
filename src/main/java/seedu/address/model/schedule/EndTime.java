package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Schedule's end time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
 */
public class EndTime {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String MESSAGE_CONSTRAINTS =
            "EndTime should only contain a valid date and time in the format 2023-09-15T09:00:00, and it should not "
                    + "be blank";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

    public final LocalDateTime value;

    /**
     * Constructs a {@code EndTime}.
     *
     * @param time A valid end time.
     */
    public EndTime(LocalDateTime time) {
        requireNonNull(time);

        this.value = time;
    }

    /**
     * Returns true if a given string is a valid end time.
     */
    public static boolean isValidEndTime(String test) {
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
        if (!(other instanceof EndTime)) {
            return false;
        }

        EndTime otherEndTime = (EndTime) other;
        return value.equals(otherEndTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
